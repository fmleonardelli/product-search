package com.capitole.productsearch.app.exception;

import com.capitole.productsearch.core.exception.RepositoryException;

public class DatabaseRepositoryException extends RepositoryException {

    public DatabaseRepositoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
