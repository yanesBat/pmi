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
import com.system.entities.Ubicacion;
import com.system.service.ActivoService;
import com.system.service.ContactoService;
import com.system.service.MovService;

@Component
@ManagedBean
@Scope("session")
public class CambUbicMbean {

	@Autowired
	MovService movService;

	@Autowired
	ActivoService activoService;

	@Autowired
	UbicacionMbean ubic;

	private String obsrv;

	private boolean isOldset = false;

	private List<ActivoClon> activos;

	private List<ActivoClon> selectedActivos = new ArrayList<ActivoClon>();

	private Ubicacion oldUbic = new Ubicacion();

	private Ubicacion newUbic = new Ubicacion();

	public void addActivo(ActivoClon ac) {
		if (ac != null) {

			if (selectedActivos.contains(ac)) {
				selectedActivos.remove(ac);
			} else {

				selectedActivos.add(ac);
			}
		}

	}

	public void addOldUbic() {
		oldUbic = ubic.getSelectedUbicacion();
		isOldset = true;
		newUbic = new Ubicacion();
		ubic.setSelectedNode(ubic.getRoot());

	}

	public void addNewUbic() {
		newUbic = ubic.getSelectedUbicacion();
		ubic.setSelectedNode(ubic.getRoot());
	}

	public void addmov() {
		if (selectedActivos != null && newUbic != null && oldUbic != null) {

			for (ActivoClon activoClon : selectedActivos) {
				activoClon.setIdUbic(newUbic);
				activoService.updateClon(activoClon);
			}
			Date fechaActual = new Date();
			Movimiento mov = new Movimiento();
			mov.setFecha(fechaActual);
			mov.setObservaciones(obsrv);
			mov.setDe(oldUbic.getNombre());
			mov.setA(newUbic.getNombre());
			mov.setTipo("Cambio de ubicacion");
			movService.add(mov, selectedActivos);
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(
					"Se ha realizado el cambio de ubicación de "
							+ selectedActivos.size() + " activos"));
			selectedActivos = new ArrayList<ActivoClon>();
			oldUbic = new Ubicacion();
			newUbic = new Ubicacion();
			isOldset = false;
			obsrv = "";

		}
	}

	public List<ActivoClon> getActivos() {

		activos = activoService.getAllClon();

		for (ActivoClon activoClon : activoService.getAllClon()) {

			if (oldUbic != null
					&& activoClon.getIdUbic().getIdUbic() != oldUbic
							.getIdUbic()) {
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

	public void setUbic(UbicacionMbean ubic) {
		this.ubic = ubic;
	}

	public Ubicacion getOldUbic() {
		return oldUbic;
	}

	public void setOldUbic(Ubicacion oldUbic) {
		this.oldUbic = oldUbic;
	}

	public Ubicacion getNewUbic() {
		return newUbic;
	}

	public void setNewUbic(Ubicacion newUbic) {
		this.newUbic = newUbic;
	}

}
