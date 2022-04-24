package pl.edu.wszib.orders.api.product;

import java.math.BigDecimal;
import java.util.Objects;

public record ProductApi(String id,
                         String name,
                         BigDecimal price) {
    public boolean hasId(final String productId) {
        return Objects.equals(id, productId);
    }
}
