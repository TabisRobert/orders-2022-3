package pl.edu.wszib.orders.application.order;

import java.util.UUID;

public record OrderId(String id) {

    public static OrderId create() {
        return new OrderId(UUID.randomUUID().toString());
    }
}
