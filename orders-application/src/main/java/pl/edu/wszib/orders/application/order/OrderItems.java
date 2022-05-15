package pl.edu.wszib.orders.application.order;

import pl.edu.wszib.orders.api.order.OrderItemApi;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

public class OrderItems {
    private final Set<OrderItem> items;

    private OrderItems(final Set<OrderItem> items) {
        this.items = items;
    }

    public static OrderItems create() {
        return new OrderItems(new HashSet<>());
    }

    public Set<OrderItemApi> toApi() {
        return this.items.stream()
                .map(OrderItem::toApi)
                .collect(Collectors.toSet());
    }

    public BigDecimal calculateAmount() {
        return items.stream()
                .map(OrderItem::calculateAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
