package com.system.mbean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.system.entities.Contacto;
import com.system.entities.Usuario;
import com.system.service.ContactoService;
import com.system.service.interfaces.IusuarioService;

@Component
@ManagedBean
@Scope("session")
public class ContactoMbean {

	@Autowired
	ContactoService contactoService;

	@Autowired
	IusuarioService usuarioService;

	private Contacto currentContacto = new Contacto();

	private Integer selectedUser;

	private List<Usuario> usuarios = new ArrayList<Usuario>();

	public void setUsuarioService(IusuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	public void setcontactoService(ContactoService contactoService) {
		this.contactoService = contactoService;
	}

	public List<Contacto> contactos() {
		return contactoService.list();
	}

	public void updateContacto(Contacto r) {
		this.currentContacto = r;
		this.selectedUser = r.getIdUsuario().getIdUser();
	}

	public void removeContacto(int id) {
		contactoService.remove(id);
	}

	public void addContacto() {
		if (selectedUser != null) {
			currentContacto.setIdUsuario(usuarioService.getById(selectedUser));
		}
		contactoService.add(getCurrentContacto());
		currentContacto = new Contacto();  
		selectedUser = null;
	}

	public void updateContacto() {
		if (selectedUser != null) {
			currentContacto.setIdUsuario(usuarioService.getById(selectedUser));
		}
		contactoService.update(currentContacto);
		currentContacto = new Contacto(); 
		selectedUser = null;
	}

	public Contacto getContactoByID(int id) {
		return contactoService.getById(id);

	}

	public Contacto getCurrentContacto() {
		return currentContacto;
	}

	public void setCurrentContacto(Contacto currentContacto) {
		this.currentContacto = currentContacto;
	}

	public Integer getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(Integer selectedUser) {
		this.selectedUser = selectedUser;
	}

	public List<Usuario> getUsuarios() {
		return usuarioService.list();
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

}
