package com.system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.dao.interfaces.IrolDAO;
import com.system.dao.interfaces.IuserDAO;
import com.system.entities.Rol;
import com.system.entities.Usuario;
import com.system.service.interfaces.IusuarioService;

@Service
public class UsuarioService implements IusuarioService {

	@Autowired
	IuserDAO usuarioDAO;
	
	@Autowired
	IrolDAO rolDAO;

	public void add(Usuario p) {
		this.usuarioDAO.add(p);

	}
	
	
	public void update(Usuario p) {
		this.usuarioDAO.update(p);

	}

	public List<Usuario> list() {
		return this.usuarioDAO.getList();
	}

	public Usuario getById(int id) {
		return this.usuarioDAO.getById(id);
	}
	
	public Usuario getByName(String name) {
		return this.usuarioDAO.getByName(name);
	}

	public void remove(int id) {
		this.usuarioDAO.delete(id);

	}

	public void setUsuarioDAO(IuserDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}
	
	public List<Rol> rolList() {
		
		return this.rolDAO.getList();
		
	}

}
