
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
	private int passengerCapacity; 
	
	public Flight(String flightCode, String destination, String carrier,
			int passengerCapacity, float maxVol, float maxWeight,float feeMultiplier) {

		this.flightCode = flightCode;
		this.destination = destination;
		this.carrier = carrier;
		this.passengerCapacity = passengerCapacity;
		this.maxBaggageVolume = maxVol;
		this.maxBaggageWeight = maxWeight;
		this.feeMultplier = feeMultiplier;

		this.currentTotalPassengers = 0;
		this.currentTotalBaggageVolume = 0;
		this.currentTotalBaggageWeight = 0;
		this.currentTotalFees = 0;


	}
	
	public String getFlightCode() {
		return flightCode;
	}

	public String getDestination() {
		return destination;
	}

	public float getMaxBaggageVolume() {
		return maxBaggageVolume;
	}
	
	public float getMaxBaggageWeight() {
		return maxBaggageWeight;
	}

	public int getPassengerCapacity() {
		return passengerCapacity;
	}

	public String getCarrier() {
		return carrier;
	}
	
	public void addPassengerAndBaggage(float vol, float weight,float fee) {
		//update weight and vol with this passenger's data
		this.currentTotalBaggageWeight+=weight;
		this.currentTotalBaggageVolume+=vol;
		this.currentTotalFees+=fee;
		
		//add a passenger to the current count
		this.currentTotalPassengers++;
	}
	
	public String generateReport()
	{
		StringBuilder report = new StringBuilder();

		String excess = "no";
		if( ( this.currentTotalBaggageVolume > this.maxBaggageVolume ) || ( this.currentTotalBaggageWeight > this.maxBaggageWeight )|| ( this.currentTotalPassengers > this.passengerCapacity ) ){
			excess = "yes";
		}

		report.append(String.format("Flight code: %s\n",this.flightCode));
		report.append(String.format("Number of Passengers: %d\n", this.currentTotalPassengers));
		report.append(String.format("Total Baggage Weight: %.2f\n", this.currentTotalBaggageWeight));
		report.append(String.format("Total Baggage Volume: %.2f\n", this.maxBaggageVolume));
		report.append(String.format("Total Excess Fees: %.2f\n", this.currentTotalFees));
		report.append(String.format("Exceeded: %s\n",excess));
		// report.append(String.format("Exceeded: %s\n", this.hasExceeded() ? "yes" : "no"));

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
}
