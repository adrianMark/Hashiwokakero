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

import listener.StationListener;

/**
 * Diese Klasse ist von JButton abgeleitet und wird für die grafische Anzeige von Stationen auf dem Spielfeld verwendet.
 * Der Button hat Informationen zu seiner Position und seinem Ziel.
 * @author Adrian
 *
 */


public class StationButton extends JButton {

	private static final long serialVersionUID = 1L;
	
	private int hoehe = 0; //Hoehe auf dem Spielfeld
	private int laenge = 0; //Laenge auf dem Spielfeld
	private int ziel = 0; //Wie viele Verbindungen sind nötig
	
	//SpielPanel, das diesen Button erzeugt
	private SpielPanel panel;

	//Konstruktor
	public StationButton(String theme, boolean station, int hoehe, int laenge, int ziel, SpielPanel gamePanel) {
		super();
		
		File f = new File("");
		this.hoehe = hoehe;
		this.laenge = laenge;
		this.ziel = ziel;
		this.panel = gamePanel;
		
		setBorder(null);
		setBackground(Color.WHITE);
		setFocusPainted(false);
		setOpaque(false);
		
		setFont(new Font("Bookman Old Style", Font.BOLD, 120 / gamePanel.getGitter().getLaenge())); //Dynamische Schriftgröße 
		
		//Setzt die Grafik einer Station und passt die Größe an
		if (station) {
			try {
				BufferedImage img = ImageIO
						.read(new File(f.getAbsolutePath() + "\\themes\\" + theme + "\\station.jpg"));
				img = FileHandler.groesseÄndern(img, 700 / gamePanel.getGitter().getLaenge(),
						600 / gamePanel.getGitter().getHoehe());
				setIcon(new ImageIcon(img));
				setText(String.valueOf(ziel));
			} catch (IOException e) {
				JOptionPane.showConfirmDialog(null, "Ein unerwarteter Fehler ist aufgetreten!", "Fehler!",
						JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE);
			}
		}
		addActionListener(new StationListener(this)); //Fügt einen ActionListener hinzu
	}
	
	/**
	 * Diese Methode speichert diese Instanz als markierten Button in dem {@link SpielPanel}. Wenn dort nun zwei Buttons/Stationen ausgewählt wurden, werden sie
	 * miteinander verbunden und die gespeicherten Buttons zurückgesetzt.
	 */
	public void select() {
		if (panel.getSelectedButton1() == null && panel.getSelectedButton2() == null) {
			panel.setSelectedButton1(this);
		} else if (panel.getSelectedButton1() != null && panel.getSelectedButton2() == null) {
			panel.setSelectedButton2(this);
			panel.verbinde();
			panel.setSelectedButton1(null);
			panel.setSelectedButton2(null);
		}
	}

	/**
	 * Diese Methode entfernt das Icon und trennt den Button von einem anderen.
	 */
	public void deselect() {
		if (getIcon() != null) {
			panel.trenne(this);
		}
	}
	
	/**
	 * Eine überschriebene equals Methode, die die Position von zwei Buttons vergleicht.
	 * @param sb - Zu vergleichender Button
	 * @return - Wahrheitswert
	 */
	public boolean equals(StationButton sb) {
		if (this.hoehe == sb.getHoehe() && this.laenge == sb.getLaenge()) {
			return true;
		} else {
			return false;
		}
	}
	
	//Getter und Setter
	public int getHoehe() {
		return hoehe;
	}

	public void setHoehe(int hoehe) {
		this.hoehe = hoehe;
	}

	public int getLaenge() {
		return laenge;
	}

	public void setLaenge(int laenge) {
		this.laenge = laenge;
	}

	public int getZiel() {
		return ziel;
	}

	public void setZiel(int ziel) {
		this.ziel = ziel;
	}

	public SpielPanel getPanel() {
		return panel;
	}

	public void setPanel(SpielPanel panel) {
		this.panel = panel;
	}
}
