package com.system.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.system.dao.interfaces.IrolDAO;
import com.system.entities.Rol;

@Repository
public class RolDAO implements IrolDAO {
	
		
	@PersistenceContext
	EntityManager em;
			
	@Transactional
		public void add(Rol p) {
			em.persist(p);
			em.flush();
		}

	@Transactional
		public void update(Rol p) {
			em.merge(p);
			em.flush();
			
		}

	@Transactional
		public void delete(int id) {
			Rol r = em.find(Rol.class, id);
			em.remove(r);
			
		}

	@Transactional
		public Rol getById(int id) {
		  return em.find(Rol.class, id);
		}
	@Transactional
	public Rol getByName(String n) {
		
	  return (Rol)em.createQuery("select r from Rol r where r.nombre =:n").setParameter("n", n).getSingleResult();
	}
	
	@Transactional
		 @SuppressWarnings("unchecked")		
		public List<Rol> getList() {
			return em.createQuery("select r from Rol r").getResultList();
		}

	
}
