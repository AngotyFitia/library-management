package project.library.restapi.dto.stats;

/**
 * Projection Spring Data JPA pour la requête d'agrégation :
 * auteurs dont les livres sont les plus empruntés.
 *
 * Les noms de getters doivent correspondre aux alias utilisés dans le @Query JPQL.
 */
public interface TopAuteurDTO {

    String getPrenom();

    String getNom();

    Long getNombreEmprunts();
}
