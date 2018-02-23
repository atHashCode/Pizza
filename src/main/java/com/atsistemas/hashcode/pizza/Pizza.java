package com.atsistemas.hashcode.pizza;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Pizza {
    private static final Logger LOGGER = LoggerFactory.getLogger(Pizza.class);

    Repositorio repo;
    private Restriccion restriccion;
    private List<List<Celda>> celdas;

    public Pizza(String inputFile, String outputFile) {
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
		LOGGER.info("Restricciones "+restriccion);
		LOGGER.info("celdas "+celdas);
    	long comienzo = System.currentTimeMillis();
    	int colIni = new Random().nextInt((celdas.get(0).size()/2) + 1);
    	int rowIni = new Random().nextInt((celdas.size()/2) + 1);
    	Solucion solucion = new Solucion(restriccion, celdas,rowIni ,colIni);
        LOGGER.info("Tiempo de calculo "+(System.currentTimeMillis()-comienzo));
        repo.grabaSolucion(solucion);
	}

}
