package darstellung;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Diese Klasse ist von JPanel abgeleitet und dient als Vorschaufenster für geladene Bilddateien.
 * 
 * @author Adrian
 *
 */

public class BildVorschau extends JPanel{

	private static final long serialVersionUID = 1L;
	private ImageIcon image;
	private JLabel label;
	
	//Konstruktor
	public BildVorschau(ImageIcon image) {
		this.image = image;
	}
	
	/**
	 * Diese Methode entfernt das angezeigte Bild vom Panel.
	 */
	public void reset() {
		this.image = null;
	}
	
	
	//Getter und Setter
	public ImageIcon getImage() {
		return image;
	}
	public void setImage(ImageIcon image) {
		this.image = image;
		label = new JLabel(image);
		add(label);
	}
	
	public void setImage(BufferedImage buffer) {
		this.image = new ImageIcon(buffer);
		label = new JLabel(image);
		add(label);
	}

	public JLabel getLabel() {
		return label;
	}

	public void setLabel(JLabel label) {
		this.label = label;
	}
	
}
