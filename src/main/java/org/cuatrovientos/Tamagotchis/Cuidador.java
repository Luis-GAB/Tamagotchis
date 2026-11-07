package org.cuatrovientos.Tamagotchis;

import java.util.Scanner;

import org.cuatrovientos.HilosCarrera.HilosCorredor;

public class Cuidador {
	
    public static void main(String[] args) {
    	
    	Scanner readFromConsole = new Scanner(System.in);
    	boolean error;
    	String leer;
    	int numero = 0;
    	
    	do {
    		error = true;
    		System.out.print("¿Cuantos Tamagotchis quieres encender?: ");
    		try {
    			leer = readFromConsole.nextLine();
	       		numero = Integer.parseInt(leer);
	       		if (numero <= 0) {
	       			System.out.println("¡Error! El número debe ser mayor a 0");
	       		} else {
	       			error = false;
	       		}
    		} catch (Exception e) {
    			System.out.println("Ha habido un error en la ejecución: " + e.getMessage());
    			//e.printStackTrace();
    		}
    		
    	} while(error);
        
    	Thread[] hilos = new Thread[numero];
    	for (int i = 0; i < hilos.length; i++) {
            hilos[i] = new Thread(new Tamagotchis(i + 1));
        }
    	
    	for (int i = 0; i < hilos.length; i++) {
            hilos[i].start();
        }
    }
}
