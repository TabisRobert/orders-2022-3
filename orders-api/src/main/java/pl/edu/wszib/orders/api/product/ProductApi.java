package pl.edu.wszib.orders.api.product;

import java.math.BigDecimal;

public record ProductApi(String id,
                         String name,
                         BigDecimal price) {
}
