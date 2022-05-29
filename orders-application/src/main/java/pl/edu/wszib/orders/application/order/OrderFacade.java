package pl.edu.wszib.orders.application.order;

import pl.edu.wszib.orders.api.Either;
import pl.edu.wszib.orders.api.order.OrderApi;
import pl.edu.wszib.orders.api.order.OrderError;
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

    @Override
    public Either<OrderError, OrderApi> addItem(final String orderId,
                                                final String productId) {
        return orderRepository.findById(OrderId.from(orderId))
                .map(order -> addItem(order, productId))
                .orElse(Either.left(OrderError.orderNotFound(orderId)));
    }

    private Either<OrderError, OrderApi> addItem(final Order order,
                                                 final String productId) {
        return productFacade.findById(productId)
                .map(order::addItem)
                .map(orderRepository::save)
                .map(Order::toApi)
                .<Either<OrderError, OrderApi>>map(Either::right)
                .orElse(Either.left(OrderError.productNotFound(productId)));
    }

    @Override
    public Either<OrderError, OrderApi> removeItem(final String orderId,
                                                   final String productId) {
        return null;
    }
}
