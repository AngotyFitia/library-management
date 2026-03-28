package project.library.restapi.dto.books;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.util.List;

@Data
public class CategoryDTO {
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    private List<String> bookTitles;

    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public List<String> getBookTitles() {
        return bookTitles;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setDescription(String description) {
        this.description = description;
    }  
    public void setBookTitles(List<String> bookTitles) {
        this.bookTitles = bookTitles;
    }  
}