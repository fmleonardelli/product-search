package com.capitole.productsearch.app.entrypoint.response;

import com.capitole.productsearch.core.model.Price;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class ProductPriceResponse {

    private Long productId;
    private Long brandId;
    private Long priceListId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Double price;

    public ProductPriceResponse(@JsonProperty("product_id") final Long productId,
                                @JsonProperty("brand_id") final Long brandId,
                                @JsonProperty("price_list_id") final Long priceListId,
                                @JsonProperty("start_date") final LocalDateTime startDate,
                                @JsonProperty("end_date") final LocalDateTime endDate,
                                @JsonProperty("price") final Double price) {
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

    public Boolean compare(final ProductPriceResponse other) {
        return productId.equals(other.productId) &&
                brandId.equals(other.brandId) &&
                priceListId.equals(other.priceListId) &&
                startDate.equals(other.startDate) &&
                endDate.equals(other.endDate) &&
                price.equals(other.price);
    }
}
