package com.system.service;

import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.dao.ContactoDAO;
import com.system.entities.Contacto;

@Service
public class ContactoService{

	@Autowired
	ContactoDAO contactoDAO;

	public void add(Contacto p) {
		this.contactoDAO.add(p);

	}

	public void update(Contacto p) {
		this.contactoDAO.update(p);

	}

	public List<Contacto> list() {
		return this.contactoDAO.getList();
	}

	public Contacto getById(int id) {
		return this.contactoDAO.getById(id);
	}	

	public void remove(int id) {
		this.contactoDAO.delete(id);

	}

	public void setContactoDAO(ContactoDAO contactoDAO) {
		this.contactoDAO = contactoDAO;
	}

}
