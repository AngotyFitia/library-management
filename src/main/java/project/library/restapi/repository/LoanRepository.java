package project.library.restapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.library.restapi.dto.stats.LoanByCategoryDTO;
import project.library.restapi.dto.stats.TopAuthorDTO;
import project.library.restapi.model.Loan;
import project.library.restapi.model.LoanStatus;

import java.util.List;
import java.util.Optional;

public interface LoanRepository extends JpaRepository<Loan, Long> {

    Page<Loan> findByUserId(Long userId, Pageable pageable);

    Page<Loan> findByStatus(LoanStatus status, Pageable pageable);

    Optional<Loan> findByBookIdAndStatus(Long bookId, LoanStatus status);

    boolean existsByBookIdAndStatus(Long bookId, LoanStatus status);

    @Query("""
            SELECT c.name AS category, COUNT(l) AS loanCount
            FROM Loan l
            JOIN l.book b
            JOIN b.categories c
            GROUP BY c.id, c.name
            ORDER BY COUNT(l) DESC
            """)
    List<LoanByCategoryDTO> countLoansByCategory();

    @Query("""
            SELECT a.firstName AS firstName, a.lastName AS lastName, COUNT(l) AS loanCount
            FROM Loan l
            JOIN l.book b
            JOIN b.author a
            GROUP BY a.id, a.lastName, a.firstName
            ORDER BY COUNT(l) DESC
            """)
    List<TopAuthorDTO> findTopAuthors(Pageable pageable);

    @Query("SELECT COUNT(l) FROM Loan l WHERE l.user.id = :userId")
    long countByUserId(@Param("userId") Long userId);
}
