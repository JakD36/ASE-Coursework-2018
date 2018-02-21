import org.junit.*;
import static org.junit.Assert.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class CheckInTest {
	
	public CheckInHandler desk;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	/**
	 * Instantiate the CheckInHandler object desk
	 */
	public void setUp() throws Exception {
		desk = new CheckInHandler(); 
	}

//	Check fees dont drop below zero
//	Check neg weight is added
//	check neg volume is added
//	check passenger is added
//	check passenger is removed from to be checked in map

	
	
	@Test
	/**
	 * 
	 */
	public void negativeWeight() {
		float[] dimensions = {0.4f,1,1};
		float weight = -10.4f;
		float fees = desk.processPassenger("dav0001",dimensions,weight);
		assertTrue(fees>=0);
	}
	
	@Test
	/**
	 * 
	 */
	public void negativeVol() {
		float[] dimensions = {-10.4f,1,1};
		float weight = 0.4f;
		float fees = desk.processPassenger("dav0001",dimensions,weight);
		assertTrue(fees>=0);
	}
	
	@Test
	/**
	 * 
	 */
	public void negativeFeesTest() {
		float[] dimensions = {0.3f,1,1};
		float weight = 0.3f;
		float fees = desk.processPassenger("dav0001",dimensions,weight);
		assertFalse("The fees have dropped below zero in test 1",fees<0);
	}
	
	@Test
	/**
	 * Fees are calculated as 80% of the max / passenger capacity
	 * Then summed together
	 * For flight KLM1234 10 m3 and 10 kg are maxs therefore 0.4 m3 and 0.4 kg are the max for each passenger, 
	 * so we should have 0 fee for these values 
	 */
	public void NoFeesTest() {
		float[] dimensions = {0.4f,1,1};
		float weight = 0.4f;
		float fees = desk.processPassenger("dav0001",dimensions,(weight/20f)*0.8f);
		assertTrue(fees == 0);
	}
	
	@Test
	/**
	 * Fees are calculated as 80% of the max / passenger capacity
	 * Then summed together
	 * For flight KLM1234 10 m3 and 10 kg are maxs therefore 0.4 m3 and 0.4 kg are the max for each passenger, 
	 * There is a fee multiplier of 2 for KLM1234, so the sum of the two excesses above the max * 2 gives us the fee
	 * So 1.4 kg and 1.4 m3 should give 1 excess each, giving a total fee of 4
	 */
	public void CheckFeeCalcTest() {
		float[] dimensions = {1.4f,1,1};
		float weight = 1.4f;
		float fees = desk.processPassenger("dav0001",dimensions,weight);
		assertTrue(fees == 4);
	}

	@Test
	/**
	 * Fees are calculated as 80% of the max / passenger capacity
	 * Then summed together
	 * For flight KLM1234 10 m3 and 10 kg are maxs therefore 0.4 m3 and 0.4 kg are the max for each passenger, 
	 * There is a fee multiplier of 2 for KLM1234, so the sum of the two excesses above the max * 2 gives us the fee
	 * So 0.9 kg and 0.9 m3 should give 0.5 excess each, giving a total fee of 1
	 * This test was added to look into if the floats are an issue for accuracy
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
	 * Make sure passengers are actually deducted from the to be checked in list, when they are processed
	 */
	public void passengerCheckedInTest() {
		float[] dimensions = {0.9f,1f,1f};
		float weight = 0.9f;
		int initialCount = desk.getNumToCheckIn();
		
		desk.processPassenger("dav0001",dimensions,weight);
		
		int afterCount = desk.getNumToCheckIn();
		
		assertTrue( (initialCount-afterCount) == 1);
	}
	
	@Test
	/**
	 * Try check in same passenger twice
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
	 * Try check in same passenger twice
	 */
	public void passengerCheckedInTest3() {
		float[] dimensions = {0.9f,1f,1f};
		float weight = 0.9f;
		int initialCount = desk.getNumToCheckIn();
		
		desk.processPassenger("dav0001",dimensions,weight);
		desk.processPassenger("dav0001",dimensions,weight);
		
		int afterCount = desk.getNumToCheckIn();
		
		// Should only lower count by 1 as the same passenger shouldnt be able to be processed twice
		assertTrue( (initialCount-afterCount) == 1);
	}
}