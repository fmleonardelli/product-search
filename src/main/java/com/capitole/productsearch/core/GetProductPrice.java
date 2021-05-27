package com.capitole.productsearch.core;

import com.capitole.productsearch.core.repository.GetPricesByBrandIdAndProductIdAndDateRepository;

import java.time.LocalDateTime;

public class GetProductPrice {

    private final GetPricesByBrandIdAndProductIdAndDateRepository getPricesByBrandIdAndProductIdAndDateRepository;

    public GetProductPrice(final GetPricesByBrandIdAndProductIdAndDateRepository
                                   getPricesByBrandIdAndProductIdAndDateRepository) {
        this.getPricesByBrandIdAndProductIdAndDateRepository = getPricesByBrandIdAndProductIdAndDateRepository;
    }

    public void execute(final Long brandId,
                        final Long productId,
                        final LocalDateTime date) {
        var prices = getPricesByBrandIdAndProductIdAndDateRepository.execute(brandId, productId, date);
    }
}
