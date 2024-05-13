package com.ahmed.challenge2.service;

import com.ahmed.challenge2.entities.Book;
import com.ahmed.challenge2.entities.BorrowingRecord;
import com.ahmed.challenge2.entities.Patron;
import com.ahmed.challenge2.repository.BookRepository;
import com.ahmed.challenge2.repository.BorrowingRecordRepository;
import com.ahmed.challenge2.repository.PatronRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BorrowingRecordService {
    @Autowired
    BorrowingRecordRepository borrowingRecordRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    PatronRepository patronRepository;

    public BorrowingRecord borrowBook(int bookId, int patronId) {
        Patron patron = patronRepository.findById(patronId)
                .orElseThrow(() -> new IllegalArgumentException("Patron not found with id: " + patronId));

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Book not found with id: " + bookId));

        if (!book.isAvailable()) {
            throw new IllegalStateException("Book is not available for borrowing");
        }

        book.setAvailable(false);

        BorrowingRecord borrowRecord = new BorrowingRecord();
        borrowRecord.setPatron(patron);
        borrowRecord.setBook(book);
        borrowRecord.setBorrowing_date(LocalDate.now());

        return borrowingRecordRepository.save(borrowRecord);
    }

    public void returnBook(int bookId, int patronId) {

        BorrowingRecord borrowRecord = borrowingRecordRepository.findByBookIdAndPatronId(bookId, patronId)
                .orElseThrow(() -> new IllegalArgumentException("Borrow record not found for bookId: " + bookId + " and patronId: " + patronId));

        Book book = borrowRecord.getBook();
        book.setAvailable(true);

        borrowRecord.setReturning_date(LocalDate.now());

        borrowingRecordRepository.save(borrowRecord);
    }
}
