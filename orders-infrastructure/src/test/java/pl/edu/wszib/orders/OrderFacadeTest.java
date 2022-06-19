package pl.edu.wszib.orders;

import org.junit.jupiter.api.Test;
import pl.edu.wszib.orders.api.Either;
import pl.edu.wszib.orders.api.order.OrderApi;
import pl.edu.wszib.orders.api.order.OrderError;
import pl.edu.wszib.orders.api.product.ProductApi;
import pl.edu.wszib.orders.application.order.OrderFacade;
import pl.edu.wszib.orders.application.order.OrderId;
import pl.edu.wszib.orders.application.product.ProductFacade;
import pl.edu.wszib.orders.infrastructure.order.InMemoryOrderRepository;
import pl.edu.wszib.orders.infrastructure.product.InMemoryProductRepository;

import static org.junit.jupiter.api.Assertions.*;

public class OrderFacadeTest {
    private final ProductFacade productFacade = new ProductFacade(new InMemoryProductRepository());
    private final OrderFacade orderFacade = new OrderFacade(new InMemoryOrderRepository(), productFacade);

    @Test
    public void should_be_able_to_create_order() {
        // when: we create order
        final OrderApi createdOrder = orderFacade.create();

        // then: created order should not be null
        assertNotNull(createdOrder);
        assertExist(createdOrder.id());
    }

    @Test
    public void should_be_able_to_find_order_by_id() {
        fail();
    }

    @Test
    public void should_not_be_able_to_find_not_existing_order() {
        fail();
    }

    @Test
    public void should_be_able_to_add_item_to_order() {
        // given: we have existing order id
        final String existingOrderId = createExampleOrder().id();
        // and: we have existing product id
        final String existingProductId = createExampleProduct().id();

        // when: we try to add item with existing product
        final Either<OrderError, OrderApi> result = orderFacade.addItem(existingOrderId, existingProductId);

        // then: order with added item should be success
        assertTrue(result.isRight());
        // and: order should contain added product
        final OrderApi orderWithAddedItem = result.get();
        assertOrderContainProduct(orderWithAddedItem, existingProductId);
    }

    @Test
    public void should_be_able_to_remove_item_from_order() {
        // given: we have existing order id
        final String existingOrderId = createExampleOrder().id();
        // and: we have existing product id
        final String existingProductId = createExampleProduct().id();
        // and: we have successfully added item to order
        assertTrue(orderFacade.addItem(existingOrderId, existingProductId).isRight());

        // when: we try to remove existing item from order
        final Either<OrderError, OrderApi> result = orderFacade.removeItem(existingOrderId, existingProductId);

        // then: order with removed item should be success
        assertTrue(result.isRight());
        // and: order should not contain removed product
        assertOrderNotContainProduct(result.get(), existingProductId);
    }

    @Test
    public void when_adding_the_same_product_to_order_twice_it_should_result_increasing_quantity() {
        fail();
    }

    private OrderApi createExampleOrder() {
        return orderFacade.create();
    }

    private void assertExist(final String id) {
        assertTrue(orderFacade.findById(id).isPresent());
    }

    private void assertOrderContainProduct(final OrderApi orderApi,
                                           final String productId) {
        assertTrue(orderApi.hasProduct(productId), "order does not contain product id = " + productId);
    }

    private void assertOrderNotContainProduct(final OrderApi orderApi,
                                              final String productId) {
        assertFalse(orderApi.hasProduct(productId), "order should not contain product id = " + productId);
    }

    private ProductApi createExampleProduct() {
        return productFacade.create(ProductSamples.exampleProduct());
    }
}
