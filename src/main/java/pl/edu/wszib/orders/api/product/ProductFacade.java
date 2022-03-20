package pl.edu.wszib.orders.api.product;

import java.util.Optional;

public interface ProductFacade {
    ProductApi create(final ProductApi product);

    Optional<ProductApi> findById(String id);
}
