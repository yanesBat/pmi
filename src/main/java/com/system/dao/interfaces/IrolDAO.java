package com.system.dao.interfaces;

import java.util.List;

import com.system.entities.Rol;

public interface IrolDAO {
	
	public void add(Rol p);
	
	 public void update(Rol p);
	
	 public void delete(int id);
	
	 public Rol getById(int id);
	 
	 public Rol getByName(String n);
	
	 public List<Rol> getList();
}
