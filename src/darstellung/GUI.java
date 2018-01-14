package darstellung;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.awt.Toolkit;


/**
 * Diese Klasse stellt das Hauptfenster der Anwendung dar. Es erbt von JFrame und bietet Methoden, die die verschiedenen Menüs und Komponenten anzeigen können.
 * @author Adrian
 *
 */

public class GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	//Konstruktor
	public GUI() {
		//Icon laden
		setIconImage(Toolkit.getDefaultToolkit().getImage(new File("").getAbsolutePath()+"\\images\\logo.png"));
		setResizable(false); //statische Größe
		setTitle("Hashiwokakero - Code Competition Dezember 2017");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 50, 1100, 700);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		hauptMenueZeigen(); //zeigt beim Start das Hauptmenü
		
		
		//Listener, der beim Schließen die temporären Dateien, die beim Erzeugen von neuen Themen erstellt werden, löscht.
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				File [] temporaryFiles = new File(new File("").getAbsolutePath()+"\\themes\\temp").listFiles();
				for(int x = 0; x<temporaryFiles.length;x++) {
					temporaryFiles[x].deleteOnExit();
				}
			}
		});
		}
	
	
	/**
	 * Diese Methode zeigt das Menü, das die verschiedenen Möglichkeiten ein Rätsel zu starten enthält.
	 */
	public void spieleMenueZeigen() {
		contentPane.removeAll();
		contentPane.add(new SpielenMenuePanel(this));
        repaint();
	}
	
	/**
	 * Diese Methode zeigt das Hauptmenü des Spiels an.
	 */
	public void hauptMenueZeigen() {
		contentPane.removeAll();
		contentPane.add(new HauptMenuePanel(this));
		repaint();
	}
	/**
	 * Diese Methode zeigt das Editor Menü, das zu den Editoren für Levels und Themen führt.
	 */
	public void editorMenueZeigen() {
		contentPane.removeAll();
		contentPane.add(new EditorMenue(this));
		repaint();
	}
	
	/**
	 * Diese Methode startet ein Rätsel/Level und kann die Anwendung eines gewählten Themas erzwingen.
	 * @param level - Der Pfad zum gewünschten Level.
	 * @param forceTheme - Der Name des Themas, das erzwungen werden soll. Null wenn das Thema des Levels genutzt werden soll.
	 */
	public void spielStarten(String level, String forceTheme) {
		contentPane.removeAll();
		SpielPanel panel = new SpielPanel(level,this,forceTheme);
		contentPane.add(panel);
		String name = level.substring(level.indexOf("levels")+7,level.length()).replace("\\", " ");
		name = name.replace("User", "");
		contentPane.add(new MenueBarImSpiel(name,panel));
		repaint();
		validate();
	}
	
	/**
	 * Diese Methode startet den Editor, der es erlaubt neue Themen zu errstellen.
	 */
	public void  themaEditorZeigen() {
		contentPane.removeAll();
		contentPane.add(new ThemaCreationPanel(this));
		repaint();
		validate();
	}
	
	/**
	 * Diese Methode startet den Editor, der es erlaubt neue Level zu errstellen.
	 */
	public void  levelEditorZeigen() {
		contentPane.removeAll();
		contentPane.add(new LevelCreationPanel(this));
		repaint();
		validate();
	}

}
