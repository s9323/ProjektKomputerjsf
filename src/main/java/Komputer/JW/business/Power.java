package Komputer.JW.business;



public class Power<T> extends Parts<T>{
	Computer comp;	
	private String nazwa_firmy;
	private int ilosc;
	private int cena;
	private int id_zasilacza;
	
	public Power(Computer comp){
		this.comp = comp;	
		}	
	
	public Power(String nazwa_firmy, int ilosc,
			int cena) {
		this.nazwa_firmy = nazwa_firmy;
		this.ilosc = ilosc;
		this.cena = cena;
	}
	
	public int getId() {
		return id_zasilacza;
	}

	public void setId(int id_zasilacza) {
		this.id_zasilacza = id_zasilacza;
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
  
    public int getCena() {
		return cena;
	}
    
    public void setCena(int cena) {
	  this.cena = cena;
	}
	
	
	@Override	
	public String about(String firma) 
	{
		return comp.about(firma) + "ZASILACZ" + firma ;	
	}	
	
	public double cena()
	{		
		return comp.cena()+ cena;			
	}


	@Override
	public boolean check(T obj) {
		
		return false;
	}

@Override
	
	public String toString (){
		String s="";
		s+=id_zasilacza+" "+nazwa_firmy+" "+ilosc+" "+cena;
		
		return s;	
	}
	
	
}
