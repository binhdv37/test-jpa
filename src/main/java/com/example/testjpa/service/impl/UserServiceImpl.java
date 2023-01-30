package com.example.testjpa.service.impl;

import com.example.testjpa.model.Test;
import com.example.testjpa.model.User;
import com.example.testjpa.repo.TestRepository;
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
    private TestRepository testRepository;

    @Autowired
    TestService testService;

    @Transactional
    @Override
    public void test(Long userId, Long id, String name) {
        User user = validateExistAndReturn(userId);
        user.setName("start name");

        testRepository.testModify(id, name);

        System.out.println(user.getName());

        User user1 = validateExistAndReturn(userId);
        System.out.println(user1.getName());

//        User user1 = validateExistAndReturn(userId);

//        System.out.println("ten dau: " + user.getName());
//        System.out.println("ten: " + user1.getName());

        return;

//        Test test = testRepository.findById(id).orElse(null);
//
//        if (test != null) {
//            test.setName("hahahaha");
//
//            testRepository.testModify(id, name);
//
//            String z = test.getName();
//
//            System.out.println(z);
//
//        }



    }

    @Override
    public User validateExistAndReturn(Long id) {
//        userRespository.preExecute();
        User user = userRespository.findById(id).orElse(null);
//        User user = userRespository.findByIdVer2(id);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
//        userRespository.postExecute();
        return user;
    }

    @Override
    public void save(User user) {
        if (user.getId() == null) {
            userRespository.save(user);
        } else {
            User current = validateExistAndReturn(user.getId());
            current.setEmail(user.getEmail());
            current.setName(user.getName());
            current.setStatus(user.getStatus());
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
