public class Skonczony extends Skladnik {
	private int start;
	private int koniec;
	private int przyrost;


	Skonczony(int start, int koniec, int przyrost) {
		this.start = start;
		this.koniec = koniec;
		this.przyrost = przyrost;
	}


	public boolean czyZawiera(int element) {
		return start <= element && element <= koniec && (element - start) % przyrost == 0;
	}
}
