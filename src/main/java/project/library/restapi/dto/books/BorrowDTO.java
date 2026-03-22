package project.library.restapi.dto.books;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDate;

@Data
public class BorrowDTO {
    private Long id;

    @NotNull
    private Long userId;

    @NotNull
    private Long bookId;

    private LocalDate borrowDate;
    private LocalDate returnDate;
}
