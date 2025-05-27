package com.unkq.journalApp.service;

import com.unkq.journalApp.entity.User;
import com.unkq.journalApp.repository.UserEntryRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Component
@Slf4j
public class UserEntryService {

    @Autowired
    private UserEntryRepository userEntryRepository;

    public User saveEntry(User user){
        userEntryRepository.save(user);
        return user;
    }

    public User findByUserName(String username){
        return userEntryRepository.findByUserName(username);
    }

    public List<User> getAllEntries(){
        return userEntryRepository.findAll();
    }

    public Optional<User> getUserById(ObjectId objectId){
        return userEntryRepository.findById(objectId);
    }
}
