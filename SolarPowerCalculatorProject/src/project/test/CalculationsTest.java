package project.test;

import java.text.DecimalFormat;

import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import project.server.Calculations;


public class CalculationsTest {
	
	private Calculations test;
	private String panelNumber = "REC230PE";
	private int numPanels = 16;
	private String suburb = "Brisbane";
	private String inverterNumber = "SB1600TL";
	private String energyCompany = "Origin";
	private double dailyUsage = 10.0;
	private int tilt = 20;
	private int orientation = 0;
	public static final String decFormat = "#.##";
	DecimalFormat decForm = new DecimalFormat(decFormat);
	
	@Before
	public void setup() {
		//helper.setUp();
		test = new Calculations(panelNumber, numPanels, suburb, inverterNumber,
				energyCompany, dailyUsage, tilt, orientation, "test");
	}
	
	@After
	public void teardown() {
		//helper.tearDown();
		test = null;
	}
		
	// Test to ensure constructor working as expected
	@Test
	public void testCalculatorConstructor() {
		assertTrue(test.getPanelNumber() == "REC230PE");
		assertTrue(test.getNumberPanels() == 16);
		assertTrue(test.getInverterNumber() == "SB1600TL");
		assertTrue(test.getEnergyCompany() == "Origin");
		assertTrue(test.getSystemPower() == 1.7);
		assertTrue(test.getTariff11() == 0.253781);
		assertTrue(test.getSolarExposure() == 5.3);
		assertTrue(test.getLatitude() == 27);
		assertTrue(test.getInverterEfficiency() == 0.96);
		assertTrue(test.getReplacementGeneration() == 10.0);
		assertTrue(test.getTilt() == 20);
		assertTrue(test.getOrientation() == 0);
		System.out.print(test.getPowerGenerated(1));
		System.out.print(test.getPowerGenerated(10));
		System.out.print(test.getDailySavings(1));
	}

	@Test
	public void testGetDailyOutputYearOne() {
		assertTrue(decForm.format(test.getPowerGenerated(1)).equals("8.65"));
	}
	
	@Test
	public void testGetDailyOutputYearTen() {
		assertTrue(decForm.format(test.getPowerGenerated(10)).equals("8.1"));
	}
	
	@Test
	public void testGetDailySavingsYearOne(){
		assertTrue(decForm.format(test.getDailySavings(1)).equals("2.2"));
	}

	@Test
	public void testGetDailySavingsYearTen(){
		assertTrue(decForm.format(test.getDailySavings(10)).equals("2.06"));
	}
	// Test lower boundary limit on orientation at 100% efficiency (negative)

	// Test upper boundary limit on orientation at 100% efficiency (positive)

	// Test borderline lower limit on orientation at 100% efficiency (negative)

	// Test borderline upper limit on orientation at 100% efficiency (positive)

	// Test orientation at case 0 (exactly middle)

	// Test case outside lower boundary limit at 100% efficiency (outsize maximum negative orientation)

	// Test case outside upper boundary limit at 100% efficiency (outsize maximum positive orientation)
}
