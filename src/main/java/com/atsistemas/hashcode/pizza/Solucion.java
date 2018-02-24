package com.atsistemas.hashcode.pizza;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.naming.SizeLimitExceededException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Solucion {
	private static final Logger LOGGER = LoggerFactory.getLogger(Solucion.class);
	private List<Porcion> porciones;
	private Restriccion restriccion;
	private Integer reintentos;
	public Solucion(Restriccion restriccion, List<List<Celda>> celdas, Integer reintentos) {
		this.restriccion = restriccion;
		this.reintentos = reintentos;
		try {
			this.porciones = optimiza(trocea(celdas));
		} catch (SizeLimitExceededException e) {
			LOGGER.error(e.getMessage());
		}
	}
	private List<Porcion> trocea(List<List<Celda>> celdas) throws SizeLimitExceededException {
		List<Porcion> porciones = new ArrayList<>();
		if (new Random().nextBoolean()) {
			int colCorte = new Random().nextInt((celdas.get(0).size()));
			try {
				Porcion resultante = new Porcion(celdas, 0, 0, celdas.size() - 1, colCorte);
				if (resultante.tamanio() > 0)
					porciones.add(resultante);
				resultante = new Porcion(celdas, 0, colCorte + 1);
				if (resultante.tamanio() > 0)
					porciones.add(resultante);
			} catch (SizeLimitExceededException e) {
				porciones = new ArrayList<>();
				porciones.add(new Porcion(celdas, 0, 0));
			}
			
		} else {
			int rowCorte = new Random().nextInt((celdas.size()));
			try {
				Porcion resultante = new Porcion(celdas, 0, 0, rowCorte, celdas.get(0).size() - 1);
				if (resultante.tamanio() > 0)
					porciones.add(resultante);
				resultante = new Porcion(celdas, rowCorte + 1, 0);
				if (resultante.tamanio() > 0)
					porciones.add(resultante);
			
			} catch (SizeLimitExceededException e) {
				porciones = new ArrayList<>();
				porciones.add(new Porcion(celdas, 0, 0));
			}
		}

        return porciones;
	}
	private List<Porcion> optimiza(List<Porcion> porciones) {

		List<Porcion> porcionesOptimizadas = new ArrayList<>();

		for(Porcion porcion : porciones) {
			List<Porcion> p = optimiza(porcion, reintentos);
			if (p != null) {
				porcionesOptimizadas.addAll(p);
			}

		}
		return porcionesOptimizadas;
	}

	private List<Porcion> optimiza(Porcion porcion, int reintento) {

		List<Porcion> porcionesOptimizadas = new ArrayList<>();
		Porcion.Validez validez = porcion.esValida(this.restriccion);
		if (validez == Porcion.Validez.TAMANIO) {
			try {
				List<Porcion> porcionesTroceadas = trocea(porcion.getCeldas());
				if (reintento > 1) {
					Porcion porcion1 = porcionesTroceadas.get(0);
					Porcion porcion2 = (porcionesTroceadas.size()>1)?porcionesTroceadas.get(1):null;
					if (porcion2 != null) {
						List<Porcion> optimizadas1 = optimiza(porcion1, reintentos);
						List<Porcion> optimizadas2 = optimiza(porcion2, reintentos);
						if ((optimizadas1 != null) && (optimizadas2 != null)) {
							optimizadas1.addAll(optimizadas2);
							return optimizadas1;
						} else {
							return optimiza(porcion, reintento - 1);
						}
					} else {
						return optimiza(porcion1, reintentos);
					}
				}
				else 
				{
					return optimiza(porcionesTroceadas);
				}
			} catch (SizeLimitExceededException e) {
				return null;
			}				
		} else if (validez == Porcion.Validez.INGREDIENTES) {
			return null;
		} else {
			porcionesOptimizadas.add(porcion);
			return porcionesOptimizadas;
		}
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
			result.append("\n");
		}
		return result.toString();
	}
	public Long getPuntuacion() {
		Long puntuacion = 0L;
		for (Porcion porcion : porciones) {
			puntuacion += porcion.numCeldas();
		}
		return puntuacion;
	}


}
