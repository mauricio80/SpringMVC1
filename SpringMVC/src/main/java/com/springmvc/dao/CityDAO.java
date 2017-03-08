package com.springmvc.dao;

import java.util.List;

import com.springmvc.model.City;

public interface CityDAO {

	public void addCity(City c);
	public void updateCity(City c);
	public List<City> listCities();
	public City getCityById(int id);
	public void removeCity(int id);
}
