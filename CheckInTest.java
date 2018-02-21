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

	@Test
	public void constructorTest() {
		assertEquals(1,1);
	}
	
}