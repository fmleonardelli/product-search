package com.capitole.productsearch.core.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "brand_id")
    private Brand brand;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private Long priceListId;

    private Long productId;

    private Integer priority;

    private Double price;

    private String curr;

    public Price() {
    }

    public Long getId() {
        return id;
    }

    public Brand getBrand() {
        return brand;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public Long getPriceListId() {
        return priceListId;
    }

    public Long getProductId() {
        return productId;
    }

    public Integer getPriority() {
        return priority;
    }

    public Double getPrice() {
        return price;
    }

    public String getCurr() {
        return curr;
    }
}
