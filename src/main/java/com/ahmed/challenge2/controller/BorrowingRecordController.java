package com.ahmed.challenge2.controller;

import com.ahmed.challenge2.entities.Book;
import com.ahmed.challenge2.entities.BorrowingRecord;
import com.ahmed.challenge2.service.BorrowingRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BorrowingRecordController {
    @Autowired
    BorrowingRecordService borrowingRecordService;

    @PostMapping("/api/borrow/{bookId}/patron/{patronId}")
    public ResponseEntity<BorrowingRecord> borrowBook(@PathVariable int bookId , @PathVariable int patronId){
        BorrowingRecord borrowRecord = borrowingRecordService.borrowBook(bookId , patronId);
        return new ResponseEntity<>(borrowRecord, HttpStatus.OK);
    }

    @PutMapping("/api/return/{bookId}/patron/{patronId}")
    public ResponseEntity<?> returnBook (@PathVariable int bookId , @PathVariable int patronId){
        borrowingRecordService.returnBook(bookId , patronId);
        return ResponseEntity.ok().build();
    }

}
