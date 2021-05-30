package com.capitole.productsearch.integration.app.entrypoint;

import com.capitole.productsearch.ApplicationTests;
import com.capitole.productsearch.app.entrypoint.response.ProductPriceResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class GetProductPriceControllerTest extends ApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void test_when_date_format_is_invalid() throws Exception {
        var randomLong = UUID.randomUUID().getLeastSignificantBits();
        mockMvc.perform(
                get("/brands/{brandId}/products/{productId}", randomLong, randomLong)
                        .param("date", "2020-12-12")
        ).andExpect(status().is4xxClientError());
    }

    @Test
    public void test_when_brand_id_format_is_invalid() throws Exception {
        var randomLong = UUID.randomUUID().getLeastSignificantBits();
        mockMvc.perform(
                get("/brands/{brandId}/products/{productId}", null, randomLong)
                        .param("date", LocalDateTime.now().toString())
        ).andExpect(status().is4xxClientError());
    }

    @Test
    public void test_when_product_id_format_is_invalid() throws Exception {
        var randomLong = UUID.randomUUID().getLeastSignificantBits();
        mockMvc.perform(
                get("/brands/{brandId}/products/{productId}", randomLong, null)
                        .param("date", LocalDateTime.now().toString())
        ).andExpect(status().is4xxClientError());
    }

    @Test
    public void test_when_not_found_data_product_price() throws Exception {
        var randomLong = UUID.randomUUID().getLeastSignificantBits();
        mockMvc.perform(
                get("/brands/{brandId}/products/{productId}", randomLong, randomLong)
                .param("date", LocalDateTime.now().toString())
        ).andExpect(status().isNoContent());
    }

    @Test
    public void test_case_one_when_only_one_price_applies() throws Exception {
        var brandId = 1L;
        var productId = 35455L;
        var date = "2020-06-14T10:00:00";

        var requestResult = mockMvc.perform(
                get("/brands/{brandId}/products/{productId}", brandId, productId)
                        .param("date", date))
                .andExpect(status().isOk())
                .andReturn();

        var productPriceExpected = new ProductPriceResponse(productId,
                brandId,
                1L,
                LocalDateTime.of(2020, 6, 14, 0, 0),
                LocalDateTime.of(2020, 12, 31, 23, 59, 59), 35.5d);
        var productPriceResult = serializer.readValue(requestResult.getResponse().getContentAsString(), ProductPriceResponse.class);
        assert productPriceExpected.compare(productPriceResult);
    }

    @Test
    public void test_case_two_when_two_price_applies_in_same_dates_and_the_priority_define_the_final_result() throws Exception {
        var brandId = 1L;
        var productId = 35455L;
        var date = "2020-06-14T16:00:00";

        var requestResult = mockMvc.perform(
                get("/brands/{brandId}/products/{productId}", brandId, productId)
                        .param("date", date))
                .andExpect(status().isOk())
                .andReturn();

        var productPriceExpected = new ProductPriceResponse(productId,
                brandId,
                2L,
                LocalDateTime.of(2020, 6, 14, 15, 0),
                LocalDateTime.of(2020, 6, 14, 18, 30, 0), 25.45d);
        var productPriceResult = serializer.readValue(requestResult.getResponse().getContentAsString(), ProductPriceResponse.class);
        assert productPriceExpected.compare(productPriceResult);
    }

    @Test
    public void test_case_three_when_only_one_price_applies_because_the_end_date_is_closer() throws Exception {
        var brandId = 1L;
        var productId = 35455L;
        var date = "2020-06-14T21:00:00";

        var requestResult = mockMvc.perform(
                get("/brands/{brandId}/products/{productId}", brandId, productId)
                        .param("date", date))
                .andExpect(status().isOk())
                .andReturn();

        var productPriceExpected = new ProductPriceResponse(productId,
                brandId,
                1L,
                LocalDateTime.of(2020, 6, 14, 0, 0),
                LocalDateTime.of(2020, 12, 31, 23, 59, 59), 35.5d);
        var productPriceResult = serializer.readValue(requestResult.getResponse().getContentAsString(), ProductPriceResponse.class);
        assert productPriceExpected.compare(productPriceResult);
    }

    @Test
    public void test_case_four_when_two_price_applies_in_different_dates_and_the_priority_define_the_final_result() throws Exception {
        var brandId = 1L;
        var productId = 35455L;
        var date = "2020-06-15T10:00:00";

        var requestResult = mockMvc.perform(
                get("/brands/{brandId}/products/{productId}", brandId, productId)
                        .param("date", date))
                .andExpect(status().isOk())
                .andReturn();

        var productPriceExpected = new ProductPriceResponse(productId,
                brandId,
                3L,
                LocalDateTime.of(2020, 6, 15, 0, 0),
                LocalDateTime.of(2020, 6, 15, 11, 0, 0), 30.5d);
        var productPriceResult = serializer.readValue(requestResult.getResponse().getContentAsString(), ProductPriceResponse.class);
        assert productPriceExpected.compare(productPriceResult);
    }

    @Test
    public void test_case_two_price_applies_in_different_dates_and_the_priority_define_the_final_result() throws Exception {
        var brandId = 1L;
        var productId = 35455L;
        var date = "2020-06-16T21:00:00";

        var requestResult = mockMvc.perform(
                get("/brands/{brandId}/products/{productId}", brandId, productId)
                        .param("date", date))
                .andExpect(status().isOk())
                .andReturn();

        var productPriceExpected = new ProductPriceResponse(productId,
                brandId,
                4L,
                LocalDateTime.of(2020, 6, 15, 16, 0),
                LocalDateTime.of(2020, 12, 31, 23, 59, 59), 38.95d);
        var productPriceResult = serializer.readValue(requestResult.getResponse().getContentAsString(), ProductPriceResponse.class);
        assert productPriceExpected.compare(productPriceResult);
    }
}
