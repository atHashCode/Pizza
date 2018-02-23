package com.atsistemas.hashcode.pizza;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Porcion {

    private static final Logger LOGGER = LoggerFactory.getLogger(Porcion.class);
	public List<List<Celda>> cells = new ArrayList<>();

	public Porcion(List<List<Celda>> cells, int rowIni, int colIni) {

		LOGGER.info("Porcion desde "+ rowIni+", "+ colIni);
		for (int i = rowIni; i <= cells.size() - 1; i++) {
			List<Celda> fila = cells.get(i);
			List<Celda> filaExtraida = new ArrayList<>();
			for (int j = colIni; j <= fila.size() - 1; j++)
				filaExtraida.add(fila.get(j));
			this.cells.add(filaExtraida);
		}
	}

	public Boolean esValido(Restriccion restriccion) {
		if (cells.size() > restriccion.getMaxCeldasPorcion())
			return false;

		int tomatoes = 0;
		int mushroom = 0;
		for(List<Celda> fila:cells) {
			for(Celda cell:fila) {
				if (cell.ingrediente.equals(Celda.Ingrediente.MUSHROOM))
					mushroom++;
				else
					tomatoes++;

				if ((mushroom > restriccion.getMinIngredientesPorcion()) &&
						(tomatoes > restriccion.getMinIngredientesPorcion()))
					return true;
			}
		}
		if ((mushroom > restriccion.getMinIngredientesPorcion()) &&
				(tomatoes > restriccion.getMinIngredientesPorcion()))
			return true;
		else
			return false;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return cells.get(0).get(0).getRow()+" "+ 
			   cells.get(cells.size()-1).get(0).getRow()+" "+
			   cells.get(0).get(0).getCol()+" "+ 
			   cells.get(cells.size()-1).get(cells.get(cells.size()-1).size()-1).getRow()+" ";
	}
}
