package com.atsistemas.hashcode.pizza;

import java.io.File;
import java.io.IOException;
import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Pizza {
    private static final Logger LOGGER = LoggerFactory.getLogger(Pizza.class);
    private final File input;
    private final File output;
    private Restriccion restriccion;
    private List<Celda> celdas;

    public Pizza(String inputFile, String outputFile) {
        this.input = new File(inputFile);
        this.output = new File(outputFile);
        PizzaRepositorio repo = new PizzaRepositorio();
        try {
			this.celdas = repo.cargaCeldas(this.input);
	        this.restriccion = repo.cargaRestriccion(this.input);
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		}
    }


	public File getInput() {
        return input;
    }

    public List<Celda> getCeldas() {
        return celdas;
    }

    public void setCeldas(List<Celda> celdas) {
        this.celdas = celdas;
    }


	public void trozeaPizza() {
		LOGGER.info("Restricciones "+restriccion);
		LOGGER.info("celdas "+celdas);
    	long comienzo = System.currentTimeMillis();

        LOGGER.info("Tiempo de calculo "+(System.currentTimeMillis()-comienzo));
	}

}
