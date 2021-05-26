import java.util.Set;
import java.util.HashSet;


public class Zbior {
	private int indeks;
	private Set<Skladnik> zawartosc;


	Zbior(int indeks, int[] argumenty) {
		this.indeks = indeks;
		this.zawartosc = new HashSet<Skladnik>();

		dodajZawartosc(argumenty);
	}


	public int dajIndeks() {
		return indeks;
	}


	public boolean czyZawiera(int element) {
		for (Skladnik skladnik : zawartosc) {
			if (skladnik.czyZawiera(element)) {
				return true;
			}
		}
		return false;
	}


	public void dodajZawartosc(int[] argumenty) {
		if (argumenty[1] == 0) {
			zawartosc.add(new Element(argumenty[0]));
		} else if (argumenty[2] == 0) {
			zawartosc.add(new Nieskonczony(argumenty[0], argumenty[1]));
		} else {
			zawartosc.add(new Skonczony(argumenty[0], argumenty[2], argumenty[1]));
		}
	}
}
