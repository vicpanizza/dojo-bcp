package com.bcp.bancamovil.demo;

import org.junit.Before;
import org.junit.Test;
import junit.framework.Assert;

public class CalculadoraTest{
	
	@Before
	public void init() {
		
	}

	@Test
	public void testSuma(){

		Calculadora calc = new Calculadora(); //arrange

		int resultado = calc.suma(4,3); //act

		Assert.assertEquals(resultado,7); //assertion

	}

}
