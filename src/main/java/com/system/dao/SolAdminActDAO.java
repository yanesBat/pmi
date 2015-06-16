package com.system.dao;

import java.util.ArrayList;
import java.util.List;

import org.primefaces.event.ReorderEvent;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.system.entities.ActivoClon;
import com.system.entities.ActivoClonSolicitAdminAct;
import com.system.entities.SolicitAdminAct;

@Repository
public class SolAdminActDAO {

	@PersistenceContext
	EntityManager em;

	@Transactional
	public void add(SolicitAdminAct p, List<ActivoClon> lc) {
		em.persist(p);
		for (ActivoClon ac : lc) {
			ActivoClonSolicitAdminAct a = new ActivoClonSolicitAdminAct();
			a.setIdActivClon(ac);
			a.setIdSolicitAdminAct(p);
			em.persist(a);
		}

		em.flush();
	}

	@Transactional
	public void update(SolicitAdminAct p, List<ActivoClon> lc) {
		removeAct(p.getIdSolicitAdminAct());
		em.merge(p);
		for (ActivoClon ac : lc) {
			ActivoClonSolicitAdminAct a = new ActivoClonSolicitAdminAct();
			a.setIdActivClon(ac);
			a.setIdSolicitAdminAct(p);
			em.persist(a);
		}
		em.flush();

	}

	@Transactional
	@SuppressWarnings("unchecked")
	private void removeAct(Integer id) {

		List<Integer> ids = (List<Integer>) em.createQuery(
				"select a.idSolicitAdminAct.idSolicitAdminAct from ActivoClonSolicitAdminAct a")
				.getResultList();

		if (ids.contains(id)) {
			List<ActivoClonSolicitAdminAct> acl = (List<ActivoClonSolicitAdminAct>) em
					.createQuery(
							"select a from ActivoClonSolicitAdminAct a where a.idSolicitAdminAct.idSolicitAdminAct =:id")
					.setParameter("id", id).getResultList();
			for (ActivoClonSolicitAdminAct ac : acl) {
				em.remove(ac);
			}
		}

	}

	@Transactional
	@SuppressWarnings("unchecked")
	public List<ActivoClon> getActClon(Integer id) {
		List<ActivoClon> acl = new ArrayList<ActivoClon>();
		List<Integer> ids = (List<Integer>) em.createQuery(
				"select a.idSolicitAdminAct.idSolicitAdminAct from ActivoClonSolicitAdminAct a")
				.getResultList();

		if (ids.contains(id)) {
			 acl = (List<ActivoClon>) em
					.createQuery(
							"select a.idActivClon from ActivoClonSolicitAdminAct a where a.idSolicitAdminAct.idSolicitAdminAct =:id")
					.setParameter("id", id).getResultList();
			
		}
		return acl;
 
	}

	@Transactional
	public void delete(int id) {
		removeAct(id);
		SolicitAdminAct r = em.find(SolicitAdminAct.class, id);
		em.remove(r);

	}

	@Transactional
	public Integer clonCount(int id) {

		return em.find(SolicitAdminAct.class, id)
				.getActivoClonSolicitAdminActList().size();

	}

	@Transactional
	public SolicitAdminAct getById(int id) {
		return em.find(SolicitAdminAct.class, id);
	}

	@Transactional
	@SuppressWarnings("unchecked")
	public List<SolicitAdminAct> getList() {
		return em.createQuery("select r from SolicitAdminAct r")
				.getResultList();
	}

}
