package com.ahmed.challenge2.service;

import com.ahmed.challenge2.entities.Patron;
import com.ahmed.challenge2.repository.PatronRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatronService {
    @Autowired
    PatronRepository patronRepository;

    public List<Patron> getAllPatrons() {
        return patronRepository.findAll();
    }

    public Patron getPatronById(int id) {
        return patronRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Patron not found with id: " + id));
    }

    public Patron addPatron(Patron patron) {
        return patronRepository.save(patron);
    }

    public Patron updatePatron(int id, Patron patron) {
        Patron oldPatron = patronRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Patron not found with id: " + id));
        oldPatron.setName(patron.getName());
        oldPatron.setEmail(patron.getEmail());
        oldPatron.setMobile(patron.getMobile());
        return patronRepository.save(oldPatron);
    }

    public void deletePatronById(int id){
        patronRepository.deleteById(id);
    }
}
