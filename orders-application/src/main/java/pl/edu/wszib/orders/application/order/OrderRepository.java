package pl.edu.wszib.orders.application.order;

import pl.edu.wszib.orders.api.order.OrderApi;

import java.util.Optional;

public interface OrderRepository {
    Order save(Order order);

    Optional<Order> findById(OrderId id);
}
