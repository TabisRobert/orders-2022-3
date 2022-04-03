package pl.edu.wszib.orders.infrastructure.product;

import pl.edu.wszib.orders.api.product.ProductApi;
import pl.edu.wszib.orders.application.product.ProductRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryProductRepository implements ProductRepository {
    private final Map<String, ProductApi> products = new HashMap<>();

    @Override
    public ProductApi save(final ProductApi product) {
        products.put(product.id(), product);
        return product;
    }

    @Override
    public Optional<ProductApi> findById(final String id) {
        return Optional.ofNullable(products.get(id));
    }
}
