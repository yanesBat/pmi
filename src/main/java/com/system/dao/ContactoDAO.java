package com.system.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.system.entities.Contacto;

@Repository
public class ContactoDAO{
	
		
	@PersistenceContext
	EntityManager em;
			
	@Transactional
		public void add(Contacto p) {
			em.persist(p);
			em.flush();
		}

	@Transactional
		public void update(Contacto p) {
			em.merge(p);
			em.flush();
			
		}

	@Transactional
		public void delete(int id) {
			Contacto r = em.find(Contacto.class, id);
			em.remove(r);
			
		}

	@Transactional
		public Contacto getById(int id) {
		  return em.find(Contacto.class, id);
		}

	
	@Transactional
		 @SuppressWarnings("unchecked")		
		public List<Contacto> getList() {
			return em.createQuery("select r from Contacto r").getResultList();
		}

	
}
