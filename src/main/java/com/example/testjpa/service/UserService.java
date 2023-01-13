package com.example.testjpa.service;

import com.example.testjpa.model.User;

public interface UserService {

    User validateExistAndReturn(Long id);

    void save(User user);

    void delete(Long id);

}
