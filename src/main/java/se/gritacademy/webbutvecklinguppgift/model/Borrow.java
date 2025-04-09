package se.gritacademy.webbutvecklinguppgift.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Borrows")
public class Borrow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;  // Boken som lånas eller reserveras

    @Column(name = "borrower_name", nullable = false)
    private String borrowerName;  // Namn på den som lånar boken

    @Column(name = "borrow_date", nullable = false)
    private Date borrowDate;  // Datum för när boken lånades

    @Column(name = "due_date", nullable = false)
    private Date dueDate;  // Datum för när boken ska lämnas tillbaka

    @Column(name = "reserved", nullable = false)
    private boolean reserved;  // Om boken är reserverad

    @Column(name = "returned", nullable = false)
    private boolean returned;

    public Borrow() {}

    public Borrow(long id, Book book, String borrowerName, Date borrowDate, Date dueDate, boolean reserved, boolean returned) {
        this.id = id;
        this.book = book;
        this.borrowerName = borrowerName;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
        this.reserved = reserved;
        this.returned = returned;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public void setBorrowerName(String borrowerName) {
        this.borrowerName = borrowerName;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }
}
