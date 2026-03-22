package project.library.restapi.controller;

import project.library.restapi.model.Borrow;
import project.library.restapi.model.User;
import project.library.restapi.service.BorrowService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/borrows")
public class BorrowController {
    @Autowired
    private BorrowService service;

    @PostMapping("/{bookId}")
    public Borrow borrowBook(@PathVariable Long bookId) {
        User user = new User();
        user.setId(1L);
        return service.borrowBook(bookId, user);
    }

    @PutMapping("/{id}/return")
    public Borrow returnBook(@PathVariable Long id) {
        return service.returnBook(id);
    }
}
