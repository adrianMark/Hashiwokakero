package listener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;

import darstellung.GUI;

/**
 * Dieser Listener erlaubt es ein angeklicktes Level zu starten. Dazu ruft es die levelStarten() Methode von {@link GUI} auf.
 * @author Adrian
 *
 */

public class LevelAufrufenListener implements ActionListener{

	private GUI gui;
	private boolean userLevel = false;
	
	public  LevelAufrufenListener(GUI gui, boolean userLevel) {
		super();
		this.userLevel = userLevel;
		this.gui = gui;
	}
	
	public  LevelAufrufenListener(GUI gui) {
		super();
		this.gui = gui;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		JButton button = (JButton)arg0.getSource();
		String pfad;
		if(!userLevel){
			pfad = button.getText();
			pfad = pfad.replace(" - ", "\\");
			gui.spielStarten(new File("").getAbsolutePath()+"\\levels\\"+pfad,null);
		}else {
			pfad = button.getText();
			gui.spielStarten(new File("").getAbsolutePath()+"\\levels\\User\\"+pfad,null);
		}
	}
	
}
