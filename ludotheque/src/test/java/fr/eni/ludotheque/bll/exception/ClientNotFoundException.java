package fr.eni.ludotheque.bll.exception;

public class ClientNotFoundException extends RuntimeException {
    public ClientNotFoundException(Long id) {
        super("Client introuvable (id=" + id + ")");
    }
}
