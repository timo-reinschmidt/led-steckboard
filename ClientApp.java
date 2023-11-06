package ch.hslu.prg.led.steckboard;

import java.util.Random;
import java.util.Scanner;
import ch.hslu.prg.leds.proxy.LedColor;

import ch.hslu.prg.leds.proxy.LedService;

public class ClientApp {
	
	private static Scanner sc = new Scanner(System.in);
	private static Random random = new Random();
	private static String binaryString;

	public static void main(String[] args) {
		LedService service = new LedService();
		//ledsOnOff(service);
		//switchRandom(service);
		showBinary(service);
		
		

	}
/*	
	// Aufgabe 1.1
	public static void ledsOnOff(LedService service) {
		int ledsCount = 0;
		String colorStr = null;
		
		//Scanner sc = new Scanner(System.in);
		// Variables 
		
		boolean isTooBig = false;
		
		do {
			System.out.print("Geben Sie die Anzahl der LEDs ein (Maximum 256): ");
			ledsCount = sc.nextInt();
			if (ledsCount > service.MAX_NUMBER_OF_LEDS) {
				isTooBig = true;
				System.out.println("Geben Sie eine Zahl KLEINER als 256 ein!");
			}
			else{
				
				isTooBig = false;
			}
			
		} while (isTooBig);
		
		// Aufgabe 1.2
		boolean isCorrect = false;
		do {
			
			System.out.print("Geben Sie die gewünschte Farbe ein (RED, BLUE, YELLOW, RANDOM): ");
			colorStr = sc.next();
			if (colorStr.equals("RED") || colorStr.equals("BLUE") || colorStr.equals("YELLOW") || colorStr.equals("RANDOM")){
				isCorrect = true;
			}
			else {
				isCorrect = false;
			}
			
		} while (isCorrect == false);
		
		service.addLeds(ledsCount, LedColor.valueOf(colorStr));
		service.stopExecutionFor(2000);
		
		// wiederholung
		
		for(int i = 0; i < 3; i++) {
			for(int k = 0; k < ledsCount; k++) {
				service.turnLedOn(k);
				service.setDelayInMillis(200);
			}
			
			service.stopExecutionFor(250);
			
			for(int l = ledsCount -1; l >= 0 ; l--) {
				service.turnLedOff(l);
				service.setDelayInMillis(200);
			}
			
			service.stopExecutionFor(250);
		}
		
		service.stopExecutionFor(2000);
		service.reset();
		
	}
*/
/*	
	// Aufgabe 3
	public static void switchRandom(LedService service) {
		
		// Variablen
		int half;
		int ledsCount = 0;

		
		// 1. Einlesen eines Vielfachen von 16	
		// Variables 
		
		boolean isTooBig = false;
		
		do {
			System.out.print("Geben Sie eine Zahl eines Vielfachen von 16 ein (Maximum 256): ");
			ledsCount = sc.nextInt();
			if (ledsCount > service.MAX_NUMBER_OF_LEDS) {
				isTooBig = true;
				System.out.println("Geben Sie eine Zahl KLEINER als 256 ein!");
			}
			else{
				
				isTooBig = false;
			}
			
		} while (isTooBig);
		

		// Ueberpruefung anzahlLED/16 teilbar
		if (ledsCount % 16 == 0) {

		} else {		
			while (ledsCount % 16 != 0) {
				System.out.print("Dies ist kein vielfaches von 16, geben Sie eine neue Zahl ein: ");
				ledsCount = sc.nextInt();		
			}
		}
		
		// 2. LEDs dem Steckboard hinzufügen und 2 Sekunden anhalten
		service.addLeds(ledsCount, LedColor.RANDOM);				
		service.stopExecutionFor(2000);

		Random random = new Random();
		
		// 8.) Schritte 3 - 8, 3x wiederholen
		for (int r = 0; r <= 3; r++) {
		
			// 3. Haelfte aller LEDs mit java.util.Random einschalten
			half = ledsCount / 2;
			int sizeFull = ledsCount;		
	
			// Erstellung Array und Random Obj
			int[] arr = new int[sizeFull];
			
			for (int i = 0; i < arr.length; i++) {
				
				int randomNummer;
	
				boolean exklusiv = false;
				
				do {
					// Zufaellige Nummer erstellen
					randomNummer = random.nextInt(ledsCount); 
	
					// Ueberpruefen ob Nummer schon existiert
					exklusiv = true;
					for (int x = 0; x < i; x++) {
						
						if (randomNummer == arr[x]) {
							exklusiv = false;
							break;
						}
					}
				} while (!exklusiv);        
	
				// Random Nummer in Array hinzufuegen
				arr[i] = randomNummer;
			}
	
			// Lampen anschalten
			for (int i = 0; i < half; i++) {
				
				service.turnLedOn(arr[i]);
			}
			
			// 4. Methode 1 Sekunde anhalten
			service.stopExecutionFor(1000);
		
			// 5. Switch LedON / LedOFF
			for (int i = 0; i < arr.length; i++) {
				
				if (service.isOn(arr[i])) {
					service.turnLedOff(arr[i]);
					
				} else {
					service.turnLedOn(arr[i]);
				}
			}
			
			// 6. Methode 1 sekunde anhalten
			service.stopExecutionFor(1000);
			
			// 7. Alle LEDS ausschalten und Methode fuer 1 Sekunde anhalten
			for (int i = 0; i < arr.length; i++) {
				
				if (service.isOn(arr[i])) {
					service.turnLedOff(arr[i]);
				}
			}
			service.stopExecutionFor(1000);
		}
		
		// 9. Anzeige zuruecksetzen
		service.reset();
	}
	
*/	
///*
	// Aufgabe 4
	public static void showBinary(LedService service) {
		
		// 1. Nur positiven Zahlen einlesen
		int number = 0;		// Variable wird initialisiert um eingegebene Zahl zu speicher
        boolean validInput = false;

        while (!validInput) {
            System.out.print("Bitte geben Sie eine positive ganze Zahl ein: ");
            if (sc.hasNextInt()) {		// Es wird überprüft, ob die Eingabe eine ganze Zahl ist
                number = sc.nextInt();	// wenn ja, wird diese in der number Variable gespeichert
                if (number > 0) {
                	
                	validInput = true;
                	// Schritt 2: Umwandlung in eine Binärzahl
                    String binaryString = Integer.toBinaryString(number);

                    // Ausgabe der Binärzahl
                    System.out.println("Die Binärdarstellung der eingegebenen Zahl ist: " + binaryString);
                    
                    // 3. LED's hinzufuegen wie lang die Binaer Zahl ist
                    int binaryLength = binaryString.length();
                    System.out.println("Es werden " + binaryLength + " LEDs benoetigt.");
             
                    
                    // 4.2 
                    int binaryLengthRounded = (binaryLength + 7) / 8 * 8; // Auf das nächste Vielfache von 8 aufrunden
                    System.out.println("Es werden " + binaryLengthRounded + " LEDs hinzugefügt.");
                    
                    service.addLeds(binaryLengthRounded);
                    // 4. LEDs einschalten wo der character 1 ist im Binaer String
                    char characterAt;
                    
                    for(int i = 0; i < binaryLength; i++) {
            			characterAt = binaryString.charAt(i);
            			
            			if(characterAt == '1') {
            				service.turnLedOn(binaryLength -1 -i);
            			}
            			
            		}
                    
                    service.stopExecutionFor(4000);
                    
                    service.reset();
                    
                } else {
                    System.out.println("Die eingegebene Zahl ist nicht positiv. Versuchen Sie es erneut.");
                }
                
            } else {
                System.out.println("Ungültige Eingabe. Bitte geben Sie eine ganze Zahl ein.");
                sc.next(); // Um den ungültigen Input zu löschen.
            }
        }	
	}
//*/
}
