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

public class HauptMenuePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public HauptMenuePanel(GUI gui) {
		setBounds(0, 0, 1084, 661);
		setLayout(null);
		setBackground(Color.WHITE);
		File f = new File("");
		JLabel background = new JLabel(new ImageIcon(f.getAbsolutePath()+"\\images\\menue.jpg"));
		background.setBounds(10, 97, 1064, 496);
		add(background);
		
		JLabel schrift = new JLabel(new ImageIcon(f.getAbsolutePath()+"\\images\\label.jpg"));
		schrift.setBounds(10, 20, 1064, 66);
		add(schrift);
		
		JButton btnSpielen = new JButton("Spielen");
		btnSpielen.setToolTipText("Spiele das japanische R\u00E4tsel!");
		btnSpielen.setFont(new Font("Bookman Old Style", Font.BOLD, 12));
		btnSpielen.setBounds(350, 595, 126, 33);
		add(btnSpielen);
		
		JButton btnEditor = new JButton("Editor");
		btnEditor.setToolTipText("Erstelle Levels und Designs!");
		btnEditor.setFont(new Font("Bookman Old Style", Font.BOLD, 12));
		btnEditor.setBounds(630, 595, 126, 33);
		add(btnEditor);
		
		btnSpielen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				gui.spieleMenueZeigen();
			}
		});
		
		btnEditor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				gui.editorMenueZeigen();
			}
		});
	}
}
