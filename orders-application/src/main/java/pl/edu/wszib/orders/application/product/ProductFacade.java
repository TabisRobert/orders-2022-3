package pl.edu.wszib.orders.application.product;

import pl.edu.wszib.orders.api.product.ProductApi;
import pl.edu.wszib.orders.api.product.ProductFacadeApi;

import java.util.Optional;

public class ProductFacade implements ProductFacadeApi {
    private final ProductRepository productRepository;

    public ProductFacade(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductApi create(final ProductApi product) {
        return productRepository.save(product);
    }

    @Override
    public Optional<ProductApi> findById(final String id) {
        return productRepository.findById(id);
    }
}
