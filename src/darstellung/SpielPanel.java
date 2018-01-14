package darstellung;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import logik.Gitter;
import logik.Station;

/**
 * Diese Klasse erlaubt das Starten von Leveln. Es lädt die Logik des Levels und wendet ein erzwungenes oder im Level vorgesehenes Thema für die Grafik an.
 * @author Adrian
 *
 */

public class SpielPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private Gitter gitter; //Logische Einheit, die die Verarbeitung von Spielzügen übernimmt
	
	//GUI Komponente, die von Methoden oder anderen Klassen genutzt werden müssen.
	private StationButton selectedButton1 = null; //Ein Button, der vom Benutzer angeklickt wurde (Nötig um Verbindungen zw. Stationen herzustellen)
	private StationButton selectedButton2 = null; //Der Button, der nach dem ersten angeklickt wurde (Nötig um Verbindungen zw. Stationen herzustellen)
	private ArrayList<ArrayList<StationButton>> board = new ArrayList<>(); //Zur verwaltung der grafischen Stationen
	
	//Notwendige Informationen für die Erzeugung des SpielPanels
	private int weite = 0;//Weite des Rasters
	private int hoehe = 0;//Hoehe des Rasters
	private int anzahlStationen = 0; //Gesamtzahl an Stationen auf dem Raster
	private String theme = ""; //Thema des Spiels
	private String buffer; //Variable zum Zwischenspeichern von Strings, die aus Dateien gelesen werden und weiter bearbeitet werden.
	private File file;
	private GUI gui;

	
	//Konstruktor
	public SpielPanel(String level, GUI gui, String forceTheme) {
		this.gui = gui;
		setBorder(new LineBorder(new Color(0, 0, 0), 1));
		setBounds(1, 1, 900, 659);
		setBackground(Color.WHITE);
		file = new File(level);
		
		//Level laden
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			try {
				if (forceTheme == null) { //Wenn kein Thema erzwungen werden soll, wird das Thema verwendet, das in der Datei des Levels vorgesehen ist
					theme = br.readLine();
				}else {
					theme = forceTheme;
					br.readLine();
				}
				//Level Informationen werden gelesen
				buffer = br.readLine();
				hoehe = Integer.parseInt(buffer.substring(0, buffer.indexOf(",")));
				weite = Integer.parseInt(buffer.substring(buffer.indexOf(",") + 1, buffer.length()));
				buffer = br.readLine();
				anzahlStationen = Integer.parseInt(buffer);
				gitter = new Gitter(hoehe, weite);
				//Stationen in der logischen Einheit(Gitter) aktivieren
				for (int x = 0; x < anzahlStationen; x++) {
					buffer = br.readLine();
					gitter.stationAktivieren(Integer.parseInt(buffer.substring(0, buffer.indexOf(","))),
							Integer.parseInt(buffer.substring(buffer.indexOf(",") + 1,
									buffer.indexOf(",", buffer.indexOf(",") + 1))),
							Integer.parseInt(buffer.substring(buffer.indexOf(",", buffer.indexOf(",") + 1) + 1,
									buffer.length())));
				}
				br.close();
				//Hintergrundfarbe einrichten
				BufferedReader br2 = new BufferedReader(new FileReader(
						new File(new File("").getAbsolutePath() + "\\themes\\" + theme + "\\background")));
				setBackground(new Color(Integer.parseInt(br2.readLine()), Integer.parseInt(br2.readLine()),
						Integer.parseInt(br2.readLine())));
				br2.close();
				//Stationen auf der Grafischen Anzeige aktivieren
				for (int x = 0; x < hoehe; x++) {
					board.add(new ArrayList<StationButton>());
					for (int y = 0; y < weite; y++) {
						boolean station = gitter.getStation(x, y).isAktiv();
						int ziel = gitter.getStation(x, y).getZiel();
						StationButton button = new StationButton(theme, station, x, y, ziel, this);
						board.get(x).add(button);
						add(button);
					}
				}
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Fehler beim Lesen der Datei!", "Fehler",
						JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Das Level konnte nicht gefunden werden!", "Fehler",
					JOptionPane.ERROR_MESSAGE);
		}
		setLayout(new GridLayout(hoehe, weite));
	}
	
	/**
	 * Diese Methode verbindet zwei zuvor ausgewählte Stationen, nachdem die Regelkonformität des Zugs im logischen {@link Gitter} überprüft wurde.
	 */
	public void verbinde() {
		if(gitter.verbinde(selectedButton1.getHoehe(), selectedButton1.getLaenge(), selectedButton2.getHoehe(),
				selectedButton2.getLaenge())) { //Der Zug wird, wenn möglich, ausgeführt
		update(); //Die Darstellung wird aktualisiert
		}else {
			selectedButton1 = null;
			selectedButton2 = null;
		}
	}

	/**
	 * Diese Methode trennt zwei Stationen voneinander (logisch und grafisch).
	 * @param s - Der Ausgangspunkt der Trennung
	 */
	public void trenne(StationButton s) {
		gitter.trenne(gitter.getStation(s.getHoehe(), s.getLaenge())); //logische Trennung wenn möglich
		update(); //grafische Aktualisierung
	}

	/**
	 * Diese Methode zeigt einen Dialog, der signalisiert, dass alle Stationen auf dem Spielfeld richtig Verbunden worden sind.
	 * Danach springt die Anzeige zurück in das {@link SpielenMenuePanel}
	 */
	public void gewonnenDialogZeigen() {
		JOptionPane.showConfirmDialog(this, "Alle Stationen sind korrekt verbunden!", "Gewonnen",
				JOptionPane.PLAIN_MESSAGE, JOptionPane.INFORMATION_MESSAGE,
				new ImageIcon(new File("").getAbsolutePath() + "\\images\\gewonnen.png"));
		gui.spieleMenueZeigen();
	}

	/**
	 * Diese Methode zeigt einen Dialog, der signalisiert, dass mindestens eine Station nicht richtig verbunden wurde.
	 * Der Spieler kann nach der Bestätigung weiterspielen.
	 * 
	 */
	public void verlorenDialogZeigen() {
		JOptionPane.showConfirmDialog(this, "Deine Lösung ist leider nicht korrekt!", "Leider nicht korrekt",
				JOptionPane.PLAIN_MESSAGE, JOptionPane.INFORMATION_MESSAGE,
				new ImageIcon(new File("").getAbsolutePath() + "\\images\\verloren.png"));
	}
	/**
	 * Diese Methode aktualisiert die grafische Anzeige und übersetzt die Informationen aus dem logischen {@link Gitter} in die grafische Anzeige.
	 */
	public void update() {
		for (int x = 0; x < gitter.getHoehe(); x++) {
			for (int y = 0; y < gitter.getLaenge(); y++) {
				Station s = gitter.getStation(x, y);
				StationButton dieser = board.get(x).get(y);
				if (s.isAktiv()) {
					dieser.setText(String.valueOf(s.getZiel() - s.getVerbindungen())); //Wie viele Verbindungen sind noch nötig um das Ziel zu erfüllen?
				}
				StationButton links = null;
				StationButton oben = null;
				if (s.getHoehe() > 1) {
					oben = board.get(x - 1).get(y);
				}
				if (s.getLaenge() > 1) {
					links = board.get(x).get(y - 1);
				}
				
				//Sucht und trennt die beiden Stationen die verbunden sind
				if (s.isVerbLinks1() && !s.isVerbLinks2()) {
					if (links != null) {
						if (!gitter.getStation(links.getHoehe(), links.getLaenge()).isAktiv()) {
							BufferedImage img;
							try {
								img = ImageIO.read(new File(new File("").getAbsolutePath() + "\\themes\\" + theme
										+ "\\horizontalSingle.jpg"));
								img = FileHandler.groesseÄndern(img, 700 / getGitter().getLaenge(), 600 / getGitter().getHoehe());
								links.setIcon(new ImageIcon(img));
							} catch (IOException e) {
								JOptionPane.showConfirmDialog(null, "Ein unerwarteter Fehler ist aufgetreten!", "Fehler!",
										JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE);
							}
						}
					}
				} else if (s.isVerbLinks1() && s.isVerbLinks2()) {
					if (links != null) {
						if (!gitter.getStation(links.getHoehe(), links.getLaenge()).isAktiv()) {
							BufferedImage img;
							try {
								img = ImageIO.read(new File(new File("").getAbsolutePath() + "\\themes\\" + theme
										+ "\\horizontalDouble.jpg"));
								img = FileHandler.groesseÄndern(img, 700 / getGitter().getLaenge(), 600 / getGitter().getHoehe());
								links.setIcon(new ImageIcon(img));
							} catch (IOException e) {
								JOptionPane.showConfirmDialog(null, "Ein unerwarteter Fehler ist aufgetreten!", "Fehler!",
										JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE);
							}
						}
					}
				}
				if (s.isVerbOben1() && !s.isVerbOben2()) {
					if (oben != null) {
						if (!gitter.getStation(oben.getHoehe(), oben.getLaenge()).isAktiv()) {
							BufferedImage img;
							try {
								img = ImageIO.read(new File(new File("").getAbsolutePath() + "\\themes\\" + theme
										+ "\\verticalSingle.jpg"));
								img = FileHandler.groesseÄndern(img, 700 / getGitter().getLaenge(), 600 / getGitter().getHoehe());
								oben.setIcon(new ImageIcon(img));
							} catch (IOException e) {
								JOptionPane.showConfirmDialog(null, "Ein unerwarteter Fehler ist aufgetreten!", "Fehler!",
										JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE);
							}
						}
					}
				} else if (s.isVerbOben1() && s.isVerbOben2()) {
					if (oben != null) {
						if (!gitter.getStation(oben.getHoehe(), oben.getLaenge()).isAktiv()) {
							BufferedImage img;
							try {
								img = ImageIO.read(new File(new File("").getAbsolutePath() + "\\themes\\" + theme
										+ "\\verticalDouble.jpg"));
								img = FileHandler.groesseÄndern(img, 700 / getGitter().getLaenge(), 600 / getGitter().getHoehe());
								oben.setIcon(new ImageIcon(img));
							} catch (IOException e) {
								JOptionPane.showConfirmDialog(null, "Ein unerwarteter Fehler ist aufgetreten!", "Fehler!",
										JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE);
							}
						}
					}
				}
				if (!s.isVerbLinks1() && !s.isVerbLinks2() && !s.isVerbOben1() && !s.isVerbOben2() && !s.isAktiv()) {
					dieser.setIcon(null);
				}
			}
		}
		repaint();
		validate();
	}
	
	//Getter und Setter
	public StationButton getSelectedButton1() {
		return selectedButton1;
	}

	public void setSelectedButton1(StationButton sb1) {
		this.selectedButton1 = sb1;
	}

	public StationButton getSelectedButton2() {
		return selectedButton2;
	}

	public void setSelectedButton2(StationButton sb2) {
		this.selectedButton2 = sb2;
	}

	public Gitter getGitter() {
		return gitter;
	}

	public void setGitter(Gitter gitter) {
		this.gitter = gitter;
	}

	public GUI getGui() {
		return gui;
	}

	public void setGui(GUI gui) {
		this.gui = gui;
	}

	public ArrayList<ArrayList<StationButton>> getBoard() {
		return board;
	}

	public void setBoard(ArrayList<ArrayList<StationButton>> board) {
		this.board = board;
	}

	public int getWeite() {
		return weite;
	}

	public void setWeite(int weite) {
		this.weite = weite;
	}

	public int getHoehe() {
		return hoehe;
	}

	public void setHoehe(int hoehe) {
		this.hoehe = hoehe;
	}

	public int getAnzahlStationen() {
		return anzahlStationen;
	}

	public void setAnzahlStationen(int anzahlStationen) {
		this.anzahlStationen = anzahlStationen;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getBuffer() {
		return buffer;
	}

	public void setBuffer(String buffer) {
		this.buffer = buffer;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

}
