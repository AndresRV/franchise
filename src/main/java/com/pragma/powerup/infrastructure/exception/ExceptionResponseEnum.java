package com.pragma.powerup.infrastructure.exception;

public enum ExceptionResponseEnum {
    PRODUCT_NOT_FOUND("Producto no encontrado"),
    BRANCH_NOT_FOUND("Sucursal no encontrada"),
    FRANCHISE_NOT_FOUND("Franquicia no encontrada");

    private final String message;

    ExceptionResponseEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
