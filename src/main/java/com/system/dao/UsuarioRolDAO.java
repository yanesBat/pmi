package com.system.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.system.dao.interfaces.IuserRolDAO;
import com.system.entities.Rol;
import com.system.entities.Usuario;
import com.system.entities.UsuarioRol;

@Repository
public class UsuarioRolDAO implements IuserRolDAO {
	
	@PersistenceContext
	EntityManager em;

	@Transactional
	public void add(Usuario u, List<Integer> rls) {
		
		for (int i = 0; i < rls.size(); i++) {
			UsuarioRol ur = new UsuarioRol();
			ur.setIdUser(u);
			int id = rls.get(i);
			Rol r = em.find(Rol.class, id ) ;
			ur.setIdRol(r);	
			em.persist(ur);
		}
		
		em.flush();
		
	}

	@Transactional
	public void update(Usuario u, List<Integer> rls) {		
		this.delete(u);
		this.add(u, rls);
	}

	@Transactional
	public void delete(Usuario u) {
		List<UsuarioRol> ur = (List<UsuarioRol>)em.createQuery("select ur from UsuarioRol ur where ur.idUser.idUser =:id").setParameter("id", u.getIdUser()).getResultList();
		for (UsuarioRol usuarioRol : ur) {
			em.remove(usuarioRol);
		}
	}

	@Transactional
	public List<Usuario> getUsers(Rol ro) {
		List<Usuario> r = new ArrayList();
		r = (List<Usuario>)em.createQuery("select u from Usuario u inner join u.usuarioRolList as ur inner join ur.idRol r where r.idRol =:id").setParameter("id", ro.getIdRol()).getResultList();	
		return r;
	}

	@Transactional
	public List<Rol> getRoles(Usuario u) {
		List<Rol> r = new ArrayList();
		r = (List<Rol>)em.createQuery("select r from Rol r inner join r.usuarioRolList as ur inner join ur.idUser u where u.idUser =:id").setParameter("id", u.getIdUser()).getResultList();	
		return r;
	}

	

}
