package project.library.restapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.library.restapi.dto.stats.EmpruntParCategorieDTO;
import project.library.restapi.dto.stats.TopAuteurDTO;
import project.library.restapi.model.Emprunt;
import project.library.restapi.model.EmpruntStatut;

import java.util.List;
import java.util.Optional;

public interface EmpruntRepository extends JpaRepository<Emprunt, Long> {

    Page<Emprunt> findByUtilisateurId(Long utilisateurId, Pageable pageable);

    Page<Emprunt> findByStatut(EmpruntStatut statut, Pageable pageable);

    Optional<Emprunt> findByLivreIdAndStatut(Long livreId, EmpruntStatut statut);

    boolean existsByLivreIdAndStatut(Long livreId, EmpruntStatut statut);

    // ===== Requêtes d'agrégation pour les statistiques =====

    /**
     * Retourne le nombre d'emprunts groupé par catégorie, trié par volume décroissant.
     * Utilise une projection d'interface pour mapper le résultat.
     */
    @Query("""
            SELECT c.nom AS categorie, COUNT(e) AS nombreEmprunts
            FROM Emprunt e
            JOIN e.livre l
            JOIN l.categories c
            GROUP BY c.id, c.nom
            ORDER BY COUNT(e) DESC
            """)
    List<EmpruntParCategorieDTO> countEmpruntsParCategorie();

    /**
     * Retourne les auteurs dont les livres sont les plus empruntés.
     * Pageable permet de limiter les résultats (ex. top 10).
     */
    @Query("""
            SELECT a.prenom AS prenom, a.nom AS nom, COUNT(e) AS nombreEmprunts
            FROM Emprunt e
            JOIN e.livre l
            JOIN l.auteur a
            GROUP BY a.id, a.nom, a.prenom
            ORDER BY COUNT(e) DESC
            """)
    List<TopAuteurDTO> findTopAuteurs(Pageable pageable);

    /**
     * Nombre total d'emprunts d'un utilisateur donné.
     */
    @Query("SELECT COUNT(e) FROM Emprunt e WHERE e.utilisateur.id = :userId")
    long countByUtilisateurId(@Param("userId") Long userId);
}
