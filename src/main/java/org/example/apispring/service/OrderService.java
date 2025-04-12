package org.example.apispring.service;

import org.bson.types.ObjectId;
import org.example.apispring.dto.request.OrderCreationReq;
import org.example.apispring.dto.request.OrderItemCreationReq;
import org.example.apispring.dto.response.OrderItemResponse;
import org.example.apispring.enums.OrderStatus;
import org.example.apispring.mapper.OrderItemMapper;
import org.example.apispring.mapper.OrderMapper;
import org.example.apispring.model.Order;
import org.example.apispring.model.OrderItem;
import org.example.apispring.model.Product;
import org.example.apispring.model.User;
import org.example.apispring.repository.OrderItemRepo;
import org.example.apispring.repository.OrderRepo;
import org.example.apispring.repository.ProductRepo;
import org.example.apispring.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderNotificationService notificationService;

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderItemRepo orderItemRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private OrderItemMapper orderItemMapper;

    public List<Order> findAll() {
        return orderRepo.findAll();
    }

    public Order save(OrderCreationReq req) {
        Order order = orderMapper.toOrder(req);
        List<OrderItem> orderItems = new ArrayList<>();
        User user = userRepo.findById(req.getUserId()).get();
        Set<String> notifiedStoreIds = new HashSet<>();

        for(OrderItemCreationReq item : req.getItems()) {
            OrderItem orderItem = new OrderItem();
            Product product = productRepo.findById(item.getProductId()).get();
            product.setStock(product.getStock() - item.getQuantity());
            orderItem.setProduct(product);
            orderItem.setQuantity(item.getQuantity());
            orderItem.setPrice(item.getPrice());
            orderItem.setStatus(OrderStatus.Pending);
            orderItems.add(orderItem);
            orderItemRepo.save(orderItem);

            // 🔔 Lưu storeId và gửi thông báo nếu chưa gửi
            String storeId = product.getStore().getId();
            if (!notifiedStoreIds.contains(storeId)) {
                notificationService.notifyStoreOwner(storeId, "Bạn có đơn hàng mới từ " + user.getName() + "!");
                notifiedStoreIds.add(storeId);
            }
        }
        order.setUser(user);
        order.setItems(orderItems);
        order.setStatus(OrderStatus.Processing);
        order.setOrderDate(new Date());

        return orderRepo.save(order);
    }

    public List<Order> findByUserId(String userId) {
        return orderRepo.findByUser_Id(userId);
    }

    public List<OrderItemResponse> findByStoreId(String storeId) {
        List<Product> products = productRepo.findByStore_Id(storeId);
        System.out.println(products);
        List<String> productIds = products.stream()
                .map(Product::getId)
                .collect(Collectors.toList());
        System.out.println(productIds);
        List<OrderItem> items = orderItemRepo.findByProductIn(productIds);
        System.out.println(items);
        List<OrderItemResponse> orderItemResponses = new ArrayList<>();
        for(OrderItem order : items) {
            OrderItemResponse orderItemResponse = orderItemMapper.toOrderItemResponse(order);
            orderItemResponses.add(orderItemResponse);
        }
        return orderItemResponses;
    }

    public OrderItemResponse changeItemStatus(String orderItemId, String status) {
        OrderItem orderItem = orderItemRepo.findById(orderItemId).get();
        orderItem.setStatus(OrderStatus.valueOf(status));
        return orderItemMapper.toOrderItemResponse(orderItemRepo.save(orderItem));
    }

    public Order updateOrderStatus(String orderId, String status) {
        Order order = orderRepo.findById(orderId).get();
        order.setStatus(OrderStatus.valueOf(status));
        return orderRepo.save(order);
    }
}
