package project.library.restapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Book{
    
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String isbn;
    private LocalDate publicationDate;

    @ManyToOne
    private Author author;

    @ManyToMany
    private List<Category> categories = new ArrayList<>();

    private boolean available = true;


    public Book() {}
    public Long getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getIsbn() {
        return isbn;
    }
    public LocalDate getPublicationDate() {
        return publicationDate;
    }
    public boolean isAvailable() {
        return available;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    public void setPublicationDate(LocalDate date) {
        this.publicationDate = date;
    }
    public void setAvailability(boolean available) {
        this.available = available;
    }
}
