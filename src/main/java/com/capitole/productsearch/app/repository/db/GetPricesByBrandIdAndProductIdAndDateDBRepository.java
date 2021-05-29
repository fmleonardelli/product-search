package com.capitole.productsearch.app.repository.db;

import com.capitole.productsearch.app.client.jpa.PriceJpaRepository;
import com.capitole.productsearch.app.exception.DatabaseRepositoryException;
import com.capitole.productsearch.core.exception.ProductSearchException;
import com.capitole.productsearch.core.model.Price;
import com.capitole.productsearch.core.repository.GetPricesByBrandIdAndProductIdAndDateRepository;
import io.vavr.collection.List;
import io.vavr.control.Either;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class GetPricesByBrandIdAndProductIdAndDateDBRepository implements GetPricesByBrandIdAndProductIdAndDateRepository {

    private final PriceJpaRepository priceClient;

    public GetPricesByBrandIdAndProductIdAndDateDBRepository(final PriceJpaRepository priceClient) {
        this.priceClient = priceClient;
    }

    public Either<ProductSearchException, List<Price>> execute(final Long brandId,
                                                               final Long productId,
                                                               final LocalDateTime date) {
        try {
            var prices = priceClient
                    .findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(brandId, productId, date, date);
            return Either.right(prices);
        } catch (Exception ex) {
            return Either.left(new DatabaseRepositoryException(
                    String.format("GetPriceByBrandIdAndProductIdAndDateDBRepository " +
                                    "brandId: %s productId: %s date: %s",
                            brandId, productId, date),
                            ex)
            );
        }
    }
}
