package org.cuatrovientos.Tamagotchis;

import java.util.Scanner;

public class Cuidador {
	
    public static void main(String[] args) {
    	
    	Scanner readFromConsole = new Scanner(System.in);
    	boolean error = false;
    	
    	do {
    		try {
    			System.out.println("¿Cuantos Tamagotchis quieres encender?: ");
    		} catch (Exception e) {
    			System.out.print("Ha habido un error en la ejecución: " + e.getMessage());
    			
    		}
    		
    	} while(error);
        
    }
}
