package com.atsistemas.hashcode.pizza;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App 
{
	public static void main(String[] args) {

	    final Logger LOGGER = LoggerFactory.getLogger(App.class);
		for (int i = 0 ; i < 10; i++) {
			LOGGER.info("Iteraccion "+i);
//			new Pizza("ficheros/example.in", "resultado/example").trozeaPizza();
//			new Pizza("ficheros/small.in", "resultado/small").trozeaPizza();
			new Pizza("ficheros/medium.in", "resultado/medium").trozeaPizza();
			new Pizza("ficheros/big.in", "resultado/big").trozeaPizza();
		}


	}
}
