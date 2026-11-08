package org.cuatrovientos.Tamagotchis;

import java.util.Random;

public class Tamagotchis implements Runnable {
	
	private int numTamagotchi;
	private volatile boolean vivo = true;
	private volatile boolean ocupado = false;
	
	private int suciedad;
	private int tiempoSuciedad = 0;
	private int velComer = 0;
	
	private long timestampInicio;
	private Random randomNum = new Random();

	public Tamagotchis(int i) {
		// TODO Auto-generated constructor stub
		this.numTamagotchi = i;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		timestampInicio = System.currentTimeMillis();
		logConTimestamp("Tamagotchi encendido.");
		//Definimos la velocidad a la que comera este Tamagotchi ---> entre 20 y 50 segundos
		velComer = randomNum.nextInt(20000, 50001);
		int tiempoJugar  = randomNum.nextInt(20000, 50001);
		int tiempoComer = 0;
		int tiempoVida = 0;
		
		while (vivo) {
			try {
				//Le impide ejecutarse constantemente. Se ejecuta cada segundo
				Thread.sleep(1000);
				tiempoVida++;
				tiempoSuciedad++;
				
				//Suciedad (Suma 1 de suciedad cada 20 segundos)
				if (tiempoSuciedad >= 20) {
					
					suciedad++;
					tiempoSuciedad = 0;
                    //logConTimestamp("Nivel de suciedad: " + suciedad);
                    
                    if (suciedad == 5) {
                        logConTimestamp("El Tamagotchi esta empezando a estar muy sucio.");
                    }
                    
                    if (suciedad >= 10) {
                        logConTimestamp("El Tamagotchi ha muerto por la suciedad.");
                        vivo = false;
                    }
					
				}
				//Cada 20 segundos sube en uno la suciedad. 20*5 = 100
				//if (tiempoSuciedad == 100) {
				//	logConTimestamp("El Tamagotchi esta muy sucio.");
				//} else if (tiempoSuciedad == 200) {
					//Al tener suciedad 10 (tiempoSuciedad -> 20*10 = 200) se muere.
				//	logConTimestamp("El Tamagotchi ha muerto por la suciedad.");
				//	vivo = false;
				//}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				logConTimestamp("Error desconocido, se apago al Tamagotchi.");
				vivo = false;
			}
		}
		
	}
	
	private void logConTimestamp(String log){
    	System.out.println("Tamagotchi nº: "+ numTamagotchi +"; Timestamp: "+(System.currentTimeMillis() - timestampInicio)+": "+log);
    }
	
	public boolean comer() {

	    if (!vivo || ocupado) {
	        return false;
	    }
	    ocupado = true;

        Thread hiloComer = new Thread(() -> {

            logConTimestamp("Empieza a comer una manzana. velocidad:" + velComer);
            try {
                Thread.sleep(velComer);
                if (vivo) {
                    logConTimestamp("Ha terminado de comer.");
                }

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();

            } finally {
                ocupado = false;
            }
        });

        hiloComer.start();
        return true;
	    
	}
	
	public boolean limpiar() {
		 
		if (!vivo || ocupado) {
	        return false;
	    }
		 
		ocupado = true;
		
		Thread hiloLimpiar = new Thread(() -> {
		
			logConTimestamp("El Tamagotchi empieza a bañarse.");
		 
		    try {
                Thread.sleep(5000);

                if (vivo) {
                    suciedad = 0;
                    tiempoSuciedad = 0;
                    logConTimestamp("El Tamagotchi ha finalizado su baño.");
                }

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                ocupado = false;
            }
		});
		
		hiloLimpiar.start();
        return true;	 
	}

	public int getNumTamagotchi() {
		return numTamagotchi;
	}

	public void setNumTamagotchi(int numTamagotchi) {
		this.numTamagotchi = numTamagotchi;
	}
	
	

}
