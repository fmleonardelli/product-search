package com.capitole.productsearch.app.entrypoint;

import com.capitole.productsearch.app.entrypoint.response.ErrorMessage;
import com.capitole.productsearch.app.exception.DatabaseRepositoryException;
import com.capitole.productsearch.app.exception.ProductPriceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;

@ControllerAdvice
public class ErrorHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorHandler.class);

    @ExceptionHandler(ProductPriceNotFoundException.class)
    public ResponseEntity<ErrorMessage> productPriceNotFoundHandler(final ProductPriceNotFoundException ex) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(DatabaseRepositoryException.class)
    public ResponseEntity<ErrorMessage> databaseRepositoryHandler(final DatabaseRepositoryException ex) {
        LOGGER.error(ex.getMessage(), ex.getCause());
        return new ResponseEntity<>(new ErrorMessage(LocalDateTime.now(),
                ex.getMessage(),
                "Resource Error"),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorMessage> formatHandler(final MethodArgumentTypeMismatchException ex) {
        LOGGER.error(ex.getMessage(), ex);
        return new ResponseEntity<>(new ErrorMessage(LocalDateTime.now(),
                ex.getMessage(),
                "Format Error"),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> generalHandler(final Exception ex) {
        LOGGER.error(ex.getMessage(), ex);
        return new ResponseEntity<>(new ErrorMessage(LocalDateTime.now(),
                ex.getMessage(),
                "Unknow Error"),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
