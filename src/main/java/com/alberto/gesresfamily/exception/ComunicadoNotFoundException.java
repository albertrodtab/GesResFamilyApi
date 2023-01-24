package com.alberto.gesresfamily.exception;

public class ComunicadoNotFoundException extends Exception {

    private static final String DEFAULT_ERROR_MESSAGE = "Comunicado no encontrado";

    //hago dos metodos uno para poder pasar un mensaje y otro que no pasa un mensaje por defecto.
    public ComunicadoNotFoundException(String message) {
        super(message);
    }

    public ComunicadoNotFoundException() {
        super(DEFAULT_ERROR_MESSAGE);
    }
}
