package Food.pricing;

import java.util.List;

public class PricingEngine {
    public double calculate(double baseAmount, List<ChargePolicy> policies) {
        double total = baseAmount;
        for (ChargePolicy policy : policies) {
            total = policy.apply(total);
        }
        return total;
    }
}
