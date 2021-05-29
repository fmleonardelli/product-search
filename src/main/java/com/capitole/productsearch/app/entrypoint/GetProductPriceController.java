package com.capitole.productsearch.app.entrypoint;

import com.capitole.productsearch.app.entrypoint.response.ProductPriceResponse;
import com.capitole.productsearch.app.exception.ProductPriceNotFoundException;
import com.capitole.productsearch.core.GetPriceByBrandAndProductAndDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class GetProductPriceController {

    private final GetPriceByBrandAndProductAndDate getPriceByBrandAndProductAndDate;

    public GetProductPriceController(final GetPriceByBrandAndProductAndDate getPriceByBrandAndProductAndDate) {
        this.getPriceByBrandAndProductAndDate = getPriceByBrandAndProductAndDate;
    }

    @GetMapping("/brands/{brandId}/products/{productId}")
    public ProductPriceResponse getProductPriceByBrandIdAndProductIdAndDate(final @PathVariable("brandId") Long brandId,
                                                                            final @PathVariable("productId") Long productId,
                                                                            final @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {
        var price = getPriceByBrandAndProductAndDate.execute(brandId, productId, date);
        var priceFound = price
                .flatMap(p -> p.toEither(() -> new ProductPriceNotFoundException(
                        String.format("Product price not found brandId: %s productId: %s date: %s",
                                brandId, productId, date))));
        return priceFound
                .map(ProductPriceResponse::build)
                .getOrElseThrow(priceFound::getLeft);
    }
}
