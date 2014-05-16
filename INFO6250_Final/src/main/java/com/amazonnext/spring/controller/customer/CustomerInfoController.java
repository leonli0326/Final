package com.amazonnext.spring.controller.customer;

import javax.validation.Valid;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.amazonnext.spring.DAO.LocationDAO;
import com.amazonnext.spring.DAO.UserDAO;
import com.amazonnext.spring.pojo.Location;
import com.amazonnext.spring.pojo.User;
import com.amazonnext.spring.pojo.UserAddress;
import com.amazonnext.spring.pojo.UserPaymentMethod;

@Controller
public class CustomerInfoController {

	LocationDAO ld = new LocationDAO();
	String fromUrl = null;

	@RequestMapping(value = "/address/update*", method = RequestMethod.GET)
	public String addAddress(
			@RequestParam(value = "fromUrl", required = false) String fromUrl,
			Model model) {
		if (fromUrl == null || fromUrl.equals("")) {
			fromUrl = "/";
		}
		UserDAO ud = new UserDAO();
		String userName = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		User u = ud.findUserByUsername(userName);
		this.fromUrl = fromUrl;
		model.addAttribute("userAddressesList", u.getUserAddresses());
		model.addAttribute("locations", ld.findAll());
		return "addAddress";
	}

	@RequestMapping(value = "/address/update*", method = RequestMethod.POST)
	public String addAddress(
			@RequestParam("selectedLocationId") Integer selectedLocationId,
			@RequestParam("address") String address,
			@RequestParam(value = "isDefault", required = false) Boolean isDefault,
			Model model) {

		UserDAO ud = new UserDAO();
		Location selectedLocation = (Location) ld.find(selectedLocationId,
				false);
		String userName = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		User u = ud.findUserByUsername(userName);
		UserAddress ua = new UserAddress(address, selectedLocation, u);
		if (isDefault != null && isDefault) {
			for (UserAddress uat : u.getUserAddresses()) {
				uat.setDefault(false);
			}
		}
		u.getUserAddresses().add(ua);
		ud.saveOrUpdate(u);
		return "redirect:" + fromUrl;
	}

	@RequestMapping(value = "/address/setDefault*", method = RequestMethod.GET)
	public String setDefaultAddress(
			@RequestParam("selectedAddressId") Integer selectedAddressId,
			Model model) {
		UserDAO ud = new UserDAO();
		String userName = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		User u = ud.findUserByUsername(userName);
		for (UserAddress uat : u.getUserAddresses()) {
			if (uat.getId() == selectedAddressId) {
				uat.setDefault(true);
			} else {
				uat.setDefault(false);
			}
		}
		return "redirect:/address/update";
	}

	@RequestMapping(value = "/payment/update*", method = RequestMethod.GET)
	public String addAPaymentMethod(@RequestParam(value = "fromUrl", required = false) String fromUrl,
			Model model) {
		if (fromUrl == null || fromUrl.equals("")) {
			fromUrl = "/";
		}
		UserDAO ud = new UserDAO();
		String userName = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		User u = ud.findUserByUsername(userName);
		this.fromUrl = fromUrl;
		model.addAttribute("userPaymentMethodList", u.getUserPaymentMethods());
		model.addAttribute("paymentMethod", new UserPaymentMethod());
		return "addPaymentMethod";
	}

	@RequestMapping(value = "/payment/update*", method = RequestMethod.POST)
	public String addAPaymentMethod(
			@Valid @ModelAttribute("paymentMethod") UserPaymentMethod paymentMethod,
			BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "addPaymentMethod";
		}
		UserDAO ud = new UserDAO();
		String userName = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		User u = ud.findUserByUsername(userName);
		if (paymentMethod.isDefault()) {
			for (UserPaymentMethod upm : u.getUserPaymentMethods()) {
				upm.setIsDefault(false);
			}
		}
		paymentMethod.setUser(u);
		u.getUserPaymentMethods().add(paymentMethod);
		ud.saveOrUpdate(u);
		return "redirect:" + fromUrl;
	}
	
	@RequestMapping(value = "/payment/setDefault*", method = RequestMethod.GET)
	public String setDefaultPayment(
			@RequestParam("selectedPaymentMethodId") Integer selectedPaymentMethodId,
			Model model) {
		UserDAO ud = new UserDAO();
		String userName = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		User u = ud.findUserByUsername(userName);
		for (UserPaymentMethod upm : u.getUserPaymentMethods()) {
			if (upm.getId() == selectedPaymentMethodId) {
				upm.setDefault(true);
			} else {
				upm.setDefault(false);
			}
		}
		return "redirect:/payment/update";
	}

}
