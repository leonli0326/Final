package com.amazonnext.spring.DAO;

import org.hibernate.Query;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.amazonnext.spring.pojo.User;

@Repository
public class UserDAO extends HibernateDAO<User, Integer> {

	public UserDAO() {
		super();
	}

	public User findUserByUsername(String username) {
		getSession().beginTransaction();
		Query query = getSession().createQuery(
				"FROM User WHERE username = :username");
		query.setString("username", username);
		User u = (User) query.uniqueResult();
		getSession().getTransaction().commit();
		return u;
	}

	public User findUserById(Integer id) {
		getSession().beginTransaction();
		User u = (User) getSession().get(User.class, id);
		getSession().getTransaction().commit();
		return u;
	}

	public boolean newUser(String username, String password, String firstName,
			String lastName, String phone, String email) {
		User u = new User(username, sha256Convert(password, username),
				firstName, lastName, phone, email);
		return saveUserOnly(u);
	}

	public void resetPassword(User u, String password) {
		u.setPassword(sha256Convert(password, u.getUsername()));
		saveOrUpdate(u);
	}

	public boolean saveUserOnly(User u) {
		try {
			getSession().beginTransaction();
			getSession().save(u);
			getSession().getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			getSession().getTransaction().rollback();
			return false;
		}
	}


	public static String sha256Convert(String password, String username) {
		return new ShaPasswordEncoder(256).encodePassword(password, username);
	}
}
