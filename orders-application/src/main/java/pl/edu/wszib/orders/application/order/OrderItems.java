package pl.edu.wszib.orders.application.order;

import pl.edu.wszib.orders.api.order.OrderItemApi;
import pl.edu.wszib.orders.api.product.ProductApi;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

//TODO [EQUALS/HASHCODE]
public class OrderItems {
    private final Set<OrderItem> items;

    private OrderItems(final Set<OrderItem> items) {
        this.items = items;
    }

    public static OrderItems create() {
        return new OrderItems(Set.of());
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

    public OrderItems add(final ProductApi product) {
        final Set<OrderItem> newItems = new HashSet<>(this.items);
        newItems.add(OrderItem.create(product));
        return new OrderItems(Set.copyOf(newItems));
    }
}
