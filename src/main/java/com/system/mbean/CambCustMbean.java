package com.system.mbean;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.system.entities.ActivoClon;
import com.system.entities.Contacto;
import com.system.entities.Movimiento;
import com.system.service.ActivoService;
import com.system.service.ContactoService;
import com.system.service.MovService;

@Component
@ManagedBean
@Scope("session")
public class CambCustMbean {

	@Autowired
	MovService movService;

	@Autowired
	ActivoService activoService;

	@Autowired
	ContactoService contactoService;

	private Contacto newContacto = new Contacto();
	
	private Contacto oldContacto = new Contacto();	
	
	
	private String obsrv;
	
	private boolean isOldset = false;

	private List<Contacto> contactos;

	private List<ActivoClon> activos;

	private List<ActivoClon> selectedActivos = new ArrayList<ActivoClon>();

	public void addNewContact(Contacto c) {
		newContacto = c;
	}
	
	public void addOldContact(Contacto c) {
		oldContacto = c;
		newContacto = new Contacto();
		this.isOldset =  true;
	}
	
	public void addActivo(ActivoClon ac) {
		if (ac != null) {

			if (selectedActivos.contains(ac)) {
				selectedActivos.remove(ac);
			} else {

				selectedActivos.add(ac);
			}
		}
		
		
	}


	public void addmov() {
		if (oldContacto != null && newContacto != null &&  selectedActivos != null) {

			for (ActivoClon activoClon : selectedActivos) {
				activoClon.setIdContacto(newContacto);
				activoService.updateClon(activoClon);
			}
			Date fechaActual = new Date();
			Movimiento mov = new Movimiento();
			mov.setFecha(fechaActual);
			mov.setObservaciones(obsrv);
			mov.setDe(oldContacto.getNombre());
			mov.setA(newContacto.getNombre());
			mov.setTipo("Cambio de custodio");
			movService.add(mov,selectedActivos);			
			FacesContext context = FacesContext.getCurrentInstance();	         
	        context.addMessage(null, new FacesMessage("Se ha realizado el cambio de custodio de "+ selectedActivos.size()+" activos" ) );
	        selectedActivos = new ArrayList<ActivoClon>();
			oldContacto = new Contacto();
			newContacto = new Contacto();
			isOldset = false;
			obsrv = "";
			

		}
	}

	

	public List<Contacto> getContactos() {
		return contactoService.list();
	}
	
	public List<Contacto> getNewContactos() {
		List<Contacto> cl = this.getContactos();
		if (oldContacto != null) {
			cl.remove(oldContacto);
		}
		return cl;
	}

	public void setContactos(List<Contacto> contactos) {
		this.contactos = contactos;
	}
	
	

	public Contacto getNewContacto() {
		return newContacto;
	}

	public void setNewContacto(Contacto newContacto) {
		this.newContacto = newContacto;
	}

	public Contacto getOldContacto() {
		return oldContacto;
	}

	public void setOldContacto(Contacto oldContacto) {
		this.oldContacto = oldContacto;
	}

	public List<ActivoClon> getActivos() {
		
		activos =activoService.getAllClon();
		
		for (ActivoClon activoClon : activoService.getAllClon()) {			
				
				if (activoClon.getIdContacto() == null || activoClon.getIdContacto().getIdContacto() != oldContacto.getIdContacto()) {
					activos.remove(activoClon);
				}			
			
		}
		
		return activos;
	}

	public void setActivos(List<ActivoClon> activos) {
		this.activos = activos;
	}

	public List<ActivoClon> getSelectedActivos() {
		return selectedActivos;
	}

	public void setSelectedActivos(List<ActivoClon> selectedActivos) {
		this.selectedActivos = selectedActivos;
	}

	public void setContactoService(ContactoService contactoService) {
		this.contactoService = contactoService;
	}

	public void setMovService(MovService movService) {
		this.movService = movService;
	}

	public void setActivoService(ActivoService activoService) {
		this.activoService = activoService;
	}

	public String getObsrv() {
		return obsrv;
	}

	public void setObsrv(String obsrv) {
		this.obsrv = obsrv;
	}

	public boolean isOldset() {
		return isOldset;
	}

	public void setOldset(boolean isOldset) {
		this.isOldset = isOldset;
	}
	
	

}
