package com.system.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.dao.SolAdminActDAO;
import com.system.entities.ActivoClon;
import com.system.entities.SolicitAdminAct;

@Service
public class SolAdminActService {

	@Autowired
	SolAdminActDAO solAdminActDAO;

	public void add(SolicitAdminAct p, List<ActivoClon> lc) {
		
		this.solAdminActDAO.add(p,lc);

	}

	public void update(SolicitAdminAct p, List<ActivoClon> lc) {
		this.solAdminActDAO.update(p,lc);

	}

	public List<SolicitAdminAct> list() {
		return this.solAdminActDAO.getList();
	}

	public SolicitAdminAct getById(int id) {
		return this.solAdminActDAO.getById(id);
	}

	public void remove(int id) {
		this.solAdminActDAO.delete(id);

	}
	public List<ActivoClon> getActClon(Integer id) {
		return solAdminActDAO.getActClon(id);
 
	}

	public Integer clonCount(int id) {

		return solAdminActDAO.clonCount(id);

	}

	public void setSolAdminActDAO(SolAdminActDAO solAdminActDAO) {
		this.solAdminActDAO = solAdminActDAO;
	}

}
