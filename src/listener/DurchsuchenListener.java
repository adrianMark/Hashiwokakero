package listener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import darstellung.BildVorschau;

/**
 * Diese Klasse (Listener) startet einen FileChooser. Das selektierte Bild wird in eine übergebene Instanz von {@link BildVorschau} geladen.
 * @author Adrian
 *
 */

public class DurchsuchenListener implements ActionListener {
	private BildVorschau anzeige;

	public DurchsuchenListener(BildVorschau anzeige) {
		super();
		this.anzeige = anzeige;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		JFileChooser chooser = new JFileChooser();
		int choice = chooser.showOpenDialog(null);
		if (choice != 1) {
			File f = chooser.getSelectedFile();
			if (!f.getAbsolutePath().endsWith(".jpg")) {
				JOptionPane.showConfirmDialog(null, "Das Dateiformat wird nicht unterstützt!", "Fehler",
						JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE);
			} else {
				try {
					anzeige.removeAll();
					BufferedImage image = ImageIO.read(f);
					image = darstellung.FileHandler.groesseÄndern(image, anzeige.getWidth(), anzeige.getHeight());
					anzeige.setImage(image);
					anzeige.repaint();
					anzeige.validate();
				} catch (IOException e) {
					JOptionPane.showConfirmDialog(null, "Die Datei konnte nicht gelesen werden!", "Fehler",
							JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}

}
