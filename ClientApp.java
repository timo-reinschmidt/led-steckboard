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
		// switchRandom(service);
		// showBinary(service);
		// showBorder(service);
		// showSquare(service);
		// showRectangle(service);
		// showTriangle(service);
		// siebDesEratothenes(service);
		// countColors(service);
		 countColorsExt(service);

	}

	// Aufgabe 1
	public static void ledsOnOff(LedService service) {
		// Variablen
		int ledsCount = 0;
		String colorStr = null;
		boolean isTooBig = false;

		// Anzahl LEDs abfragen
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
		// Farbe abfragen
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
				service.setDelayInMillis(50);
			}

			service.stopExecutionFor(250);

			for (int l = ledsCount - 1; l >= 0; l--) {
				service.turnLedOff(l);
				service.setDelayInMillis(50);
			}

			service.stopExecutionFor(250);
		}

		service.stopExecutionFor(2000);
		service.reset();

	}

	// Aufgabe 2
	public static void switchEvenOdd(LedService service) {
		// Variablen
		int ledsCount = 0;
		int counter = 0;
		boolean isSixteenStep = true;
		boolean isThree = true;

		// Anzahl LEDs eingeben
		do {
			System.out.print("Geben sie die Anzahl der LEDs ein (Die Zahl muss durch 16 teilbar sein): ");
			ledsCount = sc.nextInt();

			if (ledsCount % 16 == 0) {
				isSixteenStep = false;
			}

		} while (isSixteenStep);

		service.addLeds(ledsCount, LedColor.BLUE);

		// LEDs an und aus machen in blau
		do {
			for (int i = 0; i < ledsCount; i++) {
				if (i % 2 == 0) {
					service.turnLedOn(i);
					service.setDelayInMillis(50);
				}
			}

			service.stopExecutionFor(1000);

			for (int i = 0; i < ledsCount; i++) {
				if (i % 2 == 0) {
					service.turnLedOff(i);
					service.setDelayInMillis(50);

				} else {
					service.turnLedOn(i);
					service.setDelayInMillis(50);
				}

			}

			service.stopExecutionFor(1000);

			for (int i = 0; i < ledsCount; i++) {
				service.turnLedOff(i);
				service.setDelayInMillis(50);
			}

			service.stopExecutionFor(1000);
			counter++;
			if (counter == 3) {
				isThree = false;
			}
		} while (isThree);

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
			} else {

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
		for (int r = 0; r < 3; r++) {

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
					/*
					 * Zufallszahl zwischen 0 und ledsCount generiert und in randomNummer
					 * gespeichert.
					 */
					randomNummer = random.nextInt(ledsCount);

					// Schauen ob die Nummer schon existiert
					uniqueNumber = true;

					/*
					 * Schleife prüft, ob die generierte Zufallszahl bereits in einem der vorherigen
					 * Array-Elemente existiert, indem sie durch die vorherigen Elemente von arr
					 * geht
					 */
					for (int x = 0; x < i; x++) {

						if (randomNummer == arr[x]) {
							uniqueNumber = false;
							break;
						}
					}

					/*
					 * Schleife wird mindestens einmal ausgeführt, und die Schleife wird so lange
					 * wiederholt, wie uniqueNumber false ist
					 */
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
			/*
			 * Dies ist eine Schleife, die von i = 0 bis i = arr.length - 1 durchläuft,
			 * wobei arr ein Array von LEDs darstellt. Die Schleife geht durch jedes Element
			 * des Arrays.
			 */
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
		int number = 0; // Variable wird initialisiert um eingegebene Zahl zu speicher
		boolean validInput = false;

		while (!validInput) {
			System.out.print("Bitte geben Sie eine positive ganze Zahl ein: ");

			if (sc.hasNextInt()) { // Es wird überprüft, ob die Eingabe eine ganze Zahl ist
				number = sc.nextInt(); // wenn ja, wird diese in der number Variable gespeichert

				if (number > 0) {

					validInput = true;

					// Schritt 2: Umwandlung in eine Binärzahl
					binaryString = Integer.toBinaryString(number);
					// Ausgabe der Binärzahl

					// 3. LED's hinzufuegen wie lang die Binaer Zahl ist
					int binaryLength = binaryString.length();

					// 4.2
					int binaryLengthRounded = (binaryLength + 7) / 8 * 8; // Auf das nächste Vielfache von 8 aufrunden

					service.addLeds(binaryLengthRounded);

					// 4. LEDs einschalten wo der character 1 ist im Binär String
					char characterAt;

					for (int i = 0; i < binaryLength; i++) { // Schleife für jedes Zeichen im Binärstring einmal
																// durchlaufen

						/*
						 * In jeder Iteration der Schleife wird das Zeichen an der Position i im
						 * binaryString in der Variable characterAt gespeichert.
						 */
						characterAt = binaryString.charAt(i);

						if (characterAt == '1') { // Es wird überprüft, ob das Zeichen in characterAt gleich '1' ist

							/*
							 * Wenn das Zeichen '1' ist, wird die Position, an der die LED eingeschaltet
							 * werden soll, berechnet. Diese Position entspricht binaryLength - 1 - i und
							 * basiert auf der Position des '1'-Zeichens im Binärstring.
							 */

							int position = binaryLength - 1 - i;
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

	// Aufgabe 5
	public static void showBorder(LedService service) {

		// Liste für den Rahme

		List<Integer> turnedOn = new ArrayList<Integer>();
		LedColor color = readLedColor();

		// Max Anzahl LEDs eingeben
		service.addLeds(LedService.MAX_NUMBER_OF_LEDS, color);

		service.stopExecutionFor(2000);

		// Rahmen einzeichnen
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

	// Aufgabe 5.2
	private static LedColor readLedColor() {

		// Variabeln
		String colorStr = null;
		boolean isCorrect = false;

		// Farbe abfragen
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

	// Aufgabe 6 ALT
	public static void showSquare(LedService service) {

		// Variablen
		String colorStr = null;
		int topLeft = 0;
		int lengthSide = 0;
		boolean isCorrect = false;

		// Max LEDs hinzufuegen
		service.addLeds(LedService.MAX_NUMBER_OF_LEDS);

		// Wert für TopLeft und Seitenlaenge abfragen
		do {

			System.out.println("Bitte geben Sie einen gültigen Wert für den Punkt \"topLeft\" links zuoberst ein. ");
			topLeft = sc.nextInt();

			System.out.println("Bitte geben Sie einen gültigen Wert für Seitenlänge ein. ");
			lengthSide = sc.nextInt();

			// ?
			if (((topLeft + 1) % LedService.MAX_COLUMNS) + lengthSide < LedService.MAX_COLUMNS
					&& topLeft / LedService.MAX_ROWS >= lengthSide) {
				isCorrect = false;
			}

		} while (isCorrect == true);

		// Eckpunkteberechnung

		int topLeftCol = topLeft % LedService.MAX_ROWS;

		int topLeftRow = topLeft / LedService.MAX_COLUMNS;

		int topRightLength = topLeftCol - lengthSide + 1;

		int bottomRightLength = topLeftRow - lengthSide + 1;

		// Quadrat einzeichnen

		for (int row = topLeftRow; row >= bottomRightLength; row--) {
			for (int col = topLeftCol; col >= topRightLength; col--) {
				service.turnLedOn(row * LedService.MAX_COLUMNS + col);
				service.setDelayInMillis(50);

			}
		}

		for (int row = topLeftRow - 1; row > bottomRightLength; row--) {
			for (int col = topLeftCol - 1; col > topRightLength; col--) {
				service.turnLedOff(row * LedService.MAX_COLUMNS + col);
				service.setDelayInMillis(50);

			}
		}

		// Aufgabe 6.2
		// Diagonale einzeichnen
		int row = topLeftRow;

		for (int col = topLeftCol; col > topLeftCol - lengthSide; col--) {

			service.turnLedOn(row * LedService.MAX_COLUMNS + col);
			service.setDelayInMillis(50);
			row--;
		}
		row++;

		for (int col = topLeftCol; col > topLeftCol - lengthSide; col--) {

			service.turnLedOn(row * LedService.MAX_COLUMNS + col);
			service.setDelayInMillis(50);
			row++;
		}

		service.stopExecutionFor(5000);
		// service.reset();
	}

	// Aufgabe 7
	public static void showRectangle(LedService service) {

		String colorStr = null;
		boolean isCorrect = true;
		boolean isCorrectTwo = true;
		int topLeft = 0;
		int bottomRight = 0;

		// Eingabe der Farbe des Leds für das Steckbord.
		// Überprüfung, ob die Farbe, richtig eingegeben wurde.
		// Sofern die Eingabe nicht mit den Parametern übereinstimmt, wird der User
		// aufgefordert, diese neu einzugeben.

		do {
			System.out.print("Geben Sie für die gewünschte Farbe den entsprechenden Ausdruck ein\n"
					+ "Zur Auswahl steht: \"RED\" \"GREEN\" \"BLUE\" \"YELLOW\" und \"RANDOM\" für eine zufällige Auswahl: ");

			colorStr = sc.next();

			if (colorStr.equals("RED") || colorStr.equals("GREEN") || colorStr.equals("BLUE")
					|| colorStr.equals("YELLOW") || colorStr.equals("RANDOM")) {
				isCorrect = false;
			}
		} while (isCorrect);

		// Hinizufügen der Leds und der Farbe dieser.

		service.addLeds(LedService.MAX_NUMBER_OF_LEDS, LedColor.valueOf(colorStr));

		// Einlesen der Punkte TopLeft und BottomRight.

		// Kontrollschleife, ob die eingegebenen Werte benutzt werden können, um ein
		// Rechteck abzubilden.
		// Kontrolle, ob der eingegebene Punkt TopLeft tatsächlich links von
		// BottomRight.
		// Kontrolle, ob der Punkt TopLeft auch auf dem Steckbord unter der Beschränkung
		// der maximalen Anzahl der Leds abgebildet werden kann.

		do {
			System.out.print("Bitte geben Sie einen gültigen Wert für den Punkt \"topLeft\" links zuoberst ein: ");
			topLeft = sc.nextInt();

			System.out
					.print("Bitte geben Sie einen gültigen Wert für den Punkt \"bottomRight\" rechts zuunterst ein: ");
			bottomRight = sc.nextInt();

			if (topLeft + 32 >= bottomRight && topLeft <= LedService.MAX_NUMBER_OF_LEDS
					&& topLeft % LedService.MAX_ROWS > bottomRight % LedService.MAX_ROWS) {
				isCorrectTwo = false;
			}
		} while (isCorrectTwo);

		// Ableitung der zweidimensionalen Navigationspunkte für den Ort über Reihen und
		// Spalten für beide eingegebenen Werte.

		int topLeftCol = topLeft % LedService.MAX_ROWS;
		// Gibt die Spaltenzahl von TopLeft aus.

		int topLeftRow = topLeft / LedService.MAX_COLUMNS;
		// Gibt die Reihenzahl von TopLeft aus.

		int bottomRightCol = bottomRight % LedService.MAX_ROWS;
		// Gibt die Spaltenzahl von BottomRight aus.

		int bottomRightRow = bottomRight / LedService.MAX_COLUMNS;
		// Gibt die Reihenzahl von BottomRight aus.

		// In der ersten Schleife werden die einzelnen Reihen von oben nach unten
		// durchgegangen.
		// In der inneren Schleife werden je Reihe die Spalten von Links nach Rechts
		// durchgegangen.
		// Über den Service und die Formel row * 16 + col wird bestimmt, welche Led auf
		// dem Steckbord angemacht wird.
		// Durch die definierten Punkte wird somit das Rechteck ausgefüllt, da zumal von
		// oben nach unten und von links nach rechts durchgezählt wird.
		for (int row = topLeftRow; row >= bottomRightRow; row--) {
			for (int col = topLeftCol; col >= bottomRightCol; col--) {
				service.turnLedOn(row * LedService.MAX_COLUMNS + col);
				service.setDelayInMillis(75);
			}
		}

		service.stopExecutionFor(3000);

		// Für das Ausschalten wurde zusätzlich der Parameter -1 hinzugefügt und über
		// den Service turnOff die Leds ausgeschaltet.
		// Wegen dem Parameter -1 beginnt der Code im Inneren des Rechtecks (da die
		// Zählung eine Reihe darunter und eine Spalte weiter rechtsbeginnt.
		// Über die -1 und die Operatore > statt >= wird gewährleistet, dass der Rahmen
		// nicht ausgeschaltet wird.
		for (int row = topLeftRow - 1; row > bottomRightRow; row--) {
			for (int col = topLeftCol - 1; col > bottomRightCol; col--) {
				service.turnLedOff(row * LedService.MAX_COLUMNS + col);
				service.setDelayInMillis(75);

			}
		}

		service.stopExecutionFor(3000);

		service.reset();
	}

	// Aufgabe 8
	public static void showTriangle(LedService service) {

		int heightTriangle = 0;
		String colorStr = null;
		boolean isCorrect = true;
		boolean isCorrectTwo = true;
		int anzahlLeds = 0;
		int startPoint = 0;
		int row = 0;
		int col = 0;

		do {
			System.out.print(
					"Geben Sie für die gewünschte Farbe den entsprechenden Ausdruck ein\nZur Auswahl steht: \"RED\" \"GREEN\" \"BLUE\" \"YELLOW\" und \"RANDOM\" für eine zufällige Auswahl: ");

			colorStr = sc.next();

			if (colorStr.equals("RED") || colorStr.equals("GREEN") || colorStr.equals("BLUE")
					|| colorStr.equals("YELLOW") || colorStr.equals("RANDOM")) {

				isCorrect = false;
			}

		} while (isCorrect);

		// Festlegung von gültigen Parametern. Beschränkung bei 8, da für die
		// Gleichschenkiligkeit des Dreiecks die Formel 2*höhe-1 verwendet wird und
		// auf dem Steckbord nur eine Anzahl von 16 Leds je Reihe zur Verfügung stehen.
		// Einlesen der gültigen Parameter,

		do {
			System.out.print("Bitte geben Sie einen gültigen Wert für die Höhe des anzuzeigenden Dreiecks ein: ");

			heightTriangle = sc.nextInt();

			if (heightTriangle >= 2 && heightTriangle <= 8) {
				isCorrectTwo = false;
			}

		} while (isCorrectTwo);

		// Nach erfolgreiche Eingabe der Parameter wird die entsprechende Anzahl an Leds
		// hinzugefügt.

		anzahlLeds = heightTriangle * LedService.MAX_ROWS;

		service.addLeds(anzahlLeds, LedColor.valueOf(colorStr));

		// Definition von Eckdaten für die Abbildung des Dreiecks.

		startPoint = LedService.MAX_COLUMNS - 1;

		// Abbildung der ersten Hälfte des Dreiecks.

		for (col = startPoint; col > startPoint - heightTriangle; col--) {
			for (row = 0; row < LedService.MAX_COLUMNS - col; row++) {
				service.turnLedOn(row * LedService.MAX_COLUMNS + col);
				service.setDelayInMillis(50);
			}

		}

		int colSecond = startPoint - heightTriangle;

		for (col = startPoint - heightTriangle; col > 0; col--) {
			colSecond++;

			for (row = 0; row < LedService.MAX_COLUMNS - colSecond; row++) {

				service.turnLedOn(row * LedService.MAX_COLUMNS + col - LedService.MAX_COLUMNS);
				service.setDelayInMillis(50);
			}
		}
		service.stopExecutionFor(5000);

		service.reset();
	}

	// Aufgabe 9
	public static void siebDesEratothenes(LedService service) {
		// Max LEDs hinzufuegen
		service.addLeds(LedService.MAX_NUMBER_OF_LEDS);
		
		// Alle LEDs an
		for (int i = 0; i < LedService.MAX_NUMBER_OF_LEDS; i++) {
			service.turnLedOn(i);
		}
		
		// 1 ist keine Primzahl
		service.turnLedOff(1);
		
		// Schalte aus wenn es teilbar durch 2 ist und nicht die Zahl 2 ist
		for (int i = 0; i < LedService.MAX_NUMBER_OF_LEDS; i++) {
			if (i % 2 == 0 && i != 2) {
				service.turnLedOff(i);
			}
		}
		
		// Schalte alle LEDs aus die ein vielfaches von i sind aber nicht die Zahl i sind
		for (int i = 3; i < LedService.MAX_NUMBER_OF_LEDS; i++) {
			if (service.isOn(i)) {
				for (int k = 2; k <= 255; k++) {
					if (k % i == 0 && k != i) {
						service.turnLedOff(k);
					}
				}
			}
		}

	}

	// Aufgabe 10.1
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
	// Aufgabe 10.2
	public static void countColorsExt(LedService service) {
		// Arrays erstellen
		int[] arr = new int[4];
		int[] biggest = new int[4];
		int[] row = new int[4];

		/*
		 * 0 = Blau 1 = Rot 2 = Gruen 3 = Gelb
		 */
		
		// Alle LEDs an
		service.addLeds(LedService.MAX_NUMBER_OF_LEDS, LedColor.RANDOM);
		for (int i = 0; i < LedService.MAX_NUMBER_OF_LEDS; i++) {
			service.turnLedOn(i);
		}
	
		service.stopExecutionFor(2000);

		// Fuer jede Farbe zaehlen 
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
		// Die Zeile mit den meisten LEDs der jeweiligen Farbe abspeichern
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
		
		// Ranking ausgeben

		System.out.println(">> BLUE: " + biggest[0] + " LEDs" + " in der Zeile-Nr. " + row[0]);
		System.out.println(">> RED: " + biggest[1] + " LEDs" + " in der Zeile-Nr. " + row[1]);
		System.out.println(">> GREEN: " + biggest[2] + " LEDs" + " in der Zeile-Nr. " + row[2]);
		System.out.println(">> YELLOW: " + biggest[3] + " LEDs" + " in der Zeile-Nr. " + row[3]);

	}

}
