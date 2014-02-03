package com.gaspar.twitter.web.action;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class MainController extends BaseController{

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView main(ModelMap model) {

		this.setView(new ModelAndView("home/default"));

		return getView();
	}
	
}