package com.mayab.calidad;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class TestMultiplicacionParametrizada {
		@Parameters
		public static Iterable data() {
			return Arrays.asList(new Object[][] {
				{4,2,2},{6,3,2},{5,5,1},{10,5,2}
			});
		}
		
	private int multiplierOne;
	private int expected;
	private int multiplierTwo;
	
	public TestMultiplicacionParametrizada(int expected, int multiplierOne, int multiplierTwo) {
		this.multiplierOne = multiplierOne;
		this.multiplierTwo = multiplierTwo;
		this.expected = expected;
	}

	@Test
	public void givenTwo() {
		assertThat(expected, is(multiplierOne*multiplierTwo));
	}
}
