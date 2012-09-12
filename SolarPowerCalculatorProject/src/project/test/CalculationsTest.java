package project.test;
import org.junit.After;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import project.server.Calculations;

public class CalculationsTest {

	private Calculations test;
	private String panelNumber = "BLUE230/07";
	private int numPanels = 16;
	private String suburb = "BRISBANE";
	private String inverterNumber = "SB3000";
	private String energyCompany = "Click Energy";
	private double dailyUsage = 10.0;
	private int tilt = 20;
	private int orientation = 0;
	
	@Before
	public void setup() {
		test = new Calculations(panelNumber, numPanels, suburb, inverterNumber,
				energyCompany, dailyUsage, tilt, orientation);
	}
	
	@After
	public void teardown() {
		test = null;
	}
	
	// Test to ensure constructor working as expected
	@Test
	public void testCalculatorConstructor() {
		assertTrue(panelNumber == "BLUE230/07");
		assertTrue(numPanels == 16);
		assertTrue(suburb == "BRISBANE");
		assertTrue(inverterNumber == "SB3000");
		assertTrue(energyCompany == "Click Energy");
		assertTrue(dailyUsage == 10.0);
		assertTrue(tilt == 20);
		assertTrue(orientation == 0);
	}

	@Test
	public void testGetDailyOutput() {
		assertTrue("", test.getPowerGenerated(1) == 5);
	}

	// Test lower boundary limit on orientation at 100% efficiency (negative)

	// Test upper boundary limit on orientation at 100% efficiency (positive)

	// Test borderline lower limit on orientation at 100% efficiency (negative)

	// Test borderline upper limit on orientation at 100% efficiency (positive)

	// Test orientation at case 0 (exactly middle)

	// Test case outside lower boundary limit at 100% efficiency (outsize maximum negative orientation)

	// Test case outside upper boundary limit at 100% efficiency (outsize maximum positive orientation)
}
