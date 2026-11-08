package org.cuatrovientos.Tamagotchis;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Cuidador {
	
    public static void main(String[] args) {
    	
    	Scanner readFromConsole = new Scanner(System.in);
    	Map<Thread, Tamagotchis> hilosEjecutandose = new HashMap<Thread, Tamagotchis>();
    	boolean error;
    	int opcion = 0;
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
            //Si lo hago solo con este codigo, el mapa estara vacio ---> hilos[i] = new Thread(new Tamagotchis(i + 1));
            Tamagotchis tamagotchi = new Tamagotchis(i + 1);
            hilos[i] = new Thread(tamagotchi);
            hilosEjecutandose.put(hilos[i], tamagotchi);
        }
    	
    	for (int i = 0; i < hilos.length; i++) {
            hilos[i].start();
        }
    	
    	//Menu del Cuidador
    	do {	
    		System.out.println("-------- CUIDADOR --------");
            System.out.println("1. Dar de comer");
            System.out.println("2. Jugar");
            System.out.println("3. Limpiar");
            System.out.println("4. Consultar estado");
            System.out.println("5. Matar Tamagotchi");
            System.out.println("6. Matar todos los ociosos");
            System.out.println("7. Salir");
            
            try {
            	leer = readFromConsole.nextLine();
           		opcion = Integer.parseInt(leer);
           		
           	    switch (opcion) {
           	        //Dar de comer
           	        case 1:
           	        	Tamagotchis mascotaComer = seleccionarTamagotchi(readFromConsole, hilosEjecutandose);
                        if (mascotaComer != null) {

                            if (mascotaComer.comer()) {
                                System.out.println("Orden de comer enviada a Tamagotchi nº" + mascotaComer.getNumTamagotchi());
                            } else {
                                System.out.println("No se puede dar de comer al Tamagotchi nº" + mascotaComer.getNumTamagotchi() + ": está ocupado o muerto.");
                            }
                        }
           	        	break;
           	        //Jugar
           	        case 2:
           	        	
           	        	break;
           	        //Limpiar
           	        case 3:
           	        	Tamagotchis mascotaLimpiar = seleccionarTamagotchi(readFromConsole, hilosEjecutandose);
                        if (mascotaLimpiar != null) {

                            if (mascotaLimpiar.limpiar()) {
                                System.out.println("Orden de limpieza enviada a Tamagotchi nº" + mascotaLimpiar.getNumTamagotchi());
                            } else {
                                System.out.println("No puede limpiarse al Tamagotchi nº" + mascotaLimpiar.getNumTamagotchi() + ": está ocupado o muerto.");
                            }
                        }
           	        	break;
           	        //Consultar estado
           	        case 4:
           	        	
           	        	break;
           	        //Matar Tamagotchi
           	        case 5:
           	        	
           	        	break;
           	        //Matar todos los ociosos
           	        case 6:
           	        	
           	        	break;
           	        //Salir
           	        case 7:
           	        	
           	        	break;
           	        default:
           	        	System.out.println("Opción no valida.");
           	        	break;
           	    }
           		
            } catch (Exception e) {
            	System.out.println("Ha habido un error en la ejecución: " + e.getMessage());
            }
    		
    	} while (opcion != 7);
    	
    	readFromConsole.close();
    	
    }
    
    private static Tamagotchis seleccionarTamagotchi(Scanner readFromConsole, Map<Thread, Tamagotchis> hilosEjecutandose) {
    	
    	System.out.print("Introduce el número del Tamagotchi: ");
    	String leer = readFromConsole.nextLine();
   		int numTamagotchi = Integer.parseInt(leer);
   		
   		for (Tamagotchis tamagotchi : hilosEjecutandose.values()) {

   	        if (tamagotchi.getNumTamagotchi() == numTamagotchi) {
   	            return tamagotchi;
   	        }
   	    }
   		
   		System.out.println("No se ha encontrado el Tamagotchi.");
   	    return null;
    	
    }
    
    
}
