package com.system.service.interfaces;

import java.util.List;

import com.system.entities.Rol;

public interface IrolService {
	
	public void add(Rol p);
    public void update(Rol p);
    public List<Rol> list();
    public Rol getById(int id);
    public Rol getByName(String n);
    public void remove(int id);

}
