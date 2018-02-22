package ase1.tests;

import org.junit.*;

import ase1.CheckInHandler;
import ase1.IllegalReferenceCodeException;

import static org.junit.Assert.*;


public class CheckInTest {
	
	public CheckInHandler desk;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	/**
	 * Instantiate the CheckInHandler object desk.
	 * To be used through all the tests for this class.
	 */
	public void setUp() throws Exception {
		desk = new CheckInHandler(); 
	}

	@Test
	/**
	 * Check negative weight does not corrupt fee calculation.
	 * It is expected that the weight should be corrected to be a positive value and therefore return a fee above or equal to 0.
	 */
	public void negativeWeight() {
		float[] dimensions = {0.4f,1,1};
		float weight = -10.4f;
		float fees = desk.processPassenger("dav0001",dimensions,weight);
		assertTrue(fees>=0);
	}
	
	@Test
	/**
	 * Check negative volume does not corrupt fee calculation.
	 * It is expected that the volume should be corrected to positive value and therefore return a fee above or equal to 0.
	 */
	public void negativeVol() {
		float[] dimensions = {-10.4f,1,1};
		float weight = 0.4f;
		float fees = desk.processPassenger("dav0001",dimensions,weight);
		assertTrue(fees>=0);
	}
	
	@Test
	/**
	 * The passenger should not be charged if the luggage is within the allowance, and the airline 
	 * should not owe the passenger money for being under allowance.
	 * dav0001 is on KLM1234 who have a max of 10 kg and 10 m3 each.
	 * with the factor of safety of 80% and the passenger maxes being 
	 * calculated by dividing flight max values by pasenger capacity.
	 * The allowance per passenger on KLM1234 is 0.4 kg and 0.4 m3.
	 */
	public void negativeFeesTest() {
		float[] dimensions = {0.3f,1,1};
		float weight = 0.3f;
		float fees = desk.processPassenger("dav0001",dimensions,weight);
		assertFalse("The fees have dropped below zero in test 1",fees<0);
	}
	
	@Test
	/**
	 * A passenger should not be charged for using their exact allowance.
	 * dav0001 is on KLM1234 who have a max of 10 kg and 10 m3 each.
	 * with the factor of safety of 80% and the passenger maxes being 
	 * calculated by dividing flight max values by pasenger capacity.
	 * The allowance per passenger on KLM1234 is 0.4 kg and 0.4 m3. 
	 * Therefore providing these as inputs the fee is expected to be 0.
	 */
	public void NoFeesTest() {
		float[] dimensions = {0.4f,1,1};
		float weight = 0.4f;
		float fees = desk.processPassenger("dav0001",dimensions,(weight/20f)*0.8f);
		assertTrue(fees == 0);
	}
	
	@Test
	/**
	 * Test to make sure that passengers are fined the correct amount.
	 * Test provides 1 kg over allowance and 1 m3 over allowance for simplicity.
	 * Fee is defined as sum of excess times by the multiplier which for klm1234 is 2.
	 * Therefore the resulting fee should be 4.
	 * 
	 * dav0001 is on KLM1234 who have a max of 10 kg and 10 m3 each.
	 * with the factor of safety of 80% and the passenger maxes being 
	 * calculated by dividing flight max values by pasenger capacity.
	 * The allowance per passenger on KLM1234 is 0.4 kg and 0.4 m3.
	 */
	public void CheckFeeCalcTest() {
		float[] dimensions = {1.4f,1,1};
		float weight = 1.4f;
		float fees = desk.processPassenger("dav0001",dimensions,weight);
		assertTrue(fees == 4);
	}

	@Test
	/**
	 * Test to make sure that passengers are fined the correct amount.
	 * Test provides 0.5 kg over allowance and 0.5 m3 over allowance for simplicity.
	 * Fee is defined as sum of excess times by the multiplier which for klm1234 is 2.
	 * Therefore the resulting fee should be 2.
	 * 
	 * dav0001 is on KLM1234 who have a max of 10 kg and 10 m3 each.
	 * with the factor of safety of 80% and the passenger maxes being 
	 * calculated by dividing flight max values by pasenger capacity.
	 * The allowance per passenger on KLM1234 is 0.4 kg and 0.4 m3.
	 */
	public void CheckFeeCalcTest2() {
		float[] dimensions = {0.9f,1,1};
		float weight = 0.9f;
		float fees = desk.processPassenger("dav0001",dimensions,weight);
		System.out.println("Expected Fees are 2, we got "+fees);
		assertTrue(fees == 2);
	}

	@Test
	/**
	 * Make sure passengers are actually deducted from the to be checked in list, 
	 * when they are processed.
	 * Tested by checking the number of passengers to be checked in before they are processed and after.
	 * The difference should be 1.
	 */
	public void passengerCheckedInTest() {
		float[] dimensions = {0.9f,1f,1f};
		float weight = 0.9f;
		int initialCount = desk.getNumToCheckIn();
		
		desk.processPassenger("dav0001",dimensions,weight);
		
		int afterCount = desk.getNumToCheckIn();
		
		assertTrue( (initialCount-afterCount) == 1);
	}
	
	@Test(expected = IllegalReferenceCodeException.class)
	/**
	 * Test to see if passenger can be checked in twice and deducted twice.
	 * It is attempted to check in the passenger twice, however the method should not 
	 * allow this, and the difference in passengers left to be checked in before and after 
	 * should only be 1.
	 */
	public void passengerCheckedInTest2() {
		float[] dimensions = {0.9f,1f,1f};
		float weight = 0.9f;
		int initialCount = desk.getNumToCheckIn();
		
		desk.processPassenger("dav0001",dimensions,weight);
		desk.processPassenger("dav0001",dimensions,weight);
		
		int afterCount = desk.getNumToCheckIn();
		// Should only lower count by 1 as the same passenger shouldnt be able to be processed twice
		assertTrue( (initialCount-afterCount) == 1);
	}
	
	@Test
	/**
	 * Test to make sure an exception is thrown, when passenger is attempted to be checked in twice.
	 * Simply checks the exception caught contains the booking reference of the duplicated passenger.
	 * 
	 */
	public void passengerCheckedInTest3() {
		float[] dimensions = {0.9f,1f,1f};
		float weight = 0.9f;
		
		try {
			desk.processPassenger("dav0001",dimensions,weight);
			desk.processPassenger("dav0001",dimensions,weight);
		}
		catch(IllegalReferenceCodeException e) {
			assertTrue(e.getMessage().contains("dav0001"));
		}
	}

}