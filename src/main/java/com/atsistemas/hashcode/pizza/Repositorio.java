package com.atsistemas.hashcode.pizza;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Repositorio {

    private static final Logger LOGGER = LoggerFactory.getLogger(Repositorio.class);
    private File input;
	private String output;

	public Repositorio(String inputFile, String outputFile) {
        this.input = new File(inputFile);
        this.output = outputFile;
	}

	public List<List<Celda>> cargaCeldas() throws IOException {
        try (FileReader fileReader = new FileReader(input)) {
            BufferedReader br = new BufferedReader(fileReader);
            br.readLine();
            List<List<Celda>> celdas = new ArrayList<>();
            int row = 0;
            String fileLine;
            while ((fileLine = br.readLine()) != null) {
                List<Celda> fila = new ArrayList<>();
                for (int column = 0; column < fileLine.length(); column++) {
                    Character literal = fileLine.charAt(column);

                    fila.add(new Celda(row, column, literal.toString()));
                }
                celdas.add(fila);
                row++;
            }
            return celdas;
        }
    } 

    public Restriccion cargaRestriccion() throws IOException {
    	try (FileReader fileReader = new FileReader(input)) {
            BufferedReader br = new BufferedReader(fileReader);
            String[] tokens = br.readLine().split(" ");
            int minIngredientesPorcion = Integer.parseInt(tokens[2]);
            int maxCeldasPorcion = Integer.parseInt(tokens[3]);
            return new Restriccion(minIngredientesPorcion, maxCeldasPorcion);
        }
	}

	public void grabaSolucion(Solucion solucion) {
		Long puntuacion = solucion.getPuntuacion();
        try (FileWriter fileWriter = new FileWriter(new File(this.output + "(" + puntuacion + ")" + new Random().nextInt() + ".txt"))) {
        	fileWriter.write(solucion.toString());
        } catch (IOException e) {
			LOGGER.error(e.getMessage());
		}
	}
}
