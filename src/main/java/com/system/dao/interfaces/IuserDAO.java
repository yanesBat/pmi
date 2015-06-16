package com.system.dao.interfaces;

import java.util.List;

import org.hibernate.Session;

import com.system.entities.Rol;
import com.system.entities.Usuario;

public interface IuserDAO {
	
	public void add(Usuario p);
	
	 public void update(Usuario p);
	
	 public void delete(int id);
	
	 public Usuario getById(int id);
	 
	 public Usuario getByName(String n);
	
	 public List<Usuario> getList();
	 
	 public List<Rol> getRoles(Usuario u);
	 
}
