package com.system.service;

import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.dao.AreaDAO;
import com.system.dao.interfaces.IrolDAO;
import com.system.entities.Area;


@Service
@RequestScoped
public class AreaService {
	
	@Autowired
	AreaDAO areaDAO;
	
	public List<Area> list() {
		
		return areaDAO.list();
		
	}
	
	public Area getByID(Integer id) {
		return areaDAO.getByID(id);
	}

}
