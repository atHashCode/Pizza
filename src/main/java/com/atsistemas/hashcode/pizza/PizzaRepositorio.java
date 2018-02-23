package com.atsistemas.hashcode.pizza;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PizzaRepositorio {
	
    public List<Celda> cargaCeldas(File input) throws IOException {
        try (FileReader fileReader = new FileReader(input)) {
            BufferedReader br = new BufferedReader(fileReader);
            br.readLine();
            List<Celda> celdas = new ArrayList<>();
            int row = 0;
            String fileLine;
            while ((fileLine = br.readLine()) != null) {
                for (int column = 0; column < fileLine.length(); column++) {
                    Character literal = fileLine.charAt(column);

                    celdas.add(new Celda(row, column, literal.toString()));
                }
                row++;
            }
            return celdas;
        }
    }
    

    public Restriccion cargaRestriccion(File input) throws IOException {
    	try (FileReader fileReader = new FileReader(input)) {
            BufferedReader br = new BufferedReader(fileReader);
            String[] tokens = br.readLine().split(" ");
            int minIngredientesPorcion = Integer.parseInt(tokens[2]);
            int maxCeldasPorcion = Integer.parseInt(tokens[3]);
            return new Restriccion(minIngredientesPorcion, maxCeldasPorcion);
        }
	}
}
