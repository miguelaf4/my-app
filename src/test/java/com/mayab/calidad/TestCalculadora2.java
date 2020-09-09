package com.mayab.calidad;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestCalculadora2 {
	static Calculadora miCalculadora;
	
	@BeforeClass
	public static void setup() {
		//System.out.println("Before");
		miCalculadora = new Calculadora();
	}
	@Test
	public void SumaPositivosTest() {
		float sumando1 = 4;
		float sumando2 = 4;
		float expectedResult = 8;
		float resultado = -1;
		//end setup
		
		resultado = miCalculadora.suma(sumando1, sumando2);
		//end exercise
		
		assertEquals(expectedResult, resultado,0);
	}

	@Test
	public void RestaPositivosTest() {
		float num1 = 4;
		float num2 = 2;
		float expectedResult = 2;
		float resultado = -1;
				
		resultado = miCalculadora.resta(num1, num2);
		assertThat(resultado, is(expectedResult));
	}
	
	@Test
	public void DividirEntre0() {
		float num1 = 1;
		float num2 = 0;
		float expectedResult = Float.POSITIVE_INFINITY;
		float resultado = -1;
				
		resultado = miCalculadora.division(num1, num2);
		assertThat(resultado, is(expectedResult));
	}
	
	@Test
	public void Dividir0Entre0() {
		float num1 = 0;
		float num2 = 0;
		float expectedResult = Float.NaN;
		float resultado = -1;
				
		resultado = miCalculadora.division(num1, num2);
		assertThat(resultado, is(expectedResult));
	}
	/*Para que pase la prueba en caso de excepciones
	 * @Test(expected = NullPointerException.class)*/
	@Test
	public void DividendoMayorA0() {
		float num1 = 44;
		float num2 = 11;
		float expectedResult = 4;
		float resultado = -1;
				
		resultado = miCalculadora.division(num1, num2);
		assertThat(resultado, is(expectedResult));
	}
}
