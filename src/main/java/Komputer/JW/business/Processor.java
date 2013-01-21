package Komputer.JW.business;



public class Processor<T> extends Parts<T>{
	Computer comp;	
	private String nazwa_firmy;
	private int ilosc;
	private int cena;
	private int id_procesora;
	private int ilosc_rdzeni;
	
	public Processor(Computer comp){
		this.comp = comp;	
		}	
	
	public Processor(String nazwa_firmy, int ilosc, int ilosc_rdzeni,
			int cena) {
		this.nazwa_firmy = nazwa_firmy;
		this.ilosc = ilosc;
		this.ilosc_rdzeni = ilosc_rdzeni;
		this.cena = cena;
	}
	
	public int getId() {
		return id_procesora;
	}

	public void setId(int id_procesora) {
		this.id_procesora = id_procesora;
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
    
    public int getIlosc_rdzeni(){
    	return ilosc_rdzeni;
    }
    
    public void setIlosc_rdzeni(int ilosc_rdzeni){
    	this.ilosc_rdzeni = ilosc_rdzeni;
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
		return comp.about(firma) + "PROCESOR" + firma ;	
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
		s+=id_procesora+" "+nazwa_firmy+" "+ilosc+" "+ilosc_rdzeni+" "+cena;
		
		return s;	
	}
}
