package project.library.restapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.library.restapi.model.Livre;

import java.time.LocalDate;
import java.util.Optional;

/**
 * JpaSpecificationExecutor permet de construire des filtres dynamiques (filtre par auteur,
 * catégorie, date de publication) sans multiplier les méthodes de requête.
 */
public interface LivreRepository extends JpaRepository<Livre, Long>, JpaSpecificationExecutor<Livre> {

    Optional<Livre> findByIsbn(String isbn);

    boolean existsByIsbn(String isbn);

    Page<Livre> findByDisponible(boolean disponible, Pageable pageable);

    @Query("SELECT l FROM Livre l WHERE l.auteur.id = :auteurId")
    Page<Livre> findByAuteurId(@Param("auteurId") Long auteurId, Pageable pageable);

    @Query("SELECT l FROM Livre l JOIN l.categories c WHERE c.id = :categorieId")
    Page<Livre> findByCategorieId(@Param("categorieId") Long categorieId, Pageable pageable);

    @Query("SELECT l FROM Livre l WHERE l.datePublication BETWEEN :debut AND :fin")
    Page<Livre> findByDatePublicationBetween(
            @Param("debut") LocalDate debut,
            @Param("fin") LocalDate fin,
            Pageable pageable
    );
}
