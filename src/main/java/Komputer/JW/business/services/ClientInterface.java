package Komputer.JW.business.services;

import java.util.List;

public interface ClientInterface<TEntity> {

	public TEntity get(int id);
	public List<TEntity> getAll();
	public boolean save(TEntity obj);
	public boolean delete(TEntity obj);
	
	
}
