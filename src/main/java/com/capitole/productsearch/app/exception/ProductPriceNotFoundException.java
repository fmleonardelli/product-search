package com.capitole.productsearch.app.exception;

import com.capitole.productsearch.core.exception.ProductSearchException;

public class ProductPriceNotFoundException extends ProductSearchException {

    public ProductPriceNotFoundException(String message) {
        super(message);
    }
}
