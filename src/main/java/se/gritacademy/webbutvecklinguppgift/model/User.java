package se.gritacademy.webbutvecklinguppgift.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id") // Observera att primärnyckeln i databasen heter user_id
    private int id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name ="created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @Column(name = "changed")
    @Temporal(TemporalType.TIMESTAMP)
    private Date changed;

    @Column(name = "lastlogin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLogin;

    @Column(name = "role")
    private String role;

    @OneToMany(mappedBy = "borrower", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Book> borrowedBooks = new ArrayList<>();

    public User() {
        created = new Date();
        changed = new Date();
        role = "user";
    }

    public User(String username, String password, String email, String role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.created = new Date();
        this.changed = new Date();
    }

    // Getters och setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getChanged() {
        return changed;
    }

    public void setChanged(Date changed) {
        this.changed = changed;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(List<Book> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }

    @Override
    public String toString() {
        return "User ["
                + "id=" + id
                + ", username=" + username
                + ", password=" + password
                + ", email=" + email
                + ", created=" + created
                + ", changed=" + changed
                + ", lastLogin=" + lastLogin
                + ", role=" + role + "]";
    }
}
