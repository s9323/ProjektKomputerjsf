package Komputer.JW.business.services;

public class Client {

	private int id;
	private String name;
	private String surname;
	private String adress;

	public Client(String name, String surname, String adress) {
		this.name = name;
		this.surname = surname;
		this.adress = adress;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getAdress(){
		return adress;
		
	}
	
	public void setAdress(String adress){
		this.adress = adress;
	}

	public void add() {
				
	}

	public String toString (){
		String s="";
		s+=id+" "+name+" "+surname+" "+adress;
		
		return s;	
	}

	
		
}

