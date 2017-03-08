package com.springmvc.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springmvc.dao.CityDAO;
import com.springmvc.model.City;

@Service
public class CityServiceImpl implements CityService {

	private CityDAO cityDAO;
	
	public void setCityDAO(CityDAO cityDAO) {
		this.cityDAO = cityDAO;
	}

	@Override
	@Transactional
	public void addCity(City c) {
		cityDAO.addCity(c);		
	}

	@Override
	@Transactional
	public void updateCity(City c) {
		cityDAO.updateCity(c);			
	}

	@Override
	@Transactional
	public List<City> listCities() {
		return cityDAO.listCities();
	}

	@Override
	@Transactional
	public City getCityById(int id) {
		return cityDAO.getCityById(id);
	}

	@Override
	@Transactional
	public void removeCity(int id) {
		cityDAO.removeCity(id);
	}

}
