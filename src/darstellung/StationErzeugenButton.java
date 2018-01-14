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
 * Diese Klasse erbt von JButton und wird bei der Erzeugung von neuen Levels im Level Editor verwendet. So k�nnen Stationen auf einem 
 * Spielfeld platziert und ihre Metadaten bearbeitet werden.
 * 
 * @author Adrian
 *
 */

public class StationErzeugenButton extends JButton {

	private static final long serialVersionUID = 1L;
	private int laenge; //L�ngenposition auf dem Spielfeld
	private int hoehe; //H�henposition auf dem Spielfeld
	private LevelCreationPanel panel; //Erzeugendes Panel
	private boolean isStation = false; //Repr�sentiert eine Station?
	private int ziel = 1; //Ziel der Station; Initialisierung mit Eins
	
	private LineBorder d�nn = new LineBorder(new Color(0, 0, 0)); //Rand wenn nicht selektiert
	private LineBorder dick = new LineBorder(Color.RED, 3); //Rand wenn selektiert

	//Konstruktor
	public StationErzeugenButton(LevelCreationPanel panel, int hoehe, int laenge) {
		super();
		this.hoehe = hoehe;
		this.laenge = laenge;
		this.panel = panel;
		setBorder(d�nn);
		setBackground(Color.WHITE);
		addActionListener(new StationErzeugenButtonListener(this)); //F�gt einen ActionListener hinzu
		setFocusPainted(false);
		setFont(new Font("Bookman Old Style", Font.BOLD, 10 ));
	}
	
	/**
	 * Diese Methode �ndert den Rand des Panels. Wenn der Button ausgew�hlt wird, wird der Rand dicker und Rot. 
	 * Wenn er vorher bereits selektiert wurde, wird der Standardrand gesetzt.
	 */
	public void organizeBorder() {
		if (getBorder().equals(d�nn)) {
			setBorder(dick);
		} else {
			setBorder(d�nn);
		}
		repaint();
	}
	/**
	 * Diese Methode ersetzt den vorher selektierten Button in dem Edtor und liefert den Ersetzten Button zur Verarbeitung zur�ck.
	 * @return - Den ersetzten Button
	 */
	public StationErzeugenButton replaceChosen() {
		StationErzeugenButton previous = panel.getChosen();
		panel.setChosen(this);
		panel.updateStationAnzeige();
		return previous;
	}

	/**
	 * Diese Methode l�dt das Icon f�r Stationen des ausgew�hten Themas (in angepasster Gr��e) und aktualisiert die Metadaten dieser Instanz
	 */
	public void istStation() {
		try {
			setStation(true);
			setText(String.valueOf(ziel));
			BufferedImage image = ImageIO.read(new File(new File("").getAbsolutePath() + "\\themes\\"
					+ String.valueOf(panel.getComboBox().getSelectedItem()) + "\\station.jpg"));
			image = FileHandler.groesse�ndern(image, this.getWidth() - (this.getWidth() / 5),
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
			setText(String.valueOf(ziel)); //wenn gew�nscht, soll der Text des Buttons an das neue Ziel angepasst werden
		}
	}
}
