package pl.edu.wszib.orders.api.order;

import java.math.BigDecimal;
import java.util.Set;

public record OrderApi(String id,
                       Set<OrderItemApi> items,
                       BigDecimal amount) {
}
