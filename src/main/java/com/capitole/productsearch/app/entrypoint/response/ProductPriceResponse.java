package com.capitole.productsearch.app.entrypoint.response;

import com.capitole.productsearch.core.model.Price;

import java.time.LocalDateTime;

public class ProductPriceResponse {

    private Long productId;
    private Long brandId;
    private Long priceListId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Double price;

    private ProductPriceResponse(final Long productId,
                                 final Long brandId,
                                 final Long priceListId,
                                 final LocalDateTime startDate,
                                 final LocalDateTime endDate,
                                 final Double price) {
        this.productId = productId;
        this.brandId = brandId;
        this.priceListId = priceListId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
    }

    public static ProductPriceResponse build(final Price price) {
        return new ProductPriceResponse(price.getProductId(),
                price.getBrandId(),
                price.getPriceListId(),
                price.getStartDate(),
                price.getEndDate(),
                price.getPrice());
    }

    public Long getProductId() {
        return productId;
    }

    public Long getBrandId() {
        return brandId;
    }

    public Long getPriceListId() {
        return priceListId;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public Double getPrice() {
        return price;
    }
}
