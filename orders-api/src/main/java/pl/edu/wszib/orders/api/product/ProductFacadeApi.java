package pl.edu.wszib.orders.api.product;

import java.util.Optional;

public interface ProductFacadeApi {
    ProductApi create(ProductApi product);

    Optional<ProductApi> findById(String id);

    void showAllProducts();
}
