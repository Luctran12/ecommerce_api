package org.example.apispring.service;

import org.example.apispring.dto.request.StoreCreationReq;
import org.example.apispring.dto.response.StoreResponse;
import org.example.apispring.enums.Role;
import org.example.apispring.mapper.StoreMapper;
import org.example.apispring.model.Store;
import org.example.apispring.model.User;
import org.example.apispring.repository.StoreRepo;
import org.example.apispring.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreService {

    @Autowired
    private StoreRepo storeRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private StoreMapper storeMapper;

    public StoreResponse createStore(StoreCreationReq storeReq) {
        //kiem tra user co ton tai
        User owner = userRepo.findById(storeReq.getOwnerId())
                .orElseThrow(() -> new RuntimeException("User not found"));


        Store store = storeMapper.toStore(storeReq);
        store.setOwner(owner);

        return storeMapper.toStoreResponse(storeRepo.save(store));
    }
}
