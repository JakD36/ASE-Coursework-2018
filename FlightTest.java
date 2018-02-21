import static org.junit.jupiter.api.Assertions.*;

	 import java.util.ArrayList;
	 import java.util.List;
	
	 import org.junit.jupiter.api.BeforeEach;
	 import org.junit.jupiter.api.Test;
	
	 class FlightTest {
	  List<Flight> flights = new ArrayList<Flight>();
	
	  @BeforeEach
	  void setUp() throws Exception {
		   //set up flights for test
		   //set 4 flights with 4 passengers each
		   flights.add(new Flight("AZ1234","Rome","Alitalia",100,12,22,2.5f));
		   flights.add(new Flight("FR1234","London Stansted","Ryanair",3,10,10,5));
		   flights.add(new Flight("U21234","Edinburgh","Easyjet",100,13,12,1.25f));
		   flights.add(new Flight("U24321","Milan Malpensa","Easyjet",100, 1300,1200,1.25f));
		   flights.add(new Flight("AZ1234","Paris","Air France",1000,102,202,20.5f));
		   flights.add(new Flight("BB2234","Paris","Air France",1000,102,202,20.5f));
		  
		   for (Flight f : flights)
		   {
			    f.addPassengerAndBaggage(9f, 9f,2.5f);
			    f.addPassengerAndBaggage(5, 15,5);
			    f.addPassengerAndBaggage(50, 2,1.25f);
			    f.addPassengerAndBaggage(15, 14,1.25f);
		   } 
	  }
	  
	  @Test
	  /**
	   * Tests that two Flights with the same code but different
	   * data are evaluated as equal
	   */
	  public void testEquals1() {
		  assertTrue(flights.get(4).equals(flights.get(0)));
	  }
	  
	  @Test
	  /**
	   * Tests that two Flights with the same details but
	   * different flights codes are evaluated as false
	   */
	  public void testEquals2() {
		  assertFalse(flights.get(4).equals(flights.get(5)));
	  }	  
	  
	  @Test
	  /**
	   * Test flight code is correctly assigned
	   */
	  public void testConstructor1() {
		  assertEquals(flights.get(0).getFlightCode(), "AZ1234");
	  }
	  
	  @Test
	  /**
	   * Test destination is correctly assigned
	   */
	  public void testConstructor2() {
		  assertEquals(flights.get(1).getDestination(), "London Stansted");
	  }
	  
	  @Test
	  /**
	   * Test that the airline is correctly assigned
	   */
	  public void testConstructor3() {
		  assertEquals(flights.get(2).getCarrier(), "Easyjet");
	  }
	  
	  @Test
	  /**
	   * Test that the passenger capacity is correctly assigned
	   */
	  public void testConstructor4() {
		  assertEquals(flights.get(3).getPassengerCapacity(), 100);
	  }
	  
	  @Test
	  /**
	   * Test that the passenger maxVol is correctly assigned
	   */
	  public void testConstructor5() {
		  assertEquals(flights.get(3).getMaxBaggageVolume(), 1300);
	  }
	  
	  @Test
	  /**
	   * Test that the passenger maxWeight is correctly assigned
	   */
	  public void testConstructor6() {
		  assertEquals(flights.get(3).getMaxBaggageWeight(), 1200);
	  }
	
	  @Test
	  /**
	   * Test that the passenger feeMultiplier is correctly assigned
	   */
	  public void testConstructor7() {
		  assertEquals(flights.get(3).getFeeMultiplier(), 1.25f);
	  }
	 
	 @Test
	  /**
	  * Test that the report generated is of the expected format,
	  * defined by the regular expression found below.
	  */
	  public void generateReportsTest1() {
	  
	  for (Flight f : flights) {
		    String regex = "Flight code: [a-zA-Z0-9]{2}[0-9]{4}\nNumber of Passengers: [0-9]+\nTotal Baggage Weight: [0-9]+\\.[0-9]+\nTotal Baggage Volume: [0-9]+\\.[0-9]+\nTotal Excess Fees: [0-9]+\\.[0-9]+\nExceeded: (yes|no)";
		    String rep = f.generateReport().trim();
		    assertTrue(rep.matches(regex));
		   }
	  }
	 
	 
	  @Test
	  /**
	   * Tests that the generate report does return a String object,
	   * and that it doesn't return nothing.
	   */
	  public void generateReportsTest2() {
	  
		   for (Flight f : flights) {
			   assertFalse(f.generateReport() == null);
		   }
	  }
	 
	 
	  @Test
	  /**
	   * Test to make sure that the generate report doesn't
	   * just return an empty string.
	   */
	  public void generateReportsTest3() {
		   for (Flight f : flights)
		   {
			   assertFalse(f.generateReport().equals(""));
		   }
	  }
	  @Test
	  /**
	   * Test that number of passengers in report is correct
	   */
	  public void generateReportsTest4() {
		  //get the report string
		  String report = flights.get(0).generateReport();
		  
		  //split the string around "Passengers: "
		  String[] parts = report.split("Passengers: ");
		  
		  //get the content after "Passengers: " up to the end of the line
		  String passengerString = parts[1].substring(0, parts[1].indexOf("\n"));
		   
		  //check that the extracted value is correct
		  assertTrue(passengerString.equals("4"));
	  }
	  
	  @Test
	  /**
	   * Test that Total Baggage Weight in report is correct
	   */
	  public void generateReportsTest5() {
		  //get the report string
		  String report = flights.get(0).generateReport();
		  
		  //split the string around "Total Baggage Weight: "
		  String[] parts = report.split("Total Baggage Weight: ");
		  
		  //get the content after "Total Baggage Weight: " up to the end of the line
		  String baggageWeightString = parts[1].substring(0, parts[1].indexOf("\n"));
		  
		  //check that the extracted value is correct
		  assertTrue(baggageWeightString.equals("40.00"));
	  }
	  
	  @Test
	  /**
	   * Test that Total Baggage Volume in report is correct
	   */
	  public void generateReportsTest6() {
		  //get the report string
		  String report = flights.get(0).generateReport();
		  
		  //split the string around "Total Baggage Volume: "
		  String[] parts = report.split("Total Baggage Volume: ");
		  
		  //get the content after "Total Baggage Volume: " up to the end of the line
		  String baggageVolString = parts[1].substring(0, parts[1].indexOf("\n"));
		  
		  //check that the extracted value is correct
		  assertTrue(baggageVolString.equals("79.00"));
	  }
	  
	  @Test
	  /**
	   * Test that excess fees in report are correct
	   */
	  public void generateReportsTest7() {
		  //get the report string
		  String report = flights.get(0).generateReport();
		  
		  //split the string around "Total Excess Fees: "
		  String[] parts = report.split("Total Excess Fees: ");
		  
		  //get the content after "Total Excess Fees: " up to the end of the line
		  String excessString = parts[1].substring(0, parts[1].indexOf("\n"));
		  
		  //check that the extracted value is correct
		  assertTrue(excessString.equals("10.00"));
	  }
	  
	  @Test
	  /**
	   * Test that exceeded status in report are correct (flight is over)
	   */
	  public void generateReportsTest8() {
		  //get the report string
		  String report = flights.get(0).generateReport();
		  
		  //split the string around "Exceeded: "
		  String[] parts = report.split("Exceeded: ");
		  
		  //get the content after "Exceeded: " up to the end of the line
		  String exceededString = parts[1].substring(0, parts[1].indexOf("\n"));
		  
		  //check that the extracted value is correct
		  assertTrue(exceededString.equals("yes"));
	  }
	  
	  @Test
	  /**
	   * Test that exceeded status in report are correct (flight is not over)
	   */
	  public void generateReportsTest9() {
		  //get the report string
		  String report = flights.get(3).generateReport();
		  
		  //split the string around "Exceeded: "
		  String[] parts = report.split("Exceeded: ");
		  
		  //get the content after "Exceeded: " up to the end of the line
		  String exceededString = parts[1].substring(0, parts[1].indexOf("\n"));
		  
		  //check that the extracted value is correct
		  assertTrue(exceededString.equals("no"));
	  }
 }