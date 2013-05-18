package ar.edu.itba.it.paw.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;


@Service
@Scope(value="request")
public class BaseService {
	
	@Autowired
	public BaseService (HttpServletRequest request, FoodTypeService ftService) {
		request.setAttribute("foodTypesList", ftService.getAll());
	}
	
	
	
}
