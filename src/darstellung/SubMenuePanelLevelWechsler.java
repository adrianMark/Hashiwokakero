package darstellung;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import listener.LevelAufrufenListener;

/**
 * Diese Klasse erlaubt es, eines der neun vorinstallierten Levels zu starten.
 * @author Adrian
 *
 */

public class SubMenuePanelLevelWechsler extends JPanel {

	private static final long serialVersionUID = 1L;

	//Konstruktor
	public SubMenuePanelLevelWechsler(GUI gui) {
		
		setBackground(Color.WHITE);
		setBounds(290, 185, 750, 400);
		setLayout(new GridLayout(3, 3, 3, 3));
		File f = new File("");
		
		//Level Insel - Leicht
		JButton btnInselLeicht = new JButton("Insel - leicht");
		btnInselLeicht.setBackground(Color.WHITE);
		btnInselLeicht.setFocusPainted(false);
		btnInselLeicht.setBorder(null);
		btnInselLeicht.setIcon(new ImageIcon(f.getAbsolutePath()+"\\themes\\Island\\preview.jpg"));
		btnInselLeicht.setFont(new Font("Bookman Old Style", Font.BOLD, 12));
		btnInselLeicht.addActionListener(new LevelAufrufenListener(gui));
		add(btnInselLeicht);
		
		//Level Insel - Mittel
		JButton btnInselMittel = new JButton("Insel - mittel");
		btnInselMittel.setBackground(Color.WHITE);
		btnInselMittel.setFocusPainted(false);
		btnInselMittel.setBorder(null);
		btnInselMittel.setIcon(new ImageIcon(f.getAbsolutePath()+"\\themes\\Island\\preview.jpg"));
		btnInselMittel.setFont(new Font("Bookman Old Style", Font.BOLD, 12));
		btnInselMittel.addActionListener(new LevelAufrufenListener(gui));
		add(btnInselMittel);
		
		//Level Insel - Schwer
		JButton btnInselSchwer = new JButton("Insel - schwer");
		btnInselSchwer.setBackground(Color.WHITE);
		btnInselSchwer.setFocusPainted(false);
		btnInselSchwer.setBorder(null);
		btnInselSchwer.setIcon(new ImageIcon(f.getAbsolutePath()+"\\themes\\Island\\preview.jpg"));
		btnInselSchwer.setFont(new Font("Bookman Old Style", Font.BOLD, 12));
		btnInselSchwer.addActionListener(new LevelAufrufenListener(gui));
		add(btnInselSchwer);
		
		//Level Platinen - Leicht
		JButton btnPlatinenLeicht = new JButton("Platine - leicht");
		btnPlatinenLeicht.setBackground(Color.WHITE);
		btnPlatinenLeicht.setFocusPainted(false);
		btnPlatinenLeicht.setBorder(null);
		btnPlatinenLeicht.setIcon(new ImageIcon(f.getAbsolutePath()+"\\themes\\Platine\\preview.jpg"));
		btnPlatinenLeicht.setFont(new Font("Bookman Old Style", Font.BOLD, 12));
		btnPlatinenLeicht.addActionListener(new LevelAufrufenListener(gui));
		add(btnPlatinenLeicht);
		
		//Level Platinen - Mittel
		JButton btnPlatinenMittel = new JButton("Platine - mittel");
		btnPlatinenMittel.setBackground(Color.WHITE);
		btnPlatinenMittel.setFocusPainted(false);
		btnPlatinenMittel.setBorder(null);
		btnPlatinenMittel.setIcon(new ImageIcon(f.getAbsolutePath()+"\\themes\\Platine\\preview.jpg"));
		btnPlatinenMittel.setFont(new Font("Bookman Old Style", Font.BOLD, 12));
		btnPlatinenMittel.addActionListener(new LevelAufrufenListener(gui));
		add(btnPlatinenMittel);
		
		//Level Platinen - Schwer
		JButton btnPlatinenSchwer = new JButton("Platine - schwer");
		btnPlatinenSchwer.setBackground(Color.WHITE);
		btnPlatinenSchwer.setFocusPainted(false);
		btnPlatinenSchwer.setBorder(null);
		btnPlatinenSchwer.setIcon(new ImageIcon(f.getAbsolutePath()+"\\themes\\Platine\\preview.jpg"));
		btnPlatinenSchwer.setFont(new Font("Bookman Old Style", Font.BOLD, 12));
		btnPlatinenSchwer.addActionListener(new LevelAufrufenListener(gui));
		add(btnPlatinenSchwer);
		
		//Level Turing - Leicht
		JButton btnTuringLeicht = new JButton("Turing - leicht");
		btnTuringLeicht.setBackground(Color.WHITE);
		btnTuringLeicht.setFocusPainted(false);
		btnTuringLeicht.setBorder(null);
		btnTuringLeicht.setIcon(new ImageIcon(f.getAbsolutePath()+"\\themes\\Turing\\preview.jpg"));
		btnTuringLeicht.setFont(new Font("Bookman Old Style", Font.BOLD, 12));
		btnTuringLeicht.addActionListener(new LevelAufrufenListener(gui));
		add(btnTuringLeicht);
		
		//Level Turing - Mittel
		JButton btnTuringMittel = new JButton("Turing - mittel");
		btnTuringMittel.setBackground(Color.WHITE);
		btnTuringMittel.setFocusPainted(false);
		btnTuringMittel.setBorder(null);
		btnTuringMittel.setIcon(new ImageIcon(f.getAbsolutePath()+"\\themes\\Turing\\preview.jpg"));
		btnTuringMittel.setFont(new Font("Bookman Old Style", Font.BOLD, 12));
		btnTuringMittel.addActionListener(new LevelAufrufenListener(gui));
		add(btnTuringMittel);
		
		//Level Turing - Schwer
		JButton btnTuringSchwer = new JButton("Turing - schwer");
		btnTuringSchwer.setBackground(Color.WHITE);
		btnTuringSchwer.setFocusPainted(false);
		btnTuringSchwer.setBorder(null);
		btnTuringSchwer.setIcon(new ImageIcon(f.getAbsolutePath()+"\\themes\\Turing\\preview.jpg"));
		btnTuringSchwer.setFont(new Font("Bookman Old Style", Font.BOLD, 12));
		btnTuringSchwer.addActionListener(new LevelAufrufenListener(gui));
		add(btnTuringSchwer);
	}
}
