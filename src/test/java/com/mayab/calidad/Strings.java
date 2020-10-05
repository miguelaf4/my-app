package com.mayab.calidad;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class Strings {

	@Parameters
	public static Iterable data() {
		return Arrays.asList(new Object[][] {
			{"hola"},{"kufdhsksfdubfdskfddfdsf"}
		});
	}
	
private Usuario usuario;

public Strings(String password) {
	this.usuario = new Usuario(password);
}

@Test 
public void cadenaMenorA12() {
	assertThat(usuario.revisarPass(usuario.getPassword()), is("Error, minimo 12 caracteres"));
}
}
