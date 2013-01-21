package Komputer.JW;

import Komputer.JW.business.Disk;
import Komputer.JW.business.Drivers;
import Komputer.JW.business.GraphicsCard;
import Komputer.JW.business.Housing;
import Komputer.JW.business.MotherBoard;
import Komputer.JW.business.Power;
import Komputer.JW.business.Processor;
import Komputer.JW.business.RamMemory;
import Komputer.JW.business.services.Client;
import Komputer.JW.business.services.DbAdapter;
import Komputer.JW.business.services.DiskManager;
import Komputer.JW.business.services.DriversManager;
import Komputer.JW.business.services.GraphicsCardManager;
import Komputer.JW.business.services.HousingManager;
import Komputer.JW.business.services.MotherBoardManager;
import Komputer.JW.business.services.PowerManager;
import Komputer.JW.business.services.ProcessorManager;
import Komputer.JW.business.services.RamMemoryManager;


public class Main {

	public static void main(String[] args) {
		
	
	
		DbAdapter mgr = new DbAdapter();		
		mgr.save(new Client("Adam", "Kolwaski" ,"Podwale"));
		Client ktos = mgr.get(1);
	
		
		System.out.println(mgr.getAll());
	
	 
	    DiskManager dm = new DiskManager();
	    //dm.save(new Disk("Segate", 10, 500, 200));
	   
	   System.out.println(dm.search("Segate"));
	    
	   
	    DriversManager drm = new DriversManager();
	    //drm.save(new Drivers("LG", 10, 150));
        
	    System.out.println(drm.search("LG"));
        
        
	    GraphicsCardManager gcm = new GraphicsCardManager();
	    //gcm.save(new GraphicsCard("Radeon hd9550", "Gigabyte", 2 , 512, 200));
	   
	    System.out.println(gcm.search("Gigabyte"));
	    
	    
	    HousingManager hm = new HousingManager();
	    //hm.save(new Housing("Antec", 3 , 400));
	   
	    System.out.println(hm.search("Antec"));
	    
	    
	    MotherBoardManager mbm = new MotherBoardManager();
	    //mbm.save(new MotherBoard("Gigabyte", 2 , 1000));
	   
	   System.out.println(mbm.search("Gigabyte"));
	    
	    
	    PowerManager pm = new PowerManager();
	    //pm.save(new Power("Areocool", 5 , 200));
	   
	   System.out.println(pm.search("Areocool"));
	    
	    
	    ProcessorManager mp = new ProcessorManager();
	    //mp.save(new Processor("Intel core i7", 2 , 4 , 1500));
	   
	    System.out.println(mp.search("Intel core i7"));
	    
	    
	    RamMemoryManager rmm = new RamMemoryManager();
	    //rmm.save(new RamMemory("Kingston", 2 , 2, 400));
	   
	    System.out.println(rmm.search("Kingston"));
	}

}     
