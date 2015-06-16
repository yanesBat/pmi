package com.system.service.interfaces;

import java.util.List;

import com.system.entities.Rol;
import com.system.entities.Usuario;

public interface IusuarioService {

	public void add(Usuario p);
    public void update(Usuario p);
    public List<Usuario> list();
    public Usuario getById(int id);
    public void remove(int id);
    public Usuario getByName(String name);
}
