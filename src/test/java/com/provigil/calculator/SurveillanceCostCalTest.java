package com.provigil.calculator;

import org.junit.Test;

import com.provigil.calculator.pojo.Subscriptions;

/**
 * Unit test for SurveillanceCostCal App.
 */
public class SurveillanceCostCalTest {

	/**
	 * Rigorous Test :-)
	 */
	
	@Test
	public void test() {
		Subscriptions sub = new Subscriptions();
		sub.setId(1);
		sub.setArea(3000);
		sub.setPlan("MONTHLY");
		sub.setLocation("Indoor");
	}
	
	@Test
	public void testSurveillanceCostCal() {
		Subscriptions sub = new Subscriptions(1, 2500, "YEARLY", "Indoor");
		SurveillanceCostCal.surveillanceCostCalculation(sub);
	}
	
	@Test
	public void testPlan() {
		Subscriptions sub = new Subscriptions(1, 2500, "Indoor", "Indoor");
		SurveillanceCostCal.surveillanceCostCalculation(sub);
	}
	
	@Test
	public void testLocation() {
		Subscriptions sub = new Subscriptions(1, 2500, "YEARLY", "MONTHLY");
		SurveillanceCostCal.surveillanceCostCalculation(sub);
	}
	
	
	
}
