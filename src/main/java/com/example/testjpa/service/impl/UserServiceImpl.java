package com.example.testjpa.service.impl;

import com.example.testjpa.model.User;
import com.example.testjpa.repo.UserRespository;
import com.example.testjpa.service.TestService;
import com.example.testjpa.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final ObjectMapper objectMapper = new ObjectMapper().configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

    @Autowired
    private UserRespository userRespository;

    @Autowired
    TestService testService;

    @Override
    public User validateExistAndReturn(Long id) {
        User user = userRespository.findById(id).orElse(null);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        return user;
    }

    @Override
    public void save(User user) {
        User user1 = new User();
        user1.setId(1L);
        user1.setEmail("aaa");
        user1.setName("asd");
        userRespository.save(user1);

//        if (user.getId() == null) {
//            userRespository.save(user);
//        } else {
//            User current = validateExistAndReturn(user.getId());
//            current.setEmail(user.getEmail());
//            current.setName(user.getName());
//            current.setStatus(user.getStatus());
//            userRespository.save(current);
//        }
    }

    @Override
    public void delete(Long id) {
        userRespository.deleteById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void modify(Long id) throws JsonProcessingException {

        System.out.println("Enter modify: " + TransactionSynchronizationManager.isActualTransactionActive());
        System.out.println("Transaction name: " + TransactionSynchronizationManager.getCurrentTransactionName());

        for (int i = 1; i < 10; i++) {
            testService.test((long)i);
        }

        System.out.println("End of transaction");


//        User user = validateExistAndReturn(id);
//
//        userRespository.deleteCustom(id);
//
//        User newUser = new User();
//        newUser.setId(id);
//        newUser.setEmail("binhdz@gmail.com");
//        newUser.setName("aasd");
//        newUser.setStatus(0);
//
//        User saved = userRespository.save(newUser);
//
//        log.info("newUserSaved: {}", objectMapper.writeValueAsString(saved));

    }

}
