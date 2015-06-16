package com.system.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.system.entities.ActivoClon;
import com.system.entities.ActivoClonMovimiento;
import com.system.entities.Movimiento;
import com.system.entities.SolicitAdminAct;

@Repository
public class MovDAO {

	@PersistenceContext
	EntityManager em;

	@Transactional
	public List<Movimiento> list() {
		return em.createQuery("select a from Movimiento a").getResultList();
	}
	
	@Transactional
	public Integer clonCount(int id) {

		return em.find(Movimiento.class, id)
				.getActivoClonMovimientoList().size();

	}
	
	@Transactional
	public Movimiento getByID(Integer id) {
		return em.find(Movimiento.class, id);
	}
	
	@Transactional
	public void add(Movimiento m, List<ActivoClon> ac) {
		em.persist(m);
		for (ActivoClon activoClon : ac) {
			ActivoClonMovimiento am  = new ActivoClonMovimiento();
			am.setActivoClon(activoClon);
			am.setMovimiento(m);
			em.persist(am);
		}
		em.flush();
		
	}

}
