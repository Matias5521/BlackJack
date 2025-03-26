package Logik;
import java.util.ArrayList;

public class Hand {
	
	private ArrayList<Karte> karten = new ArrayList<>();
	private Kartenstapel ks;
	
	protected Hand(Kartenstapel ks) {
		
		if(ks != null) this.ks = ks;
		for(int i=0;i<2;i++) {
			addKarte();
		}
		
	}
	
	public String toString() {
		String r = "Der Spieler verfügt über folgende Karten:\n";
		for(Karte k: karten) {
			r+=k.toString()+"\n";
		}
		return r;
	}
	
	public void addKarte() {
		if(karten.size() < 10) {
			Karte k = ks.getKarte();
			karten.add(k);
		}
		else throw new RuntimeException();
	}
	
	public int getPunkte() {
		int punkte = 0;
		for(Karte k: karten) {
			punkte += k.getPunkte();
		}
		return punkte;
	}
	
	public boolean isBlackJack() {
		return karten.size() == 2 && getPunkte() == 21;
	}

}
