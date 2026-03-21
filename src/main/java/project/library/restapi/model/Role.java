package project.library.restapi.model;

/**
 * Rôles disponibles dans l'application.
 * Spring Security préfixe automatiquement avec "ROLE_" lors de l'utilisation de hasRole().
 */
public enum Role {
    USER,
    ADMIN
}
