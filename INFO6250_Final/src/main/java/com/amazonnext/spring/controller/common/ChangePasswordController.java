package com.amazonnext.spring.controller.common;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.amazonnext.spring.DAO.UserDAO;
import com.amazonnext.spring.pojo.User;

@Controller
public class ChangePasswordController {

	@RequestMapping(value="/changePassword*",method=RequestMethod.GET)
	public String changePassword(Model model) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		model.addAttribute("username",username);
		return "changePassword";
	}

	@RequestMapping(value="/changePassword*",method=RequestMethod.POST)
	public String changePassword(Model model,
			@ModelAttribute("newPassword") String newPassword,
			@ModelAttribute("confirmNewPassword") String confirmNewPassword) {
		System.out.println(newPassword);
		System.out.println(confirmNewPassword);
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		boolean hasError = false;
		if(newPassword.length()<6){
			model.addAttribute("newPasswordError","Password must be at least 6 characters");
			hasError = true;
		}
		if(!newPassword.equals(confirmNewPassword)){
			model.addAttribute("newPasswordMismatch","Password does not match");
			hasError = true;
		}
		if(hasError){
			model.addAttribute("username",username);
			return "changePassword";
		}else{
			UserDAO ud = new UserDAO();
			User u = ud.findUserByUsername(username);
			ud.resetPassword(u, newPassword);
			return "redirect:home";
		}
	}
}
