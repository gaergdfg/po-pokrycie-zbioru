import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;


public class AlgorytmDokladny extends Algorytm {
	public Set<Integer> rozwiaz(List<Zbior> listaZbiorow, Set<Integer> zbiorDoPokrycia) {
		return znajdzOptymalnyWynik(listaZbiorow, zbiorDoPokrycia, new boolean[listaZbiorow.size()], 0);
	}


	// sprawdza wszystkie mozliwe kombinacje zbiorow i zwraca najlepsza
	public Set<Integer> znajdzOptymalnyWynik(
		List<Zbior> listaZbiorow,
		Set<Integer> zbiorDoPokrycia,
		boolean[] uzywaneZbiory,
		int pozycja
	) {
		Set<Integer> wynik = new HashSet<Integer>();
		Set<Integer> kandydat = new HashSet<Integer>();

		kandydat = f(listaZbiorow, zbiorDoPokrycia, uzywaneZbiory, pozycja, false);
		wynik = dajMniejszySet(wynik, kandydat);

		kandydat = f(listaZbiorow, zbiorDoPokrycia, uzywaneZbiory, pozycja, true);
		wynik = dajMniejszySet(wynik, kandydat);

		return wynik;
	}


	// zaznacza, czy uzyc zbioru z indeksem ([pozycja] + 1), a potem ustala,
	// co bedzie z kolejnym indeksem lub oblicza wynik
	private Set<Integer> f(
		List<Zbior> listaZbiorow,
		Set<Integer> zbiorDoPokrycia,
		boolean[] uzywaneZbiory,
		int pozycja,
		boolean czyUzycZbior
	) {
		Set<Integer> wynik = new HashSet<Integer>();
		
		uzywaneZbiory[pozycja] = czyUzycZbior;
		if (pozycja < listaZbiorow.size() - 1) {
			wynik = znajdzOptymalnyWynik(listaZbiorow, zbiorDoPokrycia, uzywaneZbiory, pozycja + 1);
		} else {
			List<Zbior> wybraneZbiory = new ArrayList<Zbior>();

			for (Zbior zbior : listaZbiorow) {
				if (uzywaneZbiory[zbior.dajIndeks() - 1]) {
					wybraneZbiory.add(zbior);
				}
			}
			
			if (czyPokrywaja(wybraneZbiory, new HashSet<Integer>(zbiorDoPokrycia))) {
				for (Zbior zbior : wybraneZbiory) {
					wynik.add(zbior.dajIndeks());
				}
			}
		}

		return wynik;
	}


	// zwraca mniejszy oraz o ile to mozliwe, niepusty set
	private Set<Integer> dajMniejszySet(Set<Integer> a, Set<Integer> b) {
		if (a.size() == 0) {
			return b;
		}
		if (b.size() == 0) {
			return a;
		}
		if (a.size() < b.size()) {
			return a;
		}
		return b;
	}


	// sprawdza, czy [wybraneZbiory] pokryja [zbiorDoPokrycia]
	private boolean czyPokrywaja(List<Zbior> wybraneZbiory, Set<Integer> zbiorDoPokrycia) {
		Set<Integer> pokryteElementy = new HashSet<Integer>();

		for (Zbior zbior : wybraneZbiory) {
			for (int element : zbiorDoPokrycia) {
				if (zbior.czyZawiera(element)) {
					pokryteElementy.add(element);
				}
			}
		}

		return zbiorDoPokrycia.size() == pokryteElementy.size();
	}
}
