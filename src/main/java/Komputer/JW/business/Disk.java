package Komputer.JW.business;

public class Disk extends Parts<Object> {
	Computer comp;
	private String nazwa_firmy ="";
	private int ilosc;
	private int pojemnosc;
	private int cena;
	private int id_dysku;

	public Disk(){
	
		
	}
	
	public Disk(Computer comp) {
		this.comp = comp;
	}

	public Disk(String nazwa_firmy, int ilosc, int pojemnosc, int cena) {
		this.nazwa_firmy = nazwa_firmy;
		this.ilosc = ilosc;
		this.pojemnosc = pojemnosc;
		this.cena = cena;

	}

	public int getId() {
		return id_dysku;
	}

	public void setId(int id_dysku) {
		this.id_dysku = id_dysku;
	}

	public String getNazwa_firmy() {
		return nazwa_firmy;
	}

	public void setNazwa_firmy(String nazwa_firmy) {
		this.nazwa_firmy = nazwa_firmy;
	}

	public int getIlosc() {
		return ilosc;
	}

	public void setIlosc(int ilosc) {
		this.ilosc = ilosc;

	}

	public int getPojemnosc() {
		return pojemnosc;
	}

	public void setPojemnosc(int pojemnosc) {
		this.pojemnosc = pojemnosc;
	}

	public int getCena() {
		return cena;
	}

	public void setCena(int cena) {
		this.cena = cena;
	}

	@Override
	public String about(String firma) {
		return comp.about(firma) + "DYSK" + firma;
	}

	public double cena() {
		return comp.cena() + cena;
	}

	@Override
	public String toString() {
		String s = "";

		s += id_dysku + " " + nazwa_firmy + " " + ilosc + " " + pojemnosc + " "
				+ cena;

		return s;
	}

	@Override
	public boolean check(Object obj) {

		return false;
	}
}
