package Food.pricing;

public class PackagingChargePolicy implements ChargePolicy {
    private final double packagingCharge;

    public PackagingChargePolicy(double packagingCharge) {
        this.packagingCharge = packagingCharge;
    }

    @Override
    public double apply(double amount) {
        return amount + packagingCharge;
    }
}
