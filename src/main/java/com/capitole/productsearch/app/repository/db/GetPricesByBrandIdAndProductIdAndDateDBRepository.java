package com.capitole.productsearch.app.repository.db;

import com.capitole.productsearch.app.client.jpa.PriceJpaRepository;
import com.capitole.productsearch.app.exception.DatabaseRepositoryException;
import com.capitole.productsearch.core.entity.Price;
import com.capitole.productsearch.core.repository.GetPricesByBrandIdAndProductIdAndDateRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class GetPricesByBrandIdAndProductIdAndDateDBRepository implements GetPricesByBrandIdAndProductIdAndDateRepository {

    private final PriceJpaRepository priceClient;

    public GetPricesByBrandIdAndProductIdAndDateDBRepository(final PriceJpaRepository priceClient) {
        this.priceClient = priceClient;
    }

    public List<Price> execute(final Long brandId,
                               final Long productId,
                               final LocalDateTime date) {
        try {
            return priceClient.findByBrandIdAndProductId(brandId, productId);
        } catch (Exception ex) {
            throw new DatabaseRepositoryException("GetPriceByBrandIdAndProductIdAndDateDBRepository", ex);
        }
    }
}
