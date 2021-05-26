import java.util.Set;
import java.util.HashSet;
import java.util.List;


public class HeurystykaZachlanna extends Algorytm {
	public Set<Integer> rozwiaz(List<Zbior> listaZbiorow, Set<Integer> zbiorDoPokrycia) {
		Set<Integer> wybraneZbiory = new HashSet<Integer>();

		// przegladanie wszystkich zbiorow dopoki mozemy lub nie znalezlismy wyniku
		while (listaZbiorow.size() > 0 && zbiorDoPokrycia.size() > 0) {
			int najwiekszaLiczbaPokrytychElementow = 0;
			Zbior najlepszyZbior = null;

			// wybranie najlepszego zbioru
			for (Zbior zbior : listaZbiorow) {
				int liczbaPokrytychElementow = 0;

				for (int element : zbiorDoPokrycia) {
					if (zbior.czyZawiera(element)) {
						liczbaPokrytychElementow++;
					}
				}

				if (liczbaPokrytychElementow > najwiekszaLiczbaPokrytychElementow) {
					najwiekszaLiczbaPokrytychElementow = liczbaPokrytychElementow;
					najlepszyZbior = zbior;
				}
			}

			if (najwiekszaLiczbaPokrytychElementow == 0) {
				return new HashSet<Integer>();
			}

			// usuniecie z pokrywanego zbioru elementow wybranego zbioru
			Set<Integer> doUsuniecia = new HashSet<Integer>();
			for (int element : zbiorDoPokrycia) {
				if (najlepszyZbior.czyZawiera(element)) {
					doUsuniecia.add(element);
				}
			}
			zbiorDoPokrycia.removeAll(doUsuniecia);

			// dodanie zbioru do wyniku
			wybraneZbiory.add(najlepszyZbior.dajIndeks());
		}

		if (zbiorDoPokrycia.size() > 0) {
			return new HashSet<Integer>();
		}
		return wybraneZbiory;
	}
}
