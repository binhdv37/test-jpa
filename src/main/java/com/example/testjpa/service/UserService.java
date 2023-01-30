package com.example.testjpa.service;

import com.example.testjpa.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface UserService {

    void test(Long userId, Long id, String name);

    User validateExistAndReturn(Long id);

    void save(User user);

    void delete(Long id);

    void modify(Long id) throws JsonProcessingException;

}
