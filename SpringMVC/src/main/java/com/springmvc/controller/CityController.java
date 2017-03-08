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

@Controller
public class CityController {

	private CityService cityService;

	@Autowired(required=true)
	@Qualifier(value="cityService")
	public void setCityService(CityService cityService) {
		this.cityService = cityService;
	}
	
	@RequestMapping(value = "/cities", method = RequestMethod.GET)
	public String listCities(Model model) {
		model.addAttribute("city", new City());
		model.addAttribute("listCities", this.cityService.listCities());
		return "city";
	}
		
	@RequestMapping(value = "/city/add", method = RequestMethod.POST)
	public String addCity(@ModelAttribute("city") City c) {
		if (c.getId() == 0) {
			this.cityService.addCity(c);			
		} else {
			this.cityService.updateCity(c);
		}
		return "redirect:/cities";
	}
	
	@RequestMapping(value = "/removeCity/{id}") 
	public String removeCity(@PathVariable("id") int id) {
		this.cityService.removeCity(id);
		return"redirect:/cities";
	}
	
	@RequestMapping("/editCity/{id}")
    public String editCity(@PathVariable("id") int id, Model model){
        model.addAttribute("city", this.cityService.getCityById(id));
        model.addAttribute("listCities", this.cityService.listCities());
        return "city";
    }
}
