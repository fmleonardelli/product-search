package com.capitole.productsearch.unit.repository.db;

import com.capitole.productsearch.ApplicationTests;
import com.capitole.productsearch.app.client.jpa.PriceJpaRepository;
import com.capitole.productsearch.app.exception.DatabaseRepositoryException;
import com.capitole.productsearch.app.repository.db.GetPricesByBrandIdAndProductIdAndDateDBRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class GetPricesByBrandIdAndProductIdAndDateDBRepositoryTest extends ApplicationTests {

    @Mock
    private PriceJpaRepository priceClient;

    private GetPricesByBrandIdAndProductIdAndDateDBRepository getPricesByBrandIdAndProductIdAndDateDBRepositoryWithMock = new GetPricesByBrandIdAndProductIdAndDateDBRepository(priceClient);

    @Autowired
    private GetPricesByBrandIdAndProductIdAndDateDBRepository getPricesByBrandIdAndProductIdAndDateDBRepository;

    @Test
    public void test_when_repository_multiple_results() {
        var brandId = 1L;
        var productId = 35455L;

        var result = getPricesByBrandIdAndProductIdAndDateDBRepository.execute(brandId, productId, LocalDateTime.of(2020, 6, 14, 16, 0));

        assert result.isRight();
        assert result.map(r -> r.size() == 2).getOrElse(false);
        assert result.map(r ->
                r.forAll(p -> p.getBrandId() == brandId &&
                        p.getProductId() == productId &&
                        p.getId() > 0L &&
                        "EUR".equalsIgnoreCase(p.getCurr())))
                .getOrElse(false);
    }

    @Test
    public void test_when_client_throw_exception() {
        var randomLong = UUID.randomUUID().getLeastSignificantBits();

        when(priceClient.findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(any(), any(), any(), any()))
                .thenThrow(new RuntimeException("This was a jpa exception"));

        var result = getPricesByBrandIdAndProductIdAndDateDBRepositoryWithMock.execute(randomLong, randomLong, LocalDateTime.now());
        assert result.isLeft();
        assertThrows(DatabaseRepositoryException.class, () -> {
            throw result.getLeft();
        });
    }
}
