package com.system.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.system.dao.interfaces.IuserDAO;
import com.system.entities.Rol;
import com.system.entities.Usuario;
import com.system.entities.UsuarioRol;

@Repository
public class UsuarioDAO implements IuserDAO {
	
	@PersistenceContext
	EntityManager em;
		
	@Transactional
	public void add(Usuario p) {
		em.persist(p);
		em.flush();
	}

	@Transactional
	public void update(Usuario p) {
		em.merge(p);
		em.flush();
		
	}

	@Transactional
	public void delete(int id) {
		em.remove(this.getById(id));		
		
	}

	@Transactional
	public Usuario getById(int id) {
		 return em.find(Usuario.class, id);
	}
	
	public Usuario getByName(String n) {
		 return (Usuario)em.createQuery("select u from Usuario u where u.usuario =:n").setParameter("n", n).getSingleResult();
	}
	
	@Transactional
	 @SuppressWarnings("unchecked")	
	public List<Usuario> getList() {
		 return em.createQuery("select r from Usuario r").getResultList();
	}
	
	@Transactional
	public List<Rol> getRoles(Usuario u) {
		List<Rol> r = new ArrayList();
		r = (List<Rol>)em.createQuery("select r from Rol r inner join r.usuarioRolList as ur inner join ur.idUser u where u.idUser =:id").setParameter("id", u.getIdUser()).getResultList();	
		return r;
	}
	 
		 

}
