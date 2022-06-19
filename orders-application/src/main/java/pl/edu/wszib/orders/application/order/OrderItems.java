package pl.edu.wszib.orders.application.order;

import pl.edu.wszib.orders.api.order.OrderItemApi;
import pl.edu.wszib.orders.api.product.ProductApi;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

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

    public OrderItems remove(final String productId) {
        final Set<OrderItem> newItems = items.stream()
                .filter(item -> item.isNotProduct(productId))
                .collect(Collectors.toSet());
        // we ignore situation when product is not part of order
        return new OrderItems(newItems);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final OrderItems that = (OrderItems) o;
        return items.equals(that.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(items);
    }
}
