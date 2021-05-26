public class Element extends Skladnik {
	private int wartosc;


	Element(int wartosc) {
		this.wartosc = wartosc;
	}


	public boolean czyZawiera(int element) {
		return this.wartosc == element;
	}
}
