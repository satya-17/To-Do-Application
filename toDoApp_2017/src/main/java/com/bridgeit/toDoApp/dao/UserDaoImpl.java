package com.bridgeit.toDoApp.dao;

import java.io.File;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.SystemPropertyUtils;

import com.bridgeit.toDoApp.model.User;
import com.bridgeit.toDoApp.model.UserPicture;

/**
 * This is a simple DAO Implementation class. All Hibernate related action goes
 * here, operations related to 'Create, Insert, Update and Delete' are being
 * performed in appropriate methods for User Entity, we are getting session
 * object from an autowired sessionfactory
 * 
 * @version 1.8jdk
 * @since 2017-03-23
 * @author bridgeit
 *
 */
@Repository
@Transactional
public class UserDaoImpl implements UserDao {

	@Autowired
	SessionFactory sessionFactory;

	Session session = null;

	public void addEntity(User user) throws Exception {
		session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(user);
	}

	public User getEntityById(int id) throws Exception {

		session = sessionFactory.getCurrentSession();
		Criteria ctr = session.createCriteria(User.class);
		ctr.add(Restrictions.eq("id", id));
		User user = (User) ctr.uniqueResult();
		return user;
	}

	public List<User> getUserList() throws Exception {
		session = sessionFactory.getCurrentSession();
		Criteria ctr = session.createCriteria(User.class);

		@SuppressWarnings("unchecked")
		List<User> list = ctr.list();

		return list;
	}

	public void deleteEntity(int id) throws Exception {
		session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("delete from User where id = :id");
		query.setParameter("id", id);
		int rowCount = query.executeUpdate();
		System.out.println(rowCount + " Data Deleted");
	}

	public User authUser(String email, String password) {
		session = sessionFactory.getCurrentSession();

		Criteria ctr = session.createCriteria(User.class);
		User user = (User) ctr.add(Restrictions.conjunction().add(Restrictions.eq("email", email))
				.add(Restrictions.eq("password", password))).uniqueResult();
		return user;
	}

	public User getEntityByEmailId(String email) 
	{
		session = sessionFactory.getCurrentSession();
		Criteria ctr = session.createCriteria(User.class);
		ctr.add(Restrictions.eq("email", email));
		User user = (User) ctr.uniqueResult();
		return user;
	}

	@Override
	public void savePicture(UserPicture picture) {
			session = sessionFactory.getCurrentSession();
	        session.saveOrUpdate(picture);
	}
	
	public UserPicture getPicture(int userId)
	{	
		session = sessionFactory.getCurrentSession();
		UserPicture up = (UserPicture) session.get(UserPicture.class, userId);
		return up;
	}

}
