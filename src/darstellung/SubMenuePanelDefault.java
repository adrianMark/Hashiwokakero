package darstellung;


import java.awt.Color;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Diese Klasse ist das Standard Fenster, das auf der rechten Seite des "Spielen MEnüs" angezeigt wird.
 * @author Adrian
 *
 */

public class SubMenuePanelDefault extends JPanel {

	private static final long serialVersionUID = 1L;

	public SubMenuePanelDefault() {
		File f = new File("");
		setBackground(Color.WHITE);
		setBounds(290, 185, 784, 418);
		setLayout(null);
		JLabel defaultBild = new JLabel(new ImageIcon(f.getAbsolutePath()+"\\images\\menue.jpg"));
		defaultBild.setBounds(0, 0, 784, 484);
		add(defaultBild);
	}

}
