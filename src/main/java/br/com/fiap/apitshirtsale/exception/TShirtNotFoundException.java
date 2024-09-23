package br.com.fiap.apitshirtsale.exception;

public class TShirtNotFoundException extends RuntimeException {
    public TShirtNotFoundException(Long id) {
        super("TShirt not found with id: " + id);
    }
}
