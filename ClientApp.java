package ch.hslu.prg.led.steckboard;

import java.util.ArrayList;
import java.util.List;
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
		// ledsOnOff(service);
		// switchEvenOdd(service);
		// siebDesEratothenes(service);
		// countColors(service);
		// countColorsExt(service);
		// showBinary(service);
		// showRectangle(service);
		// switchRandom(service);
		// showBorder(service);
		// showSquare(service);
		showTriangle(service);

	}

	// Aufgabe 1
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

	// Aufgabe 2
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

				} else {
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
			counter++;
			if (counter == 3) {
				isThree = false;
			}
		} while (isThree);

	}

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
			if (ledsCount > LedService.MAX_NUMBER_OF_LEDS) {
				isTooBig = true;
				System.out.println("Geben Sie eine Zahl KLEINER als 256 ein!");
			} else {

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

	// Aufgabe 4
	public static void showBinary(LedService service) {

		// 1. Nur positiven Zahlen einlesen
		int number = 0; // Variable wird initialisiert um eingegebene Zahl zu speicher
		boolean validInput = false;

		while (!validInput) {
			System.out.print("Bitte geben Sie eine positive ganze Zahl ein: ");
			if (sc.hasNextInt()) { // Es wird überprüft, ob die Eingabe eine ganze Zahl ist
				number = sc.nextInt(); // wenn ja, wird diese in der number Variable gespeichert
				if (number > 0) {

					validInput = true;
					// Schritt 2: Umwandlung in eine Binärzahl
					String binaryString = Integer.toBinaryString(number);

					// Ausgabe der Binärzahl
					System.out.println("Die Binärdarstellung der eingegebenen Zahl ist: " + binaryString);

					// 3. LED's hinzufuegen wie lang die Binaer Zahl ist
					int numLEDs = binaryString.length();
					System.out.println("Es werden " + numLEDs + " LEDs benoetigt.");

					// 4.2
					int numLEDsRounded = (numLEDs + 7) / 8 * 8; // Auf das nächste Vielfache von 8 aufrunden
					System.out.println("Es werden " + numLEDsRounded + " LEDs hinzugefügt.");

					service.addLeds(numLEDsRounded);
					// 4. LEDs einschalten wo der character 1 ist im Binaer String
					char characterAt;

					for (int i = 0; i < numLEDs; i++) {
						characterAt = binaryString.charAt(i);

						if (characterAt == '1') {
							service.turnLedOn(numLEDs - 1 - i);
						}

					}

					// service.stopExecutionFor(4000);

					// service.reset();

				} else {
					System.out.println("Die eingegebene Zahl ist nicht positiv. Versuchen Sie es erneut.");
				}

			} else {
				System.out.println("Ungültige Eingabe. Bitte geben Sie eine ganze Zahl ein.");
				sc.next(); // Um den ungültigen Input zu löschen.
			}
		}
	}

	// Aufgabe 5
	public static void showBorder(LedService service) {

		List<Integer> turnedOn = new ArrayList<Integer>();
		LedColor color = readLedColor();

		service.addLeds(LedService.MAX_NUMBER_OF_LEDS, color);

		service.stopExecutionFor(2000);

		for (int row = 0; row < LedService.MAX_ROWS; row++) {
			for (int column = 0; column < LedService.MAX_COLUMNS; column++) {
				int i = (LedService.MAX_COLUMNS * row) + column;
				if (i % 16 == 0) {
					service.turnLedOn(i);
					turnedOn.add(i);
				}
				if ((i + 1) % 16 == 0) {
					service.turnLedOn(i);
					turnedOn.add(i);
				}
				if (row == 0) {
					service.turnLedOn(i);
					turnedOn.add(i);
				}
				if (row == LedService.MAX_ROWS - 1) {
					service.turnLedOn(i);
					turnedOn.add(i);
				}
			}
		}

		service.stopExecutionFor(5000);

		for (int i : turnedOn) {
			service.turnLedOff(i);
			service.setDelayInMillis(50);
		}

		service.stopExecutionFor(2000);
		service.reset();

	}

	private static LedColor readLedColor() {
		String colorStr = null;
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
		return LedColor.valueOf(colorStr);

	}

	// Aufgabe 6
	public static void showSquare(LedService service) {

		Scanner sc = new Scanner(System.in);

		String colorStr = null;

		service.addLeds(service.MAX_NUMBER_OF_LEDS);

		int topLeft = 0;
		int lengthSide = 0;
		boolean isCorrect = true;

		do {

			System.out.println("Bitte geben Sie einen gültigen Wert für den Punkt \"topLeft\" links zuoberst ein. ");
			topLeft = sc.nextInt();

			System.out.println("Bitte geben Sie einen gültigen Wert für Seitenlänge ein. ");
			lengthSide = sc.nextInt();

			if (((topLeft + 1) % service.MAX_COLUMNS) + lengthSide < service.MAX_COLUMNS
					&& topLeft / LedService.MAX_ROWS >= lengthSide) {
				isCorrect = false;
			}

		} while (isCorrect);

		int topLeftCol = topLeft % service.MAX_ROWS;

		int topLeftRow = topLeft / service.MAX_COLUMNS;

		int topRightLength = topLeftCol - lengthSide + 1;

		int bottomRightLength = topLeftRow - lengthSide + 1;

		for (int row = topLeftRow; row >= bottomRightLength; row--) {
			for (int col = topLeftCol; col >= topRightLength; col--) {
				service.turnLedOn(row * service.MAX_COLUMNS + col);
				service.setDelayInMillis(50);

			}
		}

		for (int row = topLeftRow - 1; row > bottomRightLength; row--) {
			for (int col = topLeftCol - 1; col > topRightLength; col--) {
				service.turnLedOff(row * service.MAX_COLUMNS + col);
				service.setDelayInMillis(50);

			}
		}

		// Aufgabe 6.2
		int row = topLeftRow;

		for (int col = topLeftCol; col > topLeftCol - lengthSide; col--) {

			service.turnLedOn(row * service.MAX_COLUMNS + col);
			service.setDelayInMillis(50);
			row--;
		}
		row++;

		for (int col = topLeftCol; col > topLeftCol - lengthSide; col--) {

			service.turnLedOn(row * service.MAX_COLUMNS + col);
			service.setDelayInMillis(50);
			row++;
		}

		service.stopExecutionFor(5000);
		service.reset();
	}

	// Aufgabe 7
	public static void showRectangle(LedService service) {

		Scanner sc = new Scanner(System.in);

		String colorStr = null;

		do {
			System.out.print("Geben Sie für die gewünschte Farbe den entsprechenden Ausdruck ein\n"
					+ "Zur Auswahl steht: \"RED\" \"GREEN\" \"BLUE\" \"YELLOW\" und \"RANDOM\" für eine zufällige Auswahl: ");

			colorStr = sc.next();

			if (colorStr.equals("RED") || colorStr.equals("GREEN") || colorStr.equals("BLUE")
					|| colorStr.equals("RANDOM")) {

				break;
			} else {

				System.out.print("Bitte geben Sie den zur Auswahl stehenden Parameter ein.");
			}
		} while (true);

		service.addLeds(LedService.MAX_NUMBER_OF_LEDS, LedColor.valueOf(colorStr));

		int topLeft = 0;

		int bottomRight = 0;

		do {
			System.out.print("Bitte geben Sie einen gültigen Wert für den Punkt \"topLeft\" links zuoberst ein: ");
			topLeft = sc.nextInt();

			System.out
					.print("Bitte geben Sie einen gültigen Wert für den Punkt \"bottomRight\" rechts zuunterst ein: ");
			bottomRight = sc.nextInt();

			if (topLeft + 32 >= bottomRight && topLeft <= LedService.MAX_NUMBER_OF_LEDS
					&& topLeft % LedService.MAX_ROWS > bottomRight % LedService.MAX_ROWS) {
				break;
			} else {
				System.out.println(
						"Die von Ihnen eingegebenen Werte sind ungültig.\nBitte beachten Sie, dass topLeft kleiner als 255 und um 32 Werteinheiten\ngrösser ist als bottomRight.");
				System.out.print("Bitte geben Sie die Werte erneut ein: ");
			}
		} while (true);

		int topLeftCol = topLeft % LedService.MAX_ROWS;

		int topLeftRow = topLeft / LedService.MAX_COLUMNS;

		int bottomRightCol = bottomRight % LedService.MAX_ROWS;

		int bottomRightRow = bottomRight / LedService.MAX_COLUMNS;

		for (int row = topLeftRow; row >= bottomRightRow; row--) {
			for (int col = topLeftCol; col >= bottomRightCol; col--) {
				service.turnLedOn(row * LedService.MAX_COLUMNS + col);
				service.setDelayInMillis(75);
			}
		}

		service.stopExecutionFor(300);

		for (int row = topLeftRow - 1; row > bottomRightRow; row--) {
			for (int col = topLeftCol - 1; col > bottomRightCol; col--) {
				service.turnLedOff(row * LedService.MAX_COLUMNS + col);
				service.setDelayInMillis(75);

			}
		}

		service.stopExecutionFor(300);

		service.reset();
	}

	// Aufgbae 8
	public static void triangle(LedService service) {

		int row = 0;
		service.addLeds(LedService.MAX_NUMBER_OF_LEDS);

		int hoehe = 7;
		int startPoint = LedService.MAX_COLUMNS - 1;

		for (int col = startPoint; col > startPoint - hoehe; col--) {
			for (row = 0; row < LedService.MAX_COLUMNS - col; row++) {
				service.turnLedOn(row * service.MAX_COLUMNS + col);
				service.setDelayInMillis(200);
			}

		}

		for (int col = startPoint - hoehe; col >= startPoint - (2 * hoehe - 2); col--) {
			for (row = 0; row < col - hoehe - 1; row++) {
				service.turnLedOn(row * service.MAX_COLUMNS + col);
				service.setDelayInMillis(200);
			}

		}

	}

	// Aufgabe 8 final

	public static void showTriangle(LedService service) {

		int heightTriangle = 0;

		Scanner sc = new Scanner(System.in);

		String colorStr = null;

		// Festlegung von gültigen Parametern. Beschränkung bei 8, da für die
		// Gleichschenkiligkeit des Dreiecks die Formel 2*höhe-1 verwendet wird und
		// auf dem Steckbord nur eine Anzahl von 16 Leds je Reihe zur Verfügung stehen.
		// Einlesen der gültigen Parameter,

		do {
			System.out.println("Bitte geben Sie einen gültigen Wert für die Höhe des anzuzeigenden Dreiecks ein:");

			heightTriangle = sc.nextInt();

			if (heightTriangle >= 2 && heightTriangle <= 8) {
				break;
			} else {
				System.out.println("Bitte geben Sie eine geeignete Höhe zwischen 2 und 8 ein.");
			}

		} while (true);

		// Nach erfolgreiche Eingabe der Parameter wird die entsprechende Anzahl an Leds
		// hinzugefügt.

		int anzahlLeds = 0;

		anzahlLeds = heightTriangle * LedService.MAX_ROWS;

		service.addLeds(anzahlLeds);

		// Definition von Eckdaten für die Abbildung des Dreiecks.

		int startPoint = 0;

		startPoint = LedService.MAX_COLUMNS - 1;

		int row = 0;

		int col = 0;

		int endPoint = 0;

		// Abbildung der ersten Hälfte des Dreiecks.

		for (col = startPoint; col > startPoint - heightTriangle; col--) {
			for (row = 0; row < LedService.MAX_COLUMNS - col; row++) {
				service.turnLedOn(row * service.MAX_COLUMNS + col);
				service.setDelayInMillis(50);
			}

		}
		int colZwei = startPoint - heightTriangle;
		for (col = startPoint - heightTriangle; col > 0; col--) {
			colZwei++;
			;
			for (row = 0; row < LedService.MAX_COLUMNS - colZwei; row++) {

				service.turnLedOn(row * service.MAX_COLUMNS + col - 16);
				service.setDelayInMillis(50);
			}
		}

		// Abbildung der zweiten Hälfte des Dreiecks. Hier ist das Problem

		/*
		 * for (col = startPoint - heightTriangle; col >= startPoint - (2 *
		 * heightTriangle - 2); col--) { for (row = 0; row <= col % (heightTriangle -
		 * 1); row++) {
		 * 
		 * service.turnLedOn(row * service.MAX_COLUMNS + col);
		 * service.setDelayInMillis(50); }
		 * 
		 * }
		 */
	}

	// Aufgabe 9
	public static void siebDesEratothenes(LedService service) {

		service.addLeds(LedService.MAX_NUMBER_OF_LEDS);

		for (int i = 0; i < LedService.MAX_NUMBER_OF_LEDS; i++) {
			service.turnLedOn(i);
		}
		service.turnLedOff(1);
		for (int i = 0; i < LedService.MAX_NUMBER_OF_LEDS; i++) {
			if (i % 2 == 0 && i != 2) {
				service.turnLedOff(i);
			}
		}

		for (int i = 3; i < LedService.MAX_NUMBER_OF_LEDS; i++) {
			if (service.isOn(i)) {
				for (int k = 2; k <= 255; k++) {
					if (k % i == 0 && k != i) {
						service.turnLedOff(k);
						System.out.println(k);
					}
				}
			}
		}

	}

	// Aufgabe 10.1
	public static void countColors(LedService service) {
		int red = 0;
		int green = 0;
		int blue = 0;
		int yellow = 0;

		service.addLeds(LedService.MAX_NUMBER_OF_LEDS, LedColor.RANDOM);
		for (int i = 0; i < LedService.MAX_NUMBER_OF_LEDS; i++) {
			service.turnLedOn(i);
		}

		service.stopExecutionFor(2000);

		for (int i = 0; i < LedService.MAX_NUMBER_OF_LEDS; i++) {
			if (service.color(i).equals(LedColor.BLUE)) {
				blue++;
			}
			if (service.color(i).equals(LedColor.RED)) {
				red++;
			}
			if (service.color(i).equals(LedColor.GREEN)) {
				green++;
			}
			if (service.color(i).equals(LedColor.YELLOW)) {
				yellow++;
			}
		}

		System.out.println(">> RED: " + red + " LEDs");
		System.out.println(">> GREEN: " + green + " LEDs");
		System.out.println(">> BLUE: " + blue + " LEDs");
		System.out.println(">> YELLOW: " + yellow + " LEDs");

	}

	// Aufgabe 10.2
	public static void countColorsExt(LedService service) {

		int[] arr = new int[4];
		int[] biggest = new int[4];
		int[] row = new int[4];

		/*
		 * 0 = Blau 1 = Rot 2 = Gruen 3 = Gelb
		 */

		service.addLeds(LedService.MAX_NUMBER_OF_LEDS, LedColor.RANDOM);
		for (int i = 0; i < LedService.MAX_NUMBER_OF_LEDS; i++) {
			service.turnLedOn(i);
		}
		// LedService.MAX_NUMBER_OF_LEDS
		service.stopExecutionFor(2000);

		for (int i = 0; i < LedService.MAX_ROWS; i++) {
			for (int k = 0; k < LedService.MAX_COLUMNS; k++) {
				LedColor color = service.color((i * LedService.MAX_ROWS) + k);
				if (color.equals(LedColor.BLUE)) {
					arr[0]++;
				}
				if (color.equals(LedColor.RED)) {
					arr[1]++;
				}
				if (color.equals(LedColor.GREEN)) {
					arr[2]++;
				}
				if (color.equals(LedColor.YELLOW)) {
					arr[3]++;
				}

			}

			for (int l = 0; l < 4; l++) {
				if (arr[l] >= biggest[l]) {
					biggest[l] = arr[l];
					arr[l] = 0;
					row[l] = i;
				} else {
					arr[l] = 0;
				}

			}

		}

		System.out.println(">> BLUE: " + biggest[0] + " LEDs" + " in der Zeile-Nr. " + row[0]);
		System.out.println(">> RED: " + biggest[1] + " LEDs" + " in der Zeile-Nr. " + row[1]);
		System.out.println(">> GREEN: " + biggest[2] + " LEDs" + " in der Zeile-Nr. " + row[2]);
		System.out.println(">> YELLOW: " + biggest[3] + " LEDs" + " in der Zeile-Nr. " + row[3]);

	}

}
