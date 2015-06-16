package com.system.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.system.dao.interfaces.IuserDAO;
import com.system.entities.Rol;
import com.system.entities.Usuario;

@Service
public class UserAuthProvider implements UserDetailsService {

	@Autowired
	IuserDAO userDAO;
	
	
	public UserDetails loadUserByUsername(String arg0)
			throws UsernameNotFoundException, DataAccessException {

	    UserDetails userDetails = null;
	    Usuario userEntity = userDAO.getByName(arg0);
	    if (userEntity == null)
	      throw new UsernameNotFoundException("user not found");
	    
	    String username = userEntity.getUsuario();
	    String password = userEntity.getPass();
	    boolean enabled = true;
	    boolean accountNonExpired = true;
	    boolean credentialsNonExpired = true;
	    boolean accountNonLocked = true;

	    Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
	    for (Rol role : userDAO.getRoles(userEntity)) {
	      authorities.add(new SimpleGrantedAuthority(role.getNombre()));
	    }

	    User user = new User(username, password, enabled,
	      accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
	    return user;
	    
	}

}
