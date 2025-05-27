package com.unkq.journalApp.service;

import com.unkq.journalApp.entity.JournalEntity;
import com.unkq.journalApp.repository.JournalEntryRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Component
@Slf4j
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    public void saveEntry(JournalEntity journalEntity){
        try{
            journalEntity.setDate(LocalDateTime.now());
            journalEntryRepository.save(journalEntity);
        } catch (Exception e){
            log.error("Exception", e);
        }

    }

    public List<JournalEntity> getAllEntries(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntity> getJournalEntityById(ObjectId objectId){
        return journalEntryRepository.findById(objectId);
    }

    public boolean deleteById(ObjectId objectId){
        journalEntryRepository.deleteById(objectId);
        return true;
    }
}
