package pl.edu.wszib.orders.application.order;

import pl.edu.wszib.orders.api.order.OrderItemApi;
import pl.edu.wszib.orders.api.product.ProductApi;

import java.math.BigDecimal;
import java.util.Objects;

public class OrderItem {
    private final ProductApi product;
    //https://unitsofmeasurement.github.io/unit-api/
    private final Integer quantity;

    private OrderItem(final ProductApi product,
                      final Integer quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public static OrderItem create(final ProductApi product,
                                   final Integer quantity) {
        return new OrderItem(product, quantity);
    }

    public OrderItem increaseQuantity(final Integer increaseQuantity) {
        return new OrderItem(product, quantity + increaseQuantity);
    }

    public OrderItemApi toApi() {
        return new OrderItemApi(product, quantity, calculateAmount());
    }

    BigDecimal calculateAmount() {
        return product.price().multiply(BigDecimal.valueOf(quantity));
    }

    boolean isProduct(final String productId) {
        return product.hasId(productId);
    }

    public boolean isNotProduct(final String productId) {
        return !isProduct(productId);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final OrderItem orderItem = (OrderItem) o;
        return product.equals(orderItem.product) && quantity.equals(orderItem.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, quantity);
    }
}
