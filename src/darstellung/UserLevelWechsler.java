package darstellung;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import listener.LevelAufrufenListener;

import java.awt.GridLayout;

/**
 * Diese Klasse verwaltet die Anzeige und das Starten von Leveln, die vom Benutzer erzeugt oder importiert wordden sind.
 * Wenn noch keine eigenen Level vorhanden sind, kann der Spieler direkt in den Editor Modus springen.
 * @author Adrian
 *
 */

public class UserLevelWechsler extends JPanel {

	private static final long serialVersionUID = 1L;

	//Konstruktor
	public UserLevelWechsler(File[] files, GUI gui) {
		setBackground(Color.WHITE);
		setBounds(290, 185, 750, 400);
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.getHorizontalScrollBar().setUnitIncrement(16);
		scrollPane.setViewportBorder(null);
		scrollPane.setBounds(0, 10, 752, 381);
		add(scrollPane);
		
		JPanel panelAnzeige = new JPanel();
		panelAnzeige.setBorder(null);
		panelAnzeige.setBackground(Color.WHITE);
		scrollPane.setViewportView(panelAnzeige);
		panelAnzeige.setLayout(new GridLayout(1, 1, 0, 0));
		BufferedReader reader;
		String theme = "";
		JButton b;
		
		//User Level laden
		for(int x = 0; x<files.length;x++) {
			b = new JButton();
			String text = files[x].getAbsolutePath();
			text = text.substring(text.lastIndexOf("\\")+1,text.length());
			b.setText(text);
			try {
				reader = new BufferedReader(new FileReader(new File(files[x].getAbsolutePath())));
				theme = reader.readLine();
			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(null, "Das Level konnte nicht gefunden werden!", "Fehler",
						JOptionPane.ERROR_MESSAGE);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Fehler beim Lesen der Datei!", "Fehler",
						JOptionPane.ERROR_MESSAGE);
			}
			b.setIcon(new ImageIcon(new File("").getAbsolutePath()+"\\themes\\"+theme+"\\preview.jpg"));
			b.setBackground(Color.WHITE);
			b.setFocusPainted(false);
			b.setBorder(null);
			b.addActionListener(new LevelAufrufenListener(gui,true)); //Listener zum Starten des Levels
			panelAnzeige.add(b);
		}
	}
}
