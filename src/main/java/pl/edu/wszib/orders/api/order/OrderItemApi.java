package pl.edu.wszib.orders.api.order;

import pl.edu.wszib.orders.api.product.ProductApi;

import java.math.BigDecimal;

public record OrderItemApi(ProductApi product,
                           Integer quantity,
                           BigDecimal amount) {
}
