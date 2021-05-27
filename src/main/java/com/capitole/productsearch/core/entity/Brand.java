package com.capitole.productsearch.core.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Brand {

    @Id
    private Long id;

    private String name;

    public Brand() {
    }

    public Brand(final Long id,
                 final String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
