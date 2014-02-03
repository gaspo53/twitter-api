package com.gaspar.twitter.web.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BaseController {
	
	private ModelAndView view;
	
	public BaseController(){
		this.view = new ModelAndView();
	}

	
	
	
	//Getters and Setters
	public ModelAndView getView() {
		return view;
	}

	public void setView(ModelAndView view) {
		this.view = view;
	}

}
