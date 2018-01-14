package logik;

public class Station {
	
	private int ziel = 0; //Wie viele Verbindungen sind für die Station vorgesehen
	private int hoehe = 0; //Hoehe auf dem Spielfeld
	private int laenge = 0; //Länge auf dem Spielfeld
	private int verbindungen = 0; //Aktuelle Zahl an Verbindungen
	private boolean aktiv = false; //True, wenn die Station eine Insel ist
	
	private boolean verbOben1 = false; //True,wenn einfach nach oben verbunden
	private boolean verbOben2 = false;//True, wenn doppelt nach oben verbunden
	private boolean verbLinks1 = false;//True, wenn einfach nach links verbunden
	private boolean verbLinks2 = false; //True, wenn doppelt nach oben verbunden
	
	//Konstruktor
	public Station(int x, int y) {
		this.hoehe = x;
		this.laenge = y;
	}
	
	/**
	 * Diese Methode prüft, ob die Anzahl der Verbindungen mit dem Ziel der Station übereinstimmt.
	 * @return True, wenn Ziel erfüllt
	 */
	public boolean zielErfuellt() {
		if(this.ziel == this.verbindungen) {
			return true;
		}else {
			return false;
		}
	}
	
	//Getter und Setter
	public int getVerbindungen() {
		return verbindungen;
	}

	public void setVerbindungen(int verbindungen) {
		this.verbindungen = verbindungen;
	}

	public int getZiel() {
		return ziel;
	}

	public void setZiel(int ziel) {
		this.ziel = ziel;
	}

	public int getHoehe() {
		return hoehe;
	}

	public void setHoehe(int x) {
		this.hoehe = x;
	}

	public int getLaenge() {
		return laenge;
	}

	public void setLaenge(int y) {
		this.laenge = y;
	}

	public boolean isAktiv() {
		return aktiv;
	}

	public void setAktiv(boolean aktiv) {
		this.aktiv = aktiv;
	}

	public boolean isVerbOben1() {
		return verbOben1;
	}

	public void setVerbOben1(boolean verbOben1) {
			this.verbOben1 = verbOben1;
	}

	public boolean isVerbOben2() {
		return verbOben2;
	}

	public void setVerbOben2(boolean verbOben2){
			this.verbOben2 = verbOben2;
	}

	public boolean isVerbLinks1() {
		return verbLinks1;
	}

	public void setVerbLinks1(boolean verbLinks1) {
		this.verbLinks1 = verbLinks1;
	}

	public boolean isVerbLinks2() {
		return verbLinks2;
	}

	public void setVerbLinks2(boolean verbLinks2) {
		this.verbLinks2 = verbLinks2;
	}

}
