package project.library.restapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.util.List;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String label;
    private String description;

    @ManyToMany(mappedBy = "categories")
    private List<Book> books;

    public Category() {}
    public Long getId() {
        return id;
    }
    public String getLabel() {
        return label;
    }
    public String getDescription() {
        return description;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setLabel(String label) {
        this.label = label;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    
}