package pl.edu.wszib.orders.application.order;

import pl.edu.wszib.orders.api.order.OrderApi;

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

    public Order addItem() {
//        items.
        return null;
    }
}
