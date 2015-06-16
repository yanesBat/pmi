package com.system.service;

import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.dao.AreaDAO;
import com.system.dao.MovDAO;
import com.system.dao.interfaces.IrolDAO;
import com.system.entities.ActivoClon;
import com.system.entities.Area;
import com.system.entities.Movimiento;

@Service
@RequestScoped
public class MovService {

	@Autowired
	MovDAO movDAO;

	public List<Movimiento> list() {

		return movDAO.list();

	}

	public Integer clonCount(int id) {
		return movDAO.clonCount(id);

	}

	public Movimiento getByID(Integer id) {
		return movDAO.getByID(id);
	}

	public void add(Movimiento m, List<ActivoClon> ac) {
		movDAO.add(m, ac);
	}

}
