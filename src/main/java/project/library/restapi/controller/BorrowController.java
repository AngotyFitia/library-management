package project.library.restapi.controller;

import project.library.restapi.dto.books.BorrowDTO;
import project.library.restapi.model.User;
import project.library.restapi.service.BorrowService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/api/borrows")
@Tag(name="Borrows", description ="Borrows management - users only")
public class BorrowController {
    @Autowired
    private BorrowService service;

    @PostMapping("/{bookId}/{idUser}")
    public BorrowDTO borrowBook(@PathVariable Long bookId, @PathVariable Long idUser) {
        User user = new User();
        user.setId(idUser);
        return service.toDTO(service.borrowBook(bookId, user));
    }

    @PutMapping("/{id}/return")
    public BorrowDTO returnBook(@PathVariable Long id) {
        return service.toDTO(service.returnBook(id));
    }
}
