package com.springmvc.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.springmvc.model.City;

@Repository
public class CityDAOImpl implements CityDAO {

	private static final Logger logger = LoggerFactory.getLogger(CityDAOImpl.class);
	
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public void addCity(City c) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(c);
		logger.info("City saved successfully, City Details = " + c);
	}

	@Override
	public void updateCity(City c) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(c);
		logger.info("City updated successfully, City Details = " + c);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<City> listCities() {
		Session session = this.sessionFactory.getCurrentSession();
		List<City> cityList = session.createQuery("from City").list();		
		for(City c: cityList){
			logger.info("City List::" + c);
		}
		return cityList;
	}

	@Override
	public City getCityById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		City c = (City) session.load(City.class, new Integer(id));
		logger.info("City updated successfully, City Details = " + c);
		return c;
	}

	@Override
	public void removeCity(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		City c = (City) session.load(City.class, new Integer(id));
		if (c != null) {
			session.delete(c);
		}
		logger.info("City deleted successfully, City Details = " + c);
	}
}
