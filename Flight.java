
public class Flight {
	private String flightCode;
	private String destination;
	private String carrier;
	private int passengerCapacity;
	private float maxBaggageVolume;
	private float maxBaggageWeight;
	private float feeMultplier;
	
	private int totalPassengers;
	private float totalBaggageVolume;
	private float totalBaggageWeight;
	private float totalFees;
	
	public Flight(String flightCode, String destination, String carrier,
			int passengerCapacity,float maxVol, float maxWeight,float feeMultiplier) {
		this.setFlightCode(flightCode);
		this.setDestination(destination);
		this.setCarrier(carrier);
		this.setPassengerCapacity(passengerCapacity);
		this.setMaxBaggageVolume(maxVol);
		this.setMaxBaggageWeight(maxWeight);
		this.setFeeMultplier(feeMultiplier);
	}
	
	public int getTotalPassengers()
	{
		return this.totalPassengers;
	}

	public String getFlightCode() {
		return flightCode;
	}

	public void setFlightCode(String flightCode) {
		this.flightCode = flightCode;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public int getPassengerCapacity() {
		return passengerCapacity;
	}

	public void setPassengerCapacity(int passengerCapacity) {
		this.passengerCapacity = passengerCapacity;
	}

	public float getMaxBaggageVolume() {
		return maxBaggageVolume;
	}

	public void setMaxBaggageVolume(float maxBaggageVolume) {
		this.maxBaggageVolume = maxBaggageVolume;
	}

	public float getFeeMultplier() {
		return feeMultplier;
	}

	public void setFeeMultplier(float feeMultplier) {
		this.feeMultplier = feeMultplier;
	}

	public float getMaxBaggageWeight() {
		return maxBaggageWeight;
	}

	public void setMaxBaggageWeight(float maxBaggageWeight) {
		this.maxBaggageWeight = maxBaggageWeight;
	}

	public float getTotalBaggageVolume() {
		return totalBaggageVolume;
	}

	public void setTotalBaggageVolume(float totalBaggageVolume) {
		this.totalBaggageVolume = totalBaggageVolume;
	}

	public float getTotalFees() {
		return totalFees;
	}

	public void setTotalFees(float totalFees) {
		this.totalFees = totalFees;
	}

	public float getTotalBaggageWeight() {
		return totalBaggageWeight;
	}

	public void setTotalBaggageWeight(float totalBaggageWeight) {
		this.totalBaggageWeight = totalBaggageWeight;
	}
	
	public void addPassengerAndBaggage(float vol, float weight) {
		float excess_weight = weight - this.getMaxBaggageWeight();
		
		this.totalBaggageWeight+=weight;
		this.totalBaggageVolume+=vol;
		
		if (excess_weight>0)
		{
			this.totalFees+=this.getFeeMultplier()*excess_weight;
		}
		
		this.totalPassengers++;
		
		//TODO: do we apply the excess (and fee) to the volume too?
	}
	
	public String generateReport()
	{
		StringBuilder report = new StringBuilder();
		
		report.append(String.format("Total passengers: %d\n", this.getTotalPassengers()));
		report.append(String.format("Total weight: %.2f\n", this.getTotalBaggageWeight()));
		
		report.append(String.format("Total volume: %.2f\n", this.getMaxBaggageVolume()));
		
		report.append(String.format("Total paid fees: %.2f\n", this.getTotalFees()));
		
		return report.toString();
	}
	
	public boolean equals(Object obj)
	{
		return (obj instanceof Flight) && (((Flight)obj).getFlightCode().toUpperCase().equals(this.getFlightCode().toUpperCase()));
	}
	
	public int compareTo(Flight flight)
	{
		return this.getFlightCode().toUpperCase().compareTo(flight.getFlightCode().toUpperCase());
	}
}
