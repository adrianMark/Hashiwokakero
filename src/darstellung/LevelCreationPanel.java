package darstellung;

import java.awt.Color;
import java.awt.Component;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SpinnerNumberModel;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.Font;

public class LevelCreationPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textFieldName;
	private JPanel panelSpielfeld = new JPanel();
	private JTextField textFieldStation = new JTextField();
	private StationErzeugenButton chosen = null;
	private JComboBox<String> comboBoxThema = new JComboBox<String>();
	private JSpinner spinnerZiel = new JSpinner();
	private JSpinner spinnerHoehe = new JSpinner();
	private JSpinner spinnerLaenge = new JSpinner();
	private ArrayList<ArrayList<StationErzeugenButton>> buttons = new ArrayList<>();

	public LevelCreationPanel(GUI gui) {
		LevelCreationPanel self = this; // Nötig, um es an den Netzwerkdialog weiterzureichen
		setBackground(Color.WHITE);
		setBounds(0, 0, 1084, 661);
		setLayout(null);
		panelSpielfeld.setBackground(Color.WHITE);
		panelSpielfeld.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelSpielfeld.setBounds(10, 10, 839, 641);
		panelSetzen(5, 5);
		add(panelSpielfeld);

		JPanel panelMenueBar = new JPanel();
		panelMenueBar.setBackground(Color.WHITE);
		panelMenueBar.setBounds(859, 10, 215, 641);
		add(panelMenueBar);
		panelMenueBar.setLayout(null);

		JLabel lblThema = new JLabel("Thema");
		lblThema.setFont(new Font("Bookman Old Style", Font.BOLD, 10));
		lblThema.setHorizontalAlignment(SwingConstants.CENTER);
		lblThema.setBounds(32, 122, 159, 13);
		panelMenueBar.add(lblThema);
		comboBoxThema.setFont(new Font("Bookman Old Style", Font.PLAIN, 10));

		comboBoxThema.setBackground(Color.WHITE);
		File[] files = new File(new File("").getAbsolutePath() + "\\themes").listFiles();
		for (int x = 0; x < files.length; x++) {
			if (!files[x].getName().equals("temp")) {
				comboBoxThema.addItem(files[x].getName());
			}
		}
		comboBoxThema.setBounds(32, 145, 159, 21);
		panelMenueBar.add(comboBoxThema);

		JLabel lblDimensionen = new JLabel("Dimensionen");
		lblDimensionen.setFont(new Font("Bookman Old Style", Font.BOLD, 10));
		lblDimensionen.setBounds(23, 195, 95, 13);
		panelMenueBar.add(lblDimensionen);

		JLabel lblHhe = new JLabel("H\u00F6he");
		lblHhe.setFont(new Font("Bookman Old Style", Font.PLAIN, 10));
		lblHhe.setBounds(48, 229, 45, 13);
		panelMenueBar.add(lblHhe);

		JLabel label = new JLabel("L\u00E4nge");
		label.setFont(new Font("Bookman Old Style", Font.PLAIN, 10));
		label.setBounds(48, 269, 45, 13);
		panelMenueBar.add(label);

		spinnerHoehe.setFont(new Font("Bookman Old Style", Font.PLAIN, 10));
		spinnerHoehe.setModel(new SpinnerNumberModel(5, 3, 15, 1));
		spinnerHoehe.setBounds(113, 229, 47, 20);
		panelMenueBar.add(spinnerHoehe);

		spinnerLaenge.setFont(new Font("Bookman Old Style", Font.PLAIN, 10));
		spinnerLaenge.setModel(new SpinnerNumberModel(5, 3, 15, 1));
		spinnerLaenge.setBounds(113, 266, 47, 20);
		panelMenueBar.add(spinnerLaenge);

		spinnerZiel.setModel(new SpinnerNumberModel(1, 1, 8, 1));
		spinnerZiel.setFont(new Font("Bookman Old Style", Font.PLAIN, 10));
		spinnerZiel.setBounds(104, 403, 56, 20);
		panelMenueBar.add(spinnerZiel);

		JLabel lblStation = new JLabel("Station");
		lblStation.setFont(new Font("Bookman Old Style", Font.BOLD, 10));
		lblStation.setBounds(21, 343, 45, 13);
		panelMenueBar.add(lblStation);

		JLabel lblZiel = new JLabel("Ziel");
		lblZiel.setFont(new Font("Bookman Old Style", Font.PLAIN, 10));
		lblZiel.setBounds(48, 406, 45, 13);
		panelMenueBar.add(lblZiel);

		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Bookman Old Style", Font.BOLD, 10));
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setBounds(10, 64, 45, 13);
		panelMenueBar.add(lblName);

		textFieldName = new JTextField();
		textFieldName.setFont(new Font("Bookman Old Style", Font.PLAIN, 10));
		textFieldName.setBounds(65, 61, 114, 19);
		panelMenueBar.add(textFieldName);
		textFieldName.setColumns(10);

		JButton btnSpeichern = new JButton("Speichern");
		btnSpeichern.setFont(new Font("Bookman Old Style", Font.BOLD, 10));
		btnSpeichern.setBackground(Color.WHITE);
		btnSpeichern.setBounds(48, 509, 131, 21);
		panelMenueBar.add(btnSpeichern);

		JButton buttonZurueck = new JButton("Zur\u00FCck");
		buttonZurueck.setFont(new Font("Bookman Old Style", Font.PLAIN, 10));
		buttonZurueck.setBackground(Color.WHITE);
		buttonZurueck.setBounds(48, 540, 131, 21);
		panelMenueBar.add(buttonZurueck);

		JButton btnImport = new JButton("Import");
		btnImport.setFont(new Font("Bookman Old Style", Font.PLAIN, 10));
		btnImport.setBackground(Color.WHITE);
		btnImport.setBounds(48, 571, 131, 21);
		panelMenueBar.add(btnImport);

		JButton btnStationEntfernen = new JButton("entfernen");
		btnStationEntfernen.setFont(new Font("Bookman Old Style", Font.BOLD, 10));
		btnStationEntfernen.setBackground(Color.WHITE);
		btnStationEntfernen.setBounds(48, 433, 114, 21);
		panelMenueBar.add(btnStationEntfernen);
		textFieldStation.setFont(new Font("Bookman Old Style", Font.PLAIN, 10));

		textFieldStation.setEditable(false);
		textFieldStation.setBounds(48, 374, 112, 19);
		panelMenueBar.add(textFieldStation);
		textFieldStation.setColumns(10);

		JButton btnAnwenden = new JButton("Anwenden");
		btnAnwenden.setFont(new Font("Bookman Old Style", Font.BOLD, 10));
		btnAnwenden.setBackground(Color.WHITE);
		btnAnwenden.setBounds(48, 292, 112, 21);
		panelMenueBar.add(btnAnwenden);

		JButton button = new JButton("Netzwerk");
		button.setFont(new Font("Bookman Old Style", Font.PLAIN, 10));
		button.setBackground(Color.WHITE);
		button.setBounds(48, 602, 131, 21);
		panelMenueBar.add(button);

		comboBoxThema.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String thema = String.valueOf(comboBoxThema.getSelectedItem());
				Component[] buttons = panelSpielfeld.getComponents();
				try {
					BufferedImage image = ImageIO
							.read(new File(new File("").getAbsolutePath() + "\\themes\\" + thema + "\\station.jpg"));
					image = FileHandler.groesseÄndern(image, buttons[0].getWidth() - (buttons[0].getWidth() / 5),
							buttons[0].getHeight() - (buttons[0].getHeight() / 5));
					for (int x = 0; x < buttons.length; x++) {
						StationErzeugenButton b = (StationErzeugenButton) buttons[x];
						if (b.isStation()) {
							b.setIcon(new ImageIcon(image));
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		});
		spinnerZiel.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				chosen.setZiel((int) spinnerZiel.getValue(), true);
			}
		});

		btnStationEntfernen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				chosen.setIcon(null);
				chosen.organizeBorder();
				chosen.setStation(false);
				chosen.setText("");
				chosen.setZiel(1, false);
				chosen = null;
			}
		});
		buttonZurueck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				gui.editorMenueZeigen();
			}
		});
		btnAnwenden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelSetzen((int) spinnerHoehe.getValue(), (int) spinnerLaenge.getValue());
				repaint();
				validate();
			}
		});

		btnSpeichern.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (textFieldName.getText().length() > 0) {
					File level = new File(
							new File("").getAbsolutePath() + "\\levels\\User\\" + textFieldName.getText());
					try {
						level.createNewFile();
						FileWriter fw = new FileWriter(level);
						BufferedWriter writer = new BufferedWriter(fw);
						writer.write(String.valueOf(comboBoxThema.getSelectedItem()) + "\r\n");
						writer.write(spinnerHoehe.getValue() + "," + spinnerLaenge.getValue() + "\r\n");
						Component[] stations = panelSpielfeld.getComponents();
						int anzahl = 0;
						for (int x = 0; x < stations.length; x++) {
							StationErzeugenButton b = (StationErzeugenButton) stations[x];
							if (b.isStation()) {
								anzahl++;
							}
						}
						writer.write(anzahl + "\r\n");
						for (int x = 0; x < stations.length; x++) {
							StationErzeugenButton b = (StationErzeugenButton) stations[x];
							if (b.isStation()) {
								writer.write(b.getHoehe() + "," + b.getLaenge() + "," + b.getZiel() + "\r\n");
							}
						}
						writer.close();
						JOptionPane.showConfirmDialog(null, "Das Level wurde gespeichert!", "Super!",
								JOptionPane.PLAIN_MESSAGE);
					} catch (IOException e) {
						JOptionPane.showConfirmDialog(null, "Ein unerwarteter Fehler ist aufgetreten!", "Fehler!",
								JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showConfirmDialog(null, "Trage zuerst einen Namen für dein Level ein!", "Fehler!",
							JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		btnImport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
				int choice = chooser.showOpenDialog(null);
				if (choice != 1) {
					File f = chooser.getSelectedFile();
					try {
						BufferedReader br = new BufferedReader(new FileReader(f));
						String theme = br.readLine();
						String buffer = br.readLine();
						int hoehe = Integer.parseInt(buffer.substring(0, buffer.indexOf(",")));
						int weite = Integer.parseInt(buffer.substring(buffer.indexOf(",") + 1, buffer.length()));
						buffer = br.readLine();
						int anzahlStationen = Integer.parseInt(buffer);
						panelSetzen(hoehe, weite);
						for (int x = 0; x < anzahlStationen; x++) {
							buffer = br.readLine();
							int h = Integer.parseInt(buffer.substring(0, buffer.indexOf(",")));
							int l = Integer.parseInt(buffer.substring(buffer.indexOf(",") + 1,
									buffer.indexOf(",", buffer.indexOf(",") + 1)));
							int z = Integer.parseInt(buffer.substring(buffer.indexOf(",", buffer.indexOf(",") + 1) + 1,
									buffer.length()));
							buttons.get(h).get(l).setZiel(z, true);
							buttons.get(h).get(l).istStation();
							comboBoxThema.setSelectedItem(theme);
						}

						br.close();

					} catch (IOException e) {
						JOptionPane.showConfirmDialog(null, "Ein unerwarteter Fehler ist aufgetreten!", "Fehler!",
								JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});

		button.addActionListener(new ActionListener() { // Startet einen Dialog zum senden oder empfangen von Levels
			public void actionPerformed(ActionEvent arg0) {
				NetzwerkDialog dialog = new NetzwerkDialog(self);
				dialog.setVisible(true);
			}
		});

	}

	public void panelSetzen(int hoehe, int laenge) {
		buttons.clear();
		panelSpielfeld.removeAll();
		panelSpielfeld.setLayout(new GridLayout(hoehe, laenge, 0, 0));
		StationErzeugenButton b = null;
		for (int x = 0; x < hoehe; x++) {
			buttons.add(new ArrayList<StationErzeugenButton>());
			for (int y = 0; y < laenge; y++) {
				b = new StationErzeugenButton(this, x, y);
				panelSpielfeld.add(b);
				buttons.get(x).add(b);
			}
		}
	}

	public StationErzeugenButton getChosen() {
		return chosen;
	}

	public void setChosen(StationErzeugenButton chosen) {
		this.chosen = chosen;
	}

	public JComboBox<String> getComboBox() {
		return comboBoxThema;
	}

	public void setComboBox(JComboBox<String> comboBox) {
		this.comboBoxThema = comboBox;
	}

	public void updateStationAnzeige() {
		textFieldStation.setText("Höhe: " + chosen.getHoehe() + " , Länge: " + chosen.getLaenge());
		spinnerZiel.setValue(chosen.getZiel());
	}

	public JTextField getTextFieldName() {
		return textFieldName;
	}

	public void setTextFieldName(JTextField textFieldName) {
		this.textFieldName = textFieldName;
	}

	public JPanel getPanelSpielfeld() {
		return panelSpielfeld;
	}

	public void setPanelSpielfeld(JPanel panelSpielfeld) {
		this.panelSpielfeld = panelSpielfeld;
	}

	public JTextField getTextFieldStation() {
		return textFieldStation;
	}

	public void setTextFieldStation(JTextField textFieldStation) {
		this.textFieldStation = textFieldStation;
	}

	public JComboBox<String> getComboBoxThema() {
		return comboBoxThema;
	}

	public void setComboBoxThema(JComboBox<String> comboBoxThema) {
		this.comboBoxThema = comboBoxThema;
	}
	
	public JSpinner getSpinnerHoehe() {
		return spinnerHoehe;
	}

	public void setSpinnerHoehe(JSpinner spinnerHoehe) {
		this.spinnerHoehe = spinnerHoehe;
	}

	public JSpinner getSpinnerLaenge() {
		return spinnerLaenge;
	}

	public void setSpinnerLaenge(JSpinner spinnerLaenge) {
		this.spinnerLaenge = spinnerLaenge;
	}

	public JSpinner getSpinnerZiel() {
		return spinnerZiel;
	}

	public void setSpinnerZiel(JSpinner spinnerZiel) {
		this.spinnerZiel = spinnerZiel;
	}

	public ArrayList<ArrayList<StationErzeugenButton>> getButtons() {
		return buttons;
	}

	public void setButtons(ArrayList<ArrayList<StationErzeugenButton>> buttons) {
		this.buttons = buttons;
	}
}
