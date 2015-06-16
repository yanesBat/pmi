package com.system.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.system.entities.Area;

@Repository
public class AreaDAO {

	@PersistenceContext
	EntityManager em;

	@Transactional
	public List<Area> list() {
		return em.createQuery("select a from Area a").getResultList();
	}
	
	public Area getByID(Integer id) {
		return em.find(Area.class, id);
	}

}
