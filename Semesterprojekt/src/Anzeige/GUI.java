package Anzeige;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Logik.TicTacToe;

public class GUI extends JFrame{
	
	private TicTacToe ttt;
	private ArrayList<JButton> buttons = new ArrayList<>();
	private JButton neuesSpiel = new JButton();
	private JButton konsole = new JButton();
	private boolean spielEnde = false;

	public GUI(TicTacToe ttt) {
		
		this.ttt = ttt;
		
		this.setTitle("TicTacToe");
		this.setSize(500,600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		
		fuelleArrayList();
		
		JPanel jp2 = new JPanel();
		jp2.setLayout(new BorderLayout());
		jp2.setPreferredSize(new Dimension(500,150));
		
		neuesSpiel.setFocusPainted(false);
		neuesSpiel.setFont(new Font("Arial",Font.PLAIN, 30));
		neuesSpiel.setBackground(new Color(65,65,65));
		neuesSpiel.setForeground(Color.WHITE);
		neuesSpiel.setText("Neues Spiel beginnnen?");
		neuesSpiel.setPreferredSize(new Dimension(500,75));
		neuesSpiel.setBorderPainted(false);
		neuesSpiel.addActionListener(new ActionListener() {
		

		@Override
		public void actionPerformed(ActionEvent e) {
			
				ttt.setCounter(0);
				ttt.setAktuellerSpieler('X');
				spielEnde = false;
				ttt.fuelleTreeMap();
				
				
				for(JButton b: buttons) {
					
					b.setForeground(Color.WHITE);
					b.setText("");
					
				}
				
				revalidate();
				repaint();
			}	
		});
		
		jp2.add(neuesSpiel, BorderLayout.NORTH);
		
		konsole.setFocusPainted(false);
		konsole.setFont(new Font("Arial",Font.PLAIN, 30));
		konsole.setBackground(new Color(90,90,90));
		konsole.setForeground(Color.WHITE);
		konsole.setText("Zurück zur Konsole?");
		konsole.setPreferredSize(new Dimension(500,75));
		konsole.setBorderPainted(false);
		konsole.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					dispose();
					ttt.ermittleEingabeAnfang();
					
				} catch (Exception e1) {
					
					System.out.println("Eingabe von GUI zur Konsole konnte nicht aufgerufen werden");
				}
			}
			
		});
		
		jp2.add(konsole, BorderLayout.SOUTH);
		
		
		this.add(jp2, BorderLayout.SOUTH);
		this.setVisible(true);
		
	}
	
	private void fuelleArrayList() {
		
		JPanel jp = new JPanel(new GridLayout(3,3));
		
		for(int i = 0; i < 9; i++) {
			
			JButton b = new JButton();
			
			//System.out.println("Button "+i+" wurde erstellt.");
			
			b.setFocusPainted(false);
			b.setFont(new Font("Arial",Font.PLAIN, 70));
			
			if(i%2 == 0) {
				b.setBackground(new Color(90,90,90));
				
			}else {
				b.setBackground(new Color(65,65,65));
			}
			
			b.setForeground(Color.WHITE);
			b.setBorderPainted(false);
			
			b.addActionListener(new ActionListener() {
				
			    @Override
			    public void actionPerformed(ActionEvent e) {
			        SwingUtilities.invokeLater(() -> {
			        	
			            actionListenerInhalt(b);
			            
			        });
			    }
			});
			
			b.setVisible(true);
			
			buttons.add(b);
			jp.add(b);
			this.add(jp);
		}
	}
	
	private void actionListenerInhalt(JButton b) {
		
		int index = buttons.indexOf(b);
        if (!ttt.pruefeBelegtesFeld(index) && !spielEnde) {
            ttt.belegteFelder.put(index, ttt.getAktuellerSpieler());
            b.setText("" + ttt.getAktuellerSpieler());

            if (ttt.getCounter() >= 4 && ttt.pruefeSieg()) {
                spielEnde = true;
                int[] gewinnerIndexe = ttt.getGewinnerIndexe();

                for (int i = 0; i < 3; i++) {
                    buttons.get(gewinnerIndexe[i]).setForeground(Color.RED);
                    buttons.get(gewinnerIndexe[i]).setText("" + ttt.getAktuellerSpieler());
                }
            }

            ttt.setCounter(ttt.getCounter() + 1);

            if (ttt.getAktuellerSpieler() == 'X') {
                ttt.setAktuellerSpieler('O');
            } else {
                ttt.setAktuellerSpieler('X');
            }

            repaint();
        }
	}
}
