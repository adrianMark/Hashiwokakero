package darstellung;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;

import listener.StationErzeugenButtonListener;

/**
 * Diese Klasse erbt von JButton und wird bei der Erzeugung von neuen Levels im Level Editor verwendet. So können Stationen auf einem 
 * Spielfeld platziert und ihre Metadaten bearbeitet werden.
 * 
 * @author Adrian
 *
 */

public class StationErzeugenButton extends JButton {

	private static final long serialVersionUID = 1L;
	private int laenge; //Längenposition auf dem Spielfeld
	private int hoehe; //Höhenposition auf dem Spielfeld
	private LevelCreationPanel panel; //Erzeugendes Panel
	private boolean isStation = false; //Repräsentiert eine Station?
	private int ziel = 1; //Ziel der Station; Initialisierung mit Eins
	
	private LineBorder dünn = new LineBorder(new Color(0, 0, 0)); //Rand wenn nicht selektiert
	private LineBorder dick = new LineBorder(Color.RED, 3); //Rand wenn selektiert

	//Konstruktor
	public StationErzeugenButton(LevelCreationPanel panel, int hoehe, int laenge) {
		super();
		this.hoehe = hoehe;
		this.laenge = laenge;
		this.panel = panel;
		setBorder(dünn);
		setBackground(Color.WHITE);
		addActionListener(new StationErzeugenButtonListener(this)); //Fügt einen ActionListener hinzu
		setFocusPainted(false);
		setFont(new Font("Bookman Old Style", Font.BOLD, 10 ));
	}
	
	/**
	 * Diese Methode ändert den Rand des Panels. Wenn der Button ausgewählt wird, wird der Rand dicker und Rot. 
	 * Wenn er vorher bereits selektiert wurde, wird der Standardrand gesetzt.
	 */
	public void organizeBorder() {
		if (getBorder().equals(dünn)) {
			setBorder(dick);
		} else {
			setBorder(dünn);
		}
		repaint();
	}
	/**
	 * Diese Methode ersetzt den vorher selektierten Button in dem Edtor und liefert den Ersetzten Button zur Verarbeitung zurück.
	 * @return - Den ersetzten Button
	 */
	public StationErzeugenButton replaceChosen() {
		StationErzeugenButton previous = panel.getChosen();
		panel.setChosen(this);
		panel.updateStationAnzeige();
		return previous;
	}

	/**
	 * Diese Methode lädt das Icon für Stationen des ausgewähten Themas (in angepasster Größe) und aktualisiert die Metadaten dieser Instanz
	 */
	public void istStation() {
		try {
			setStation(true);
			setText(String.valueOf(ziel));
			BufferedImage image = ImageIO.read(new File(new File("").getAbsolutePath() + "\\themes\\"
					+ String.valueOf(panel.getComboBox().getSelectedItem()) + "\\station.jpg"));
			image = FileHandler.groesseÄndern(image, this.getWidth() - (this.getWidth() / 5),
					this.getHeight() - (this.getHeight() / 5));
			setIcon(new ImageIcon(image));
			repaint();
		} catch (IOException e) {
			JOptionPane.showConfirmDialog(null, "Ein unerwarteter Fehler ist aufgetreten!", "Fehler!",
					JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE);
		}
	}

	//Getter und Setter
	public int getLaenge() {
		return laenge;
	}

	public void setLaenge(int laenge) {
		this.laenge = laenge;
	}

	public int getHoehe() {
		return hoehe;
	}

	public void setHoehe(int hoehe) {
		this.hoehe = hoehe;
	}

	public boolean isStation() {
		return isStation;
	}

	public void setStation(boolean isStation) {
		this.isStation = isStation;
	}

	public LevelCreationPanel getPanel() {
		return panel;
	}

	public void setPanel(LevelCreationPanel panel) {
		this.panel = panel;
	}

	public int getZiel() {
		return ziel;
	}

	public void setZiel(int ziel, boolean update) {
		this.ziel = ziel;
		if (update) {
			setText(String.valueOf(ziel)); //wenn gewünscht, soll der Text des Buttons an das neue Ziel angepasst werden
		}
	}
}
