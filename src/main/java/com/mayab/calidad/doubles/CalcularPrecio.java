package com.mayab.calidad.doubles;

public class CalcularPrecio {

	public float precioProducto;
	public float porcentaje;
	public CalcularIVA formulaIVA;
	
	public CalcularPrecio() {
		precioProducto = 100;
		porcentaje = .16f;
	}
	public float Calcular(CalcularIVA formula) {
		formulaIVA = formula;
		float iva = formulaIVA.getIVA(porcentaje, precioProducto);
		return iva + precioProducto;
	}
}
