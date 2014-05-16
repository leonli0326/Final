package com.amazonnext.spring.controller.common;

import javax.validation.Valid;

import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.amazonnext.spring.DAO.RoleDAO;
import com.amazonnext.spring.DAO.UserDAO;
import com.amazonnext.spring.pojo.Role;
import com.amazonnext.spring.pojo.User;

@Controller
public class UserRegisterController {

//	User user = new User();

	@RequestMapping(value = "/userRegister", method = RequestMethod.GET)
	public String registerUser(Model model) {
		model.addAttribute(new User());
		return "userRegister";
	}

	@RequestMapping(value = "/userRegister", method = RequestMethod.POST)
	public String registerUser(@Valid @ModelAttribute("user") User user,
			BindingResult result, Model model) {
		System.out.println(result.getFieldErrors());
		if (result.hasErrors()) {
			return "userRegister";
		} else {
			Role customer = (Role) (new RoleDAO()).find(1, false);
			System.out.println(customer);
			user.setPassword(new ShaPasswordEncoder(256).encodePassword(user.getPassword(), user.getUsername()));
			user.getRoles().add((Role) (new RoleDAO()).find(1, false));
			if((new UserDAO()).saveUserOnly(user)){
				return "login";
			}else{
				model.addAttribute("userNameRepeat","User name has already existed, please enter a new one.");
				return "userRegister";
			}			
		}
	}

}
