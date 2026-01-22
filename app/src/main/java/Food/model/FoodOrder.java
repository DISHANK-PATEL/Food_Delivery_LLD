package Food.model;

import Food.pricing.ChargePolicy;
import Food.pricing.PricingEngine;

import java.util.*;

public class FoodOrder {
    private final List<OrderItem> items = new ArrayList<>();
    private final List<ChargePolicy> policies = new ArrayList<>();
    private final PricingEngine engine = new PricingEngine();
    private OrderStatus status = OrderStatus.PLACED;

    private static final Map<OrderStatus, Set<OrderStatus>> validTransitions = Map.of(
            OrderStatus.PLACED, Set.of(OrderStatus.ACCEPTED),
            OrderStatus.ACCEPTED, Set.of(OrderStatus.PREPARING),
            OrderStatus.PREPARING, Set.of(OrderStatus.OUT_FOR_DELIVERY),
            OrderStatus.OUT_FOR_DELIVERY, Set.of(OrderStatus.DELIVERED),
            OrderStatus.DELIVERED, Set.of()
    );

    public void addItem(OrderItem item) {
        items.add(item);
    }

    public void addChargePolicy(ChargePolicy policy) {
        policies.add(policy);
    }

    public double calculateFinal() {
        double baseAmount = items.stream()
                .mapToDouble(OrderItem::getTotalPrice)
                .sum();
        return engine.calculate(baseAmount, policies);
    }

    public double getBaseTotal() {
        return items.stream()
                .mapToDouble(OrderItem::getTotalPrice)
                .sum();
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void transition(OrderStatus next) {
        if (!validTransitions.get(status).contains(next)) {
            throw new IllegalStateException("Invalid transition: " + status + " -> " + next);
        }
        status = next;
    }

    public List<OrderItem> getItems() {
        return items;
    }
}
