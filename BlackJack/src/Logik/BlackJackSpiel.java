package Logik;

public class BlackJackSpiel {
	
	private Hand hand;
	private Kartenstapel ks = new Kartenstapel();
	private boolean ende = false;

	public BlackJackSpiel() {
		
	}
	
	public Hand getNeueHand() {
		hand = new Hand(ks);
		return hand;
	}
	
	public Hand getHand() {
		return hand;
	}
	
	public boolean isEnde() {
		return ende;
	}
	
}
