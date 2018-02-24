package com.atsistemas.hashcode.pizza;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Pizza {
    private static final Logger LOGGER = LoggerFactory.getLogger(Pizza.class);

    Repositorio repo;
    private Restriccion restriccion;
    private List<List<Celda>> celdas;

    public Pizza(String inputFile, String outputFile) {
    	LOGGER.info("Inicio de " + inputFile);
    	repo = new Repositorio(inputFile, outputFile);

        try {
			this.celdas = repo.cargaCeldas();
	        this.restriccion = repo.cargaRestriccion();
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		}
    }

    public List<List<Celda>> getCeldas() {
        return celdas;
    }

    public void setCeldas(List<List<Celda>> celdas) {
        this.celdas = celdas;
    }


	public void trozeaPizza() {
		LOGGER.debug("Restricciones "+restriccion);
		LOGGER.debug("celdas "+celdas.subList(0, (celdas.size()>20)?20:celdas.size()));
    	long comienzo = System.currentTimeMillis();

    	Solucion solucion = new Solucion(restriccion, celdas);
        LOGGER.info("Tiempo de calculo "+(System.currentTimeMillis()-comienzo));
        repo.grabaSolucion(solucion);
	}

}
