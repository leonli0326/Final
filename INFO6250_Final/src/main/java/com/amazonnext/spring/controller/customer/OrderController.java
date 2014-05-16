package com.amazonnext.spring.controller.customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;

import org.joda.time.DateTime;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.amazonnext.spring.DAO.OrderDAO;
import com.amazonnext.spring.DAO.UserDAO;
import com.amazonnext.spring.pojo.DeliveryPackage;
import com.amazonnext.spring.pojo.Enterprise;
import com.amazonnext.spring.pojo.Order;
import com.amazonnext.spring.pojo.OrderItem;
import com.amazonnext.spring.pojo.Product;
import com.amazonnext.spring.pojo.Stock;
import com.amazonnext.spring.pojo.User;
import com.amazonnext.spring.pojo.UserAddress;
import com.amazonnext.spring.pojo.UserPaymentMethod;

@Controller
@SessionAttributes({ "cart", "selectedAddress", "selectedPaymentMethod" })
public class OrderController {

	UserDAO ud = new UserDAO();

	@ModelAttribute("selectedAddress")
	public UserAddress getSelectedAddress(
			@ModelAttribute("selectedAddress") UserAddress selectedAddress) {
		if (selectedAddress == null) {
			selectedAddress = new UserAddress();
		}
		return selectedAddress;
	}

	@ModelAttribute("selectedPaymentMethod")
	public UserPaymentMethod getSelectedAddress(
			@ModelAttribute("selectedPaymentMethod") UserPaymentMethod selectedPaymentMethod) {
		if (selectedPaymentMethod == null) {
			selectedPaymentMethod = new UserPaymentMethod();
		}
		return selectedPaymentMethod;
	}

	@ModelAttribute("cart")
	public HashMap<Stock, Integer> getCart(
			@ModelAttribute("cart") HashMap<Stock, Integer> cart,
			@ModelAttribute("selectedAddress") UserAddress selectedAddress,
			Model model) {
		if (cart == null) {
			return new HashMap<Stock, Integer>();
		}
		return cart;
	}

	@RequestMapping(value = "/order/place*", method = RequestMethod.GET)
	public String placeOrder(
			@ModelAttribute("cart") HashMap<Stock, Integer> cart,
			@RequestParam(value = "selectedAddressId", required = false) Integer selectedAddressId,
			@RequestParam(value = "selectedPaymentMethodId", required = false) Integer selectedPaymentMethodId,
			@ModelAttribute("selectedAddress") UserAddress selectedAddress,
			@ModelAttribute("selectedPaymentMethod") UserPaymentMethod selectedPaymentMethod,
			Model model) {
		if (cart.size() == 0) {
			return "redirect:/spring/";
		}
		String userName = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		User u = ud.findUserByUsername(userName);
		ArrayList<UserAddress> userAddressesList = new ArrayList<UserAddress>(
				u.getUserAddresses());
		ArrayList<UserPaymentMethod> userPaymentMethodList = new ArrayList<UserPaymentMethod>(
				u.getUserPaymentMethods());
		// address
		if (selectedAddressId != null) {
			if (!userAddressesList.isEmpty()) {
				for (UserAddress ua : userAddressesList) {
					if (ua.getId().equals(selectedAddressId)) {
						selectedAddress = ua;
						break;
					}
				}
				cart = replaceCart(cart, selectedAddress, model);
			}
		} else {
			if (selectedAddress.getId() == null) {
				if (!userAddressesList.isEmpty()) {
					selectedAddress = userAddressesList.get(0);
					for(UserAddress ua:userAddressesList){
						if(ua.isDefault()){
							selectedAddress=ua;
							break;
						}
					}
					cart = replaceCart(cart, selectedAddress, model);
				}
			}
		}
		// payment method
		if (selectedPaymentMethodId != null) {
			if (!userPaymentMethodList.isEmpty()) {
				for (UserPaymentMethod upm : userPaymentMethodList) {
					if (upm.getId().equals(selectedPaymentMethodId)) {
						selectedPaymentMethod = upm;
						break;
					}
				}
			}
		} else {
			if (selectedPaymentMethod.getId() == null) {
				if (!userPaymentMethodList.isEmpty()) {
					selectedPaymentMethod = userPaymentMethodList.get(0);
					for(UserPaymentMethod upm:userPaymentMethodList){
						if(upm.isDefault()){
							selectedPaymentMethod=upm;
							break;
						}
					}
				}
			}
		}

		double beforeTax = 0.0;
		double tax = 0.0;
		for (Entry<Stock, Integer> e : cart.entrySet()) {
			if (e.getValue() > 0) {
				beforeTax += e.getKey().getProduct().getRetailPrice() * e.getValue();
				tax += e.getKey().getProduct().getRetailPrice()*e.getKey().getTaxRate() * e.getValue();
			}
		}

		if (selectedAddress.getId() != null
				&& selectedPaymentMethod.getId() != null) {
			model.addAttribute("confirmable", true);
		}

		model.addAttribute("beforeTax", String.format("%.2f", beforeTax));
		model.addAttribute("afterTax", String.format("%.2f", beforeTax + tax));
		model.addAttribute("tax", String.format("%.2f", tax));
		model.addAttribute("cart", cart);
		model.addAttribute("selectedAddress", selectedAddress);
		model.addAttribute("userAddressesList", userAddressesList);
		model.addAttribute("selectedPaymentMethod", selectedPaymentMethod);
		model.addAttribute("userPaymentMethodList", userPaymentMethodList);
		return "placeOrder";
	}

