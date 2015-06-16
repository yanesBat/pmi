package com.system.service;

import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.dao.interfaces.IrolDAO;
import com.system.entities.Rol;
import com.system.service.interfaces.IrolService;

@Service
@ApplicationScoped
public class RolService implements IrolService {

	@Autowired
	IrolDAO rolDAO;

	public void add(Rol p) {
		this.rolDAO.add(p);

	}

	public void update(Rol p) {
		this.rolDAO.update(p);

	}

	public List<Rol> list() {
		return this.rolDAO.getList();
	}

	public Rol getById(int id) {
		return this.rolDAO.getById(id);
	}
	
	public Rol getByName(String n) {
		return this.rolDAO.getByName(n);
	}

	public void remove(int id) {
		this.rolDAO.delete(id);

	}

	public void setRolDAO(IrolDAO rolDAO) {
		this.rolDAO = rolDAO;
	}

}
