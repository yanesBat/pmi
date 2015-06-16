package com.system.mbean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.system.entities.Rol;
import com.system.service.interfaces.IrolService;

@Component
@ManagedBean
@Scope("session")
public class RolMbean {	

		@Autowired
		IrolService rolService;
		
		private Rol currentRol = new Rol();

		public void setrolService(IrolService rolService) {
			this.rolService = rolService;
		}
		
		public List<Rol> roles() {				
			return rolService.list();			
		}
		
		public void updateRol(Rol r) {
			this.currentRol = r;
		}
		
		public void removeRol(int id)
		{
			rolService.remove(id);
		}
		
		public void addRol() {
			rolService.add(getCurrentRol());
			setCurrentRol(new Rol()); 
		}
		
		
		public void updateRol() {
			rolService.update(currentRol);
			setCurrentRol(new Rol());
		}
		
		public Rol getRolByID(int id) {
			return	rolService.getById(id);
			
		}

		public Rol getCurrentRol() {
			return currentRol;
		}

		public void setCurrentRol(Rol currentRol) {
			this.currentRol = currentRol;
		}

			
		
}
