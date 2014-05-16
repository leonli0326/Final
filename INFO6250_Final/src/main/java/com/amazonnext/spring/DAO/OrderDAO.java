package com.amazonnext.spring.DAO;

import java.util.List;

import org.hibernate.Query;

import com.amazonnext.spring.pojo.Order;
import com.amazonnext.spring.pojo.OrderItem;

public class OrderDAO extends HibernateDAO<Order, Integer>{

	public OrderDAO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<Order> findByProduct(Integer productId,Integer userId){
		getSession().beginTransaction();
		Query q = getSession().createQuery("select oi.order from OrderItem oi left join oi.stock.product as product where product.id = :productId  and oi.order.user.id = :userId");
		q.setInteger("productId", productId);
		q.setInteger("userId", userId);
		List<Order> lo = q.list();
		getSession().getTransaction().commit(); 
		return lo;
	}
	
	
	
}
