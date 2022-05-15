package pl.edu.wszib.orders.application.order;

import pl.edu.wszib.orders.api.order.OrderApi;
import pl.edu.wszib.orders.api.order.OrderFacadeApi;
import pl.edu.wszib.orders.application.product.ProductFacade;

import java.util.Optional;

public class OrderFacade implements OrderFacadeApi {
    private final OrderRepository orderRepository;
    private final ProductFacade productFacade;

    public OrderFacade(final OrderRepository orderRepository,
                       final ProductFacade productFacade) {
        this.orderRepository = orderRepository;
        this.productFacade = productFacade;
    }

    @Override
    public OrderApi create() {
        final Order order = Order.create();
        return orderRepository.save(order)
                .toApi();
    }

    @Override
    public Optional<OrderApi> findById(String orderId) {
        return orderRepository.findById(OrderId.from(orderId))
                .map(Order::toApi);
    }

    //TODO Error handling
    @Override
    public OrderApi addItem(String orderId,
                            String productId) {
        //TODO TASK: impl addItem
        //TODO find product by given productId, if not exist return null
        //TODO find order by id and if exists add product with quantity 1, if not exist return null
        return null;
    }

    @Override
    public OrderApi removeItem(String orderId,
                               String productId) {
        return null;
    }
}
