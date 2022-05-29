package pl.edu.wszib.orders.application.order;

import pl.edu.wszib.orders.api.order.OrderApi;
import pl.edu.wszib.orders.api.order.OrderFacadeApi;
import pl.edu.wszib.orders.application.product.ProductFacade;

import java.util.Optional;

public class OrderFacade implements OrderFacadeApi {
    private final OrderRepository orderRepository;
    private final ProductFacade productFacade;

    public OrderFacade(final OrderRepository orderRepository,
                       final ProductFacade productFacade) {
        this.orderRepository = orderRepository;
        this.productFacade = productFacade;
    }

    @Override
    public OrderApi create() {
        final Order order = Order.create();
        return orderRepository.save(order)
                .toApi();
    }

    @Override
    public Optional<OrderApi> findById(final String orderId) {
        return orderRepository.findById(OrderId.from(orderId))
                .map(Order::toApi);
    }

    //TODO Error handling
    @Override
    public Optional<OrderApi> addItem(final String orderId,
                                      final String productId) {
        return orderRepository.findById(OrderId.from(orderId))
                .flatMap(order -> addItem(order, productId))
                .map(Order::toApi);
    }

    private Optional<Order> addItem(final Order order,
                                    final String productId) {
        return productFacade.findById(productId)
                .map(order::addItem)
                .map(orderRepository::save);
    }

    @Override
    public OrderApi removeItem(final String orderId,
                               final String productId) {
        return null;
    }
}
