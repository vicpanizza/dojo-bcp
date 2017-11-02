package com.bcp.bancamovil.demo;

import org.junit.Test;
import junit.framework.Assert;

public class CalculadoraTest{
	

	@Test
	public void testSuma(){

		Calculadora calc = new Calculadora(); //arrange

		int resultado = calc.suma(4,3); //act

		Assert.assertEquals(resultado,7); //assertion

	}

}