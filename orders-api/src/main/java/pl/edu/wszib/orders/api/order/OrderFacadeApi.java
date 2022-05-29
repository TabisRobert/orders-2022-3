package pl.edu.wszib.orders.api.order;

import pl.edu.wszib.orders.api.Either;

import java.util.Optional;

public interface OrderFacadeApi {
    OrderApi create();

    Either<OrderError, OrderApi> addItem(String orderId, String productId);

    Either<OrderError, OrderApi> removeItem(String orderId, String productId);

    Optional<OrderApi> findById(String orderId);
}
