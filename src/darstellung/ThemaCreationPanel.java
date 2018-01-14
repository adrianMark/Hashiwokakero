package darstellung;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import listener.DurchsuchenListener;

/**
 * Mit dieser Klasse können neue Themen für das Spiel erstellt oder importiert werden. Dazu werden Bilder für die horizontalen und
 * vertikalen Verbindungen, Inseln und Previews vom System geladen. Schlussendlich kann in einer Preview eine interaktive Demo des Themas gestartet werden.
 * @author Adrian
 *
 */

public class ThemaCreationPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	//Konstruktor
	public ThemaCreationPanel(GUI gui) {
		setBounds(0, 0, 1084, 661);
		setBackground(Color.WHITE);
		setBorder(null);
		setLayout(null);

		//TabbedPane für die sequentielle Vorangehensweise bei der Erstellung
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Bookman Old Style", Font.BOLD, 10));
		tabbedPane.setBorder(null);
		tabbedPane.setBounds(63, 0, 955, 614);
		JPanel panelStart = new JPanel();
		panelStart.setBorder(null);
		panelStart.setBackground(Color.WHITE);
		tabbedPane.addTab("Start", panelStart);
		panelStart.setLayout(null);

		JLabel lblWillkommenImThemen = new JLabel("Willkommen im Themen Editor");
		lblWillkommenImThemen.setFont(new Font("Bookman Old Style", Font.BOLD, 15));
		lblWillkommenImThemen.setBounds(80, 45, 343, 26);
		panelStart.add(lblWillkommenImThemen);

		ButtonGroup startButtonGroup = new ButtonGroup(); //Button Group für Radio Buttons

		JLabel lblWasTun = new JLabel("Was m\u00F6chtest du tun?");
		lblWasTun.setFont(new Font("Bookman Old Style", Font.BOLD, 12));
		lblWasTun.setBounds(90, 81, 238, 13);
		panelStart.add(lblWasTun);

		JRadioButton rdbtnNeuesThemaErstellen = new JRadioButton("Neues Thema erstellen");
		rdbtnNeuesThemaErstellen.setSelected(true);
		rdbtnNeuesThemaErstellen.setBackground(Color.WHITE);
		rdbtnNeuesThemaErstellen.setFont(new Font("Bookman Old Style", Font.PLAIN, 12));
		rdbtnNeuesThemaErstellen.setBounds(194, 246, 271, 21);
		startButtonGroup.add(rdbtnNeuesThemaErstellen);
		panelStart.add(rdbtnNeuesThemaErstellen);

		JRadioButton radioButtonImport = new JRadioButton("Thema Importieren");
		radioButtonImport.setFont(new Font("Bookman Old Style", Font.PLAIN, 12));
		radioButtonImport.setBackground(Color.WHITE);
		radioButtonImport.setBounds(194, 302, 271, 21);
		startButtonGroup.add(radioButtonImport);
		panelStart.add(radioButtonImport);

		JButton btnLosGehts = new JButton("Los gehts!");
		btnLosGehts.setBackground(Color.WHITE);
		btnLosGehts.setBounds(194, 403, 117, 21);
		panelStart.add(btnLosGehts);

		JPanel panelBilder = new JPanel();
		panelBilder.setBackground(Color.WHITE);
		tabbedPane.addTab("Bilder", panelBilder);
		panelBilder.setLayout(null);

		JLabel lblTitle = new JLabel("W\u00E4hle Bilder f\u00FCr dein Design");
		lblTitle.setFont(new Font("Bookman Old Style", Font.BOLD, 15));
		lblTitle.setBounds(80, 45, 256, 13);
		panelBilder.add(lblTitle);

		JLabel lblInsel = new JLabel("Insel");
		lblInsel.setFont(new Font("Bookman Old Style", Font.BOLD, 11));
		lblInsel.setHorizontalAlignment(SwingConstants.CENTER);
		lblInsel.setBounds(30, 199, 205, 13);
		panelBilder.add(lblInsel);

		BildVorschau panelInsel = new BildVorschau(null);
		panelInsel.setBounds(30, 222, 205, 155);
		panelBilder.add(panelInsel);

		JButton btnSuchen1 = new JButton("Durchsuchen");
		btnSuchen1.addActionListener(new DurchsuchenListener(panelInsel));
		panelInsel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		btnSuchen1.setBackground(Color.WHITE);
		btnSuchen1.setFont(new Font("Bookman Old Style", Font.PLAIN, 10));
		btnSuchen1.setBounds(30, 387, 115, 21);
		panelBilder.add(btnSuchen1);

		JLabel label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Bookman Old Style", Font.BOLD, 11));
		label.setBounds(80, 404, 45, 13);
		panelBilder.add(label);

		JLabel label_1 = new JLabel("Horizontale Verbindung - einfach");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Bookman Old Style", Font.BOLD, 11));
		label_1.setBounds(329, 72, 205, 13);
		panelBilder.add(label_1);

		BildVorschau panelhoriSingle = new BildVorschau(null);
		panelhoriSingle.setBounds(329, 95, 205, 155);
		panelBilder.add(panelhoriSingle);
		panelhoriSingle.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton btnSuchen2 = new JButton("Durchsuchen");
		btnSuchen2.setBackground(Color.WHITE);
		btnSuchen2.setFont(new Font("Bookman Old Style", Font.PLAIN, 10));
		btnSuchen2.setBounds(329, 260, 115, 21);
		btnSuchen2.addActionListener(new DurchsuchenListener(panelhoriSingle));
		panelBilder.add(btnSuchen2);

		JLabel label_2 = new JLabel("Horizontale Verbindung - doppelt");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("Bookman Old Style", Font.BOLD, 11));
		label_2.setBounds(329, 346, 205, 13);
		panelBilder.add(label_2);

		BildVorschau panelhoriDouble = new BildVorschau(null);
		panelhoriDouble.setBounds(329, 369, 205, 155);
		panelBilder.add(panelhoriDouble);
		panelhoriDouble.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton buttonbtnSuchen3 = new JButton("Durchsuchen");
		buttonbtnSuchen3.setBackground(Color.WHITE);
		buttonbtnSuchen3.setFont(new Font("Bookman Old Style", Font.PLAIN, 10));
		buttonbtnSuchen3.setBounds(329, 534, 115, 21);
		buttonbtnSuchen3.addActionListener(new DurchsuchenListener(panelhoriDouble));
		panelBilder.add(buttonbtnSuchen3);

		JLabel label_3 = new JLabel("Vertikale Verbindung - einfach");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setFont(new Font("Bookman Old Style", Font.BOLD, 11));
		label_3.setBounds(658, 72, 205, 13);
		panelBilder.add(label_3);

		BildVorschau panelVertiSingle = new BildVorschau(null);
		panelVertiSingle.setBounds(658, 95, 205, 155);
		panelBilder.add(panelVertiSingle);
		panelVertiSingle.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton buttonbtnSuchen4 = new JButton("Durchsuchen");
		buttonbtnSuchen4.setBackground(Color.WHITE);
		buttonbtnSuchen4.setFont(new Font("Bookman Old Style", Font.PLAIN, 10));
		buttonbtnSuchen4.setBounds(658, 260, 115, 21);
		buttonbtnSuchen4.addActionListener(new DurchsuchenListener(panelVertiSingle));
		panelBilder.add(buttonbtnSuchen4);

		JLabel label_4 = new JLabel("Vertikale Verbindung - doppelt");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setFont(new Font("Bookman Old Style", Font.BOLD, 11));
		label_4.setBounds(658, 346, 205, 13);
		panelBilder.add(label_4);

		BildVorschau panelVertiDouble = new BildVorschau(null);
		panelVertiDouble.setBounds(658, 369, 205, 155);
		panelBilder.add(panelVertiDouble);
		panelVertiDouble.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton buttonbtnSuchen5 = new JButton("Durchsuchen");
		buttonbtnSuchen5.setBackground(Color.WHITE);
		buttonbtnSuchen5.setFont(new Font("Bookman Old Style", Font.PLAIN, 10));
		buttonbtnSuchen5.setBounds(658, 534, 115, 21);
		buttonbtnSuchen5.addActionListener(new DurchsuchenListener(panelVertiDouble));
		panelBilder.add(buttonbtnSuchen5);

		JButton button_5 = new JButton("<>");
		button_5.setToolTipText("Drehen und verwenden");
		button_5.setFont(new Font("Bookman Old Style", Font.BOLD, 10));
		button_5.setBackground(Color.WHITE);
		button_5.setBounds(554, 170, 85, 21);
		panelBilder.add(button_5);

		JButton button_6 = new JButton("<>");
		button_6.setToolTipText("Drehen und verwenden");
		button_6.setFont(new Font("Bookman Old Style", Font.BOLD, 10));
		button_6.setBackground(Color.WHITE);
		button_6.setBounds(554, 449, 85, 21);
		panelBilder.add(button_6);

		JButton buttonEntfernen2 = new JButton("Entfernen");
		buttonEntfernen2.setFont(new Font("Bookman Old Style", Font.PLAIN, 10));
		buttonEntfernen2.setBackground(Color.WHITE);
		buttonEntfernen2.setBounds(444, 260, 90, 21);
		panelBilder.add(buttonEntfernen2);

		JButton buttonEntfernen4 = new JButton("Entfernen");
		buttonEntfernen4.setFont(new Font("Bookman Old Style", Font.PLAIN, 10));
		buttonEntfernen4.setBackground(Color.WHITE);
		buttonEntfernen4.setBounds(773, 260, 90, 21);
		panelBilder.add(buttonEntfernen4);

		JButton buttonEntfernen3 = new JButton("Entfernen");
		buttonEntfernen3.setFont(new Font("Bookman Old Style", Font.PLAIN, 10));
		buttonEntfernen3.setBackground(Color.WHITE);
		buttonEntfernen3.setBounds(444, 534, 90, 21);
		panelBilder.add(buttonEntfernen3);

		JButton button5 = new JButton("Entfernen");
		button5.setFont(new Font("Bookman Old Style", Font.PLAIN, 10));
		button5.setBackground(Color.WHITE);
		button5.setBounds(773, 534, 90, 21);
		panelBilder.add(button5);

		JButton buttonEntfernen1 = new JButton("Entfernen");
		buttonEntfernen1.setFont(new Font("Bookman Old Style", Font.PLAIN, 10));
		buttonEntfernen1.setBackground(Color.WHITE);
		buttonEntfernen1.setBounds(145, 387, 90, 21);
		panelBilder.add(buttonEntfernen1);
		JPanel panelHintergrund = new JPanel();
		panelHintergrund.setBackground(Color.WHITE);
		tabbedPane.addTab("Hintergrund", panelHintergrund);
		panelHintergrund.setLayout(null);

		JPanel panelVorschau = new JPanel();
		panelVorschau.setBackground(Color.WHITE);
		panelVorschau.setBorder(new LineBorder(new Color(0, 0, 0), 5));
		panelVorschau.setBounds(196, 74, 723, 461);
		panelHintergrund.add(panelVorschau);

		JCheckBox chckbxAnInselAusrichten = new JCheckBox("An Insel ausrichten");
		chckbxAnInselAusrichten.setToolTipText("An der Hintergrundfarbe der Insel ausrichten");
		chckbxAnInselAusrichten.setFont(new Font("Bookman Old Style", Font.PLAIN, 10));
		chckbxAnInselAusrichten.setBackground(Color.WHITE);
		chckbxAnInselAusrichten.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxAnInselAusrichten.setBounds(196, 541, 723, 21);
		panelHintergrund.add(chckbxAnInselAusrichten);

		JSpinner spinnerRot = new JSpinner();
		spinnerRot.setFont(new Font("Bookman Old Style", Font.BOLD, 10));
		spinnerRot.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spinnerRot.setBounds(65, 213, 110, 20);
		panelHintergrund.add(spinnerRot);

		JLabel lblBlau = new JLabel("Rot:");
		lblBlau.setFont(new Font("Bookman Old Style", Font.BOLD, 10));
		lblBlau.setHorizontalAlignment(SwingConstants.CENTER);
		lblBlau.setBounds(10, 216, 53, 13);
		panelHintergrund.add(lblBlau);

		JSpinner spinnerGruen = new JSpinner();
		spinnerGruen.setFont(new Font("Bookman Old Style", Font.BOLD, 10));
		spinnerGruen.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spinnerGruen.setBounds(65, 298, 110, 20);
		panelHintergrund.add(spinnerGruen);

		JLabel lblGelb = new JLabel("Gr\u00FCn:");
		lblGelb.setFont(new Font("Bookman Old Style", Font.BOLD, 10));
		lblGelb.setHorizontalAlignment(SwingConstants.CENTER);
		lblGelb.setBounds(10, 301, 53, 13);
		panelHintergrund.add(lblGelb);

		JSpinner spinnerBlau = new JSpinner();
		spinnerBlau.setFont(new Font("Bookman Old Style", Font.BOLD, 10));
		spinnerBlau.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spinnerBlau.setBounds(65, 383, 110, 20);
		panelHintergrund.add(spinnerBlau);

		JLabel label_6 = new JLabel("Blau:");
		label_6.setFont(new Font("Bookman Old Style", Font.BOLD, 10));
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		label_6.setBounds(10, 386, 53, 13);
		panelHintergrund.add(label_6);

		JLabel label_5 = new JLabel("Hier kannst du den Hintergrund des Spielfeldes festlegen!");
		label_5.setFont(new Font("Bookman Old Style", Font.BOLD, 15));
		label_5.setBounds(80, 45, 477, 13);
		panelHintergrund.add(label_5);
		JPanel panelPreview = new JPanel();
		panelPreview.setBackground(Color.WHITE);
		tabbedPane.addTab("Preview Bild", panelPreview);
		panelPreview.setLayout(null);

		BildVorschau panelPreviewBild = new BildVorschau(null);
		panelPreviewBild.setBounds(394, 232, 100, 100);
		panelPreview.add(panelPreviewBild);
		panelPreviewBild.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton btnDurchsuchen7 = new JButton("Durchsuchen");
		btnDurchsuchen7.setFont(new Font("Bookman Old Style", Font.PLAIN, 10));
		btnDurchsuchen7.setBackground(Color.WHITE);
		btnDurchsuchen7.setBounds(289, 395, 115, 21);
		btnDurchsuchen7.addActionListener(new DurchsuchenListener(panelPreviewBild));
		panelPreview.add(btnDurchsuchen7);

		JCheckBox chckbxInselAlsPreview = new JCheckBox("Insel als Preview verwenden");
		chckbxInselAlsPreview.setToolTipText("Das Bild der Insel als Vorschau benutzen");
		chckbxInselAlsPreview.setFont(new Font("Bookman Old Style", Font.PLAIN, 10));
		chckbxInselAlsPreview.setBackground(Color.WHITE);
		chckbxInselAlsPreview.setBounds(465, 395, 251, 21);
		panelPreview.add(chckbxInselAlsPreview);

		JLabel label_7 = new JLabel("W\u00E4hle ein Bild f\u00FCr die Vorschau des Themas");
		label_7.setFont(new Font("Bookman Old Style", Font.BOLD, 15));
		label_7.setBounds(80, 45, 477, 13);
		panelPreview.add(label_7);

		JLabel label_8 = new JLabel("Dieses Bild dient als Vorschau f\u00FCr alle Levels mit dem Thema");
		label_8.setFont(new Font("Bookman Old Style", Font.BOLD, 12));
		label_8.setBounds(90, 68, 580, 13);
		panelPreview.add(label_8);

		JButton btnEntfernen6 = new JButton("Entfernen");
		btnEntfernen6.setFont(new Font("Bookman Old Style", Font.PLAIN, 10));
		btnEntfernen6.setBackground(Color.WHITE);
		btnEntfernen6.setBounds(394, 336, 100, 21);
		panelPreview.add(btnEntfernen6);
		JPanel panelEnde = new JPanel();
		panelEnde.setBackground(Color.WHITE);
		tabbedPane.addTab("Zusammenfassung", panelEnde);
		panelEnde.setLayout(null);

		JButton btnSpeichern = new JButton("Thema Speichern");
		btnSpeichern.setBackground(Color.WHITE);
		btnSpeichern.setFont(new Font("Bookman Old Style", Font.BOLD, 11));
		btnSpeichern.setBounds(394, 538, 150, 21);
		panelEnde.add(btnSpeichern);

		JPanel panelDemo = new JPanel();
		panelDemo.setBounds(10, 10, 930, 516);
		panelEnde.add(panelDemo);
		panelDemo.setLayout(null);

		JButton btnPreviewLaden = new JButton("Preview Laden");
		btnPreviewLaden.setBackground(Color.WHITE);
		btnPreviewLaden.setFont(new Font("Bookman Old Style", Font.BOLD, 11));
		btnPreviewLaden.setBounds(10, 538, 130, 21);
		panelEnde.add(btnPreviewLaden);
		add(tabbedPane);

		JButton btnEditorMenue = new JButton("Zur\u00FCck");
		btnEditorMenue.setBackground(Color.WHITE);
		btnEditorMenue.setBounds(495, 624, 85, 21);
		add(btnEditorMenue);

		JButton buttonZurueck = new JButton("<");

		buttonZurueck.setBounds(8, 24, 45, 590);
		add(buttonZurueck);
		buttonZurueck.setBorder(new LineBorder(new Color(0, 0, 0)));
		buttonZurueck.setBackground(Color.WHITE);

		JButton buttonWeiter = new JButton(">");
		buttonWeiter.setBorder(new LineBorder(new Color(0, 0, 0)));
		buttonWeiter.setBackground(Color.WHITE);
		buttonWeiter.setBounds(1028, 24, 45, 590);
		add(buttonWeiter);

		//Listener für die einzelnen Komponenten
		btnEditorMenue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				gui.editorMenueZeigen();
			}
		});
		btnLosGehts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (rdbtnNeuesThemaErstellen.isSelected()) {//Neues Thema manuell erstellen
					tabbedPane.setSelectedIndex(tabbedPane.getSelectedIndex() + 1);
				} else if (radioButtonImport.isSelected()) { //Wenn importiert werden soll..
					JFileChooser dirc = new JFileChooser();
					dirc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					int choice = dirc.showOpenDialog(null);
					if (choice != 1) { 
						File d = dirc.getSelectedFile();
						File[] files = d.listFiles();
						if (files.length == 7) {
							BufferedImage image;
							for (int x = 0; x < files.length; x++) {
								try {
									if (files[x].getName().equals("station.jpg")) {
										image = ImageIO.read(files[x]);
										panelInsel.setImage(image);
									} else if (files[x].getName().equals("horizontalSingle.jpg")) {
										image = ImageIO.read(files[x]);
										panelhoriSingle.setImage(image);
									} else if (files[x].getName().equals("horizontalDouble.jpg")) {
										image = ImageIO.read(files[x]);
										panelhoriDouble.setImage(image);
									} else if (files[x].getName().equals("verticalSingle.jpg")) {
										image = ImageIO.read(files[x]);
										panelVertiSingle.setImage(image);
									} else if (files[x].getName().equals("verticalDouble.jpg")) {
										image = ImageIO.read(files[x]);
										panelVertiDouble.setImage(image);
									} else if (files[x].getName().equals("background")) {
										BufferedReader reader = new BufferedReader(new FileReader(files[x]));
										spinnerRot.setValue(Integer.parseInt(reader.readLine()));
										spinnerGruen.setValue(Integer.parseInt(reader.readLine()));
										spinnerBlau.setValue(Integer.parseInt(reader.readLine()));
										reader.close();
									} else if (files[x].getName().equals("preview.jpg")) {
										image = ImageIO.read(files[x]);
										panelPreviewBild.setImage(image);
									}
								} catch (IOException e) {
									JOptionPane.showConfirmDialog(null, "Das Thema konnte nicht importiert werden!",
											"Fehler!", JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE);
								}
							}
							tabbedPane.setSelectedIndex(tabbedPane.getSelectedIndex() + 1);
						}

					}
				}
			}
		});
		buttonZurueck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int index = tabbedPane.getSelectedIndex();
				if (index > 0) {
					tabbedPane.setSelectedIndex(index - 1);
				}
			}
		});
		buttonWeiter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int index = tabbedPane.getSelectedIndex();
				if (index < 4) {
					tabbedPane.setSelectedIndex(index + 1);
				}
			}
		});

		button_5.addActionListener(new ActionListener() { //Drehen und verwenden eines bereits geladenen Bildes
			public void actionPerformed(ActionEvent arg0) {

				if (panelhoriSingle.getImage() != null && panelVertiSingle.getImage() != null) {
					return;
				} else if (panelhoriSingle.getImage() != null) {
					BufferedImage image = FileHandler.IconZuImage(panelhoriSingle.getImage());
					image = FileHandler.bildDrehen(image, 270.0);
					panelVertiSingle.setImage(image);
					panelVertiSingle.repaint();
					panelVertiSingle.validate();
				} else if (panelVertiSingle.getImage() != null) {
					BufferedImage image = FileHandler.IconZuImage(panelVertiSingle.getImage());
					image = FileHandler.bildDrehen(image, 90.0);
					panelhoriSingle.setImage(image);
					panelhoriSingle.repaint();
					panelhoriSingle.validate();
				} else {
					JOptionPane.showConfirmDialog(null,
							"Es wurde noch kein Bild für vertikale oder horizontale Verbindungen ausgewählt!", "Fehler",
							JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		button_6.addActionListener(new ActionListener() {//Drehen und verwenden eines bereits geladenen Bildes
			public void actionPerformed(ActionEvent arg0) {
				if (panelhoriDouble.getImage() != null && panelVertiDouble.getImage() != null) {
					return;
				} else if (panelhoriDouble.getImage() != null) {
					BufferedImage image = FileHandler.IconZuImage(panelhoriDouble.getImage());
					image = FileHandler.bildDrehen(image, 270.0);
					panelVertiDouble.setImage(image);
					panelVertiDouble.repaint();
					panelVertiDouble.validate();
				} else if (panelVertiDouble.getImage() != null) {
					BufferedImage image = FileHandler.IconZuImage(panelVertiDouble.getImage());
					image = FileHandler.bildDrehen(image, 90.0);
					panelhoriDouble.setImage(image);
					panelhoriDouble.repaint();
					panelhoriDouble.validate();
				} else {
					JOptionPane.showConfirmDialog(null,
							"Es wurde noch kein Bild für vertikale oder horizontale Verbindungen ausgewählt!", "Fehler",
							JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		buttonEntfernen1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelInsel.removeAll();
				panelInsel.reset();
				panelInsel.repaint();
				panelInsel.validate();
			}
		});
		buttonEntfernen2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelhoriSingle.removeAll();
				panelhoriSingle.reset();
				panelhoriSingle.repaint();
				panelhoriSingle.validate();
			}
		});
		buttonEntfernen4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelVertiSingle.removeAll();
				panelVertiSingle.reset();
				panelVertiSingle.repaint();
				panelVertiSingle.validate();

			}
		});
		buttonEntfernen3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelhoriDouble.removeAll();
				panelhoriDouble.reset();
				panelhoriDouble.repaint();
				panelhoriDouble.validate();
			}
		});
		button5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelVertiDouble.removeAll();
				panelVertiDouble.reset();
				panelVertiDouble.repaint();
				panelVertiDouble.validate();
			}
		});
		chckbxAnInselAusrichten.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (chckbxAnInselAusrichten.isSelected()) {
					if (panelInsel.getImage() != null) {
						BufferedImage image = FileHandler.IconZuImage(panelInsel.getImage());
						Color c = new Color(image.getRGB(1, 1));
						spinnerRot.setValue(c.getRed());
						spinnerGruen.setValue(c.getGreen());
						spinnerBlau.setValue(c.getBlue());
					}
				} else {
					spinnerRot.setValue(255);
					spinnerGruen.setValue(255);
					spinnerBlau.setValue(255);
				}
			}
		});

		spinnerRot.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				panelVorschau.setBackground(
						new Color((int) spinnerRot.getValue(), (int) spinnerGruen.getValue(), (int) spinnerBlau.getValue()));
				panelVorschau.repaint();
				panelVorschau.validate();
			}
		});
		spinnerGruen.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				panelVorschau.setBackground(
						new Color((int) spinnerRot.getValue(), (int) spinnerGruen.getValue(), (int) spinnerBlau.getValue()));
				panelVorschau.repaint();
				panelVorschau.validate();
			}
		});
		spinnerBlau.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				panelVorschau.setBackground(
						new Color((int) spinnerRot.getValue(), (int) spinnerGruen.getValue(), (int) spinnerBlau.getValue()));
				panelVorschau.repaint();
				panelVorschau.validate();
			}
		});

		btnEntfernen6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelPreviewBild.removeAll();
				panelPreviewBild.reset();
				panelPreviewBild.repaint();
				panelPreviewBild.validate();
			}
		});

		chckbxInselAlsPreview.addActionListener(new ActionListener() { //Lädt die farbe des Inselhintergrundes 
			public void actionPerformed(ActionEvent arg0) {
				if (chckbxInselAlsPreview.isSelected()) {
					BufferedImage image = FileHandler.IconZuImage(panelInsel.getImage());
					image = FileHandler.groesseÄndern(image, 100, 100);
					panelPreviewBild.setImage(image);
					panelPreviewBild.repaint();
					panelPreviewBild.validate();
				} else {
					panelPreviewBild.reset();
					panelPreviewBild.removeAll();
					panelPreviewBild.repaint();
					panelPreviewBild.validate();
				}
			}
		});
		btnPreviewLaden.addActionListener(new ActionListener() { //Lädt die Interaktive Demo
			public void actionPerformed(ActionEvent arg0) {
				File file = new File("");
				File station = new File(file.getAbsolutePath() + "\\themes\\temp\\station.jpg");
				File h1 = new File(file.getAbsolutePath() + "\\themes\\temp\\horizontalSingle.jpg");
				File h2 = new File(file.getAbsolutePath() + "\\themes\\temp\\horizontalDouble.jpg");
				File v1 = new File(file.getAbsolutePath() + "\\themes\\temp\\verticalSingle.jpg");
				File v2 = new File(file.getAbsolutePath() + "\\themes\\temp\\verticalDouble.jpg");
				File preview = new File(file.getAbsolutePath() + "\\themes\\temp\\preview.jpg");
				File background = new File(file.getAbsolutePath() + "\\themes\\temp\\background");
				try {
					FileWriter writer = new FileWriter(background, true);
					ImageIO.write(FileHandler.IconZuImage(panelInsel.getImage()), "jpg", station);
					ImageIO.write(FileHandler.IconZuImage(panelhoriSingle.getImage()), "jpg", h1);
					ImageIO.write(FileHandler.IconZuImage(panelhoriDouble.getImage()), "jpg", h2);
					ImageIO.write(FileHandler.IconZuImage(panelVertiSingle.getImage()), "jpg", v1);
					ImageIO.write(FileHandler.IconZuImage(panelVertiDouble.getImage()), "jpg", v2);
					ImageIO.write(FileHandler.IconZuImage(panelPreviewBild.getImage()), "jpg", preview);
					writer.write(String.valueOf(spinnerRot.getValue()) + "\r\n");
					writer.write(String.valueOf(spinnerGruen.getValue()) + "\r\n");
					writer.write(String.valueOf(spinnerBlau.getValue()) + "\r\n");
					writer.close();
					SpielPanel p = new SpielPanel(file.getAbsolutePath() + "\\levels\\Insel\\leicht", gui, "temp");
					p.setBounds(0, 0, 925, 500);
					panelDemo.add(p);
					panelDemo.repaint();
					panelDemo.validate();
				} catch (IOException e) {
					JOptionPane.showConfirmDialog(null, "Ein unerwarteter Fehler ist aufgetreten!", "Fehler!",
							JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		btnSpeichern.addActionListener(new ActionListener() { //Speichert das Thema, wenn alle Infos vorhanden sind
			public void actionPerformed(ActionEvent arg0) {
				File f = new File("");
				File[] files = new File(f.getAbsolutePath() + "\\themes\\temp").listFiles();
				if (files.length == 7) {
					String name = JOptionPane.showInputDialog(null, "Gib einen Namen für das Thema ein!");
					File dir = new File(f.getAbsolutePath() + "\\themes\\" + name);
					dir.mkdir();
					try {
						for (int x = 0; x < files.length; x++) {
							File to = new File(f.getAbsolutePath() + "\\themes\\" + name + "\\" + files[x].getName());
							to.createNewFile();
							FileHandler.dateiKopieren(files[x], to);
							files[x].delete();
						}
					} catch (IOException e) {
						JOptionPane.showConfirmDialog(null,
								"Ein unerwarteter Fehler ist aufgetreten! Das Thema konnte nicht gespeichert werden.",
								"Fehler!", JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE);
						e.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "Das Thema wurde gespeichert");
					gui.editorMenueZeigen();
				} else {
					JOptionPane.showConfirmDialog(null, "Bitte zunächst alle Felder ausfüllen und die Preview laden!",
							"Fehler!", JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}
}
