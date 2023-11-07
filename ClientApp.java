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
		ledsOnOff(service);
		switchRandom(service);
		showBinary(service);
<<<<<<< Updated upstream
=======
		//showRectangle(service);
>>>>>>> Stashed changes
		
		

	}
	
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
	
	// Aufgabe 3
	public static void switchRandom(LedService service) {
		
		// Variablen deklarieren
		int halfLEDCount = 0;
		int ledsCount = 0;
		boolean isTooBig = false;
		
		// 1. Einlesen eines Vielfachen von 16	
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
		
		// Schauen ob die Anzahl LEDs durch 16 teilbar ist
		if (ledsCount % 16 == 0) {

		} else {		
			while (ledsCount % 16 != 0) {
				System.out.print("Dies ist kein vielfaches von 16, geben Sie eine neue Zahl ein: ");
				ledsCount = sc.nextInt();		
			}
		}
		
		// 2. LEDs hinzufügen und 2 Sekunden anhalten
		service.addLeds(ledsCount, LedColor.RANDOM);				
		service.stopExecutionFor(2000);
		
		// 8. Aufgabe 3-8 3mal wiederholen
		for (int r = 0; r <= 3; r++) {
		
			// 3. Hälfte aller LEDs einschalten
			halfLEDCount = ledsCount / 2;
			int fullLEDCount = ledsCount;		
	
			// Array und Random Objekt erstellen
			int[] arr = new int[fullLEDCount];
			
			for (int i = 0; i < arr.length; i++) {
				
				int randomNummer;
	
				boolean uniqueNumber = false;
				
				do {
					// Random Nummer erstellen
<<<<<<< Updated upstream
=======
					/* Zufallszahl zwischen 0 und ledsCount generiert und 
					 * in randomNummer gespeichert.
					 */
>>>>>>> Stashed changes
					randomNummer = random.nextInt(ledsCount); 
	
					// Schauen ob die Nummer schon existiert
					uniqueNumber = true;
<<<<<<< Updated upstream
=======
					
					/* Schleife prüft, ob die generierte Zufallszahl bereits in einem 
					 * der vorherigen Array-Elemente existiert, indem sie durch die 
					 * vorherigen Elemente von arr geht
					 */
>>>>>>> Stashed changes
					for (int x = 0; x < i; x++) {
						
						if (randomNummer == arr[x]) {
							uniqueNumber = false;
							break;
						}
					}
<<<<<<< Updated upstream
=======
				/* Schleife wird mindestens einmal ausgeführt, und die Schleife 
				 * wird so lange wiederholt, wie uniqueNumber false ist
				 */
>>>>>>> Stashed changes
				} while (!uniqueNumber);        
	
				// Random Nummer in Array hinzufügen
				arr[i] = randomNummer;
			}
	
			// LEDs einschalten
			for (int i = 0; i < halfLEDCount; i++) {
				
				service.turnLedOn(arr[i]);
			}
			
			// 4. 1 Sekunde anhalten
			service.stopExecutionFor(1000);
		
			// 5. LEDs ein- oder aus-schalten
<<<<<<< Updated upstream
=======
			
			/* Dies ist eine Schleife, die von i = 0 bis i = arr.length - 1 durchläuft, 
			 * wobei arr ein Array von LEDs darstellt. Die Schleife geht durch jedes 
			 * Element des Arrays.
			 */
>>>>>>> Stashed changes
			for (int i = 0; i < arr.length; i++) {
				
				if (service.isOn(arr[i])) {
					service.turnLedOff(arr[i]);
					
				} else {
					service.turnLedOn(arr[i]);
				}
			}
			
			// 6. 1 sekunde anhalten
			service.stopExecutionFor(1000);
			
			// 7. LEDs ausschalten und für 1 Sekunde anhalten
			for (int i = 0; i < arr.length; i++) {
				
				if (service.isOn(arr[i])) {
					service.turnLedOff(arr[i]);
				}
			}
			service.stopExecutionFor(1000);
		}
		
		// 9. Reset vom Program machen
		service.reset();
	}
	
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
                    binaryString = Integer.toBinaryString(number);

                    // Ausgabe der Binärzahl
                    //System.out.println("Die Binärdarstellung der eingegebenen Zahl ist: " + binaryString); -> zum testen gewesen
                    
                    // 3. LED's hinzufuegen wie lang die Binaer Zahl ist
                    int binaryLength = binaryString.length();
                    // System.out.println("Es werden " + binaryLength + " LEDs benoetigt."); -> zum testen gewesen
             
                    
                    // 4.2 
                    int binaryLengthRounded = (binaryLength + 7) / 8 * 8; // Auf das nächste Vielfache von 8 aufrunden
                    //System.out.println("Es werden " + binaryLengthRounded + " LEDs hinzugefügt."); -> zum testen gewesen
                    
                    service.addLeds(binaryLengthRounded);
                    
                    // 4. LEDs einschalten wo der character 1 ist im Binär String
                    char characterAt;
                    
<<<<<<< Updated upstream
                    for(int i = 0; i < binaryLength; i++) {
            			characterAt = binaryString.charAt(i);
            			
            			if(characterAt == '1') {
=======
                    for(int i = 0; i < binaryLength; i++) {  // Schleife für jedes Zeichen im Binärstring einmal durchlaufen
            			
                    	/* In jeder Iteration der Schleife wird das Zeichen an der Position i im binaryString 
                    	 * in der Variable characterAt gespeichert. 
                    	 */
                    	characterAt = binaryString.charAt(i);
            			
            			if(characterAt == '1') { // Es wird überprüft, ob das Zeichen in characterAt gleich '1' ist
            				
            				/* Wenn das Zeichen '1' ist, wird die Position, an der die LED eingeschaltet 
            				 * werden soll, berechnet. Diese Position entspricht binaryLength - 1 - i und 
            				 * basiert auf der Position des '1'-Zeichens im Binärstring.
            				 */
>>>>>>> Stashed changes
            				int position = binaryLength -1 -i;
            				service.turnLedOn(position);
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
<<<<<<< Updated upstream
}
=======
}
>>>>>>> Stashed changes
