package com.example.testjpa.controller;

import com.example.testjpa.model.User;
import com.example.testjpa.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserControlller {

    @Autowired
    private UserService userService;

    @GetMapping("/test")
    public ResponseEntity<String> test(
            @RequestParam(name = "userId") Long userId,
            @RequestParam(name = "id") Long id,
            @RequestParam(name = "name") String name
    ) {
        userService.test(userId, id, name);
        return ResponseEntity.ok("OKEEE");
    }

    @GetMapping
    public ResponseEntity<User> getById(@RequestParam(name = "id") Long id) {
        return ResponseEntity.ok(userService.validateExistAndReturn(id));
    }

    @PostMapping
    public ResponseEntity<String> save(@RequestBody User user) {
        userService.save(user);
        return ResponseEntity.ok("SUCCESS");
    }

    @DeleteMapping
    public ResponseEntity<String> delete(@RequestParam Long id) {
        userService.delete(id);
        return ResponseEntity.ok("SUCCESS");
    }

    @GetMapping("/modify")
    public ResponseEntity<String> modify(@RequestParam(name = "id") Long id) throws JsonProcessingException {
        userService.modify(id);
        return ResponseEntity.ok("SUCCESS");
    }

}
