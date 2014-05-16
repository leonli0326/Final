package com.amazonnext.spring.DAO;

import org.hibernate.Query;

import com.amazonnext.spring.pojo.Stock;

public class StockDAO extends HibernateDAO<Stock, Integer>{

	public StockDAO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public void updateStock(Integer id, Integer stockCount,Double price){
		getSession().beginTransaction();
		Query q = getSession().createQuery("update Stock set stockCount = :stockCount, price = :price where id = :id");
		q.setInteger("stockCount", stockCount);
		q.setDouble("price", price);
		q.setInteger("id", id);
		q.executeUpdate();
		getSession().getTransaction().commit();
	}

}
