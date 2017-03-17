package com.springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springmvc.model.City;
import com.springmvc.service.CityService;

/**
 * Clase controladora para la pagina que administra las ciudades.
 * @author Mauricio Ospina.
 */
@Controller
public class CityController {

	/** Servicio para las ciudades. */
	private CityService cityService;

	/**
	 * Metodo para establecer cityService.
	 * @param cityService.
	 */
	@Autowired(required=true)
	@Qualifier(value="cityService")
	public void setCityService(CityService cityService) {
		this.cityService = cityService;
	}
	
	/**
	 * Metodo llamado cuando se accede por medio de "/cities" para listar las ciudades.
	 * @param model Modelo.
	 * @return String.
	 */
	@RequestMapping(value = "/cities", method = RequestMethod.GET)
	public String listCities(Model model) {
		model.addAttribute("city", new City());
		model.addAttribute("listCities", this.cityService.listCities());
		return "city";
	}
		
	/**
	 * Metodo llamado cuando se accede por medio de "/city/add" para adicionar una ciudad.
	 * @param city City.
	 * @return
	 */
	@RequestMapping(value = "/city/add", method = RequestMethod.POST)
	public String addCity(@ModelAttribute("city") City city) {
		if (city.getId() == 0) {
			this.cityService.addCity(city);			
		} else {
			this.cityService.updateCity(city);
		}
		return "redirect:/cities";
	}
	
	/**
	 * Metodo llamado cuando se accede por medio de "/removeCity/{id}" para eliminar una ciudad.
	 * @param id Identificador unico de la ciudad.
	 * @return String.
	 */
	@RequestMapping(value = "/removeCity/{id}") 
	public String removeCity(@PathVariable("id") int id) {
		this.cityService.removeCity(id);
		return"redirect:/cities";
	}
	
	/**
	 * Metodo llamado cuando se accede por medio de "/editCity/{id}" para modificar una ciudad.
	 * @param id Identificar unico de la ciudad.
	 * @param model Modelo.
	 * @return String.
	 */
	@RequestMapping("/editCity/{id}")
    public String editCity(@PathVariable("id") int id, Model model){
        model.addAttribute("city", this.cityService.getCityById(id));
        model.addAttribute("listCities", this.cityService.listCities());
        return "city";
    }

}
