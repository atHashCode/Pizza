package com.atsistemas.hashcode.pizza;

public class Restriccion {
    private final Integer minIngredientesPorcion;
    private final Integer maxCeldasPorcion;

    public Restriccion(Integer minIngredientesPorcion, Integer maxCeldasPorcion) {
        this.minIngredientesPorcion = minIngredientesPorcion;
        this.maxCeldasPorcion = maxCeldasPorcion;
    }

    public Integer getMinIngredientesPorcion() {
        return minIngredientesPorcion;
    }

    public Integer getMaxCeldasPorcion() {
        return maxCeldasPorcion;
    }

    public Boolean esValido() {
    	return true;
    }
    @Override
    public String toString() {
        return "Restriccion: \n" +
                "min " + minIngredientesPorcion + " ingredientes por porcion, " +
                "max " + maxCeldasPorcion + " celdas por porcion ";
    }
}
