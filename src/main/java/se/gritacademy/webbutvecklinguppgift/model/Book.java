package se.gritacademy.webbutvecklinguppgift.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private int id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "description")
    private String description;

    @Column(name = "published_year")
    private Integer publishedYear;


    @Column(name = "media_type", nullable = false)
    private String mediaType;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private BookType type;

    @Column(name = "year")
    private Integer year;

    @Column(name = "available")
    private Boolean available = true;

    @Column(name = "reserved")
    private Boolean reserved = false;

    @ManyToOne
    @JoinColumn(name = "borrower_id")
    private User borrower;

    public Book() {}

    // Fullständig konstruktor med 9 parametrar
    public Book(String title, String author, String description, Integer publishedYear, String mediaType,
                BookType type, Integer year, Boolean available, Boolean reserved) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.publishedYear = publishedYear;
        this.mediaType = mediaType;
        this.type = type;
        this.year = year;
        this.available = available;
        this.reserved = reserved;
    }

    // Överlagrad konstruktor med 6 parametrar, med default-värden för de övriga fälten
    public Book(String title, String author, BookType type, int year, Boolean available, Boolean reserved) {
        this(title, author, null, null, null, type, year, available, reserved);
    }

    // Getters och setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Integer getPublishedYear() {
        return publishedYear;
    }
    public void setPublishedYear(Integer publishedYear) {
        this.publishedYear = publishedYear;
    }
    public String getMediaType() {
        return mediaType;
    }
    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }
    public BookType getType() {
        return type;
    }
    public void setType(BookType type) {
        this.type = type;
    }
    public Integer getYear() {
        return year;
    }
    public void setYear(Integer year) {
        this.year = year;
    }
    public Boolean getAvailable() {
        return available;
    }
    public void setAvailable(Boolean available) {
        this.available = available;
    }
    public Boolean getReserved() {
        return reserved;
    }
    public void setReserved(Boolean reserved) {
        this.reserved = reserved;
    }
    public User getBorrower() {
        return borrower;
    }
    public void setBorrower(User borrower) {
        this.borrower = borrower;
    }

    // Metod som returnerar ett primitivt boolean-värde
    public boolean isAvailable() {
        return available != null ? available : false;
    }
}
