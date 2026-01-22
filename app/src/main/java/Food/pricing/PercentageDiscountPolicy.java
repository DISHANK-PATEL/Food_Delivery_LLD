package Food.pricing;

public class PercentageDiscountPolicy implements ChargePolicy {
    private final double percent;

    public PercentageDiscountPolicy(double percent) {
        this.percent = percent;
    }

    @Override
    public double apply(double amount) {
        return amount - (amount * percent / 100.0);
    }
}
