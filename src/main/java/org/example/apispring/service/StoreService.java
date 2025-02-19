package org.example.apispring.service;

import org.example.apispring.dto.request.ProductCreationReq;
import org.example.apispring.dto.request.StoreCreationReq;
import org.example.apispring.dto.response.StoreResponse;
import org.example.apispring.enums.Role;
import org.example.apispring.mapper.StoreMapper;
import org.example.apispring.model.Product;
import org.example.apispring.model.Store;
import org.example.apispring.model.User;
import org.example.apispring.repository.ProductRepo;
import org.example.apispring.repository.StoreRepo;
import org.example.apispring.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class StoreService {

    @Autowired
    private StoreRepo storeRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private StoreMapper storeMapper;

    @Autowired
    private ProductService productService;

    public StoreResponse createStore(StoreCreationReq storeReq) {
        //kiem tra user co ton tai
        User owner = userRepo.findById(storeReq.getOwnerId())
                .orElseThrow(() -> new RuntimeException("User not found"));


        Store store = storeMapper.toStore(storeReq);
        store.setOwner(owner);

        return storeMapper.toStoreResponse(storeRepo.save(store));
    }

    public StoreResponse addProductToStore(String storeId, ProductCreationReq product) throws IOException {
        // tao san pham
        var p = productService.createProduct(product);
        Product product1 = productRepo.findById(p.getId()).get();

        //kiem tra cua hang co ton tai khong
        Store store = storeRepo.findById(storeId)
                .orElseThrow(() -> new RuntimeException("Store not found with ID: " + storeId));
        //save store for product
        product1.setStore(store);
        productRepo.save(product1);

        //save product for store
        store.getProducts().add(product1);

        return storeMapper.toStoreResponse(storeRepo.save(store));
    }

    public StoreResponse deleteProductFromStore(String storeId, String productId) {
        Store store = storeRepo.findById(storeId).get();
        store.getProducts().remove(productRepo.findById(productId).get());
        return storeMapper.toStoreResponse(storeRepo.save(store));
    }
}
