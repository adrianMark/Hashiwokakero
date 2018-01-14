package listener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import darstellung.StationButton;

/**
 * Dieser Listener verwaltet die Stationen während ein Level gespielt wird. 
 * @author Adrian
 *
 */

public class StationListener implements ActionListener {

	private StationButton button;

	public StationListener(StationButton button) {
		super();
		this.button = button;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (button.getPanel().getGitter().getStation(button.getHoehe(), button.getLaenge()).isAktiv()) {
			button.select();
		} else {
			button.deselect();
		}
	}
}
