package com.unkq.journalApp.controller;


import com.unkq.journalApp.entity.User;
import com.unkq.journalApp.service.UserEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/users")
public class UserEntryController {

    @Autowired
    private UserEntryService userEntryService;

    @GetMapping
    public List<User> getAllUsers(){
        return userEntryService.getAllEntries();
    }

    @PostMapping
    public User saveUser(@RequestBody User user){
        return userEntryService.saveEntry(user);
    }

    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable ObjectId id){
        return userEntryService.getUserById(id);
    }

    @PutMapping("/{userName}")
    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable String userName){
        User userInDB = userEntryService.findByUserName(userName);
        if(userInDB!=null){
            userInDB.setUserName(user.getUserName());
            userInDB.setPassword(user.getPassword());
            userEntryService.saveEntry(userInDB);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
