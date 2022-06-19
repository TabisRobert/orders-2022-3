package pl.edu.wszib.orders.application.order;

import pl.edu.wszib.orders.api.order.OrderApi;
import pl.edu.wszib.orders.api.product.ProductApi;

import java.util.Objects;

public class Order {
    private final OrderId id;
    private final OrderItems items;

    private Order(final OrderId id,
                  final OrderItems items) {
        this.id = id;
        this.items = items;
    }

    public static Order create() {
        return new Order(OrderId.create(), OrderItems.create());
    }

    public OrderApi toApi() {
        return new OrderApi(
                id.id(),
                items.toApi(),
                items.calculateAmount()
        );
    }

    public OrderId id() {
        return id;
    }

    public Order addItem(final ProductApi product) {
        final OrderItems newItems = items.add(product);
        return new Order(this.id, newItems);
    }

    public Order removeItem(final String productId) {
        final OrderItems newItems = items.remove(productId);
        return new Order(this.id, newItems);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {  // or if we allow classes that extend Order: o instanceof Order
            return false;
        }
        final Order order = (Order) o;
        return id.equals(order.id) && items.equals(order.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
