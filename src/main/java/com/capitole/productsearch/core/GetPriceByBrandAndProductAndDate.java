package com.capitole.productsearch.core;

import com.capitole.productsearch.core.model.Price;
import com.capitole.productsearch.core.exception.ProductSearchException;
import com.capitole.productsearch.core.repository.GetPricesByBrandIdAndProductIdAndDateRepository;
import io.vavr.collection.List;
import io.vavr.control.Either;
import io.vavr.control.Option;

import java.time.LocalDateTime;
import java.util.Comparator;

public class GetPriceByBrandAndProductAndDate {

    private final GetPricesByBrandIdAndProductIdAndDateRepository getPricesByBrandIdAndProductIdAndDateRepository;

    public GetPriceByBrandAndProductAndDate(final GetPricesByBrandIdAndProductIdAndDateRepository
                                   getPricesByBrandIdAndProductIdAndDateRepository) {
        this.getPricesByBrandIdAndProductIdAndDateRepository = getPricesByBrandIdAndProductIdAndDateRepository;
    }

    public Either<ProductSearchException, Option<Price>> execute(final Long brandId,
                                                                 final Long productId,
                                                                 final LocalDateTime date) {
        var prices = getPricesByBrandIdAndProductIdAndDateRepository.execute(brandId, productId, date);
        return prices.map(this::searchPriceWithHigherPriority);
    }

    private Option<Price> searchPriceWithHigherPriority(final List<Price> prices) {
        return prices
                .sorted(Comparator.comparingInt(Price::getPriority))
                .lastOption();
    }
}
