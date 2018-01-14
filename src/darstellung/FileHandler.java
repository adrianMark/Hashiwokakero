package darstellung;
import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.Iterator;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.FileImageOutputStream;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * Diese Klasse bietet Methoden zum Bearbeiten von Bildern und zum kopieren von Dateien an.
 * @author Adrian
 *
 */

public abstract class FileHandler {

	
	/**
	 * Die Methode liefert eine Kopie eines BufferedImage in der vorgegebenen Größe.
	 * @param original - Das zu verändernde Image
	 * @param neueWeite - Die gewünschte Breite des Bildes
	 * @param neueHoehe - Die gewünschte Länge des Bildes
	 * @return Das veränderte Image
	 */
	public static BufferedImage groesseÄndern(Image original, int neueWeite, int neueHoehe) {
		//Bei Fehlerhaften Parametern soll eine Standardgröße erzeugt werden
		if(neueWeite == 0) {
			neueWeite = 100;
		}
		if(neueHoehe == 0) {
			neueHoehe = 50;
		}
		int typ = BufferedImage.TRANSLUCENT;
		BufferedImage verändert = new BufferedImage(neueWeite, neueHoehe, typ);
		Graphics2D g = verändert.createGraphics();
		g.setComposite(AlphaComposite.Src);
		g.drawImage(original, 0, 0, neueWeite, neueHoehe, null);
		g.dispose();
		return verändert;
	}

	/**
	 * Die Methode speichert ein BufferedImage als JPEG Datei.
	 * @param image - Das zu speichernde Bild
	 * @param outFile - Die Zieldatei in der gespeichert werden soll
	 */
	public static void jpegSpeichern(BufferedImage image, File outFile) {
		Iterator<ImageWriter> iter = ImageIO.getImageWritersByFormatName("jpeg");
		ImageWriter writer = (ImageWriter) iter.next();
		ImageWriteParam iwp = writer.getDefaultWriteParam();
		iwp.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
		iwp.setCompressionQuality(0.1f);
		FileImageOutputStream output = null;
		try {
			output = new FileImageOutputStream(outFile);
			writer.setOutput(output);
			IIOImage img = new IIOImage((RenderedImage) image, null, null);
			writer.write(null, img, iwp);
			output.close();
		} catch (FileNotFoundException e) {
			JOptionPane.showConfirmDialog(null, "Die Zieldatei konnte nicht gefunden werden!", "Fehler!",
					JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE);
		} catch (IOException e) {
			JOptionPane.showConfirmDialog(null, "Ein unerwarteter Fehler ist aufgetreten!", "Fehler!",
					JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Diese Methode kopiert eine Datei und kann eine IOException auslösen, wenn das kopieren scheitert.
	 * @param in - Die zu speichernde Datei
	 * @param out - Die Zieldatei der Speicheroperation
	 * @throws IOException Wenn das kopieren scheitert
	 */
	@SuppressWarnings("resource")
	public static void dateiKopieren(File in, File out) throws IOException {
		FileChannel inChannel = null;
		FileChannel outChannel = null;
		try {
			inChannel = new FileInputStream(in).getChannel();
			outChannel = new FileOutputStream(out).getChannel();
			inChannel.transferTo(0, inChannel.size(), outChannel);
			inChannel.close();
			outChannel.close();
		} catch (IOException e) {
			throw e;
		}
	}
	
	/**
	 * Diese Methode dreht ein BufferedImage um eine vorgegebene Gradzahl.
	 * @param src - Das BufferedImage
	 * @param grad - Gewünschter Drehungsgrad
	 * @return Das gedrehte Image
	 */
	public static BufferedImage bildDrehen(BufferedImage src, double grad) {
        AffineTransform affineTransform = AffineTransform.getRotateInstance(
                Math.toRadians(grad),
                src.getWidth() / 2,
                src.getHeight() / 2);
        BufferedImage gedrehtesBild = new BufferedImage(src.getWidth(), src
                .getHeight(), src.getType());
        Graphics2D g = (Graphics2D) gedrehtesBild.getGraphics();
        g.setTransform(affineTransform);
        g.drawImage(src, 0, 0, null);
        return gedrehtesBild;
    }
	
	/**
	 * Diese Methode konvertiert ein ImageIcon (z.B. aus einem JLabel) in ein BufferedImage.
	 * @param icon Das ImageIcon
	 * @return Ein BufferedImage mit dem Inhalt des Icons
	 */
	
	public static BufferedImage IconZuImage(ImageIcon icon) {
		BufferedImage bi = new BufferedImage(
			    icon.getIconWidth(),
			    icon.getIconHeight(),
			    BufferedImage.TYPE_INT_RGB);
				Graphics g = bi.createGraphics();
				icon.paintIcon(null, g, 0,0);
				g.dispose();
				return bi;
	}
}
