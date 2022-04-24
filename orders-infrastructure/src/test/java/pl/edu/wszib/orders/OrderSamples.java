package pl.edu.wszib.orders;

import pl.edu.wszib.orders.api.order.OrderApi;
import pl.edu.wszib.orders.api.order.OrderItemApi;
import pl.edu.wszib.orders.api.product.ProductApi;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

public class OrderSamples {
    public OrderApi exampleOrder() {
        return new OrderApi(
                exampleOrderId(),
                exampleOrderItems(),
                BigDecimal.ONE  //TODO
        );
    }

    private Set<OrderItemApi> exampleOrderItems() {
        return Set.of(
                exampleOrderItem(ProductSamples.exampleProduct())
        );
    }

    private OrderItemApi exampleOrderItem(final ProductApi productApi) {
        return new OrderItemApi(
                productApi,
                1,
                productApi.price());
    }

    private String exampleOrderId() {
        return UUID.randomUUID().toString();
    }
}
