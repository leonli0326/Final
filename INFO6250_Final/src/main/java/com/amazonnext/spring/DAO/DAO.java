package com.amazonnext.spring.DAO;

import java.io.Serializable;
import java.util.List;

public interface DAO<T,ID extends Serializable> {
	
	T find(ID id, boolean lock);
	
	List<T> findAll();
	
	T saveOrUpdate(T entity);
	
	void delete(T entity);
}
