import java.util.InputMismatchException;
import java.util.Scanner;

public class Karte {

	private String farbe, karte;
	private int punkte;

	protected Karte(String farbe, String karte) {
		this.farbe = farbe;
		this.karte = karte;
		weisePunkteZu();
	}

	public String toString() {
		return karte + " in " + farbe + ".";
	}

	private void weisePunkteZu() {

		String[] karte = this.karte.split("-");

		if (karte[1].equals("Bube") || karte[1].equals("Dame") || karte[1].equals("König")) {
			punkte = 10;
		} else if (karte[1].equals("Ass")) {
			punkte = -1; 
		} else {
			punkte = Integer.parseInt(karte[1]);
		}
	}

	public void waehleWertAss() {

		Scanner s = new Scanner(System.in);
		System.out.println("Welchen Wert soll das Ass haben? (nur 1 oder 11 möglich)");
		
		try {
			int wert = Integer.parseInt(s.nextLine());
			
			if (wert != 1 && wert != 11) throw new NumberFormatException();
			else punkte = wert;
			
		} catch (NumberFormatException e) {
			System.out.println("Geben Sie einen korrekten Wert ein...");
			waehleWertAss();
			e.printStackTrace();
		}

	}

	public int getPunkte() {
		return punkte;
	}
}
