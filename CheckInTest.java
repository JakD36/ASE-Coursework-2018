import static org.junit.Assert.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import org.mockito.Mockito;

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

	@Test
	public void constructorTest() {
		assertEquals(1,1);
	}
	
}