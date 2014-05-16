package com.amazonnext.spring.controller.common;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String loginPage(){
		return "login";
	}
	
	@RequestMapping(value="/loginFailure",method=RequestMethod.GET)
	public String loginFailure(Model model){
		model.addAttribute("loginError", "Username or Password error!");
		return "login";
	}
	
	@RequestMapping(value="/loginSuccess",method=RequestMethod.GET)
	public String loginSuccess(){
		return "redirect:/";
	}
}
