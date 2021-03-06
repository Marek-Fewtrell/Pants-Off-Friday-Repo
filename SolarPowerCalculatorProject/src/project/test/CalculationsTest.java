package project.test;

import java.text.DecimalFormat;

import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import project.server.Calculations;
import project.shared.CalcException;


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
	public void setup() throws CalcException {
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
		assertTrue(decForm.format(test.getDailySavings(10)).equals("3.6"));
	}
	
	@Test
	public void testGetCumulativeSavings(){
		double[] savings = test.getCumulativeSavings(20);
		assertTrue(decForm.format(savings[0]).equals("801.76"));
		assertTrue(decForm.format(savings[1]).equals("1664.23"));
		assertTrue(decForm.format(savings[2]).equals("2586.47"));
		assertTrue(decForm.format(savings[3]).equals("3567.55"));
		assertTrue(decForm.format(savings[4]).equals("4606.53"));
		assertTrue(decForm.format(savings[5]).equals("5702.47"));
		
	}
	
	@Test 
	public void testLessPanels() throws CalcException{
		Calculations test2 = new Calculations(panelNumber, 4, suburb, inverterNumber,
				energyCompany, dailyUsage, tilt, orientation, "test");
		assertTrue(test2.getSystemPower() == 0.7);
		assertTrue(decForm.format(test2.getPowerGenerated(1)).equals("3.56"));
	}
	
	@Test 
	public void noPanels() throws CalcException{
		Calculations test2 = new Calculations(panelNumber, 0, suburb, inverterNumber,
				energyCompany, dailyUsage, tilt, orientation, "test");
		assertTrue(test2.getSystemPower() == 0);
		assertTrue(test2.getPowerGenerated(1) == 0);
	}
	
	@Test (expected = CalcException.class)
	public void negativePanels() throws CalcException{
		Calculations test2 = new Calculations(panelNumber, -5, suburb, inverterNumber,
				energyCompany, dailyUsage, tilt, orientation, "test");
	}
	
	@Test
	public void lessDailyUsage() throws CalcException{
		Calculations test2 = new Calculations(panelNumber, numPanels, suburb, inverterNumber,
				energyCompany, 2.0, tilt, orientation, "test");
		assertTrue(test2.getSystemPower() == 1.7);
		assertTrue(decForm.format(test2.getPowerGenerated(1)).equals("8.65"));
		assertTrue(decForm.format(test2.getDailySavings(1)).equals("1.04"));
	}
	
	@Test
	public void noDailyUsage() throws CalcException{
		Calculations test2 = new Calculations(panelNumber, numPanels, suburb, inverterNumber,
				energyCompany, 0.0, tilt, orientation, "test");
		assertTrue(test2.getSystemPower() == 1.7);
		assertTrue(decForm.format(test2.getPowerGenerated(1)).equals("8.65"));
		assertTrue(decForm.format(test2.getDailySavings(1)).equals("0.69"));
	}
	
	@Test (expected = CalcException.class)
	public void negativeDailyUsage() throws CalcException{
		Calculations test2 = new Calculations(panelNumber, numPanels, suburb, inverterNumber,
				energyCompany,-5.1, tilt, orientation, "test");
	}
	// Test orientation at -10
	@Test
	public void testOrientationAtNegTen() throws CalcException {
		Calculations test2 = new Calculations(panelNumber, numPanels, suburb, inverterNumber,
			energyCompany, dailyUsage, tilt, -10, "test");
		assertTrue((test2.getOrientationEfficiencyLoss()) == 0);
	}
	
	// Test orientation at -11
	@Test
	public void testOrientationAtNegEleven() throws CalcException {
		Calculations test2 = new Calculations(panelNumber, numPanels, suburb, inverterNumber,
			energyCompany, dailyUsage, tilt, -11, "test");
		assertTrue((test2.getOrientationEfficiencyLoss()) == 0.1);
	}

	// Test orientation at -60		
	@Test
	public void testOrientationAtNegSixty() throws CalcException {
		Calculations test2 = new Calculations(panelNumber, numPanels, suburb, inverterNumber,
			energyCompany, dailyUsage, tilt, -60, "test");
		assertTrue((test2.getOrientationEfficiencyLoss()) == 0.1);
	}	
	
	// Test orientation at -61		
	@Test
	public void testOrientationAtNegSixtyOne() throws CalcException {
		Calculations test2 = new Calculations(panelNumber, numPanels, suburb, inverterNumber,
			energyCompany, dailyUsage, tilt, -61, "test");
		assertTrue((test2.getOrientationEfficiencyLoss()) == 0.2);
	}
	
	// Test orientation at +10
	@Test
	public void testOrientationAtPosTen() throws CalcException {
		Calculations test2 = new Calculations(panelNumber, numPanels, suburb, inverterNumber,
			energyCompany, dailyUsage, tilt, 10, "test");
		assertTrue((test2.getOrientationEfficiencyLoss()) == 0);
	}
	
	// Test orientation at +11
	@Test
	public void testOrientationAtPosEleven() throws CalcException {
		Calculations test2 = new Calculations(panelNumber, numPanels, suburb, inverterNumber,
			energyCompany, dailyUsage, tilt, 11, "test");
		assertTrue((test2.getOrientationEfficiencyLoss()) == 0.1);
	}

	// Test orientation at +60		
	@Test
	public void testOrientationAtPosSixty() throws CalcException {
		Calculations test2 = new Calculations(panelNumber, numPanels, suburb, inverterNumber,
			energyCompany, dailyUsage, tilt, 60, "test");
		assertTrue((test2.getOrientationEfficiencyLoss()) == 0.1);
	}	
	
	// Test orientation at +61		
	@Test
	public void testOrientationAtPosSixtyOne() throws CalcException {
		Calculations test2 = new Calculations(panelNumber, numPanels, suburb, inverterNumber,
			energyCompany, dailyUsage, tilt, 61, "test");
		assertTrue((test2.getOrientationEfficiencyLoss()) == 0.2);
	}
			
	// Test to throw exception at -500
	@Test (expected = CalcException.class)
	public void testOrientationAtNegFiveHundred() throws CalcException {
		Calculations test2 = new Calculations(panelNumber, numPanels, suburb, inverterNumber,
				energyCompany, dailyUsage, tilt, -500, "test");
	}
	
	// Test to throw exception at +500
	@Test (expected = CalcException.class)
	public void testOrientationAtPosFiveHundred() throws CalcException {
		Calculations test2 = new Calculations(panelNumber, numPanels, suburb, inverterNumber,
				energyCompany, dailyUsage, tilt, 500, "test");
	}
	
	//Test -300 == 60 
	@Test
	public void testOrientationAtNegThreeHundred() throws CalcException {
		Calculations test2 = new Calculations(panelNumber, numPanels, suburb, inverterNumber,
			energyCompany, dailyUsage, tilt, -300, "test");
		assertTrue((test2.getOrientation()) == 60);
	}
	
	
	//Test 300 == 60
	//should be -60 but everything is converted to positive values
	@Test
	public void testOrientationAtPosThreeHundred() throws CalcException {
		Calculations test2 = new Calculations(panelNumber, numPanels, suburb, inverterNumber,
			energyCompany, dailyUsage, tilt, 300, "test");
		assertTrue((test2.getOrientation()) == 60);
	}
	
	//
	//Test the flat areas of the graph
	//
	//idealTilt - 17, orientation 150 = 0.2 loss
	@Test
	public void testOrientationAtSixByOneFifty() throws CalcException{
		Calculations test2 = new Calculations(panelNumber, numPanels, suburb, inverterNumber,
				energyCompany, dailyUsage, 6, 150, "test");
		assertTrue((test2.getOrientationEfficiencyLoss()) == 0.2);
	}
	
	//idealTilt -16, orientation 150 = 0.3 loss
	@Test
	public void testOrientationAtSevenByOneFifty() throws CalcException{
		Calculations test2 = new Calculations(panelNumber, numPanels, suburb, inverterNumber,
				energyCompany, dailyUsage, 7, 150, "test");
		assertTrue((test2.getOrientationEfficiencyLoss()) == 0.3);
	}
	
	//idealTilt -5 orientation 150 = 0.3 loss
	@Test
	public void testOrientationAtNineteenByOneFifty() throws CalcException{
		Calculations test2 = new Calculations(panelNumber, numPanels, suburb, inverterNumber,
				energyCompany, dailyUsage, 19, 150, "test");
		assertTrue((test2.getOrientationEfficiencyLoss()) == 0.3);
	}
	//idealTilt -4 orientation 150 = 0.4 loss
	@Test
	public void testOrientationAtTwentyByOneFifty() throws CalcException{
		Calculations test2 = new Calculations(panelNumber, numPanels, suburb, inverterNumber,
				energyCompany, dailyUsage, 20, 150, "test");
		assertTrue((test2.getOrientationEfficiencyLoss()) == 0.4);
	}
	
	//idealTilt + 8 orientation 170 = 0.4 loss
	@Test
	public void testOrientationAtThirtyTwoByOneSeventy() throws CalcException{
		Calculations test2 = new Calculations(panelNumber, numPanels, suburb, inverterNumber,
				energyCompany, dailyUsage, 32, 170, "test");
		assertTrue((test2.getOrientationEfficiencyLoss()) == 0.4);
	}
	
	//idealTilt + 9 orientation 170 = 0.5 loss
	@Test
	public void testOrientationAtThirtyThreeByOneSeventy() throws CalcException{
		Calculations test2 = new Calculations(panelNumber, numPanels, suburb, inverterNumber,
				energyCompany, dailyUsage, 33, 170, "test");
		assertTrue((test2.getOrientationEfficiencyLoss()) == 0.5);
	}
	
	//idealTilt + 21 orientation 170 = 0.5 loss
	//45
	@Test
	public void testOrientationAtFortyFiveByOneSeventy() throws CalcException{
		Calculations test2 = new Calculations(panelNumber, numPanels, suburb, inverterNumber,
				energyCompany, dailyUsage, 45, 170, "test");
		assertTrue((test2.getOrientationEfficiencyLoss()) == 0.5);
	}
	//idealTilt + 22 orientation 170 = 0.6 loss
	//46
	@Test
	public void testOrientationAtFortySixByOneSeventy() throws CalcException{
		Calculations test2 = new Calculations(panelNumber, numPanels, suburb, inverterNumber,
				energyCompany, dailyUsage, 46, 170, "test");
		assertTrue((test2.getOrientationEfficiencyLoss()) == 0.6);
	}
	
	//ideal tilt + 34 orientation 175 = 0.6 loss
	//58
	@Test
	public void testOrientationAtFiftyEightByOneSeventyFive() throws CalcException{
		Calculations test2 = new Calculations(panelNumber, numPanels, suburb, inverterNumber,
				energyCompany, dailyUsage, 58, 175, "test");
		assertTrue((test2.getOrientationEfficiencyLoss()) == 0.6);
	}
	//ideal tilt + 35 orientation 175 = 0.7 loss
	//59
	@Test
	public void testOrientationAtFiftyNineByOneSeventyFive() throws CalcException{
		Calculations test2 = new Calculations(panelNumber, numPanels, suburb, inverterNumber,
				energyCompany, dailyUsage, 59, 175, "test");
		assertTrue((test2.getOrientationEfficiencyLoss()) == 0.7);
	}
	
	//
	//Test the curves
	//
	//tilt = 41 orientation = 80 loss = 0.2
	@Test
	public void testOrientationAtFortyOneByEighty() throws CalcException{
		Calculations test2 = new Calculations(panelNumber, numPanels, suburb, inverterNumber,
				energyCompany, dailyUsage, 41, 80, "test");
		assertTrue((test2.getOrientationEfficiencyLoss()) == 0.2);
	}
	
	//orientation = 80 tilt = 42 loss = 0.3
	@Test
	public void testOrientationAtFortyTwoByEighty() throws CalcException{
		Calculations test2 = new Calculations(panelNumber, numPanels, suburb, inverterNumber,
				energyCompany, dailyUsage, 42, 80, "test");
		assertTrue((test2.getOrientationEfficiencyLoss()) == 0.3);
	}
	
	//orientation = 80 tilt 56 loss = 0.3
	@Test
	public void testOrientationAtFiftySixByEighty() throws CalcException{
		Calculations test2 = new Calculations(panelNumber, numPanels, suburb, inverterNumber,
				energyCompany, dailyUsage, 56, 80, "test");
		assertTrue((test2.getOrientationEfficiencyLoss()) == 0.3);
	}
	
	//orientation = 80 tilt = 57 loss = 0.4
	@Test
	public void testOrientationAtFiftySevenByEighty() throws CalcException{
		Calculations test2 = new Calculations(panelNumber, numPanels, suburb, inverterNumber,
				energyCompany, dailyUsage, 57, 80, "test");
		assertTrue((test2.getOrientationEfficiencyLoss()) == 0.4);
	}
	
	//orientation = 80 tilt = 70 loss 0.4
	@Test
	public void testOrientationAtSeventyByEighty() throws CalcException{
		Calculations test2 = new Calculations(panelNumber, numPanels, suburb, inverterNumber,
				energyCompany, dailyUsage, 70, 80, "test");
		assertTrue((test2.getOrientationEfficiencyLoss()) == 0.4);
	}
	
	//orientation = 80 tilt = 71 loss 0.5
	@Test
	public void testOrientationAtSeventyOneByEighty() throws CalcException{
		Calculations test2 = new Calculations(panelNumber, numPanels, suburb, inverterNumber,
				energyCompany, dailyUsage, 71, 80, "test");
		assertTrue((test2.getOrientationEfficiencyLoss()) == 0.5);
	}
	
	//orientation = 80 tilt = 82 loss = 0.5
	@Test
	public void testOrientationAtEightyTwoByEighty() throws CalcException{
		Calculations test2 = new Calculations(panelNumber, numPanels, suburb, inverterNumber,
				energyCompany, dailyUsage, 82, 80, "test");
		assertTrue((test2.getOrientationEfficiencyLoss()) == 0.5);
	}
	//orientation = 80 tilt = 83 loss = 0.6
	@Test
	public void testOrientationAtEightyThreeByEighty() throws CalcException{
		Calculations test2 = new Calculations(panelNumber, numPanels, suburb, inverterNumber,
				energyCompany, dailyUsage, 83, 80, "test");
		assertTrue((test2.getOrientationEfficiencyLoss()) == 0.6);
	}
	
	//Break even tests
	@Test (expected = CalcException.class)
	public void testBreakEvenNegativeInitialInvestment() throws CalcException{
		test.getBreakEven(-500, 5);
	}
	
	@Test (expected = CalcException.class)
	public void testBreakEvenNegativeInterest() throws CalcException{
		test.getBreakEven(500, -0.5);
	}
	
	//Break even = 0 years
	@Test
	public void testBreakEvenZeroYears() throws CalcException{
		assertTrue(test.getBreakEven(0, 0) == 0);
	}
	
	@Test
	public void testBreakEvenJustOverZeroYears() throws CalcException{
		assertTrue(decForm.format(test.getBreakEven(8, 0)).equals("0.01"));
	}
	
	//Break even = 0.5
	@Test
	public void testBreakHalfYear() throws CalcException{
		assertTrue(decForm.format(test.getBreakEven(401, 0)).equals("0.5"));
	}
	//Break even = 0.99
	@Test
	public void testBreakAlmostOneYear() throws CalcException{
		assertTrue(decForm.format(test.getBreakEven(797, 0)).equals("0.99"));
	}
	
	//Break even = 1 year
	@Test
	public void testBreakEvenOneYear() throws CalcException{
		assertTrue(test.getBreakEven(802, 0) == 1);
	}
	
	//Break even = 1.01 
	@Test
	public void testBreakJustOverOneYear() throws CalcException{
		assertTrue(decForm.format(test.getBreakEven(811, 0)).equals("1.01"));
	}
	
	//Break even = 1.5 years
	@Test public void testBreakEvenOneAndHalfYears() throws CalcException{
		assertTrue(decForm.format(test.getBreakEven(1232, 0)).equals("1.5"));
	}
	
	//Break even = 1.99
	@Test
	public void testBreakAlmostTwoYears() throws CalcException{
		assertTrue(decForm.format(test.getBreakEven(1660, 0)).equals("1.99"));
	}
	
	//Break even = 2 years
	@Test
	public void testBreakEvenTwoYears() throws CalcException{
		assertTrue(test.getBreakEven(1665, 0) == 2);
	}
	
	//Break even = 2.01
	@Test
	public void testBreakEvenJustOverTwoYears() throws CalcException{
		assertTrue(decForm.format(test.getBreakEven(1673, 0)).equals("2.01"));
	}

	//Break even = 2.5
	@Test
	public void testBreakEvenTwoAndHalfYears() throws CalcException{
		assertTrue(decForm.format(test.getBreakEven(2125, 0)).equals("2.5"));
	}
	
	@Test
	public void testNeverBreaksEven() throws CalcException{
		assertTrue(test.getBreakEven(10000, 10) == -5);
	}
	
	@Test
	public void testBreaksEvenThenFallsBelowInvestment() throws CalcException{
		assertTrue(test.getBreakEven(400, 25) == -5);
	}
/*"801.76"));
assertTrue(decForm.format(savings[1]).equals("1597.91"));
assertTrue(decForm.format(savings[2]).equals("2388.45"));
assertTrue(decForm.format(savings[3]).equals("3173.37"));
assertTrue(decForm.format(savings[4]).equals("3952.69"));
assertTrue(decForm.format(savings[5]).equals("4726.39"));*/
	
	//-11 = 0.1 loss, -10 = 0 loss, -60 = 0.1 loss, -61 = 0.2 loss
	//if orientation is -500 should throw calcExeption
	//if orientation value is -300 should change the orientation value to +60 (statement in constructor) 
	//Tests for exception handling??	
	
	// Test lower boundary limit on orientation at 100% efficiency (negative)

	// Test upper boundary limit on orientation at 100% efficiency (positive)

	// Test borderline lower limit on orientation at 100% efficiency (negative)

	// Test borderline upper limit on orientation at 100% efficiency (positive)

	// Test orientation at case 0 (exactly middle)

	// Test case outside lower boundary limit at 100% efficiency (outsize maximum negative orientation)

	// Test case outside upper boundary limit at 100% efficiency (outsize maximum positive orientation)
}