package org.cuatrovientos.Tamagotchis;

import java.util.Random;

public class Tamagotchis implements Runnable {
	
	private int numTamagotchi;
	private long timestampInicio;
	private Random randomNum = new Random();

	public Tamagotchis(int i) {
		// TODO Auto-generated constructor stub
		this.numTamagotchi = i;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		logConTimestamp("Tamagotchi encendido.");
		int velComer = randomNum.nextInt(20000, 50001);
		int tiempoJugar  = randomNum.nextInt(20000, 50001);
		int suciedad = 0;
		int tiempoSuciedad = 0;
		int tiempoVida = 0;
		
		while (true) {
			try {
				//Le impide ejecutarse constantemente. Se ejecuta cada segundo
				Thread.sleep(1001);
				tiempoVida++;
				tiempoSuciedad++;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				logConTimestamp("Error desconocido, se apago al Tamagotchi nº" + numTamagotchi + ".");
			}
		}
		
	}
	
	private void logConTimestamp(String log){
    	System.out.println("Tamagotchi nº: "+ numTamagotchi +"; Timestamp: "+(System.currentTimeMillis() - timestampInicio)+": "+log);
    }

}
