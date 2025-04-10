package UI;

import java.util.InputMismatchException;
import java.util.Scanner;

import Logik.BlackJackSpiel;

public class UserInterface {
	
	private BlackJackSpiel bjs;

	public UserInterface(BlackJackSpiel bjs) {

		if(bjs != null) {
			this.bjs = bjs;
			spiel();
		}else{
			System.out.println("Spiel konnte nicht gestartet werden.");
		}	
	}
	
	private void spiel() {
		
		Scanner s = new Scanner(System.in);
		
		ermittleSpieler(s);

		if (bjs.pruefeBlackJack()) {
			System.out.println("Es wurde ein Blackjack erreicht:\n" + bjs.ausgabeErgebnisse());
			System.exit(0);
		}

		while (!bjs.isEnde()) {
			
			//Hier Dealer Logik aufrufen
			
			for(int i = 0;i<bjs.getAnzSpieler();i++) {
				
				System.out.println(bjs.getSpieler().get(i).getName()+",\nWas ist ihr nächster Zug? (Hit/Stand/Double Down/Split)");
				
				try {
					
					String input = s.nextLine();

					if (input.equalsIgnoreCase("Hit")) {
						
						erfasseEinsatz(s, i);
						
						bjs.fuegeKarteHinzu(bjs.getSpieler().get(i));
						
						System.out.println("Ihr aktueller Stand:\n" +bjs.getSpieler().get(i).toString());
						
					} else if (input.equalsIgnoreCase("Stand")) {
						
						continue;
						
					} else if (input.equalsIgnoreCase("Double Down")) {
						
						//Einsatz verdoppeln
						
					} else if (input.equalsIgnoreCase("Split")) {
						
						//zwei identische Karten aufteilen und mit diesen beiden Händen weiterspielen
						
					} else {
						throw new InputMismatchException();
					}

					if (bjs.pruefeEnde(bjs.getSpieler().get(i))) {
						bjs.verteilePunkteNeu(bjs.getSpieler().get(i)); //hier wird eventuell ein Ass Wert neu belegt
						System.out.println("Es wurden 21 oder mehr Punkte erreicht, der Versuch ist beendet!\nDas Ergebnis ist: "+bjs.ausgabePunkteKarte(bjs.getSpieler().get(i)));
						break;
					}

				} catch (InputMismatchException e) {
					System.err.println("Ungültige Eingabe, versuchen Sie es erneut!");
					i--;
				} catch (RuntimeException re) {
					System.err.println("Es sind bereits alle Karten vergeben oder der aktuelle Spieler kann keine Karte mehr ziehen!");
				}
			}

			
		}
	}
	
	private void erfasseEinsatz(Scanner s, int id) {
		System.out.println("Was ist ihr Einsatz?");
		
		try {
			int ein = Integer.parseInt(s.nextLine());
			
			bjs.getSpieler().get(id).setEinsatz(ein);
			
		}catch(InputMismatchException e) {
			System.err.println("Dieser Einsatz ist nicht möglich!");
			erfasseEinsatz(s, id);
		}
	}
	
	private void ermittleSpieler(Scanner s) {
		
		for(int i=0;i<7;i++) {
			
			System.out.println("Wollen Sie einen weiteren Spieler hinzufügen?(y/n)");
			String input = s.nextLine();
			
			if(input.equalsIgnoreCase("y")) {
				
				System.out.println("Geben Sie einen Namen ein:");
				String name = s.nextLine();
				
				//gibt direkt auch die erhaltenen Karten aus
				System.out.println(bjs.erstelleSpieler(name).toString());
				
				erfasseEinsatz(s, i);
				
				System.out.println("Ihr aktueller Stand:\n" +bjs.getSpieler().get(i).toString());
				
			}else {
				System.out.println("Hinzufügen von Spielern wurde beendet.");
				break;
			}
		}
	}
}
