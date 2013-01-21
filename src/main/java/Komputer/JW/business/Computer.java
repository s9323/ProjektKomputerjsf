package Komputer.JW.business;



public abstract class Computer implements ComputerInterface {	
	
	protected String comp ="Komputer podstawowy";
	
    public String about(String firma)
    {		
	return comp;	
	}	
     public double cena() 
     {		
	    return 0;	
	
     }
	
}