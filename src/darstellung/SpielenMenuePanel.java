package darstellung;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;

public class SpielenMenuePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private SubMenuePanelLevelWechsler defaultLevelWechsler;
	private UserLevelWechsler eigeneLevelWechsler;

	public SpielenMenuePanel(GUI gui) {
		setBounds(0, 0, 1084, 661);
		setLayout(null);
		setBackground(Color.WHITE);
		File f = new File("");
		JLabel schrift = new JLabel(new ImageIcon(f.getAbsolutePath() + "\\images\\label.jpg"));
		schrift.setBounds(10, 68, 1064, 66);
		add(schrift);

		JButton btnHauptmenue = new JButton("Hauptmen\u00FC");
		btnHauptmenue.setToolTipText("Zur\u00FCck zum Hauptmen\u00FC");
		btnHauptmenue.setBounds(145, 215, 120, 32);
		btnHauptmenue.setFont(new Font("Bookman Old Style", Font.BOLD, 12));
		add(btnHauptmenue);

		JButton btnZufallslevel = new JButton("Zufallslevel");
		btnZufallslevel.setToolTipText("Spiele ein zuf\u00E4lliges Level");
		btnZufallslevel.setBounds(145, 315, 120, 32);
		btnZufallslevel.setFont(new Font("Bookman Old Style", Font.BOLD, 12));
		add(btnZufallslevel);

		JButton btnLevelWaehlen = new JButton("Level w\u00E4hlen");
		btnLevelWaehlen.setToolTipText("Spiele eines der Standardlevel");
		btnLevelWaehlen.setBounds(145, 415, 120, 32);
		btnLevelWaehlen.setFont(new Font("Bookman Old Style", Font.BOLD, 12));
		add(btnLevelWaehlen);

		JButton btnImportieren = new JButton("Eigenes Level");
		btnImportieren.setToolTipText("Spiele ein Importiertes oder selbst erstelltes Level");
		btnImportieren.setBounds(145, 515, 120, 32);
		btnImportieren.setFont(new Font("Bookman Old Style", Font.BOLD, 12));
		add(btnImportieren);

		JSeparator separator = new JSeparator();
		separator.setBounds(0, 231, 145, 2);
		add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 331, 145, 2);
		add(separator_1);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(0, 431, 145, 2);
		add(separator_2);

		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(0, 531, 145, 2);
		add(separator_3);

		SubMenuePanelDefault defaultPanel = new SubMenuePanelDefault();
		add(defaultPanel);

		btnHauptmenue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GUI g = (GUI) getParent().getParent().getParent().getParent();
				g.hauptMenueZeigen();
			}
		});

		btnLevelWaehlen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				remove(defaultPanel);
				if (eigeneLevelWechsler != null) {
					remove(eigeneLevelWechsler);
				}
				defaultLevelWechsler = new SubMenuePanelLevelWechsler(gui);
				add(defaultLevelWechsler);
				repaint();
				validate();
			}
		});
		btnImportieren.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				File f = new File(new File("").getAbsolutePath() + "\\levels\\User");
				File[] files = f.listFiles();
				if (files.length == 0) {
					int wahl = JOptionPane.showConfirmDialog(gui,
							"Es scheint, als hättest du noch keine eigenen Level erstellt. Möchtest du zum Editor wechseln?",
							"Fehler", JOptionPane.YES_NO_OPTION);
					if (wahl == 0) {
						gui.levelEditorZeigen();
					}
				} else {
					remove(defaultPanel);
					if (defaultLevelWechsler != null) {
						remove(defaultLevelWechsler);
					}
					eigeneLevelWechsler = new UserLevelWechsler(files, gui);
					add(eigeneLevelWechsler);
					repaint();
					validate();
				}
			}
		});
		btnZufallslevel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				File[] themes = {null};
				File[]levelsUser = {null};
				themes = new File(f.getAbsolutePath()+"\\themes").listFiles();
				levelsUser = new File(f.getAbsolutePath()+"\\levels\\User").listFiles();
				String[] defaultLevels= {f.getAbsolutePath()+"\\levels\\Insel\\leicht",f.getAbsolutePath()+"\\levels\\Insel\\mittel",f.getAbsolutePath()+"\\levels\\Insel\\schwer",f.getAbsolutePath()+"\\levels\\Platine\\leicht",f.getAbsolutePath()+"\\levels\\Platine\\schwer",
						f.getAbsolutePath()+"\\levels\\Platine\\mittel",f.getAbsolutePath()+"\\levels\\Turing\\leicht",f.getAbsolutePath()+"\\levels\\Turing\\mittel",f.getAbsolutePath()+"\\levels\\Turing\\schwer"};
				
				for(int x = 0; x < themes.length;x++) {
					if(themes[x].getName().equals("temp")) {
						themes[x] = themes[0]; //Temp wird nur beim erzeugeen von Designs verwendet
					}
				}
				int level = zufallsZahl(0, (8+(levelsUser.length-1)));
				int theme = zufallsZahl(0, themes.length-1);
				if(level<9) {
					gui.spielStarten(defaultLevels[level], themes[theme].getName());
				}else {
					gui.spielStarten(levelsUser[level-8].getAbsolutePath(), themes[theme].getName());
				}
			}			
		});

	}
	
	public int zufallsZahl(int min, int max) {
			double ran = new Random().nextInt(max+1);
			ran = ran%(max-min+1)+min;
			return (int) ran;
	}

	public SubMenuePanelLevelWechsler getDefaultLevelWechsler() {
		return defaultLevelWechsler;
	}

	public void setDefaultLevelWechsler(SubMenuePanelLevelWechsler defaultLevelWechsler) {
		this.defaultLevelWechsler = defaultLevelWechsler;
	}

	public UserLevelWechsler getEigeneLevelWechsler() {
		return eigeneLevelWechsler;
	}

	public void setEigeneLevelWechsler(UserLevelWechsler eigeneLevelWechsler) {
		this.eigeneLevelWechsler = eigeneLevelWechsler;
	}

}
