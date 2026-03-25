package project.library.restapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.library.restapi.dto.stats.LoanByCategoryDTO;
import project.library.restapi.dto.stats.TopAuthorDTO;
import project.library.restapi.model.Borrow;

import java.util.List;

public interface BorrowRepository extends JpaRepository<Borrow, Long> {

    Page<Borrow> findByUserId(Long userId, Pageable pageable);

    @Query("SELECT COUNT(b) FROM Borrow b WHERE b.user.id = :userId")
    long countByUserId(@Param("userId") Long userId);

    @Query("""
            SELECT c.name AS category, COUNT(b) AS loanCount
            FROM Borrow b
            JOIN b.book bk
            JOIN bk.categories c
            GROUP BY c.id, c.name
            ORDER BY COUNT(b) DESC
            """)
    List<LoanByCategoryDTO> countLoansByCategory();

    @Query("""
            SELECT a.firstName AS firstName, a.lastName AS lastName, COUNT(b) AS loanCount
            FROM Borrow b
            JOIN b.book bk
            JOIN bk.author a
            GROUP BY a.id, a.lastName, a.firstName
            ORDER BY COUNT(b) DESC
            """)
    List<TopAuthorDTO> findTopAuthors(Pageable pageable);
}
