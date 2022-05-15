package pl.edu.wszib.orders.application.order;

import pl.edu.wszib.orders.api.order.OrderApi;
import pl.edu.wszib.orders.api.order.OrderFacadeApi;

import java.util.Optional;

public class OrderFacade implements OrderFacadeApi {
    private final OrderRepository orderRepository;

    public OrderFacade(final OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public OrderApi create() {
        final Order order = Order.create();
        return orderRepository.save(order)
                .toApi();
    }

    @Override
    public OrderApi addItem(String orderId,
                            String productId) {
        return null;
    }

    @Override
    public OrderApi removeItem(String orderId,
                               String productId) {
        return null;
    }

    @Override
    public Optional<OrderApi> findById(String orderId) {
        return Optional.empty();
    }
}
