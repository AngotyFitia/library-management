package project.library.restapi.exception;

/**
 * Levée quand une ressource demandée n'existe pas en base (→ HTTP 404).
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String entity, Long id) {
        super(entity + " introuvable avec l'id : " + id);
    }
}
