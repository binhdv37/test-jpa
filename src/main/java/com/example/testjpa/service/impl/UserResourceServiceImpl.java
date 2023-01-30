package com.example.testjpa.service.impl;

import com.example.testjpa.controller.UserResource;
import com.example.testjpa.repo.UserResourceRepository;
import com.example.testjpa.service.UserResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserResourceServiceImpl implements UserResourceService {

    @Autowired
    private UserResourceRepository userResourceRepository;

    @Override
    public void test(Long id) {
        UserResource userResource = userResourceRepository.findById(id).orElse(null);

        Long currentId = userResource.getId();
        String currentName = userResource.getName();

        userResourceRepository.testDeleteNative(id);

        if (userResource != null) {
            UserResource userResource1 = new UserResource();
            userResource1.setId(currentId);
            userResource1.setName(currentName);
            userResourceRepository.save(userResource1); //  does not save to db - weird af
        }
    }
}
