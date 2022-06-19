package pl.edu.wszib.orders.api.order;

public record OrderError(Code code, String message) {
    public static OrderError productNotFound(final String productId) {
        return new OrderError(Code.PRODUCT_NOT_FOUND, "Product with id = " + productId + " not found.");
    }

    public static OrderError orderNotFound(final String orderId) {
        return new OrderError(Code.ORDER_NOT_FOUND, "Order with id = " + orderId + " not found.");
    }

    public static OrderError notImplemented(final String message) {
        return new OrderError(Code.NOT_IMPLEMENTED, message);
    }

    public enum Code {
        NOT_IMPLEMENTED,
        ORDER_NOT_FOUND,
        PRODUCT_NOT_FOUND,

    }
}
