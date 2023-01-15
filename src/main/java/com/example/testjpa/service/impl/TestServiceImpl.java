package com.example.testjpa.service.impl;

import com.example.testjpa.model.User;
import com.example.testjpa.repo.UserRespository;
import com.example.testjpa.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private UserRespository userRespository;

    @Transactional
    @Override
    public void test(Long id) {
        System.out.println("Enter test: " + TransactionSynchronizationManager.isActualTransactionActive());
        System.out.println("Transaction name: " + TransactionSynchronizationManager.getCurrentTransactionName());

        User user = new User();
        user.setStatus(id.intValue());
        userRespository.save(user);

        if (id > 2) {
            throw new RuntimeException("AAA zzz");
        }

    }
}
