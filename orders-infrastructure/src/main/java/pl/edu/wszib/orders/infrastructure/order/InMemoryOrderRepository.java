package pl.edu.wszib.orders.infrastructure.order;

import pl.edu.wszib.orders.api.product.ProductApi;
import pl.edu.wszib.orders.application.order.Order;
import pl.edu.wszib.orders.application.order.OrderId;
import pl.edu.wszib.orders.application.order.OrderRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryOrderRepository implements OrderRepository {
    private final Map<OrderId, Order> orders = new HashMap<>();

    @Override
    public Order save(final Order order) {
        orders.put(order.id(), order);
        return order;
    }

    @Override
    public Optional<Order> findById(final OrderId id) {
        return Optional.ofNullable(orders.get(id));
    }
}
