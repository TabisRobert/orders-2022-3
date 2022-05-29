package pl.edu.wszib.orders.application.order;

import java.util.UUID;

//TODO [EQUALS/HASHCODE]
public record OrderId(String id) {

    public static OrderId create() {
        return new OrderId(UUID.randomUUID().toString());
    }

    public static OrderId from(final String orderId) {
        return new OrderId(orderId);
    }
}
