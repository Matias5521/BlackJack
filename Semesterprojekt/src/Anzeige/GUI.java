package Anzeige;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Logik.TicTacToe;

public class GUI extends JFrame {

	private TicTacToe ttt;
	private ArrayList<JButton> buttons = new ArrayList<>();
	private JButton neuesSpiel = new JButton();
	private JButton konsole = new JButton();
	private JButton modus = new JButton();

	public GUI(TicTacToe ttt) {

		this.ttt = ttt;

		this.setTitle("TicTacToe");
		this.setSize(500, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());

		ImageIcon img = new ImageIcon(getClass().getResource("/icon.jpg"));
		if (img.getIconWidth() == -1) {
			System.out.println("Bild konnte nicht geladen werden!");
		} else {
			this.setIconImage(img.getImage());
		}

		fuelleArrayList();

		JPanel jp2 = new JPanel();
		jp2.setLayout(new GridLayout(3,1));
		jp2.setPreferredSize(new Dimension(500, 150));

		neuesSpiel.setFocusPainted(false);
		neuesSpiel.setFont(new Font("Arial", Font.PLAIN, 30));
		neuesSpiel.setBackground(new Color(65, 65, 65));
		neuesSpiel.setForeground(Color.WHITE);
		neuesSpiel.setText("Neues Spiel beginnnen?");
		neuesSpiel.setPreferredSize(new Dimension(500, 75));
		neuesSpiel.setBorderPainted(false);
		neuesSpiel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				ttt.resetGame();

				resetGui();

				revalidate();
				repaint();
			}
		});

		jp2.add(neuesSpiel);

		modus.setFocusPainted(false);
		modus.setFont(new Font("Arial", Font.PLAIN, 30));
		modus.setBackground(new Color(90, 90, 90));
		modus.setForeground(Color.WHITE);
		
		if(ttt.getModus() == 1) modus.setText("Modus wechseln? (Player vs. Player aktiv)");
		else if(ttt.getModus() == 2) modus.setText("Modus wechseln? (Player vs. AI aktiv)");
		
		modus.setPreferredSize(new Dimension(500, 75));
		modus.setBorderPainted(false);
		modus.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (ttt.getModus() == 2) {
					modus.setText("Modus wechseln? (Player vs. Player aktiv)");
					ttt.setModus(1);
				} else if (ttt.getModus() == 1) {
					modus.setText("Modus wechseln? (Player vs. AI aktiv)");
					ttt.setModus(2);
				}
				
				for (JButton b : buttons) {

					b.setForeground(Color.WHITE);
					b.setText("");

				}
				
				ttt.resetGame();

				revalidate();
				repaint();
			}
		});

		jp2.add(modus);
		
		konsole.setFocusPainted(false);
		konsole.setFont(new Font("Arial", Font.PLAIN, 30));
		konsole.setBackground(new Color(65, 65, 65));
		konsole.setForeground(Color.WHITE);
		konsole.setText("Zurück zur Konsole?");
		konsole.setPreferredSize(new Dimension(500, 75));
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

		jp2.add(konsole);

		this.add(jp2, BorderLayout.SOUTH);
		this.setVisible(true);

	}

	private void fuelleArrayList() {

		JPanel jp = new JPanel(new GridLayout(3, 3));

		for (int i = 0; i < 9; i++) {

			JButton b = new JButton();

			b.setFocusPainted(false);
			b.setFont(new Font("Arial", Font.PLAIN, 70));

			if (i % 2 == 0) {
				b.setBackground(new Color(90, 90, 90));

			} else {
				b.setBackground(new Color(65, 65, 65));
			}

			b.setForeground(Color.WHITE);
			b.setBorderPainted(false);

			b.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					SwingUtilities.invokeLater(() -> {

						ttt.actionListenerInhalt(b);
						//System.out.println("Button "+buttons.indexOf(b)+" wurde gedrückt!");

					});
				}
			});

			b.setVisible(true);

			buttons.add(b);
			jp.add(b);
			this.add(jp);
		}
	}
	
	public void resetGui(){
		
		for (JButton b : buttons) {

			b.setForeground(Color.WHITE);
			b.setText("");

		}
	}

	public ArrayList<JButton> getButtons() {
		return buttons;
	}

	public void setButtons(ArrayList<JButton> buttons) {
		this.buttons = buttons;
	}
}
