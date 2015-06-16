package com.system.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.system.entities.ActivoClon;
import com.system.entities.ActivoModelo;
import com.system.entities.InfraestructuraClon;
import com.system.entities.InfraestructuraModelo;

@Repository
public class InfraestructuraModelDAO {

	@PersistenceContext
	EntityManager em;
	
	@Transactional
	public void add(InfraestructuraModelo u , Integer padre_id){
		u.setIdInfraModel(null);
		if (padre_id > 0) {
			
			InfraestructuraModelo padre = this.getById(padre_id);
			u.setPadre(padre);
		}
		
		em.persist(u);
		em.flush();
	}
	
	@Transactional
	public void update(InfraestructuraModelo u){
		
		em.merge(u);
	}
	
	@Transactional
	public void updateClon(InfraestructuraClon u) {
		em.merge(u);
	}
	
	@Transactional
	public void remove(InfraestructuraModelo a){
		
		if (this.gethijos(a.getIdInfraModel()).size() > 0) {
			for (InfraestructuraModelo act : this.gethijos(a.getIdInfraModel())) {
				this.remove(act);
			}
		}
		em.remove(em.merge(a));
	}
	
	@Transactional
	public void addClon(InfraestructuraClon ac) {

		em.persist(ac);
		em.flush();
	}
	
	@Transactional
	public List<InfraestructuraClon> getClonList(InfraestructuraModelo am) {
		List<InfraestructuraClon> l = (List<InfraestructuraClon>) em
				.createQuery(
						"select c from InfraestructuraClon c where c.idInfraModel.idInfraModel =:id")
				.setParameter("id", am.getIdInfraModel()).getResultList();
		return l;
	}
	
	@Transactional
	public List<InfraestructuraClon> getAllClon() {
		List<InfraestructuraClon> l = (List<InfraestructuraClon>) em.createQuery(
				"select c from InfraestructuraClon c").getResultList();
		return l;
	}
	
	@Transactional
	public InfraestructuraModelo getById(Integer id ){
		
		return em.find(InfraestructuraModelo.class, id);
	}
	
	@Transactional
	public InfraestructuraClon getClonById(Integer id) {

		return em.find(InfraestructuraClon.class, id);
	}
	
	@Transactional
	public List<InfraestructuraModelo> gethijos(Integer padre_id) {

		List<InfraestructuraModelo> hijos = new ArrayList<InfraestructuraModelo>();
		if (padre_id > 0) {

			hijos = (List<InfraestructuraModelo>) em
					.createQuery(
							"select u from InfraestructuraModelo u where u.padre.idInfraModel =:id")
					.setParameter("id", padre_id).getResultList();

		}

		return hijos;

	}

	@Transactional
	public List<InfraestructuraModelo> getRoots() {

		List<InfraestructuraModelo> t = new ArrayList<InfraestructuraModelo>();

		List<InfraestructuraModelo> hijos = (List<InfraestructuraModelo>) em.createQuery(
				"select u from InfraestructuraModelo u ").getResultList();

		for (InfraestructuraModelo infraModelo : hijos) {
			if (infraModelo.getPadre() != null) {
				t.add(infraModelo);
			}
		}
		for (InfraestructuraModelo infraModelo : t) {
			hijos.remove(infraModelo);
		}

		return hijos;

	}

}
