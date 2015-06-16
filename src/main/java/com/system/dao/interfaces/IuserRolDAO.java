package com.system.dao.interfaces;

import java.util.List;

import com.system.entities.Rol;
import com.system.entities.Usuario;

public interface IuserRolDAO {

	public void add(Usuario u, List<Integer> rls );
	
	 public void update(Usuario u, List<Integer> rls);
	
	 public void delete(Usuario u);
	
	 public List<Usuario> getUsers( Rol r);
	 
	 public List<Rol> getRoles(Usuario u);
}
