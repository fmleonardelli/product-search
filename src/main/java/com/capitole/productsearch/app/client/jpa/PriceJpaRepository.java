package com.capitole.productsearch.app.client.jpa;

import com.capitole.productsearch.core.model.Price;
import io.vavr.collection.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface PriceJpaRepository extends JpaRepository<Price, Long> {

    List<Price> findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(final Long brandId,
                                                                                             final Long productId,
                                                                                             final LocalDateTime startDate,
                                                                                             final LocalDateTime endDate);
}
