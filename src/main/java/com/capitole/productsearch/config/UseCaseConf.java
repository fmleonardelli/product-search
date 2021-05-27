package com.capitole.productsearch.config;

import com.capitole.productsearch.core.GetProductPrice;
import com.capitole.productsearch.core.repository.GetPricesByBrandIdAndProductIdAndDateRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConf {

    @Bean
    public GetProductPrice getProductPrice(final GetPricesByBrandIdAndProductIdAndDateRepository getPricesByBrandIdAndProductIdAndDateRepository) {
        return new GetProductPrice(getPricesByBrandIdAndProductIdAndDateRepository);
    }
}
