package com.ahmed.challenge2.repository;

import com.ahmed.challenge2.entities.BorrowingRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord, Integer> {
    public Optional<BorrowingRecord> findByBookIdAndPatronId(int bookId , int patronId);
}
