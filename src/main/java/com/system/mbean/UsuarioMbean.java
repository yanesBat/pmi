package com.system.mbean;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.criteria.CriteriaBuilder.In;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.system.dao.interfaces.IuserRolDAO;
import com.system.entities.Rol;
import com.system.entities.Usuario;
import com.system.service.interfaces.IrolService;
import com.system.service.interfaces.IusuarioService;

@Component
@ManagedBean
@Scope("session")
public class UsuarioMbean {
	@Autowired
	IusuarioService usuarioService;

	@Autowired
	IuserRolDAO urDAO;
	
	@Autowired
	IrolService rolService;
	

	private Usuario currentUsuario = new Usuario();
	

	private List<String> rolSelected= new ArrayList<String>();
	

	
	public void setRolService(IrolService rolService) {
		this.rolService = rolService;
	}

	public void setUrDAO(IuserRolDAO urDAO) {
		this.urDAO = urDAO;
	}


	public List<String> getRolSelected() {
		return rolSelected;
	}

	public void setRolSelected(List<String> rolSelected) {
		this.rolSelected = rolSelected;
	}

	public void setusuarioService(IusuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}			
			

	public List<Usuario> usuarios() {
		return usuarioService.list();
	}

	public void updateUsuario(Usuario r) {

	     for (Rol rol : urDAO.getRoles(r)) {
	    	 rolSelected.add(rol.getIdRol().toString());
		}
	     
		this.currentUsuario = r;
				
	}
	public void updateUsuario() {	
		
		
		urDAO.update(getCurrentUsuario(), this.RolsID());
		usuarioService.update(getCurrentUsuario());
		setCurrentUsuario(new Usuario());
	}

	public void removeUsuario(int id) {
		urDAO.delete(usuarioService.getById(id));
		usuarioService.remove(id);
	}
	
	public List<Integer> RolsID() {
		List<Integer> aux = new ArrayList<Integer>(); 
		for (int i = 0; i < rolSelected.size(); i++) {
			aux.add(Integer.parseInt(rolSelected.get(i)));
		}
		return aux;
	}

	public void addUsuario() {
		
		usuarioService.add(this.getCurrentUsuario());
		urDAO.add(getCurrentUsuario(), this.RolsID());
		setCurrentUsuario(new Usuario());
		this.cleanRol();
	}
	
	public void cleanRol(){
		
		this.rolSelected = new ArrayList<String>();
	}
	

	public Usuario getUsuarioByID(int id) {
		return usuarioService.getById(id);

	}

	public Usuario getCurrentUsuario() {
		return currentUsuario;
	}

	public void setCurrentUsuario(Usuario currentUsuario) {
		this.currentUsuario = currentUsuario;
	}

	public List<Rol> roles() {
		return rolService.list();
	}	

	
		

}
