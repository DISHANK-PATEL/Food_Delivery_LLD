package Food;

import Food.model.*;
import Food.pricing.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        FoodOrder order = new FoodOrder();

        System.out.println("=====================================");
        System.out.println("            FOOD ORDER APP          ");
        System.out.println("=====================================");

        boolean running = true;

        while (running) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Add Item");
            System.out.println("2. Apply Discount");
            System.out.println("3. Add Delivery Charge");
            System.out.println("4. Add Packaging Charge");
            System.out.println("5. Calculate Bill");
            System.out.println("6. Progress Order Status");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter item name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter item price: ");
                    double price = sc.nextDouble();

                    System.out.print("Enter quantity: ");
                    int qty = sc.nextInt();

                    order.addItem(new OrderItem(name, price, qty));
                    System.out.println("##### Item added successfully!");
                    break;

                case 2:
                    System.out.print("Enter discount percentage: ");
                    double disc = sc.nextDouble();
                    order.addChargePolicy(new PercentageDiscountPolicy(disc));
                    System.out.println("##### Discount applied!");
                    break;

                case 3:
                    System.out.print("Enter delivery charge: ");
                    double del = sc.nextDouble();
                    order.addChargePolicy(new DeliveryChargePolicy(del));
                    System.out.println("##### Delivery charge applied!");
                    break;

                case 4:
                    System.out.print("Enter packaging charge: ");
                    double pack = sc.nextDouble();
                    order.addChargePolicy(new PackagingChargePolicy(pack));
                    System.out.println("##### Packaging charge applied!");
                    break;

                case 5:
                    printOrderSummary(order);
                    break;

                case 6:
                    progressOrderStatus(order);
                    break;

                case 7:
                    running = false;
                    System.out.println("Thank you for using Food Order App! #####");
                    break;

                default:
                    System.out.println("##### Invalid choice. Try again.");
            }
        }
    }

    private static void printOrderSummary(FoodOrder order) {
        System.out.println("\n==========  BILL SUMMARY ==========");
        for (OrderItem item : order.getItems()) {
            System.out.printf("%-15s x%-2d  Rs %-6.2f\n",
                    item.getName(), item.getQuantity(), item.getTotalPrice());
        }
        System.out.println("-------------------------------------");
        System.out.printf("Base Amount:    Rs %.2f\n", order.getBaseTotal());
        System.out.printf("Final Amount:   Rs %.2f\n", order.calculateFinal());
        System.out.println("=====================================");
    }

    private static void progressOrderStatus(FoodOrder order) {
        try {
            OrderStatus next = switch (order.getStatus()) {
                case PLACED -> OrderStatus.ACCEPTED;
                case ACCEPTED -> OrderStatus.PREPARING;
                case PREPARING -> OrderStatus.OUT_FOR_DELIVERY;
                case OUT_FOR_DELIVERY -> OrderStatus.DELIVERED;
                default -> null;
            };

            if (next == null) {
                System.out.println("##### Order already delivered!");
                return;
            }

            order.transition(next);
            System.out.println("##### Status updated → " + order.getStatus());

        } catch (Exception e) {
            System.out.println("##### " + e.getMessage());
        }
    }
}
