package Komputer.JW.business;



public class Housing<T> extends Parts<T>{
	Computer comp;	
	private String nazwa_firmy;
	private int ilosc;
	private int cena;
	private int id_obudowy;
	
	public Housing(Computer comp){
		this.comp = comp;	
		}	
	
	public Housing(String nazwa_firmy, int ilosc,
			int cena) {
		this.nazwa_firmy = nazwa_firmy;
		this.ilosc = ilosc;
		this.cena = cena;
	}
	
	public int getId() {
		return id_obudowy;
	}

	public void setId(int id_obudowy) {
		this.id_obudowy = id_obudowy;
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
		return comp.about(firma) + "OBUDOWA" + firma ;	
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
		s+=id_obudowy+" "+nazwa_firmy+" "+ilosc+" "+cena;
		
		return s;	
	}

}
