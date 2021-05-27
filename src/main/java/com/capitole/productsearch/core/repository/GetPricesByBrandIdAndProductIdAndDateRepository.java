package com.capitole.productsearch.core.repository;

import com.capitole.productsearch.core.entity.Price;

import java.time.LocalDateTime;
import java.util.List;

public interface GetPricesByBrandIdAndProductIdAndDateRepository {

    List<Price> execute(final Long brandId, final Long productId, final LocalDateTime date);
}