	public HashMap<Stock, Integer> replaceCart(
			@ModelAttribute("cart") HashMap<Stock, Integer> cart,
			@ModelAttribute("selectedAddress") UserAddress selectedAddress,
			Model model) {
		HashMap<Stock, Integer> newcart = new HashMap<Stock, Integer>();
		for (Entry<Stock, Integer> e : cart.entrySet()) {
			Product p = e.getKey().getProduct();
			boolean b = false;
			for (Stock s : p.getStocks()) {
				if (s.getLocation().getId() == selectedAddress.getLocation()
						.getId()) {
					if(s.getStockCount()>=e.getValue()){
						newcart.put(s, Math.abs(e.getValue()));
					}else{
						newcart.put(s, -Math.abs(e.getValue()));
					}
					b = true;
					break;
				}
			}
			if (!b) {
				newcart.put(e.getKey(), -Math.abs(e.getValue()));
			}
		}
		return newcart;
	}

	@RequestMapping(value = "/order/confirm*", method = RequestMethod.GET)
	public String confirmOrder(
			@ModelAttribute("cart") HashMap<Stock, Integer> cart,
			@ModelAttribute("selectedAddress") UserAddress selectedAddress,
			@ModelAttribute("selectedPaymentMethod") UserPaymentMethod selectedPaymentMethod,
			SessionStatus status,
			Model model) {
		String userName = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		User u = ud.findUserByUsername(userName);
		OrderDAO od = new OrderDAO();
		Order o = new Order();
		o.setOrderTotal(0.0);
		o.setOrderTotalBeforeTax(0.0);
		HashMap<Integer,DeliveryPackage> map = new HashMap<Integer,DeliveryPackage>();
		boolean b = false;
		for(Entry<Stock, Integer> e : cart.entrySet()){
			if(e.getValue()>0){
				System.out.println(e.getValue());
				OrderItem oi = new OrderItem(o, 
						e.getKey(), 
						e.getKey().getProduct().getRetailPrice(), 
						e.getKey().getTaxRate(), e.getValue()
						);
				oi.getStock().setStockCount(e.getKey().getStockCount()-e.getValue());
				o.getOrderItems().add(oi);
				o.setOrderTotalBeforeTax(
						Math.round((o.getOrderTotalBeforeTax()+e.getKey().getProduct().getRetailPrice()*e.getValue())*100)/100.00
						);
				o.setOrderTotal(
						Math.round((o.getOrderTotal()+e.getKey().getProduct().getRetailPrice()*e.getValue()*(1+e.getKey().getTaxRate()))*100)/100.00
						);
				if(map.containsKey(e.getKey().getEnterprise().getId())){
					DeliveryPackage dp = map.get(e.getKey().getEnterprise().getId());
					dp.getOrderItems().add(oi);
					oi.setDeliveryPackage(dp);
				}else{
					HashSet<OrderItem> set = new HashSet<OrderItem>();
					set.add(oi);
					DeliveryPackage dp = new DeliveryPackage(DateTime.now(), null, "Submitted", set);
					map.put(e.getKey().getEnterprise().getId(), dp);
					oi.setDeliveryPackage(dp);
				}				
				b = true;
			}
		}
		if(b){
			o.setUser(u);
			o.setDeliverAddress(selectedAddress);
			o.setOrderTime(DateTime.now());
			o.setUserPaymentMethod(selectedPaymentMethod);
			o.setOrderStatus("Submitted");
			try {
				od.saveOrUpdate(o);
				status.setComplete();
				return "redirect:successOrder";
			} catch (Exception e) {
				e.printStackTrace();
				return "redirect:orderFailure?reason=dberror";
			}
		}else{
			return "redirect:orderFailure?reason=emptystock";
		}
	}
	
	@RequestMapping(value = "/order/success*", method = RequestMethod.GET)
	public String successOrder(Model model){
		model.addAttribute("result","Order place successed!");
		return "orderResult";
	}
	
	@RequestMapping(value = "/order/failure*", method = RequestMethod.GET)
	public String orderFailure(@RequestParam String reason,Model model){
		if(reason.equals("dberror")){
			model.addAttribute("result","Order place failed due to system error");
		}else if(reason.equals("emptystock")){
			model.addAttribute("result","Order place failed due to no available stock at your address");
		}else{
			model.addAttribute("result","Order place failed");
		}
		return "orderResult";
	}
	
	@RequestMapping(value = "/order/view*", method = RequestMethod.GET)
	public String viewOrder(Model model){
		String userName = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		User u = ud.findUserByUsername(userName);
		model.addAttribute("orders",u.getOrders());
		return "viewOrder";
	}
}
