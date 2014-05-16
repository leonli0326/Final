package com.amazonnext.spring.controller.customer;

import java.util.HashMap;
import java.util.Map.Entry;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.amazonnext.spring.pojo.Stock;


@Controller
@SessionAttributes("cart")
public class CartController {
	
	@ModelAttribute("cart")
	public HashMap<Stock,Integer> getCart(@ModelAttribute("cart") HashMap<Stock,Integer> cart,Model model){
		if(cart==null){
			model.addAttribute("cart",new HashMap<Stock,Integer>());
		}
		return cart;
	}		
	
	@RequestMapping(value="/cart/add*")
	public String addToCart(@ModelAttribute("successMessage") String successMessage,
			BindingResult bindingResult,
			Model model){
		if(successMessage==null){
			return "redirect:/spring/";
		}
		model.addAttribute("successMessage", successMessage);
		return "addToCart";
	}
	
	@RequestMapping(value="/cart/view*")
	public String viewCart(@ModelAttribute("cart") HashMap<Stock,Integer> cart,
			Model model){
		double beforeTax = 0.0;
		double afterTax = 0.0;
		for(Entry<Stock,Integer> e:cart.entrySet()){
			if(e.getValue()<=e.getKey().getStockCount() && e.getValue()>0){
				beforeTax+=e.getKey().getProduct().getRetailPrice()*e.getValue();
				afterTax+=e.getKey().getProduct().getRetailPrice()*e.getValue()*(1+e.getKey().getTaxRate());
			}
		}
		model.addAttribute("beforeTax", String.format("%.2f",beforeTax));
		model.addAttribute("afterTax", String.format("%.2f",afterTax));
		return "viewCart";
	}

	@RequestMapping(value="/cart/delete*")
	public String viewCart(@ModelAttribute("cart") HashMap<Stock,Integer> cart,
			@RequestParam("deleteId") Integer deleteId,
			Model model){
		Stock s = null;
		for (Entry<Stock, Integer> e : cart.entrySet()) {
			if (e.getKey().getId().equals(deleteId)) {
				s = e.getKey();
				break;
			}
		}
		cart.remove(s);
		return "redirect:/cart/view";
	}
	
	@RequestMapping(value="/cart/changeAmount*")
	public String viewCart(@ModelAttribute("cart") HashMap<Stock,Integer> cart,
			@RequestParam("changeId") Integer deleteId,
			@RequestParam("changeAmount") Integer changeAmount,
			Model model){
		Stock s = null;
		for (Entry<Stock, Integer> e : cart.entrySet()) {
			if (e.getKey().getId().equals(deleteId)) {
				e.setValue(changeAmount);
				break;
			}
		}
		cart.remove(s);
		return "redirect:/cart/view";
	}

	
	

}
