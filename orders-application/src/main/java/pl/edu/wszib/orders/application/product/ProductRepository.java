package pl.edu.wszib.orders.application.product;

import pl.edu.wszib.orders.api.product.ProductApi;

import java.util.Optional;

public interface ProductRepository {
    ProductApi save(ProductApi product);

    Optional<ProductApi> findById(String id);
}
