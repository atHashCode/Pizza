package com.atsistemas.hashcode.pizza;

import java.util.Objects;

public class Celda {
    public int row;
    public int col;
    public Ingrediente ingrediente;

    public Celda(int row, int col, String ingrediente) {
        this.row = row;
        this.col = col;
        if (ingrediente.equals(Ingrediente.TOMATO.toString())) {
        	this.ingrediente = Ingrediente.TOMATO;
        } else if (ingrediente.equals(Ingrediente.MUSHROOM.toString())) {
        	this.ingrediente = Ingrediente.MUSHROOM;
        }
	}

	@Override
    public String toString() {
        return "(" + row +
                ", " + col + 
                ", " + ingrediente.toString()+")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Celda)) return false;
        Celda celda = (Celda) o;
        return this.col == celda.col &&
                this.row == celda.row &&
                this.ingrediente == celda.ingrediente;
    }

    @Override
    public int hashCode() {
        return Objects.hash(col, row, ingrediente);
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }
    
    public enum Ingrediente {
        MUSHROOM("M"), TOMATO("T");
        private final String tipo;

        Ingrediente(final String tipo) {
            this.tipo = tipo;
        }

        @Override
        public String toString() {
            return tipo;
        }
    }
}
