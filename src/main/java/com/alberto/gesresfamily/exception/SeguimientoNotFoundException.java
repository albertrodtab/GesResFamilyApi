package com.alberto.gesresfamily.exception;

public class SeguimientoNotFoundException extends Exception {

    private static final String DEFAULT_ERROR_MESSAGE = "Seguimiento no encontrado";

    //hago dos metodos uno para poder pasar un mensaje y otro que no pasa un mensaje por defecto.
    public SeguimientoNotFoundException(String message) {
        super(message);
    }

    public SeguimientoNotFoundException() {
        super(DEFAULT_ERROR_MESSAGE);
    }
}
