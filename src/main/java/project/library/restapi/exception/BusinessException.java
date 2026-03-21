package project.library.restapi.exception;

/**
 * Levée quand une règle métier est violée (→ HTTP 400).
 * Ex. : email déjà utilisé, livre indisponible, emprunt déjà rendu, etc.
 */
public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }
}
