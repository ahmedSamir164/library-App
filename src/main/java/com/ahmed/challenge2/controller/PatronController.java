package com.ahmed.challenge2.controller;

import com.ahmed.challenge2.entities.Patron;
import com.ahmed.challenge2.service.PatronService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/patrons")
public class PatronController {
    @Autowired
    PatronService patronService;

    @GetMapping("")
    public ResponseEntity<List<Patron>> getAllPatrons (){
        List<Patron> patronsList = patronService.getAllPatrons();
        return new ResponseEntity<>(patronsList , HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patron> getPatronById (@PathVariable int id){
        Patron patron = patronService.getPatronById(id);
        return new ResponseEntity<>(patron , HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Patron> addPatron (@Valid @RequestBody Patron patron , BindingResult result){
        if(result.hasErrors()){
            return new ResponseEntity<>(patron , HttpStatus.BAD_REQUEST);
        }
        Patron patronCreated = patronService.addPatron(patron);
        return new ResponseEntity<>(patronCreated , HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patron> updatePatron (@PathVariable int id ,@Valid @RequestBody Patron patron , BindingResult result){
        if(result.hasErrors()){
            return new ResponseEntity<>(patron , HttpStatus.BAD_REQUEST);
        }
        Patron patronUpdated = patronService.updatePatron(id , patron);
        return new ResponseEntity<>(patronUpdated , HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<?> deletePatronById(@PathVariable int id){
        patronService.deletePatronById(id);
        return ResponseEntity.ok().build();
    }

}
