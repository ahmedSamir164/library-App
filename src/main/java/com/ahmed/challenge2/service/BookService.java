package com.ahmed.challenge2.service;

import com.ahmed.challenge2.entities.Book;
import com.ahmed.challenge2.repository.BookRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(int id) {
       return bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));
    }

    public void addBook(Book book) {
        bookRepository.save(book);
    }

    public Book updateBookById(int id ,Book book) {
        Book oldBook = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));
        oldBook.setTitle(book.getTitle());
        oldBook.setAuthor(book.getAuthor());
        oldBook.setPublication_year(book.getPublication_year());
        oldBook.setIsbn(book.getIsbn());
        oldBook.setAvailable(book.isAvailable());

        return bookRepository.save(oldBook);

    }
    public void deleteById (int id){
        bookRepository.deleteById(id);
    }
}
