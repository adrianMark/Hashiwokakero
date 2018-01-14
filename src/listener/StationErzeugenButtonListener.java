package listener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import darstellung.StationErzeugenButton;

/**
 * Dieser Listener wird bei der Erzeugung von neuen Levels verwendet und erlaubt es, den angeklickten {@link StationErzeugenButton} zu einer Station des Levels zu machen.
 * @author Adrian
 *
 */

public class StationErzeugenButtonListener implements ActionListener {

	private StationErzeugenButton button;

	public StationErzeugenButtonListener(StationErzeugenButton button) {
		super();
		this.button = button;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (!button.isStation()) {
			StationErzeugenButton b = button.replaceChosen();
			button.organizeBorder();
			button.istStation();
			if (b != null) {
				b.organizeBorder();
			}
		} else {
			StationErzeugenButton b = button.replaceChosen();
			if (b != null) {
				b.organizeBorder();
			}
			button.organizeBorder();
		}

	}
}
