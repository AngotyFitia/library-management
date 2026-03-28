package project.library.restapi.service;

import project.library.restapi.model.Borrow;
import project.library.restapi.model.Book;
import project.library.restapi.model.User;
import project.library.restapi.dto.books.BorrowDTO;
import project.library.restapi.repository.BorrowRepository;
import project.library.restapi.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDate;

@Service
public class BorrowService {
    @Autowired
    private BorrowRepository borrowRepository;
    @Autowired
    private BookRepository bookRepository;

    public Borrow borrowBook(Long bookId, User user) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        if (!book.isAvailable()) {
            throw new RuntimeException("Book already borrowed");
        }

        book.setAvailable(false);
        Borrow borrow = new Borrow();
        borrow.setBook(book);
        borrow.setUser(user);
        borrow.setBorrowDate(LocalDate.now());

        bookRepository.save(book);
        return borrowRepository.save(borrow);
    }

    public Borrow returnBook(Long borrowId) {
        Borrow borrow = borrowRepository.findById(borrowId)
                .orElseThrow(() -> new RuntimeException("Borrow record not found"));

        borrow.setReturnDate(LocalDate.now());
        Book book = borrow.getBook();
        book.setAvailable(true);

        bookRepository.save(book);
        return borrowRepository.save(borrow);
    }

    public BorrowDTO toDTO(Borrow borrow) {
        BorrowDTO dto = new BorrowDTO();
        dto.setId(borrow.getId());
        dto.setUserId(borrow.getUser().getId());
        dto.setBookId(borrow.getBook().getId());
        dto.setBorrowDate(borrow.getBorrowDate());
        dto.setReturnDate(borrow.getReturnDate());
        return dto;
    }
    
}
