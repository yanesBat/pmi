package com.system.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;




import org.springframework.transaction.annotation.Transactional;

import com.system.entities.Ubicacion;

@Repository
public class UbicacionDAO {

	@PersistenceContext
	EntityManager em;
			
	@Transactional
	public void add(Ubicacion u , Integer padre_id){
		u.setIdUbic(null);
		if (padre_id != null && padre_id > 0) {
			Ubicacion padre = this.getById(padre_id);
			u.setPadre(padre);
		}else {
			u.setPadre(null);
		}
		
		em.persist(u);
		em.flush();
	}
	
	@Transactional
	public void update(Ubicacion u){
		
		em.merge(u);
	}
	
	@Transactional
	public void remove(Ubicacion u){
		
		if (this.gethijos(u.getIdUbic()).size() > 0) {
			for (Ubicacion ubic : this.gethijos(u.getIdUbic())) {
				this.remove(ubic);
			}
		}
		em.remove(em.merge(u));
	}
	
	public List<Ubicacion> gethijos(Integer padre_id){
		
		List<Ubicacion> hijos = new ArrayList<Ubicacion>();
		if (padre_id > 0) {

			hijos = (List<Ubicacion>)em.createQuery("select u from Ubicacion u where u.padre.idUbic =:id").setParameter("id", padre_id).getResultList();
		
		}
		
		return hijos;
		
	}
	
public List<Ubicacion> getRoots(){
		
		List<Ubicacion> t = new ArrayList<Ubicacion>();
		

		List<Ubicacion>	hijos = (List<Ubicacion>)em.createQuery("select u from Ubicacion u ").getResultList();
		
			for (Ubicacion ubicacion : hijos) {
				if (ubicacion.getPadre() != null) {
					t.add(ubicacion);
				}
			}
			for (Ubicacion ubicacion : t) {
				hijos.remove(ubicacion);
			}
		
		return hijos;
		
	}
	
	public Ubicacion getById(Integer id ){
		
		return em.find(Ubicacion.class, id);
	}
	
}
