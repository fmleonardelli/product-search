package com.capitole.productsearch.app.client.jpa;

import com.capitole.productsearch.core.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PriceJpaRepository extends JpaRepository<Price, Long> {

    List<Price> findByBrandIdAndProductId(final Long brandId, final Long productId);
}
