package com.capitole.productsearch.core.exception;

public abstract class ProductSearchException extends RuntimeException {

    public ProductSearchException(final String message) {
        super(message);
    }

    public ProductSearchException(final String message,
                                  final Throwable cause) {
        super(message, cause);
    }
}
