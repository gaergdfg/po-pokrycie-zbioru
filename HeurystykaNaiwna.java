import java.util.Set;
import java.util.HashSet;
import java.util.List;


public class HeurystykaNaiwna extends Algorytm {
	public Set<Integer> rozwiaz(List<Zbior> listaZbiorow, Set<Integer> zbiorDoPokrycia) {
		Set<Integer> wybraneZbiory = new HashSet<Integer>();

		for (Zbior zbior : listaZbiorow) {
			Set<Integer> doUsuniecia = new HashSet<Integer>();

			for (int element : zbiorDoPokrycia) {
				if (zbior.czyZawiera(element)) {
					doUsuniecia.add(element);
				}
			}

			if (doUsuniecia.size() > 0) {
				zbiorDoPokrycia.removeAll(doUsuniecia);
				wybraneZbiory.add(zbior.dajIndeks());
			}
		}

		if (zbiorDoPokrycia.size() > 0) {
			return new HashSet<Integer>();
		}
		return wybraneZbiory;
	}
}
