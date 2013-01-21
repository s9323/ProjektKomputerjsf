package Komputer.JW.business;



public class MotherBoard<T> extends Parts<T>{
	Computer comp;	
	private String nazwa_firmy;
	private int ilosc;
	private int cena;
	private int id_plyty;
	
	public MotherBoard(Computer comp){
		this.comp = comp;	
		}	
	
	public MotherBoard(String nazwa_firmy, int ilosc,
			int cena) {
		this.nazwa_firmy = nazwa_firmy;
		this.ilosc = ilosc;
		this.cena = cena;
	}
	
	public int getId() {
		return id_plyty;
	}

	public void setId(int id_plyty) {
		this.id_plyty = id_plyty;
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
		return comp.about(firma) + "P�YTA G��WNA" + firma ;	
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
		s+=id_plyty+" "+nazwa_firmy+" "+ilosc+" "+cena;
		
		return s;	
	}
	
	
}
