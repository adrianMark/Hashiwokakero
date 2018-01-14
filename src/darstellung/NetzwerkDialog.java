package darstellung;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * Diese Klasse wird genutzt um Level über das lokale Netzwerk zu versenden oder zu empfangen.
 * @author Adrian
 *
 */

public class NetzwerkDialog extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JButton btnSenden = new JButton("Senden");
	private JButton btnEmpfangen = new JButton("Empfangen");
	private JList<String> levelListe;
	private JScrollPane scrollPaneLevel = new JScrollPane();

	
	//Konstruktor
	public NetzwerkDialog(LevelCreationPanel panel) {
		setTitle("Senden/Empfangen");
		setIconImage(Toolkit.getDefaultToolkit().getImage(new File("").getAbsolutePath() + "\\images\\logo.png"));
		setBounds(100, 100, 590, 390);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		btnSenden.setEnabled(false);
		btnSenden.setFont(new Font("Bookman Old Style", Font.PLAIN, 10));
		btnSenden.setToolTipText("Eraubt die \u00DCbertragung eines Levels! Zuerst starten!");
		btnEmpfangen.setToolTipText("Erlaubt das Empfangen eines Levels. Erst nach dem Sender starten!");
		btnEmpfangen.setFont(new Font("Bookman Old Style", Font.PLAIN, 10));
		btnSenden.setBackground(Color.WHITE);
		btnSenden.setBounds(10, 10, 85, 21);

		contentPane.add(btnSenden);
		btnEmpfangen.setBackground(Color.WHITE);
		btnEmpfangen.setBounds(105, 10, 121, 21);
		contentPane.add(btnEmpfangen);

		JLabel lblDeineIp = new JLabel("Deine IP:");
		lblDeineIp.setFont(new Font("Bookman Old Style", Font.PLAIN, 10));
		lblDeineIp.setBounds(10, 52, 180, 13);
		try {
			InetAddress ip = InetAddress.getLocalHost();
			lblDeineIp.setText(lblDeineIp.getText() + " " + ip.getHostAddress());
		} catch (UnknownHostException e) {
			JOptionPane.showConfirmDialog(null, "Ein unerwarteter Fehler ist aufgetreten!", "Fehler!",
					JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE);
		}

		contentPane.add(lblDeineIp);

		JLabel lblPartnerIp = new JLabel("Partner IP: ");
		lblPartnerIp.setFont(new Font("Bookman Old Style", Font.PLAIN, 10));
		lblPartnerIp.setBounds(162, 52, 64, 13);
		contentPane.add(lblPartnerIp);

		textField = new JTextField();
		textField.setEnabled(false);
		textField.setFont(new Font("Bookman Old Style", Font.PLAIN, 10));
		textField.setBounds(224, 49, 126, 19);
		contentPane.add(textField);
		textField.setColumns(10);

		JButton btnStart = new JButton("Start");
		btnStart.setFont(new Font("Bookman Old Style", Font.PLAIN, 10));
		btnStart.setBackground(Color.WHITE);
		btnStart.setBounds(417, 186, 85, 21);
		contentPane.add(btnStart);

		DefaultListModel<String> model = new DefaultListModel<>();
		File[] levels = new File(new File("").getAbsolutePath() + "\\levels\\User").listFiles();
		if (levels.length == 0) {
			model.addElement("Du hast noch keine eigenen Level...");
		} else {
			for (int x = 0; x < levels.length; x++) {
				model.addElement(levels[x].getName());
			}
		}
		levelListe = new JList<String>(model);	
				
		scrollPaneLevel.setBounds(20, 79, 330, 264);
		contentPane.add(scrollPaneLevel);

		levelListe.setSelectedIndex(0);
		levelListe.setFont(new Font("Bookman Old Style", Font.BOLD, 10));
		scrollPaneLevel.setViewportView(levelListe);
		
		//Listener
		btnStart.addActionListener(new ActionListener() { // Startet die Übertragung oder lädt das Level
			public void actionPerformed(ActionEvent arg0) {
				if (btnEmpfangen.isEnabled() && !levelListe.getSelectedValue().equals("Du hast noch keine eigenen Level...")) {
					try {
						ServerSocket ssocket = new ServerSocket(5522);
						Socket socket = ssocket.accept();
						PrintWriter sender = new PrintWriter(socket.getOutputStream(), true);
						FileReader fr = new FileReader(new File(
								new File("").getAbsolutePath() + "\\levels\\User\\" + levelListe.getSelectedValue()));
						BufferedReader br = new BufferedReader(fr);

						sender.println(br.readLine()); // Thema
						String dim = br.readLine();
						sender.println(dim.substring(0, dim.indexOf(","))); // Höhe senden
						sender.println(dim.substring(dim.indexOf(",") + 1, dim.length())); // Länge
						String numStationen = br.readLine();
						sender.println(numStationen); // Zahl der Stationen senden

						for (int x = 0; x < Integer.valueOf(numStationen); x++) {
							String station = br.readLine();
							String hoehe = station.substring(0, station.indexOf(","));
							String laenge = station.substring(station.indexOf(",") + 1,
									station.indexOf(",", station.indexOf(",") + 1));
							String ziel = station.substring(
									station.indexOf(",", station.indexOf(",") + laenge.length()) + 1, station.length());
							sender.println(hoehe);
							sender.println(laenge);
							sender.println(ziel);
						}
						sender.close();
						br.close();
						fr.close();
						ssocket.close();
						JOptionPane.showConfirmDialog(null, "Die Übertragung war erfolgreich!", "Meldung!",
								JOptionPane.PLAIN_MESSAGE);
					} catch (IOException e) {
						JOptionPane.showConfirmDialog(null, "Ein unerwarteter Fehler ist aufgetreten!", "Fehler!",
								JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE);
						e.printStackTrace();
					}

				} else if(btnSenden.isEnabled()){
					try {
						if(textField.getText().length() == 0) {
							JOptionPane.showConfirmDialog(null, "Bitte gib zunächst die IP Adresse deines Freundes ein!", "Fehler!",
									JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE);
							return;
						}
						Socket socket = new Socket(textField.getText(), 5522);
						BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
						String theme = reader.readLine(); //Thema empfangen
						int hoehe = Integer.valueOf(reader.readLine());
						int laenge = Integer.valueOf(reader.readLine());
						panel.getSpinnerHoehe().setValue(hoehe);
						panel.getSpinnerLaenge().setValue(laenge);
						panel.panelSetzen(hoehe, laenge); //Erzeugung des Panels
						int stationen = Integer.valueOf(reader.readLine());
						
						//Stationen aktivieren
						for (int x = 0; x < stationen; x++) {
							int h = Integer.valueOf(reader.readLine());
							int l = Integer.valueOf(reader.readLine());
							int z = Integer.valueOf(reader.readLine());
							panel.getButtons().get(h).get(l).setZiel(z, true);
							panel.getButtons().get(h).get(l).istStation();
						}
						panel.getComboBox().setSelectedItem(theme); //Thema ändern
						socket.close();
						dispose();
					} catch (UnknownHostException e) {
						JOptionPane.showConfirmDialog(null, "Ein unerwarteter Fehler ist aufgetreten!", "Fehler!",
								JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE);
					} catch (IOException e) {
						JOptionPane.showConfirmDialog(null, "Ein unerwarteter Fehler ist aufgetreten!", "Fehler!",
								JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE);
					}
				}else {
					JOptionPane.showConfirmDialog(null, "Du hast leider keine Level, die du versenden kannst! Probiere doch einmal den Level Editor aus.", "Fehler!",
							JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE);
				}

			}
		});

		btnEmpfangen.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				empfangenWählen();
			}
		});
		btnSenden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sendenWählen();
			}
		});
	}
	
	/**
	 * Diese Methode versetzt das Programm in den Versenden - Modus
	 */
	public void sendenWählen() {
		btnEmpfangen.setEnabled(true);
		btnSenden.setEnabled(false);
		levelListe.setEnabled(true);
		levelListe.setSelectedIndex(0);
		textField.setEnabled(false);
	}

	/**
	 * Diese Methode versetzt das Programm in den Empfangen - Modus
	 */
	public void empfangenWählen() {
		textField.setEnabled(true);
		btnSenden.setEnabled(true);
		btnEmpfangen.setEnabled(false);
		levelListe.setEnabled(false);
	}

}
