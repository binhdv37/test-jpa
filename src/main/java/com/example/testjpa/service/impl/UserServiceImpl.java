package com.example.testjpa.service.impl;

import com.example.testjpa.model.User;
import com.example.testjpa.repo.UserRespository;
import com.example.testjpa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

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

}
