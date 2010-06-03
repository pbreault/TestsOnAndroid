package com.pyxis.androidmontreal.calculator;

import junit.framework.TestCase;

public class CalculatorTest extends TestCase{

	private Calculator calculator;


	@Override
	protected void setUp() throws Exception {
		calculator = new Calculator();
	}
	
	
	public void testAddition() throws Exception {
		assertEquals(2, calculator.add(1,1));
	}
	
	
	public void testMultiplication() throws Exception{
		assertEquals(8, calculator.multiply(2,4));
	}
	
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		// Nothing to teardown here
	}
	
}
