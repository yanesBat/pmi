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
public class AsigCustMbean {

	@Autowired
	MovService movService;

	@Autowired
	ActivoService activoService;

	@Autowired
	ContactoService contactoService;

	private Contacto selectedContacto = new Contacto();
	
	private String obsrv;

	private List<Contacto> contactos;

	private List<ActivoClon> activos;

	private List<ActivoClon> selectedActivos = new ArrayList<ActivoClon>();

	public void addContact(Contacto c) {
		selectedContacto = c;
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
		if (selectedContacto != null && selectedActivos != null) {

			for (ActivoClon activoClon : selectedActivos) {
				activoClon.setIdContacto(selectedContacto);
				activoService.updateClon(activoClon);
			}
			Date fechaActual = new Date();
			Movimiento mov = new Movimiento();
			mov.setFecha(fechaActual);
			mov.setObservaciones(obsrv);
			mov.setDe("");
			mov.setA(selectedContacto.getNombre());
			mov.setTipo("Asignación de custodio");
			movService.add(mov,selectedActivos);
			FacesContext context = FacesContext.getCurrentInstance();	         
	        context.addMessage(null, new FacesMessage("Se le ah asignado a "+ selectedContacto.getNombre() +" la custodia de "+selectedActivos.size() +" activos" ) );
			selectedActivos = new ArrayList<ActivoClon>();
			selectedContacto = new Contacto();
			obsrv = "";
			

		}
	}

	public Contacto getSelectedContacto() {
		return selectedContacto;
	}

	public void setSelectedContacto(Contacto selectedContacto) {
		this.selectedContacto = selectedContacto;
	}

	public List<Contacto> getContactos() {
		return contactoService.list();
	}

	public void setContactos(List<Contacto> contactos) {
		this.contactos = contactos;
	}

	public List<ActivoClon> getActivos() {
		
		activos =activoService.getAllClon();
		
		for (ActivoClon activoClon : activoService.getAllClon()) {
			if (activoClon.getIdContacto() != null) {
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

}
