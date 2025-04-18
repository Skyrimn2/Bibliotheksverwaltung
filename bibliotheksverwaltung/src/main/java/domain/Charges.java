package domain;

public class Charges {
    private double baseCharge;
    private double chargePerDay;

    public Charges(double baseCharge, double chargePerDay) {
        this.setBaseCharge(baseCharge);
        this.setChargePerDay(chargePerDay);
    }

	public double getBaseCharge() {
		return baseCharge;
	}

	public void setBaseCharge(double baseCharge) {
		this.baseCharge = baseCharge;
	}

	public double getChargePerDay() {
		return chargePerDay;
	}

	public void setChargePerDay(double chargePerDay) {
		this.chargePerDay = chargePerDay;
	}
    

}