package com.amazonnext.spring.DAO;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.amazonnext.spring.pojo.Role;
import com.amazonnext.spring.pojo.User;

@Repository
public class RoleDAO extends HibernateDAO<Role, Integer>{
	
	
	
	public RoleDAO() {
		super();
	}

//	public List<Role> getAllRoles(){
//		return findAll();
//	}
	
	public List<Role> getAllRoles(){
		getSession().beginTransaction();
		List<Role> l = getSession().createCriteria(Role.class).list();
		getSession().getTransaction().commit();
//		List<Role> l =findAll();
		return l;
	}
}
