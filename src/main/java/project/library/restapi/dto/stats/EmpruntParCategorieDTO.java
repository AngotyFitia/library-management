package project.library.restapi.dto.stats;

/**
 * Projection Spring Data JPA pour la requête d'agrégation :
 * nombre d'emprunts par catégorie.
 *
 * Les noms de getters doivent correspondre aux alias utilisés dans le @Query JPQL.
 */
public interface EmpruntParCategorieDTO {

    String getCategorie();

    Long getNombreEmprunts();
}
