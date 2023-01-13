package com.example.testjpa.service.impl;

import com.example.testjpa.model.User;
import com.example.testjpa.repo.UserRespository;
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
        if (user.getId() == null) {
            userRespository.save(user);
        } else {
            User current = validateExistAndReturn(user.getId());
            current.setFirstName(user.getFirstName());
            current.setLastName(user.getFirstName());
            current.setFullName(user.getFirstName());
            current.setPhoneNumber(user.getPhoneNumber());
            current.setAge(user.getAge());
            current.setGender(user.getGender());
            current.setAddress(user.getAddress());
            userRespository.save(current);
        }
    }

    @Override
    public void delete(Long id) {
        userRespository.deleteById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void modify(Long id) throws JsonProcessingException {
        log.info("Is in a transaction: {}", TransactionSynchronizationManager.isActualTransactionActive());

        validateExistAndReturn(id);

        userRespository.updateStatus(id, 2);

        log.info("Is in a transaction: {}", TransactionSynchronizationManager.isActualTransactionActive());

        User user = validateExistAndReturn(id);

//        System.out.println(1/0);

        log.info("User: {}", objectMapper.writeValueAsString(user));

    }

}
