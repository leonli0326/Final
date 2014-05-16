package com.amazonnext.spring.DAO;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Criterion;

public class HibernateDAO<T, ID extends Serializable> implements DAO<T, ID> {

	protected Class<T> persistentClass;
	// @Autowired
	protected final SessionFactory sessionFactory = new Configuration()
			.configure().buildSessionFactory();

	public Class<T> getPersistentClass() {
		return persistentClass;
	}

	@SuppressWarnings("unchecked")
	public HibernateDAO() {
		super();
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
//		System.out.println("sessionFactory" + sessionFactory);
	}

	public Session getSession() {
		try {
			return sessionFactory.getCurrentSession();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object find(Serializable id, boolean lock) {
		T entity;
		getSession().beginTransaction();
		if (lock) {
			entity = (T) getSession().get(persistentClass, id,
					LockOptions.UPGRADE);
		} else {
			entity = (T) getSession().get(persistentClass, id);
		}
		getSession().getTransaction().commit();
		return entity;
	}

	public void flush() {
		getSession().flush();
	}


	@Override
	public List<T> findAll() {
		getSession().beginTransaction();
		Query q = getSession().createQuery("from "+getPersistentClass().getName());
		List<T> l = q.list();
		getSession().getTransaction().commit();
		return l;
	}

	@Override
	public T saveOrUpdate(T entity) {
		try {
			getSession().beginTransaction();
			getSession().saveOrUpdate(entity);
			getSession().getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			getSession().getTransaction().rollback();
		}
		return entity;
	}

	@Override
	public void delete(T entity) {
		getSession().beginTransaction();
		getSession().delete(entity);
		getSession().getTransaction().commit();
	}

	@SuppressWarnings("unchecked")
	protected List<T> findByCriteria(Criterion... criterions) {
		getSession().beginTransaction();
		Criteria ca = getSession().createCriteria(persistentClass);
		for (Criterion cn : criterions) {
			ca.add(cn);
		}
		getSession().getTransaction().commit();
		return ca.list();
	}

}
