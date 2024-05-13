package com.ahmed.challenge2.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BorrowingRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    @NotNull
    private Book book;

    @ManyToOne
    @JoinColumn(name = "patron_id")
    @NotNull
    private Patron patron;

    private LocalDate borrowing_date;
    private LocalDate returning_date;

}
