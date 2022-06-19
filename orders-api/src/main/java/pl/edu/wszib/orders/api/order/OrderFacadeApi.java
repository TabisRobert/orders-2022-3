package pl.edu.wszib.orders.api.order;

import pl.edu.wszib.orders.api.Either;

import java.util.Optional;

public interface OrderFacadeApi {
    OrderApi create();

    default Either<OrderError, OrderApi> addItem(String orderId, String productId) {
        return addItem(orderId, productId, 1);
    }

    Either<OrderError, OrderApi> addItem(String orderId, String productId, Integer quantity);

    Either<OrderError, OrderApi> removeItem(String orderId, String productId);

    Optional<OrderApi> findById(String orderId);
}
