package darstellung;
import javax.swing.JPanel;


import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import logik.Gitter;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class MenueBarImSpiel extends JPanel {

	private static final long serialVersionUID = 1L;

	public MenueBarImSpiel(String levelName, SpielPanel panel) {
		setBackground(Color.WHITE);
		setBounds(900,0,184,661);
		setLayout(null);
		
		JButton btnPruefen = new JButton("Pr\u00FCfen");
		btnPruefen.setBackground(Color.WHITE);
		btnPruefen.setFont(new Font("Bookman Old Style", Font.BOLD, 10));
		btnPruefen.setBounds(48, 227, 84, 22);
		add(btnPruefen);
		
		JLabel label = new JLabel(levelName);
		label.setBackground(Color.WHITE);
		label.setFont(new Font("Bookman Old Style", Font.BOLD, 15));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(10, 70, 164, 46);
		add(label);
		
		JButton btnBeenden = new JButton("Beenden");
		btnBeenden.setBackground(Color.WHITE);
		btnBeenden.setFont(new Font("Bookman Old Style", Font.BOLD, 10));
		btnBeenden.setBounds(47, 414, 85, 21);
		add(btnBeenden);
		
		JButton btnNeustart = new JButton("Neustart");
		btnNeustart.setBackground(Color.WHITE);
		btnNeustart.setFont(new Font("Bookman Old Style", Font.BOLD, 10));
		btnNeustart.setBounds(48, 259, 84, 22);
		add(btnNeustart);
		
		btnPruefen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Gitter g = panel.getGitter();
				boolean alleRichtig = true;
				for(int x = 0; x<g.getHoehe()-1;x++) {
					for(int y = 0; y<g.getLaenge()-1;y++) {
						if(g.getStation(x, y).isAktiv() && !g.getStation(x, y).zielErfuellt()) {
							alleRichtig = false;
						}
					}
				}
				if(alleRichtig) {
					panel.gewonnenDialogZeigen();
				}else {
					panel.verlorenDialogZeigen();
				}
			}
		});
		
		btnBeenden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel.getGui().spieleMenueZeigen();
			}
		});
		btnNeustart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel.getGitter().neuStart();
				panel.update();
			}
		});
	}
}
