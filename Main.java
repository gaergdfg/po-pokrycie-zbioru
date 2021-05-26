import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;


public class Main {
	private static Scanner in = new Scanner(System.in);

	private static Algorytm[] algorytmy = {
		new AlgorytmDokladny(),
		new HeurystykaZachlanna(),
		new HeurystykaNaiwna()
	};


	public static void main(String[] args) {
		List<Zbior> zbiory = new ArrayList<Zbior>();
		int liczbaZbiorow = 0;

		int[] dane = {0, 0, 0, 0};
		int pozycja = 0;
		boolean czyNowyZbior = true;
		Zbior ostatniZbior = null;

		while (in.hasNextInt()) {
			dane[pozycja++] = in.nextInt();

			if (dane[pozycja - 1] == 0) {
				if (czyNowyZbior) {
					zbiory.add(new Zbior(++liczbaZbiorow, wartoscBezwzglednaTablicy(dane)));
					ostatniZbior = zbiory.get(zbiory.size() - 1);
				} else {
					ostatniZbior.dodajZawartosc(dane);
				}

				dane = new int[] {0, 0, 0, 0};
				pozycja = 0;
				czyNowyZbior = true;
			} else if (pozycja > 1 && dane[0] > 0 && dane[pozycja - 1] > 0) {
				int ostatniaLiczba = dane[pozycja - 1];
				dane[pozycja - 1] = 0;

				if (czyNowyZbior) {
					zbiory.add(new Zbior(++liczbaZbiorow, wartoscBezwzglednaTablicy(dane)));
					ostatniZbior = zbiory.get(zbiory.size() - 1);
					czyNowyZbior = false;
				} else {
					ostatniZbior.dodajZawartosc(dane);
				}

				dane = new int[] {ostatniaLiczba, 0, 0, 0};
				pozycja = 1;
			} else if (pozycja == 2 && dane[0] < 0 && dane[1] > 0) {
				Set<Integer> wynik = algorytmy[dane[1] - 1].rozwiaz(new ArrayList<Zbior>(zbiory), stworzZbior(-dane[0]));
				wypisz(wynik);
				
				dane = new int[] {0, 0, 0, 0};
				pozycja = 0;
			}
		}
	}


	// zwraca tablice, w ktorej wartosc pod indeksem [i] jest wartoscia bezwzgledna oryginalnej talblicy pod indeksem [i]
	private static int[] wartoscBezwzglednaTablicy(int[] tablica) {
		for (int i = 0; i < tablica.length; i++) {
			tablica[i] = tablica[i] < 0 ? -tablica[i] : tablica[i];
		}
		return tablica;
	}


	// tworzy zbior zawierajacy liczby calkowite od 1 do [wartosc]
	private static Set<Integer> stworzZbior(int wartosc) {
		Set<Integer> zbior = new HashSet<Integer>();

		for (int element = 1; element <= wartosc; element++) {
			zbior.add(element);
		}

		return zbior;
	}


	private static void wypisz(Set<Integer> indeksyZbiorow) {
		// nie ma rozwiazania
		if (indeksyZbiorow.size() == 0) {
			System.out.println("0");
			return;
		}

		// jest rozwiazanie
		boolean jestPierwszy = true;
		for(int indeks : indeksyZbiorow) {
			System.out.print((jestPierwszy ? "" : " ") + indeks);
			jestPierwszy = false;
		}
		System.out.println();
	}
}
