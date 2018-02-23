package com.atsistemas.hashcode.pizza;

import java.util.ArrayList;
import java.util.List;

public class Solucion {
	private List<Porcion> porciones = new ArrayList<>();
	private Restriccion restriccion;
	public Solucion(Restriccion restriccion, List<List<Celda>> celdas, int rowIni, int colIni) {
		this.restriccion = restriccion;
		porciones.add(new Porcion(celdas, rowIni, colIni));
		optimiza(porciones);
	}
	private void optimiza(List<Porcion> porciones) {
		// TODO Auto-generated method stub

	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder(10);
		result.append(porciones.size()).append("\n");
		for (Porcion porcion : porciones) {
			result.append(porcion.toString());
		}
		return result.toString();
	}


}
