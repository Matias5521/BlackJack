import java.util.*;

public class BlackJackSpiel {
	
	private Hand hand;
	private Kartenstapel ks = new Kartenstapel();
	private boolean ende = false;

	public BlackJackSpiel(Kartenstapel ks) {
		
		System.out.println("Das Spiel wurde gestartet.\nEs kann nur ein Spieler spielen und dass auch nur einmal\n Um erneut zu spielen muss das Programm neu gestartet werden.");
		
		getNeueHand();
		
		Scanner s = new Scanner(System.in);
		
		while(!ende) {
			
			if (hand.isBlackJack()) {
				System.out.println("Es wurde ein Blackjack erreicht:\n"+hand.toString());
				System.exit(0);
			}
			
			System.out.println("Wollen Sie eine weitere Karte ziehen? (j/n)");
			
			try {
				String input = s.nextLine();
				
				if(input.equalsIgnoreCase("j")) {
					hand.addKarte();
				}else if(input.equalsIgnoreCase("n")) {
					System.out.println("Das Ergebnis des Versuchs ist: "+hand.getPunkte());
					break;
				}else{
					throw new InputMismatchException();
				}
				
				System.out.println("Ihr aktueller Stand:\n"+hand.toString());
				
				if(hand.getPunkte() >= 21) {
					System.out.println("Es wurden 21 oder mehr Punkte erreicht, der Versuch ist beendet!"); 
					break;
				}
					
			}catch(InputMismatchException e){
				System.out.println("Falsche Eingabe, versuchen Sie es erneut!");
			}catch(RuntimeException e) {
				System.out.println("Der nächste Spieler ist dran oder es sind keine Karten mehr übrig.");
				break;
			}finally {
				System.out.println("Ihre aktuellen Punkte: "+hand.getPunkte());
				
			}
		}
		
	}
	
	public Hand getNeueHand() {
		System.out.println("Ihre Hand:");
		hand = new Hand(ks);
		System.out.println(hand.toString());
		return hand;
	}
	
}
