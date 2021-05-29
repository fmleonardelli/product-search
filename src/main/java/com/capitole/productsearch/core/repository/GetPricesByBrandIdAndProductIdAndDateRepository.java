package com.capitole.productsearch.core.repository;

import com.capitole.productsearch.core.model.Price;
import com.capitole.productsearch.core.exception.ProductSearchException;
import io.vavr.collection.List;
import io.vavr.control.Either;

import java.time.LocalDateTime;

public interface GetPricesByBrandIdAndProductIdAndDateRepository {

    Either<ProductSearchException, List<Price>> execute(final Long brandId,
                                                        final Long productId,
                                                        final LocalDateTime date);
}
