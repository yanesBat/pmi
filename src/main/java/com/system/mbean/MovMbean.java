package com.system.mbean;

import java.util.List;

import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.system.entities.Movimiento;
import com.system.service.MovService;

@Component
@ManagedBean
@Scope("session")
public class MovMbean {

	@Autowired
	MovService movService;

	public List<Movimiento> allMov() {
		return movService.list();
	}

	public void setMovService(MovService movService) {
		this.movService = movService;
	}

	public Integer clonCount(int id) {
		return movService.clonCount(id);

	}

}
