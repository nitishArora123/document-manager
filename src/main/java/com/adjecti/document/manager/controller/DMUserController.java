package com.adjecti.document.manager.controller;
import com.adjecti.document.manager.model.DMUser;
import com.adjecti.document.manager.service.DMUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("api/v1/user")
public class DMUserController {


    @Autowired
    private  DMUserService userService;

  
    @PostMapping
    public ResponseEntity<DMUser> createUser(@RequestBody DMUser user) {
        DMUser createdUser = userService.create(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DMUser> getUserById(@PathVariable long id) {
        Optional<DMUser> userOptional = userService.getById(id);
        return userOptional.map(user -> ResponseEntity.ok().body(user))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<DMUser>> getAllUsers() {
        List<DMUser> users = userService.getAll();
        return ResponseEntity.ok().body(users);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DMUser> updateUser(@PathVariable long id, @RequestBody DMUser user) {
        DMUser updatedUser = userService.update(user, id);
        return ResponseEntity.ok().body(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
