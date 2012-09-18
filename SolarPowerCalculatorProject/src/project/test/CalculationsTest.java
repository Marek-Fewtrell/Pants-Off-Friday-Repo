package project.test;

import java.text.DecimalFormat;

import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import project.client.CalcException;
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
	
	@Test
	public void testGetCumulativeSavings(){
		double[] savings = test.getCumulativeSavings(20);
		assertTrue(decForm.format(savings[0]).equals("801.76"));
		assertTrue(decForm.format(savings[1]).equals("1597.91"));
		assertTrue(decForm.format(savings[2]).equals("2388.45"));
		assertTrue(decForm.format(savings[3]).equals("3173.37"));
		assertTrue(decForm.format(savings[4]).equals("3952.69"));
		assertTrue(decForm.format(savings[5]).equals("4726.39"));
	}
	
	@Test 
	public void testLessPanels(){
		Calculations test2 = new Calculations(panelNumber, 4, suburb, inverterNumber,
				energyCompany, dailyUsage, tilt, orientation, "test");
		assertTrue(test2.getSystemPower() == 0.7);
		assertTrue(decForm.format(test2.getPowerGenerated(1)).equals("3.56"));
	}
	
	@Test 
	public void noPanels(){
		Calculations test2 = new Calculations(panelNumber, 0, suburb, inverterNumber,
				energyCompany, dailyUsage, tilt, orientation, "test");
		assertTrue(test2.getSystemPower() == 0);
		assertTrue(test2.getPowerGenerated(1) == 0);
	}
	
	@Test (expected = CalcException.class)
	public void negativePanels(){
		Calculations test2 = new Calculations(panelNumber, -5, suburb, inverterNumber,
				energyCompany, dailyUsage, tilt, orientation, "test");
	}
	
	@Test
	public void lessDailyUsage(){
		Calculations test2 = new Calculations(panelNumber, numPanels, suburb, inverterNumber,
				energyCompany, 2.0, tilt, orientation, "test");
		assertTrue(test2.getSystemPower() == 1.7);
		assertTrue(decForm.format(test2.getPowerGenerated(1)).equals("8.65"));
		assertTrue(decForm.format(test2.getDailySavings(1)).equals("1.04"));
	}
	
	@Test
	public void noDailyUsage(){
		Calculations test2 = new Calculations(panelNumber, numPanels, suburb, inverterNumber,
				energyCompany, 0.0, tilt, orientation, "test");
		assertTrue(test2.getSystemPower() == 1.7);
		assertTrue(decForm.format(test2.getPowerGenerated(1)).equals("8.65"));
		assertTrue(decForm.format(test2.getDailySavings(1)).equals("0.69"));
	}
	
	@Test (expected = CalcException.class)
	public void negativeDailyUsage(){
		Calculations test2 = new Calculations(panelNumber, numPanels, suburb, inverterNumber,
				energyCompany,-5.1, tilt, orientation, "test");
	}
	// Test lower boundary limit on orientation at 100% efficiency (negative)

	// Test upper boundary limit on orientation at 100% efficiency (positive)

	// Test borderline lower limit on orientation at 100% efficiency (negative)

	// Test borderline upper limit on orientation at 100% efficiency (positive)

	// Test orientation at case 0 (exactly middle)

	// Test case outside lower boundary limit at 100% efficiency (outsize maximum negative orientation)

	// Test case outside upper boundary limit at 100% efficiency (outsize maximum positive orientation)
}