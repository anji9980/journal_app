package com.unkq.journalApp.controller;

import com.unkq.journalApp.entity.JournalEntity;
import com.unkq.journalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping("/getAllJournals")
    public ResponseEntity<?> getAll(){
        List<JournalEntity> allEntries = journalEntryService.getAllEntries();
        if(allEntries!=null && !allEntries.isEmpty()){
            return new ResponseEntity<>(allEntries, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<JournalEntity> setJournalEntry(@RequestBody JournalEntity journalEntity){
        try {
            journalEntryService.saveEntry(journalEntity);
            return new ResponseEntity<>(journalEntity, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/id/{myId}")
    public ResponseEntity<JournalEntity> getJournalById(@PathVariable ObjectId myId){
        Optional<JournalEntity> journalEntityById = journalEntryService.getJournalEntityById(myId);
        if(journalEntityById.isPresent()){
            return new ResponseEntity<>(journalEntityById.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/id/{id}")
    public  ResponseEntity<?> updateJournalEntry(@PathVariable ObjectId id, @RequestBody JournalEntity newEntry){
        JournalEntity old = journalEntryService.getJournalEntityById(id).orElse(null);
        if(old!=null){
            old.setTitle(newEntry.getTitle()!=null && !newEntry.getTitle().equals("") ? newEntry.getTitle(): old.getTitle());
            old.setContent(newEntry.getContent()!=null && !newEntry.getContent().equals("") ? newEntry.getContent(): old.getContent());
            journalEntryService.saveEntry(old);
            return new ResponseEntity<>(old, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/id")
    public ResponseEntity<?> deleteEntry(@RequestParam ObjectId id){
        journalEntryService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
