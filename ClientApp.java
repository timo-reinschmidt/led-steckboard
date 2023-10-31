package ch.hslu.prg.led.steckboard;

import java.util.Scanner;

import ch.hslu.prg.leds.proxy.LedService;

public class ClientApp {

	public static void main(String[] args) {
		LedService service = new LedService();
		Scanner sc = new Scanner(System.in);
		// Variables 
		int ledsCount = 0;
		boolean isTooBig = false;
		
		do {
			System.out.print("Geben Sie die Anzahl der LEDs ein (Maximum 256): ");
			ledsCount = sc.nextInt();
			if (ledsCount > service.MAX_NUMBER_OF_LEDS) {
				isTooBig = true;
			}
			else{
				System.out.println("Geben Sie eine Zahl KLEINER als 256 ein!");
				isTooBig = false;
			}
			
		} while (isTooBig);
		

	}
	
	public static void ledsOnOff(int ledsCount) {
		
	}

}
