package pl.edu.wszib.orders;

import pl.edu.wszib.orders.api.product.ProductApi;
import pl.edu.wszib.orders.application.product.ProductFacade;
import pl.edu.wszib.orders.infrastructure.product.InMemoryProductRepository;

import java.math.BigDecimal;
import java.util.Optional;

public class OrdersApplication {

    //TODO [CI] support github actions
    public static void main(final String[] args) {
        final ProductApi product = new ProductApi("test id", "test name", BigDecimal.TEN);
        final ProductFacade productFacade = new ProductFacade(new InMemoryProductRepository());
        productFacade.create(product);
        final Optional<ProductApi> foundProduct = productFacade.findById(product.id());
        System.out.println("Found product: " + foundProduct);
    }
}
