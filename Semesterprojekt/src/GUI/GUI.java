package GUI;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.TreeMap;

import javax.swing.JButton;
import javax.swing.JFrame;

import Verarbeitung.TicTacToe;

public class GUI extends JFrame{
	
	private TicTacToe ttt;
	private ArrayList<JButton> buttons = new ArrayList<>();
	
	public GUI(TicTacToe ttt) {
		
		this.ttt = ttt;
		
		this.setTitle("TicTacToe");
		this.setSize(500,500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setLayout(new GridLayout(3,3));
		
		fuelleArrayList();
		
		this.setVisible(true);
		
	}
	
	private void fuelleArrayList() {
		
		for(int i=0;i<9;i++) {
			JButton b = new JButton();
			b.setContentAreaFilled(false);
			b.setFocusPainted(false);
			
			b.setVisible(true);
			buttons.add(b);
			this.add(b);
			
			final int a = i;
			b.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					System.out.println("Button "+buttons.indexOf(b)+" wurde gedrueckt");
					
					TreeMap<Integer, Character> belegteFelder = ttt.getBelegteFelder();
					
					if (belegteFelder.get(buttons.indexOf(b)) == 'X' || belegteFelder.get(buttons.indexOf(b)) == 'O') {
						System.out.println("Feld " + buttons.indexOf(b) + " schon belegt, machen Sie eine erneute Eingabe...");
					} else {
						belegteFelder.put(buttons.indexOf(b), ttt.getAktuellerSpieler());
						b.setName(""+ttt.getAktuellerSpieler());
					}
					
					
					if (ttt.getAktuellerSpieler() == 'X')ttt.setAktuellerSpieler('O');
					else ttt.setAktuellerSpieler('X');
					
				}
			});
		}
	}
}
