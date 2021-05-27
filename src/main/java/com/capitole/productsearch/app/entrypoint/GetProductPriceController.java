package com.capitole.productsearch.app.entrypoint;

import com.capitole.productsearch.core.GetProductPrice;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class GetProductPriceController {

    private final GetProductPrice getProductPrice;

    public GetProductPriceController(final GetProductPrice getProductPrice) {
        this.getProductPrice = getProductPrice;
    }

    @GetMapping("/brands/{brandId}/products/{productId}")
    public void getProductPriceByBrandIdAndProductIdAndDate(final @PathVariable("brandId") Long brandId,
                                                            final @PathVariable("productId") Long productId,
                                                            final @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {
        getProductPrice.execute(brandId, productId, date);
    }
}
