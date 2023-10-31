package ch.hslu.prg.led.steckboard;

import java.util.Iterator;
import java.util.Scanner;
import ch.hslu.prg.leds.proxy.LedColor;

import ch.hslu.prg.leds.proxy.LedService;

public class ClientApp {

	public static void main(String[] args) {
		LedService service = new LedService();
		// ledsOnOff(service);

		switchEvenOdd(service);

	}

	// Aufgabe 1.1
	public static void ledsOnOff(LedService service) {
		int ledsCount = 0;
		String colorStr = null;

		Scanner sc = new Scanner(System.in);
		// Variables

		boolean isTooBig = false;

		do {
			System.out.print("Geben Sie die Anzahl der LEDs ein (Maximum 256): ");
			ledsCount = sc.nextInt();
			if (ledsCount > service.MAX_NUMBER_OF_LEDS) {
				isTooBig = true;
				System.out.println("Geben Sie eine Zahl KLEINER als 256 ein!");
			} else {

				isTooBig = false;
			}

		} while (isTooBig);

		// Aufgabe 1.2
		boolean isCorrect = false;
		do {

			System.out.print("Geben Sie die gewünschte Farbe ein (RED, BLUE, YELLOW, RANDOM): ");
			colorStr = sc.next();
			if (colorStr.equals("RED") || colorStr.equals("BLUE") || colorStr.equals("YELLOW")
					|| colorStr.equals("RANDOM")) {
				isCorrect = true;
			} else {
				isCorrect = false;
			}

		} while (isCorrect == false);

		service.addLeds(ledsCount, LedColor.valueOf(colorStr));
		service.stopExecutionFor(2000);

		// wiederholung

		for (int i = 0; i < 3; i++) {
			for (int k = 0; k < ledsCount; k++) {
				service.turnLedOn(k);
				service.setDelayInMillis(200);
			}

			service.stopExecutionFor(250);

			for (int l = ledsCount - 1; l >= 0; l--) {
				service.turnLedOff(l);
				service.setDelayInMillis(200);
			}

			service.stopExecutionFor(250);
		}

		service.stopExecutionFor(2000);
		service.reset();

	}

	// Aufgabe 2.1
	public static void switchEvenOdd(LedService service) {
		Scanner sc = new Scanner(System.in);
		int ledsCount = 0;
		int counter = 0;
		boolean isSixteenStep = true;
		boolean isThree = true;
		
		do {
			System.out.print("Geben sie die Anzahl der LEDs ein (Die Zahl muss durch 16 teilbar sein): ");
			ledsCount = sc.nextInt();

			if (ledsCount % 16 == 0) {
				isSixteenStep = false;
			}

		} while (isSixteenStep);

		service.addLeds(ledsCount, LedColor.BLUE);

		
		
		//
		do {
			for (int i = 0; i < ledsCount; i++) {
				if (i % 2 == 0) {
					service.turnLedOn(i);
					service.setDelayInMillis(200);
				}
			}
			
			service.stopExecutionFor(1000);

			for (int i = 0; i < ledsCount; i++) {
				if (i % 2 == 0) {
					service.turnLedOff(i);
					service.setDelayInMillis(200);
					
				}
				else {
					service.turnLedOn(i);
					service.setDelayInMillis(200);
				}
				
			}
			
			service.stopExecutionFor(1000);
			
			for (int i = 0; i < ledsCount; i++) {
				service.turnLedOff(i);
				service.setDelayInMillis(200);
			}
			
			service.stopExecutionFor(1000);
			counter ++;
			if (counter == 3) {
				isThree = false;
			}
		} while(isThree);
		
	}
}
