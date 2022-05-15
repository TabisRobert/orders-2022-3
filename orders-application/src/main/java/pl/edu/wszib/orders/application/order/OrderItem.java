package pl.edu.wszib.orders.application.order;

import pl.edu.wszib.orders.api.order.OrderItemApi;
import pl.edu.wszib.orders.api.product.ProductApi;

import java.math.BigDecimal;

public class OrderItem {
    private final ProductApi product;
    //https://unitsofmeasurement.github.io/unit-api/
    private final Integer quantity;

    public OrderItem(final ProductApi product,
                     final Integer quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public OrderItemApi toApi() {
        return new OrderItemApi(product, quantity, calculateAmount());
    }

    BigDecimal calculateAmount() {
        return product.price().multiply(BigDecimal.valueOf(quantity));
    }
}
