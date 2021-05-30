package com.capitole.productsearch.config;

import com.capitole.productsearch.core.GetPriceByBrandAndProductAndDate;
import com.capitole.productsearch.core.repository.GetPricesByBrandIdAndProductIdAndDateRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConf {

    @Bean
    public GetPriceByBrandAndProductAndDate getProductPrice(final GetPricesByBrandIdAndProductIdAndDateRepository getPricesByBrandIdAndProductIdAndDateRepository) {
        return new GetPriceByBrandAndProductAndDate(getPricesByBrandIdAndProductIdAndDateRepository);
    }
}
