package Komputer.JW.business.services;

import java.util.List;

import Komputer.JW.business.Disk;

public interface ManagerInterface<TEntity> {
	
	public TEntity get(int id);
	public List<TEntity> getAll();
	public boolean save(TEntity obj);
	public boolean delete(TEntity obj);
	
	
}
