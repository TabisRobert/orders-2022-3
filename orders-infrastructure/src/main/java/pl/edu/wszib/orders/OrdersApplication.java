package pl.edu.wszib.orders;

import pl.edu.wszib.orders.api.product.ProductApi;

import java.math.BigDecimal;

public class OrdersApplication {

    public static void main(final String[] args) {
        final ProductApi product = new ProductApi("test id", "test name", BigDecimal.TEN);
        System.out.println("Hello world");
        System.out.println("Product: " + product);
    }
}
