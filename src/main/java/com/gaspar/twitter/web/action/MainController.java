package com.gaspar.twitter.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;

/**
 * @author Gaspar Rajoy
 **/

@Controller
@RequestMapping("/")
public class MainController extends BaseController{
    
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView main(@RequestParam(value="username", required=false) String username,
							 HttpServletRequest request, HttpServletResponse response) {

		this.setView(new ModelAndView("home/default"));
		
		if (StringUtils.isNotBlank(username)){
			String token = this.generateToken(request.getSession());
			List<RequestMappingInfo> listMappings = new ArrayList<RequestMappingInfo>();
			List<String> mappingString = new ArrayList<String>();
			listMappings.addAll(this.getHandlerMapping().getHandlerMethods().keySet());
		
			
			 for (RequestMappingInfo mapping : listMappings) {
				 String finalMapping = mapping.getPatternsCondition().toString();
				 if (StringUtils.contains(finalMapping, "/api")){
					 finalMapping = StringUtils.remove(finalMapping,"[");
					 finalMapping = StringUtils.remove(finalMapping,"]");
					 finalMapping = StringUtils.replace(finalMapping,"{username}",username);
					 finalMapping = StringUtils.replace(finalMapping,"{userToFollow}",username);
					 mappingString.add(finalMapping);
				 }
		    }
			
			getView().addObject("handlerMethods", mappingString);
			getView().addObject("username", username);
			getView().addObject("token", token);
		}
		
		return getView();
	}

}