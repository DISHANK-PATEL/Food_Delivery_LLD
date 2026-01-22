package Food.pricing;

public class DeliveryChargePolicy implements ChargePolicy {
    private final double charge;

    public DeliveryChargePolicy(double charge) {
        this.charge = charge;
    }

    @Override
    public double apply(double amount) {
        return amount + charge;
    }
}
