package com.system.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.system.entities.ActivoClon;
import com.system.entities.ActivoModelo;

@Repository
public class ActivoDAO {

	@PersistenceContext
	EntityManager em;

	@Transactional
	public void add(ActivoModelo u, Integer padre_id) {
		u.setIdActModel(null);
		if (padre_id > 0) {

			ActivoModelo padre = this.getById(padre_id);
			u.setPadre(padre);
		} else {
			u.setPadre(null);
		}

		em.persist(u);
		em.flush();
	}

	@Transactional
	public void addClon(ActivoClon ac) {

		em.persist(ac);
		em.flush();
	}

	@Transactional
	public void update(ActivoModelo u) {

		em.merge(u);
	}
	
	@Transactional
	public void updateClon(ActivoClon u) {
		em.merge(u);
	}

	@Transactional
	public void remove(ActivoModelo a) {

		if (this.gethijos(a.getIdActModel()).size() > 0) {
			for (ActivoModelo act : this.gethijos(a.getIdActModel())) {
				this.remove(act);
			}
		}
		em.remove(em.merge(a));
	}

	public List<ActivoClon> getClonList(ActivoModelo am) {
		List<ActivoClon> l = (List<ActivoClon>) em
				.createQuery(
						"select c from ActivoClon c where c.idActModel.idActModel =:id")
				.setParameter("id", am.getIdActModel()).getResultList();
		return l;
	}

	public List<ActivoClon> getAllClon() {
		List<ActivoClon> l = (List<ActivoClon>) em.createQuery(
				"select c from ActivoClon c").getResultList();
		return l;
	}

	public ActivoModelo getById(Integer id) {

		return em.find(ActivoModelo.class, id);
	}
	
	public ActivoClon getClonById(Integer id) {

		return em.find(ActivoClon.class, id);
	}

	public List<ActivoModelo> gethijos(Integer padre_id) {

		List<ActivoModelo> hijos = new ArrayList<ActivoModelo>();
		if (padre_id > 0) {

			hijos = (List<ActivoModelo>) em
					.createQuery(
							"select u from ActivoModelo u where u.padre.idActModel =:id")
					.setParameter("id", padre_id).getResultList();

		}

		return hijos;

	}

	public List<ActivoModelo> getRoots() {

		List<ActivoModelo> t = new ArrayList<ActivoModelo>();

		List<ActivoModelo> hijos = (List<ActivoModelo>) em.createQuery(
				"select u from ActivoModelo u ").getResultList();

		for (ActivoModelo activoModelo : hijos) {
			if (activoModelo.getPadre() != null) {
				t.add(activoModelo);
			}
		}
		for (ActivoModelo activoModelo : t) {
			hijos.remove(activoModelo);
		}

		return hijos;

	}

}
