
public class Flight {
	//flight description
	private String flightCode;
	private String destination;
	private String carrier;
	
	//limits per passengers
	private float maxBaggageVolume;
	private float maxBaggageWeight;
	private float feeMultplier;
	
	//passengers/volume/weight up to date
	private int   currentTotalPassengers;
	private float currentTotalBaggageVolume;
	private float currentTotalBaggageWeight;
	private float currentTotalFees;
	
	//total limits in the aircraft
	private float maxTotalBaggageVolume;
	private float maxTotalBaggageWeight;
	private int passengerCapacity; 
	
	public Flight(String flightCode, String destination, String carrier,
			float maxVol, float maxWeight,float feeMultiplier, 
			int passengerCapacity,float totalBV, float totalBW) {
		this.setFlightCode(flightCode);
		this.setDestination(destination);
		this.setCarrier(carrier);
		this.setPassengerCapacity(passengerCapacity);
		this.setMaxBaggageVolume(maxVol);
		this.setMaxBaggageWeight(maxWeight);
		this.setFeeMultplier(feeMultiplier);
		this.setMaxTotalBaggageWeight(totalBW);
		this.setMaxTotalBaggageVolume(totalBV);
	}
	
	public int getCurrentTotalPassengers()
	{
		return this.currentTotalPassengers;
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

	public float getCurrentTotalBaggageVolume() {
		return currentTotalBaggageVolume;
	}

	public void setCurrentTotalBaggageVolume(float totalBaggageVolume) {
		this.currentTotalBaggageVolume = totalBaggageVolume;
	}

	public float getCurrentTotalFees() {
		return currentTotalFees;
	}

	public void setCurrentTotalFees(float totalFees) {
		this.currentTotalFees = totalFees;
	}

	public float getCurrentTotalBaggageWeight() {
		return currentTotalBaggageWeight;
	}

	public void setCurrentTotalBaggageWeight(float totalBaggageWeight) {
		this.currentTotalBaggageWeight = totalBaggageWeight;
	}
	
	public void addPassengerAndBaggage(float vol, float weight) {
		
		//computing whether the checked luggage is exceeding the per pax weight limit
		float excess_weight = weight - this.getMaxBaggageWeight();
		
		//update weight and vol with this passenger's data
		this.currentTotalBaggageWeight+=weight;
		this.currentTotalBaggageVolume+=vol;
		
		//if the difference above is positive, the weight is exceeding
		if (excess_weight>0)
		{
			//compute the fees only on the difference of weight
			this.currentTotalFees+=this.getFeeMultplier()*excess_weight;
		}
		//add a passenger to the current count
		this.currentTotalPassengers++;
		
		//TODO: do we apply the excess (and fee) to the volume too?
	}
	
	public String generateReport()
	{
		StringBuilder report = new StringBuilder();
		
		report.append(String.format("Flight code: %s\n",this.getFlightCode()));
		report.append(String.format("Number of Passengers: %d\n", this.getCurrentTotalPassengers()));
		report.append(String.format("Total Baggage Weight: %.2f\n", this.getCurrentTotalBaggageWeight()));
		report.append(String.format("Total Baggage Volume: %.2f\n", this.getMaxBaggageVolume()));
		report.append(String.format("Total Excess Fees: %.2f\n", this.getCurrentTotalFees()));
		report.append(String.format("Exceeded: %s\n", this.hasExceeded() ? "yes" : "no"));
		
		return report.toString();
	}
	
	public boolean equals(Object obj)
	{
		//two flights are equal if their flight codes are equal  (case-insensitive)
		return (obj instanceof Flight) && (((Flight)obj).getFlightCode().toUpperCase().equals(this.getFlightCode().toUpperCase()));
	}
	
	public int compareTo(Flight flight)
	{
		//comparisons is done w.r.t. the flight code (case-insensitive)
		return this.getFlightCode().toUpperCase().compareTo(flight.getFlightCode().toUpperCase());
	}


	/**
	 * @return the maxTotalBaggageVolume
	 */
	public float getMaxTotalBaggageVolume() {
		return maxTotalBaggageVolume;
	}

	/**
	 * @param maxTotalBaggageVolume the maxTotalBaggageVolume to set
	 */
	protected void setMaxTotalBaggageVolume(float maxTotalBaggageVolume) {
		this.maxTotalBaggageVolume = maxTotalBaggageVolume;
	}

	/**
	 * @return the maxTotalBaggageWeight
	 */
	public float getMaxTotalBaggageWeight() {
		return maxTotalBaggageWeight;
	}

	/**
	 * @param maxTotalBaggageWeight the maxTotalBaggageWeight to set
	 */
	protected void setMaxTotalBaggageWeight(float maxTotalBaggageWeight) {
		this.maxTotalBaggageWeight = maxTotalBaggageWeight;
	}
	
	public boolean hasExceeded()
	{
		//check whether any of the limits (passengers/weight/volume) is exceeding
		return  (this.getCurrentTotalBaggageVolume() > this.getMaxTotalBaggageVolume()) ||
				(this.getCurrentTotalBaggageWeight() > this.getMaxTotalBaggageWeight())||
				(this.getCurrentTotalPassengers() > this.getPassengerCapacity());
	}
}
