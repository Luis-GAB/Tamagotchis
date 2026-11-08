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
            System.out.println("3. Responder pregunta");
            System.out.println("4. Limpiar");
            System.out.println("5. Consultar estado");
            System.out.println("6. Matar Tamagotchi");
            System.out.println("7. Matar todos los ociosos");
            System.out.println("8. Salir");
            
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
           	        	Tamagotchis mascotaJugar = seleccionarTamagotchi(readFromConsole, hilosEjecutandose);
           	            if (mascotaJugar != null) {

            	            int[] pregunta = mascotaJugar.iniciarJuego();
 
            	            if (pregunta == null) {
            	                System.out.println("Está ocupado o muerto.");

                	        } else {
                	            System.out.println("Pregunta del Tamagotchi: " + pregunta[0] + " + " + pregunta[1]);
            	                System.out.println("Responde desde la opción 3");
            	            }
            	        }   	
           	        	break;
           	        	
           	        //Responder
           	        case 3:
                	    Tamagotchis mascotaRespuesta = seleccionarTamagotchi(readFromConsole, hilosEjecutandose);

           	            if (mascotaRespuesta != null) {

            	            int[] pregunta = mascotaRespuesta.getPregunta();
            	            
            	            if (pregunta[0] == -1) {
            	            	System.out.println("El Tamagotchi no esta jugando.");
            	            	System.out.println("Inizializalo desde la opción 2");
            	            	break;
            	            }
                	        System.out.print("¿Cuánto es " + pregunta[0] + " + " + pregunta[1] + "?: ");

                	        System.out.print("Respuesta: ");
                	        leer = readFromConsole.nextLine();
                       		int respuesta = Integer.parseInt(leer);

            	            if (!(mascotaRespuesta.responderJuego(respuesta))) {
            	                pregunta = mascotaRespuesta.getPregunta();
            	                System.out.println("¡Incorrecto! Nueva pregunta: " + pregunta[0] + " + " + pregunta[1]);
            	            }
            	        }
           	            break;
           	        	
           	        //Limpiar
           	        case 4:
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
           	        case 5:
           	        	System.out.println("-Estado de los Tamagotchis:");
                        for (Tamagotchis tamagotchi : hilosEjecutandose.values()) {
                            System.out.println("  Estado Tamagotchi nº" + tamagotchi.getNumTamagotchi() + "  : " + tamagotchi.getEstado() + " | Suciedad: " + tamagotchi.getSuciedad());
                        }
           	        	break;
           	        	
           	        //Matar Tamagotchi
           	        case 6:
           	        	Tamagotchis mascotaMatar = seleccionarTamagotchi(readFromConsole, hilosEjecutandose);

                        if (mascotaMatar != null) {
                            if (mascotaMatar.matar()) {
                                System.out.println("Tamagotchi destruido.");

                            } else {
                                System.out.println("No se puede destruir: está ocupado o muerto.");
                            }
                        }
           	        	break;
           	        	
           	        //Matar todos los ociosos
           	        case 7:
           	        	for (Tamagotchis tamagotchi : hilosEjecutandose.values()) {
                            if (tamagotchi.matar()) {
                                System.out.println("Tamagotchi nº" + tamagotchi.getNumTamagotchi() + " ha sido destruido.");
                            }
                        }
           	        	break;
           	        	
           	        //Salir
           	        case 8:
           	        	System.out.println("Fin del programa.");
           	        	break;
           	        	
           	        default:
           	        	System.out.println("Opción no valida.");
           	        	break;
           	    }
           		
            } catch (Exception e) {
            	System.out.println("Ha habido un error en la ejecución: " + e.getMessage());
            }
    		
    	} while (opcion != 8 && !todosMuertos(hilosEjecutandose));
    	
    	for (Tamagotchis tamagotchi : hilosEjecutandose.values()) {
    		tamagotchi.apagarForzado();
        }
    	System.out.println("Todos los Tamagotchis han muerto.");
    	System.out.println("Programa finalizado...");
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
    
    private static boolean todosMuertos(Map<Thread, Tamagotchis> hilosEjecutandose) {
    	//Recorre todos los Tamagotchis y comprueba si alguno de ellos sigue vivo
        for (Tamagotchis t : hilosEjecutandose.values()) {
            if (t.getEstado().equals("vivo y ocioso") || t.getEstado().equals("jugando") || t.getEstado().equals("ocupado")) {
                return false;
            }
        }

        return true;
    }
    
    
}
