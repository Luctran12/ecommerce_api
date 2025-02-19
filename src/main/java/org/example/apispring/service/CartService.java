package org.example.apispring.service;

import org.example.apispring.dto.request.CartItemRequest;
import org.example.apispring.dto.response.CartResponse;
import org.example.apispring.mapper.CartMapper;
import org.example.apispring.model.Cart;
import org.example.apispring.model.CartItem;
import org.example.apispring.model.Product;
import org.example.apispring.model.User;
import org.example.apispring.repository.CartItemRepo;
import org.example.apispring.repository.CartRepo;
import org.example.apispring.repository.ProductRepo;
import org.example.apispring.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    @Autowired
    private CartRepo cartRepository;

    @Autowired
    private CartItemRepo cartItemRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private ProductRepo productRepo;

    public CartResponse getCartByUserId(String userId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseGet(() -> cartRepository.save(Cart.builder().user(userRepo.findById(userId).get()).build()));

        CartResponse response = cartMapper.toCartResponse(cart);
        response.setTotalPrice(calculateTotalPrice(userId));
        return response;
    }


    public Double calculateTotalPrice(String userId) {
        Cart cart = cartRepository.findByUserId(userId).orElseThrow(() -> new RuntimeException("cart not found"));
        List<CartItem> items = cart.getItems();
        Double totalPrice = 0.0;
        if(cart.getItems() != null && cart.getItems().size() > 0) {
            for (CartItem item : items) {
                Double itemPrice = item.getProduct().getPrice() * item.getQuantity();
                totalPrice += itemPrice;
            }
        }

        return totalPrice;
    }

//    public CartResponse addProductToCart(String userId, CartItemRequest cartItemReq) {
//        // Tìm user, ném exception nếu không tồn tại
//        User user = userRepo.findById(userId)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        // Tìm cart hoặc tạo mới
//        Cart cart = cartRepository.findByUserId(userId)
//                .orElseGet(() -> {
//                    Cart newCart = Cart.builder().user(user).items(new ArrayList<>()).build();
//                    return cartRepository.save(newCart);
//                });
//
//
//        //tim product
//        Product product = productRepo.findById(cartItemReq.getProductId())
//                .orElseThrow(() -> new RuntimeException("product not found"));
//
//        // Kiểm tra sản phẩm đã có trong cart chưa
//        Optional<CartItem> existingItem = cart.getItems().stream()
//                .filter(item -> item.getProduct().getId().equals(product.getId()))
//                .findFirst();
//
//        if (existingItem.isPresent()) {
//            // Cập nhật quantity
//            CartItem item = existingItem.get();
//            item.setQuantity(item.getQuantity() + cartItemReq.getQuantity());
//            //cartItemRepo.save(item);
//        } else {
//            // Thêm mới CartItem
//            CartItem cartItem = CartItem.builder()
//                    .product(product)
//                    .quantity(cartItemReq.getQuantity())
//                    .build();
//            cart.getItems().add(cartItem);
//        }
//
//        //cap nhat cart
//        cartRepository.save(cart);
//
//        return cartMapper.toCartResponse(cart);
//    }

    public CartResponse addProductToCart(String userId, CartItemRequest cartItemReq) {
        // Tìm user, nếu không có thì ném exception
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Tìm cart của user hoặc tạo mới
        Cart cart = cartRepository.findByUserId(userId)
                .orElseGet(() -> {
                    Cart newCart = Cart.builder()
                            .user(user)
                            .items(new ArrayList<>()) // Đảm bảo danh sách không null
                            .build();
                    return cartRepository.save(newCart);
                });

        // Đảm bảo danh sách items không null
        if (cart.getItems() == null) {
            cart.setItems(new ArrayList<>());
        }

        // Tìm sản phẩm
        Product product = productRepo.findById(cartItemReq.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Kiểm tra xem sản phẩm đã có trong giỏ hàng chưa
        Optional<CartItem> existingItem = cart.getItems().stream()
                .filter(item -> item.getProduct() != null && item.getProduct().getId().equals(product.getId()))
                .findFirst();

        if (existingItem.isPresent()) {
            // Cập nhật số lượng sản phẩm
            CartItem item = existingItem.get();
            item.setQuantity(item.getQuantity() + cartItemReq.getQuantity());
        } else {
            // Tạo mới CartItem và lưu vào database trước
            CartItem cartItem = CartItem.builder()
                    .product(product)
                    .quantity(cartItemReq.getQuantity())
                    .build();
            cartItem = cartItemRepo.save(cartItem); // Lưu CartItem trước

            cart.getItems().add(cartItem); // Thêm vào danh sách giỏ hàng
        }

        // Lưu giỏ hàng
        cartRepository.save(cart);

        return cartMapper.toCartResponse(cart);
    }


}
