package darstellung;

import java.awt.Color;
import java.io.File;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Diese Klasse dient als Menü im Editor Modus. Es bietet dem Benutzer die Auswahl zwischen der Erstellung eines neuen Levels oder Designs.
 * @author Adrian
 *
 */

public class EditorMenue extends JPanel {

	private static final long serialVersionUID = 1L;

	//Konstruktor
	public EditorMenue(GUI gui) {
		setBounds(0, 0, 1084, 661);
		setLayout(null);
		setBackground(Color.WHITE);
		File f = new File("");
		
		//Schriftzug des Spiels
		JLabel schrift = new JLabel(new ImageIcon(f.getAbsolutePath()+"\\images\\label.jpg"));
		schrift.setBounds(10, 20, 1064, 66);
		add(schrift);
		
		//Zurück Button
		JButton btnBack = new JButton("Hauptmen\u00FC");
		btnBack.setToolTipText("Zurück zum Hauptmenü");
		btnBack.setFont(new Font("Bookman Old Style", Font.BOLD, 12));
		btnBack.setBounds(475, 595, 126, 33);
		add(btnBack);
		
		//Button zum Thema erzeugen
		JButton btnThema = new JButton("Neues Thema");
		btnThema.setBackground(new Color(75,198,255));
		btnThema.setFocusPainted(false);
		setBorder(null);
		btnThema.setBounds(10, 130, 524, 398);
		btnThema.setToolTipText("Erstelle oder importiere neue Designs!");
		btnThema.setFont(new Font("Bookman Old Style", Font.BOLD, 17));
		btnThema.setIcon(new ImageIcon(f.getAbsolutePath()+"\\themes\\Island\\station.jpg"));
		add(btnThema);
		
		//Button zum Level erzeugen
		JButton buttonLevel = new JButton("Neues Level");
		buttonLevel.setBackground(Color.WHITE);
		buttonLevel.setFocusPainted(false);
		buttonLevel.setBorder(null);
		buttonLevel.setBounds(550, 130, 524, 398);
		buttonLevel.setToolTipText("Erstelle oder importiere neue, spannende Levels!");
		buttonLevel.setIcon(new ImageIcon(f.getAbsolutePath()+"\\images\\level.jpg"));
		buttonLevel.setFont(new Font("Bookman Old Style", Font.BOLD, 17));
		add(buttonLevel);
		
		//Listener
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				gui.hauptMenueZeigen();
			}
		});
		btnThema.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				gui.themaEditorZeigen();
			}
		});
		buttonLevel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				gui.levelEditorZeigen();
			}
		});
	}
}
