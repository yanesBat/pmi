package com.system.mbean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import com.system.entities.ActivoClon;
import com.system.entities.ActivoClonSolicitAdminAct;
import com.system.entities.SolicitAdminAct;
import com.system.service.ActivoService;
import com.system.service.SolAdminActService;
import com.system.service.interfaces.IusuarioService;

@Component
@ManagedBean
@Scope("session")
public class SolAdminActMbean {

	@Autowired
	SolAdminActService solAdminActService;

	@Autowired
	IusuarioService usuarioService;

	@Autowired
	ActivoService activoService;

	private SolicitAdminAct currentSolicitud = new SolicitAdminAct();

	private List<String> asuntos;
	
	private boolean useless;

	private List<ActivoClon> activos;

	private String selectedAsunto;

	private List<ActivoClon> selectedActivos;
	
	
	
	@PostConstruct
	public void init(){
		asuntos = new ArrayList<String>();
		asuntos.add("Asignación de custodio");
		asuntos.add("Cambio de custodio");
		asuntos.add("Cambio de ubicación");
		asuntos.add("Cambio de estado físico");
		 SolicitAdminAct currentSolicitud = new SolicitAdminAct();

		 List<String> asuntos = new ArrayList<String>();
		
		 boolean useless = false;

		 List<ActivoClon> activos = new ArrayList<ActivoClon>();

		 selectedAsunto = "";

		 selectedActivos = new ArrayList<ActivoClon>();
		
	}
	

	public void setsolAdminActService(SolAdminActService solAdminActService) {
		this.solAdminActService = solAdminActService;
	}

	public List<SolicitAdminAct> solicitudes() {
		return solAdminActService.list();
	}

	public void updateSolicitAdminAct(SolicitAdminAct r) {
		this.currentSolicitud = r;
		this.selectedAsunto = r.getAsunto();
		selectedActivos = solAdminActService.getActClon(r.getIdSolicitAdminAct());
	}

	public void removeSolicitAdminAct(Integer id) {
		solAdminActService.remove(id);
	}
	
	public void addSelectedClon(Integer id){
		if (id != null) {
			ActivoClon x =activoService.getClonById(id);
			if (containsid(id)) {
				selectedActivos.remove(x);
			}else {

				selectedActivos.add(x);
			}
		}
	} 
	
	private boolean containsid(Integer id) {
		for (ActivoClon activoClon : selectedActivos) {
			if (activoClon.getIdActivClon() - id ==0) {
				return true;
			}
		}
		return false;
	}


	public Integer clonCount(Integer id) {
		return solAdminActService.clonCount(id);
	}

	public void addSolicitAdminAct() {
		User user = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		currentSolicitud
				.setUsuario(usuarioService.getByName(user.getUsername()));
		currentSolicitud.setAsunto(selectedAsunto);
		currentSolicitud.setAtendida(false);
		solAdminActService.add(this.getCurrentSolicitud(),selectedActivos);
		cleanCurrent();
	}
	
	public void cleanCurrent() {
		currentSolicitud = new SolicitAdminAct();
		selectedAsunto = null;
		selectedActivos = new ArrayList<ActivoClon>();
	}

	public void updateSolicitAdminAct() {

		currentSolicitud.setAsunto(selectedAsunto);
		solAdminActService.update(currentSolicitud,selectedActivos);
		cleanCurrent();
	}

	public SolicitAdminAct getSolicitAdminActByID(int id) {
		return solAdminActService.getById(id);

	}

	public SolicitAdminAct getCurrentSolicitud() {
		return currentSolicitud;
	}

	public void setCurrentSolicitud(SolicitAdminAct currentSolicitud) {
		this.currentSolicitud = currentSolicitud;
	}

	public List<String> getAsuntos() {
		return asuntos;
	}

	public void setAsuntos(List<String> asuntos) {
		this.asuntos = asuntos;
	}

	public List<ActivoClon> getActivos() {

		return activoService.getAllClon();
	}

	public void setActivos(List<ActivoClon> activos) {
		this.activos = activos;
	}

	public String getSelectedAsunto() {
		return selectedAsunto;
	}

	public void setSelectedAsunto(String selectedAsunto) {
		this.selectedAsunto = selectedAsunto;
	}

	public List<ActivoClon> getSelectedActivos() {
		return selectedActivos;
	}

	public void setSelectedActivos(List<ActivoClon> selectedActivos) {
		this.selectedActivos = selectedActivos;
	}

	public void setSolAdminActService(SolAdminActService solAdminActService) {
		this.solAdminActService = solAdminActService;
	}

	public void setActivoService(ActivoService activoService) {
		this.activoService = activoService;
	}

	public void setUsuarioService(IusuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}


	public boolean isUseless() {
		return useless;
	}


	public void setUseless(boolean useless) {
		this.useless = useless;
	}



}
