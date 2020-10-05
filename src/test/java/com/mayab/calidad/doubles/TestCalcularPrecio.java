package com.mayab.calidad.doubles;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.*;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import com.mayab.calidad.dao.DAOFake;

public class TestCalcularPrecio {
	private calcularIVAYucatan ivaYucatan;
	private CalcularPrecio calculadora;
	@Before
	public void setupMocks() {
		ivaYucatan = mock(calcularIVAYucatan.class);
		calculadora = new CalcularPrecio();
	}
	
	@Test
	public void testCalcular() {
		//when(ivaYucatan.getIVA(anyFloat(), anyFloat())).thenReturn(16f);
		when(ivaYucatan.getIVA(anyFloat(), anyFloat())).thenAnswer(new Answer<Float>() {
			public Float answer(InvocationOnMock invocation) throws Throwable{
				Float porcentaje = (Float) invocation.getArguments()[0];
				Float precioBase = (Float) invocation.getArguments()[1];
				return porcentaje * precioBase;
			}
		});
		float total = calculadora.Calcular(ivaYucatan);
		assertThat(total, is(116.0f));
	}

}
