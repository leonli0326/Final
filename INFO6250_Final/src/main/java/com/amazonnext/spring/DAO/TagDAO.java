package com.amazonnext.spring.DAO;

import java.util.List;

import org.hibernate.Query;

import com.amazonnext.spring.pojo.Tag;

public class TagDAO extends HibernateDAO<Tag, Integer>{

	public TagDAO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Tag findTagByName(String tagContent){
		getSession().beginTransaction();
		Query q = getSession().createQuery("from Tag where tagContent = :tagContent");
		q.setString("tagContent", tagContent);
		Tag t = (Tag) q.uniqueResult();
		if(t!=null){
			t.setRanking(t.getRanking()+1);
			getSession().saveOrUpdate(t);
		}
		getSession().getTransaction().commit();
		return t;
	}
	
	public List<Tag> findPopularTag(Integer limit){
		getSession().beginTransaction();
		Query q = getSession().createQuery("from Tag tag order by tag.ranking desc");
		q.setMaxResults(limit);
		List<Tag> lt = q.list();
		getSession().getTransaction().commit();
		return lt;
	}

}
