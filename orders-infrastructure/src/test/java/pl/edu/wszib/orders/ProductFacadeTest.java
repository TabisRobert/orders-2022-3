package pl.edu.wszib.orders;

import org.junit.jupiter.api.Test;
import pl.edu.wszib.orders.api.product.ProductApi;
import pl.edu.wszib.orders.application.product.ProductFacade;
import pl.edu.wszib.orders.infrastructure.product.InMemoryProductRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProductFacadeTest {
    private final ProductFacade productFacade = new ProductFacade(new InMemoryProductRepository());

    @Test
    public void should_be_able_to_create_product() {
        // given: we have example product definition
        final ProductApi exampleProduct = ProductSamples.exampleProduct();

        // when: we try to create given product
        final ProductApi createdProduct = productFacade.create(exampleProduct);

        // then: created product is not null
        assertNotNull(createdProduct);
        // and: product with created product id exists
        assertExists(createdProduct.id());
    }

    @Test
    public void should_be_able_to_get_product_by_id() {
        // given: we have example existing product id
        final String exampleExistingProductId = createExampleProduct().id();

        // when: we try to get existing product by id
        final Optional<ProductApi> foundProduct = productFacade.findById(exampleExistingProductId);

        // then: found product should be present
        assertTrue(foundProduct.isPresent());
    }

    @Test
    public void should_not_be_able_to_get_non_existing_product() {
        // given: we have example product id that does not exist
        final String notExistingProductId = ProductSamples.exampleProductId();

        // when: we try to get not existing product by id
        final Optional<ProductApi> foundProduct = productFacade.findById(notExistingProductId);

        // then:
        assertTrue(foundProduct.isEmpty());
    }

    @Test
    public void should_not_be_able_to_create_the_same_product_twice() {
        // TODO: impl
    }

    private void assertExists(final String id) {
        assertTrue(productFacade.findById(id).isPresent());
    }

    private ProductApi createExampleProduct() {
        return productFacade.create(ProductSamples.exampleProduct());
    }
}
