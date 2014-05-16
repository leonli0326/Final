package com.amazonnext.spring.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.amazonnext.spring.DAO.HibernateDAO;
import com.amazonnext.spring.DAO.RoleDAO;
import com.amazonnext.spring.DAO.UserDAO;
import com.amazonnext.spring.pojo.Role;
import com.amazonnext.spring.pojo.User;

@Service("loginService")
//@Transactional
public class LoginService implements UserDetailsService {

//	@Autowired
	private UserDAO userDAO = new UserDAO();

	@Override
	public UserDetails loadUserByUsername(String arg0)
			throws UsernameNotFoundException {
		User u = userDAO.findUserByUsername(arg0);
		List l = new ArrayList();
		for(Role r:u.getRoles()){
			l.add(new SimpleGrantedAuthority(r.getRole()));
		}		
		return new org.springframework.security.core.userdetails.User(
				u.getUsername(), u.getPassword(), true, true, true, true,
				l);
	}

}
