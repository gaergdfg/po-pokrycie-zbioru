public class Nieskonczony extends Skladnik {
	private int start;
	private int przyrost;


	Nieskonczony(int start, int przyrost) {
		this.start = start;
		this.przyrost = przyrost;
	}


	public boolean czyZawiera(int element) {
		return start <= element && (element - start) % przyrost == 0;
	}
}
