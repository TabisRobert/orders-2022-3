package pl.edu.wszib.orders.consoleui;

import pl.edu.wszib.orders.api.order.OrderApi;
import pl.edu.wszib.orders.api.order.OrderError;
import pl.edu.wszib.orders.api.order.OrderFacadeApi;
import pl.edu.wszib.orders.api.product.ProductApi;
import pl.edu.wszib.orders.api.product.ProductFacadeApi;

import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

public class ConsoleUI {
    private final OrderFacadeApi orderFacadeApi;

    private final ProductFacadeApi productFacadeApi;

    public ConsoleUI(OrderFacadeApi orderFacadeApi, ProductFacadeApi productFacadeApi) {
        this.orderFacadeApi = orderFacadeApi;
        this.productFacadeApi = productFacadeApi;
    }

    private final Scanner scanner = new Scanner(System.in);

    public void run() {
//        System.out.println("Wprowadź tekst: ");
//        final String read = scanner.nextLine();
//        System.out.println("Odczytano: " + read);
        boolean runApp = true;

        while (runApp) {
            System.out.println(
                    """
                            Welcome in app! Choose option (enter number related to option):
                                    1. Create order
                                    2. Find order
                                    3. Add product to order
                                    4. Remove product from order
                                    0. Exit app""".indent(1)
            );
            String chosenOption = scanner.nextLine();
            OrderApi orderApi;
            Optional<OrderApi> foundOrder;
            Optional<ProductApi> foundProduct;
            OrderApi existingOrder;
            ProductApi existingProduct;
            switch (chosenOption) {
                case "1":
                    orderApi = orderFacadeApi.create();
                    System.out.println("New order was created with id: " + orderApi.id());
                    break;
                case "2":
                    System.out.println("Enter order id: ");
                    String givenOrderId = scanner.nextLine();
                    foundOrder = orderFacadeApi.findById(givenOrderId);
                    foundOrder.ifPresentOrElse(order -> System.out.println("Order found with id: " + order.id()),
                            () -> System.out.println(OrderError.orderNotFound(givenOrderId)));
                    break;
                case "3":
                    System.out.println("Enter order id: ");
                    String orderId = scanner.nextLine();
                    System.out.println("List of available products:");
                    productFacadeApi.showAllProducts();
                    System.out.println("Which product you want to add to order? Enter product id: ");
                    String productId = scanner.nextLine();
                    foundOrder = orderFacadeApi.findById(orderId);
                    foundProduct = productFacadeApi.findById(productId);
                    if (foundOrder.isPresent()){
                        existingOrder = foundOrder.get();
                    } else {
                        OrderError.orderNotFound(orderId);
                        break;
                    }
                    if (foundProduct.isPresent()){
                        existingProduct = foundProduct.get();
                    } else {
                        OrderError.productNotFound(productId);
                        break;
                    }
                    orderFacadeApi.addItem(existingOrder.id(), existingProduct.id());
                    break;
                case "4":
                    System.out.println("Enter order id: ");
                    orderId = scanner.nextLine();
                    System.out.println("List of available products:");
                    productFacadeApi.showAllProducts();
                    System.out.println("Which product you want to add to order? Enter product id: ");
                    productId = scanner.nextLine();
                    foundOrder = orderFacadeApi.findById(orderId);
                    foundProduct = productFacadeApi.findById(productId);
                    if (foundOrder.isPresent()){
                        existingOrder = foundOrder.get();
                    } else {
                        OrderError.orderNotFound(orderId);
                        break;
                    }
                    if (foundProduct.isPresent()){
                        existingProduct = foundProduct.get();
                    } else {
                        OrderError.productNotFound(productId);
                        break;
                    }
                    orderFacadeApi.removeItem(existingOrder.id(), existingProduct.id());
                    break;
                case "0":
                    runApp = false;
                    System.out.println("Thank you for your visit!");
                    break;
                default:
                    System.out.println("Invalid input. Try again");
            }
        }

        // 1. Identyfikator utworzonego zamówienia: ${orderId}

        // 2.1 Podaj identyfikator zamówienia: ${orderId}
        // 2.2 (odczytaj orderId)
        // 2.3 Znaleziono zamówienie: ${orderId}
        // 2.4 Wystąpił błąd: ${error}
        // 2.5 (powrót do głównego menu)

        // 3.1 Podaj identyfikator zamówienia: ${orderId}
        // 3.2 (odczytaj orderId)
        // 3.3 Wyświetl listę dostępnych produktów: ${allProducts}
        // 3.4 Podaj identyfikator produktu, który chcesz dodać do zamówienia: ${productId}
        // 3.5 Wyświetl zmodyfikowane zamówienie: ${order}
        // 3.6 Wystąpił błąd: ${error}
        // 3.7 (powrót do głównego menu)

        // 4. (na podstawie punktu 3)
        // 5. (Przerwanie pętli głównej programu)
    }
}
