package fr.eni.ludotheque.bll.exception;

public class GenreNotFoundException extends RuntimeException {
    public GenreNotFoundException(Long id) {
        super("Genre introuvable (id=" + id + ")");
    }
}
