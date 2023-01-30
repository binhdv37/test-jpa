package com.example.testjpa.controller;

import com.example.testjpa.service.UserResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/test")
public class TestController {

    @Autowired
    private UserResourceService userResourceService;

    @GetMapping
    public ResponseEntity<?> test(@RequestParam Long id) {
        userResourceService.test(id);
        return ResponseEntity.ok("OKAYYYYY");
    }

}
