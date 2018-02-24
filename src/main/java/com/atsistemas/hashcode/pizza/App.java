package com.atsistemas.hashcode.pizza;

public class App 
{
	public static void main(String[] args) {
		for (int i= 0 ; i < 10; i++) {
			new Pizza("ficheros/example.in", "resultado/example").trozeaPizza();
			new Pizza("ficheros/medium.in", "resultado/medium").trozeaPizza();
			new Pizza("ficheros/small.in", "resultado/small").trozeaPizza();
			new Pizza("ficheros/big.in", "resultado/big").trozeaPizza();
		}


	}
}
