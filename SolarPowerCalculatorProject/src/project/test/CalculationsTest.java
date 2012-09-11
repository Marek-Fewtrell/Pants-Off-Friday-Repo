package project.test;

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
	public void setup(){
		test = new Calculations(panelNumber, numPanels, suburb, inverterNumber,
				energyCompany, dailyUsage, tilt, orientation);
	}
	
	@Test
	public void testGetDailyOutput() {
		assertTrue("", test.getPowerGenerated(1) == 5);
	}

}
