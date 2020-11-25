package com.mayab.calidad.doubles;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.*;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import static org.hamcrest.Matchers.is;

public class testDeoendency {

	private Dependency dependency;
	
	@Before
	public void setupMocks() {
		dependency = mock(Dependency.class);
		
		//dependency = new Dependency(new Subdependency());
	}
	
	@Test
	public void test() {
		when(dependency.getClassName()).thenReturn("MiNombre");
		assertThat(dependency.getClassName(), is("MiNombre"));
	}
	
	@Test
	public void OtroTest() {
		when(dependency.getClassName()).thenReturn("OtroNombre");
		assertThat(dependency.getClassName(), is("OtroNombre"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testException() {
		when(dependency.getClassName()).thenThrow(IllegalArgumentException.class);
		dependency.getClassName();
	}
	
	@Test
	public void testAddTwo() {
		when(dependency.addTwo(anyInt())).thenReturn(5);
		assertEquals(5, dependency.addTwo(1));
		assertEquals(5, dependency.addTwo(27));
	}
	
	@Test
	public void testAnswer() {
		when(dependency.addTwo(anyInt())).thenAnswer(new Answer<Integer> () {
			public Integer answer(InvocationOnMock invocation) throws Throwable{
				int arg = (Integer) invocation.getArguments()[0];
				return arg + 20;
			}
		});
		assertEquals(30, dependency.addTwo(10));
	}
	
	@Test
	public void testMultiply() {
		when(dependency.multiply(anyInt(),anyInt())).thenAnswer(new Answer<Integer> () {
			public Integer answer(InvocationOnMock invocation) throws Throwable{
				int res = (Integer) invocation.getArguments()[0] * (Integer) invocation.getArguments()[1];
				return res;
			}
		});
		assertThat(dependency.multiply(13,13), is(169));
	}
}
