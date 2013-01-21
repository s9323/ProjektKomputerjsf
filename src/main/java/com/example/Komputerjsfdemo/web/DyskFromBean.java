package com.example.Komputerjsfdemo.web;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;

import Komputer.JW.business.Disk;
import Komputer.JW.business.services.DiskManager;

@SessionScoped
@Named("dyskBean")
public class DyskFromBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Disk disk = new Disk();

	private ListDataModel<Disk> disks = new ListDataModel<Disk>();

	@Inject
	private DiskManager dm = new DiskManager();

	public Disk getDisk() {
		return disk;
	}

	public void setDisk(Disk disk) {
		this.disk = disk;
	}
	
	public ListDataModel<Disk> getAllDisks() {
		disks.setWrappedData(dm.getAll());
		return disks;
	}
//
//	// Actions
//	public String addDisk() {
//		dm.save(disk);
//		return "showDysk";
//	}
//
	public String deleteDisk() {
		Disk diskToDelete = disks.getRowData();
		dm.delete(diskToDelete);
		return null;
	}
}
