package pl.edu.wszib.orders.application.order;

import pl.edu.wszib.orders.api.order.OrderApi;
import pl.edu.wszib.orders.api.product.ProductApi;

//TODO [EQUALS/HASHCODE]
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

    //TODO removeItem
}
