package pl.edu.wszib.orders;

import pl.edu.wszib.orders.api.product.ProductApi;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;

public class ProductSamples {

    public static ProductApi exampleProduct() {
        return new ProductApi(
                exampleProductId(),
                "Example product",
                new BigDecimal("10.50").setScale(2, RoundingMode.HALF_UP)
        );
    }

    public static String exampleProductId() {
        return UUID.randomUUID().toString();
    }
}
