package pl.edu.wszib.orders;

import org.junit.jupiter.api.Test;
import pl.edu.wszib.orders.api.order.OrderApi;
import pl.edu.wszib.orders.api.product.ProductApi;
import pl.edu.wszib.orders.application.order.OrderFacade;
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
    public void should_be_able_to_add_item_to_order() {
        // given: we have existing order id
        final String existingOrderId = createExampleOrder().id();
        // and: we have existing product id
        final String existingProductId = createExampleProduct().id();

        // when: we try to add item with existing product
        final OrderApi orderWithAddedItem = orderFacade.addItem(existingOrderId, existingProductId);

        // then: order with added item should not be null
        assertNotNull(orderWithAddedItem);
        // and: order should contain added product
        assertOrderContainProduct(orderWithAddedItem, existingProductId);
    }

    @Test
    public void should_be_able_to_remove_item_from_order() {
        fail();
    }

    @Test
    public void should_be_able_to_find_order_by_id() {
        fail();
    }

    @Test
    public void when_adding_the_same_product_to_order_twice_it_should_result_increasing_quantity() {
        fail();
    }

    private OrderApi createExampleOrder() {
        return orderFacade.create();
    }

    private void assertExist(String id) {
        assertTrue(orderFacade.findById(id).isPresent());
    }

    private void assertOrderContainProduct(OrderApi orderApi,
                                           String productId) {
        assertTrue(orderApi.hasProduct(productId), "order does not contain product id = " + productId);
    }

    private ProductApi createExampleProduct() {
        return productFacade.create(ProductSamples.exampleProduct());
    }
}
