package com.atsistemas.hashcode.pizza;

import java.util.ArrayList;
import java.util.List;

import javax.naming.SizeLimitExceededException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Porcion {

    private static final Logger LOGGER = LoggerFactory.getLogger(Porcion.class);
	public List<List<Celda>> celdas = new ArrayList<>();

	public Porcion(List<List<Celda>> cells, int rowIni, int colIni) throws SizeLimitExceededException {

		if ((rowIni > cells.size() - 1) || (colIni > cells.get(0).size() - 1))
			throw new SizeLimitExceededException("Dimensiones fuera de rango (" + rowIni + ", " + colIni +")");
		LOGGER.debug("Porcion desde (" + rowIni + ", " + colIni +")");
		for (int i = rowIni; i <= cells.size() - 1; i++) {
			List<Celda> fila = cells.get(i);
			List<Celda> filaExtraida = new ArrayList<>();
			for (int j = colIni; j <= fila.size() - 1; j++)
				filaExtraida.add(fila.get(j));
			this.celdas.add(filaExtraida);
		}
	}
	public Porcion(List<List<Celda>> cells, int rowIni, int colIni, int rowFin, int colFin) throws SizeLimitExceededException {
 
		if ((rowIni > cells.size() - 1) || (colIni > cells.get(0).size() - 1) ||
				(rowFin > cells.size() - 1) || (colFin > cells.get(0).size() - 1))
			throw new SizeLimitExceededException("Dimensiones fuera de rango (" + rowIni+", " + colIni + ") a (" + rowFin+", " + colFin + ")");
		LOGGER.debug("Porcion desde (" + rowIni+", " + colIni + ") a (" + rowFin+", " + colFin + ")");
		for (int i = rowIni; i <= rowFin; i++) {
			List<Celda> fila = cells.get(i);
			List<Celda> filaExtraida = new ArrayList<>();
			for (int j = colIni; j <= colFin; j++)
				filaExtraida.add(fila.get(j));
			this.celdas.add(filaExtraida);
		}
	}
	
	public List<List<Celda>> getCeldas() {
		return celdas;
	}
	public Long numCeldas() {
		return (long) (this.celdas.size() * this.celdas.get(0).size());
	}
	public Validez esValida(Restriccion restriccion) {
		if (tamanio() > restriccion.getMaxCeldasPorcion())
			return Validez.TAMANIO;

		int tomatoes = 0;
		int mushroom = 0;
		for(List<Celda> fila:celdas) {
			for(Celda cell:fila) {
				if (cell.ingrediente.equals(Celda.Ingrediente.MUSHROOM))
					mushroom++;
				else
					tomatoes++;

				if ((mushroom >= restriccion.getMinIngredientesPorcion()) &&
						(tomatoes >= restriccion.getMinIngredientesPorcion()))
					return Validez.VALIDA;
			}
		}
		if ((mushroom > restriccion.getMinIngredientesPorcion()) &&
				(tomatoes > restriccion.getMinIngredientesPorcion()))
			return Validez.VALIDA;
		else
			return Validez.INGREDIENTES;
	}
	
	public Integer tamanio() {
		if (celdas.size() > 0) 
			return celdas.size() * celdas.get(celdas.size() - 1).size();
		else 
			return 0;
	}
	public Porcion une(Porcion porcion) {
		List<List<Celda>> celdas = porcion.getCeldas();
		if ((this.celdas.get(0).get(this.celdas.get(0).size() - 1).getCol() + 1 == (celdas.get(0).get(0).getCol()))) {
			for (int i = 0; i < celdas.size() - 1; i++) {
				this.celdas.get(i).addAll(celdas.get(i));
			}
		} else if ((this.celdas.get(this.celdas.size() - 1).get(0).getRow() + 1 == (celdas.get(0).get(0).getRow()))) {
			this.celdas.addAll(celdas);
		}
		
		return this;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return celdas.get(0).get(0).getRow()+" "+ 
				   celdas.get(0).get(0).getCol()+" "+ 
			   celdas.get(celdas.size()-1).get(0).getRow()+" "+
			   celdas.get(celdas.size()-1).get(celdas.get(celdas.size()-1).size()-1).getCol()+" ";
	}
	
    public enum Validez {
    	VALIDA(0), TAMANIO(1), INGREDIENTES(2);
        private final Integer tipo;

        Validez(final Integer tipo) {
            this.tipo = tipo;
        }

        @Override
        public String toString() {
            return "" + tipo;
        }
    }
}
