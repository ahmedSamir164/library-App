package com.ahmed.challenge2.controller;

import com.ahmed.challenge2.entities.Book;
import com.ahmed.challenge2.service.BookService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    BookService bookService;

    @GetMapping("")
    public ResponseEntity<List<Book>> getAllBooks(){
        List<Book> booksList = bookService.getAllBooks();
        return new ResponseEntity<>(booksList , HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable int id){
        Book book =bookService.getBookById(id);
        return new ResponseEntity<>(book , HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> addBook (@Valid @RequestBody Book book , BindingResult result){
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body("Validation failed: " + result.getAllErrors());
        }
        bookService.addBook(book);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable int id,@Valid @RequestBody Book book , BindingResult result){
        if (result.hasErrors()){
            return new ResponseEntity<>(book , HttpStatus.BAD_REQUEST);
        };
        Book bookUpdated =bookService.updateBookById(id , book);
        return new ResponseEntity<>(bookUpdated , HttpStatus.OK) ;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable int id){
        bookService.deleteById(id);
        return ResponseEntity.noContent().build();
    }



}


