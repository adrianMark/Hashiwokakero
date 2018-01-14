package logik;
import java.util.ArrayList;

/**
 * Diese Klasse stellt die logische Einheit des Spielbretts dar. Es werden Methoden zum Verbinden und Trennen der Stationen und zum Überprüfen des Spielstandes
 * angeboten.
 * @author Adrian
 *
 */

//Konstruktor
public class Gitter {
	private ArrayList<ArrayList<Station>> gitter = new ArrayList<>();
	private int hoehe = 0;
	private int laenge = 0;

	public Gitter(int hoehe, int laenge) {
		this.hoehe = hoehe;
		this.laenge = laenge;
		for (int x = 0; x <= hoehe; x++) {
			gitter.add(new ArrayList<Station>());
		}
		for (int x = 0; x <= hoehe; x++) {
			for (int y = 0; y < laenge; y++) {
				gitter.get(x).add(new Station(x, y));
			}
		}
	}

	/**
	 * Diese Methode verbindet zwei Stationen an den ausgewählten Positionen des Gitters
	 * @param stationHoehe1 Höhe der ersten Station 
	 * @param stationLaenge1 Länge der ersten Station
	 * @param stationHoehe2 Höhe der zweiten Station 
	 * @param stationLaenge2 Länge der zweiten Station
	 * @return True, wenn die Verbindung erfolgreich war; False, wenn die Verbindung fehlgeschlagen ist
	 */
	public boolean verbinde(int stationHoehe1, int stationLaenge1, int stationHoehe2, int stationLaenge2) {
		if (getStation(stationHoehe1, stationLaenge1).isAktiv()
				&& getStation(stationHoehe2, stationLaenge2).isAktiv()) {
			int iterator = 0;
			int ende = 0;
			if (stationHoehe1 == stationHoehe2
					&& ((stationLaenge1 - stationLaenge2) > 1 || (stationLaenge1 - stationLaenge2) < -1)) {
				if (stationLaenge1 > stationLaenge2) {
					iterator = stationLaenge1;
					ende = stationLaenge2;
				} else if (stationLaenge1 < stationLaenge2) {
					iterator = stationLaenge2;
					ende = stationLaenge1;
				} else {
					return false;
				}
				for (int x = iterator - 1; x >= ende + 1; x--) {
					Station s = getStation(stationHoehe1, x);
					if (s.isVerbOben1() || s.isVerbOben2() || s.isAktiv()) {
						return false;
					}
				}
				for (int x = iterator; x >= ende + 1; x--) {
					Station s = getStation(stationHoehe1, x);
					if (!s.isVerbLinks1()) {
						s.setVerbLinks1(true);
					} else if (s.isVerbLinks1() && !s.isVerbLinks2()) {
						s.setVerbLinks2(true);
					} else {
						return false;
					}
				}
				getStation(stationHoehe1, stationLaenge1)
						.setVerbindungen(getStation(stationHoehe1, stationLaenge1).getVerbindungen() + 1);
				getStation(stationHoehe2, stationLaenge2)
						.setVerbindungen(getStation(stationHoehe2, stationLaenge2).getVerbindungen() + 1);
				return true;
			} else if (stationLaenge1 == stationLaenge2
					&& ((stationHoehe1 - stationHoehe2 > 1) || (stationHoehe1 - stationHoehe2 < -1))) {
				if (stationHoehe1 > stationHoehe2) {
					iterator = stationHoehe1;
					ende = stationHoehe2;
				} else if (stationHoehe1 < stationHoehe2) {
					iterator = stationHoehe2;
					ende = stationHoehe1;
				} else {
					return false;
				}
				for (int x = iterator - 1; x >= ende + 1; x--) {
					Station s = getStation(x, stationLaenge1);
					if (s.isVerbLinks1() || s.isVerbLinks2() || s.isAktiv()) {
						return false;
					}
				}
				for (int x = iterator; x >= ende + 1; x--) {
					Station s = getStation(x, stationLaenge1);
					if (!s.isVerbOben1()) {
						s.setVerbOben1(true);
					} else if (s.isVerbOben1() && !s.isVerbOben2()) {
						s.setVerbOben2(true);
					} else {
						return false;
					}
				}
				getStation(stationHoehe1, stationLaenge1)
						.setVerbindungen(getStation(stationHoehe1, stationLaenge1).getVerbindungen() + 1);
				getStation(stationHoehe2, stationLaenge2)
						.setVerbindungen(getStation(stationHoehe2, stationLaenge2).getVerbindungen() + 1);
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	/**
	 * Diese Methode setzt den Spielstand auf die Ausgangssituation zurück
	 */
	public void neuStart() {
		for(int x = 0; x<hoehe;x++) {
			for(int y = 0; y<laenge;y++) {
				Station s = getStation(x, y);
				s.setVerbLinks1(false);
				s.setVerbLinks2(false);
				s.setVerbOben1(false);
				s.setVerbOben2(false);
				s.setVerbindungen(0);
			}
		}
	}

	/**
	 * Diese Methode trennt die Verbindung zwischen dieser Station und allen verbundenen (wenn vorhanden).
	 * @param station Die Ausgangsstation der Trennung
	 */
	public void trenne(Station station) {
		int x = 0;
		boolean eins = false;
		boolean zwei = false;
		if (station.isVerbOben1()) {
			while (true) {
				Station drüber = getStation(station.getHoehe() - x, station.getLaenge());
				x++;
				if (!drüber.isAktiv()) {
					if (getStation(drüber.getHoehe() - 1, drüber.getLaenge()).isAktiv()) {
						eins = drüber.isVerbOben1();
						zwei = drüber.isVerbOben2();
					}
					drüber.setVerbOben1(false);
					drüber.setVerbOben2(false);
				} else {
					if (eins && !zwei) {
						drüber.setVerbindungen(drüber.getVerbindungen() - 1);
					} else if (eins && zwei) {
						drüber.setVerbindungen(drüber.getVerbindungen() - 2);
					}
					x = 0;
					eins = false;
					zwei = false;
					break;
				}
			}
			while (true) {
				Station drunter = getStation(station.getHoehe() + x, station.getLaenge());
				x++;
				eins = drunter.isVerbOben1();
				zwei = drunter.isVerbOben2();
				drunter.setVerbOben1(false);
				drunter.setVerbOben2(false);
				if (drunter.isAktiv()) {
					if (eins && !zwei) {
						drunter.setVerbindungen(drunter.getVerbindungen() - 1);
					} else if (eins && zwei) {
						drunter.setVerbindungen(drunter.getVerbindungen() - 2);
					}
					eins = false;
					zwei = false;
					x = 0;
					break;
				}

			}
		} else if (station.isVerbLinks1()) {

			while (true) {
				Station links = getStation(station.getHoehe(), station.getLaenge() - x);
				x++;
				if (!links.isAktiv()) {
					if (getStation(links.getHoehe(), links.getLaenge() - 1).isAktiv()) {
						eins = links.isVerbLinks1();
						zwei = links.isVerbLinks2();
					}
					links.setVerbLinks1(false);
					links.setVerbLinks2(false);
				} else {
					if (eins && !zwei) {
						links.setVerbindungen(links.getVerbindungen() - 1);
					} else if (eins && zwei) {
						links.setVerbindungen(links.getVerbindungen() - 2);
					}
					eins = false;
					zwei = false;
					x = 0;
					break;
				}
			}
			while (true) {
				Station rechts = getStation(station.getHoehe(), station.getLaenge() + x);
				x++;
				eins = rechts.isVerbLinks1();
				zwei = rechts.isVerbLinks2();
				rechts.setVerbLinks1(false);
				rechts.setVerbLinks2(false);
				if (rechts.isAktiv()) {
					if (eins && !zwei) {
						rechts.setVerbindungen(rechts.getVerbindungen() - 1);
					} else if (eins && zwei) {
						rechts.setVerbindungen(rechts.getVerbindungen() - 2);
					}

					break;
				}
			}
		}
	}
	
	//Getter und Setter	
	public Station getStation(int hoehe, int laenge) {
		return gitter.get(hoehe).get(laenge);
	}

	public void stationAktivieren(int hoehe, int laenge, int ziel) {
		gitter.get(hoehe).get(laenge).setAktiv(true);
		gitter.get(hoehe).get(laenge).setZiel(ziel);
	}
	public ArrayList<ArrayList<Station>> getGitter() {
		return gitter;
	}

	public void setGitter(ArrayList<ArrayList<Station>> gitter) {
		this.gitter = gitter;
	}

	public int getHoehe() {
		return hoehe;
	}

	public void setHoehe(int hoehe) {
		this.hoehe = hoehe;
	}

	public int getLaenge() {
		return laenge;
	}

	public void setLaenge(int laenge) {
		this.laenge = laenge;
	}

	//toString Methode
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (int x = 0; x < hoehe; x++) {
			sb.append("\n");
			for (int y = 0; y < laenge; y++) {
				Station s = gitter.get(x).get(y);
				if (s.isVerbOben1() && !s.isVerbOben2()) {
					sb.append("\t|");
				} else if (s.isVerbOben1() && s.isVerbOben2()) {
					sb.append("\t||");
				} else {
					sb.append("\t");
				}
			}
			sb.append("\n");
			for (int y = 0; y < laenge; y++) {
				Station s = gitter.get(x).get(y);
				if (s.isVerbLinks1() && !s.isVerbLinks2()) {
					sb.append("-------");
				} else if (s.isVerbLinks1() && s.isVerbLinks2()) {
					sb.append("=======");
				} else {
					sb.append("\t");
				}
				if (s.isAktiv()) {
					sb.append(s.getZiel());
				} else {
					sb.append("o");
				}
			}
		}
		return sb.toString();
	}
}
