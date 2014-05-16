package com.amazonnext.spring.controller.customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.amazonnext.spring.DAO.OrderDAO;
import com.amazonnext.spring.DAO.ProductDAO;
import com.amazonnext.spring.DAO.StockDAO;
import com.amazonnext.spring.DAO.TagDAO;
import com.amazonnext.spring.DAO.UserDAO;
import com.amazonnext.spring.pojo.Location;
import com.amazonnext.spring.pojo.Order;
import com.amazonnext.spring.pojo.Product;
import com.amazonnext.spring.pojo.Review;
import com.amazonnext.spring.pojo.Stock;
import com.amazonnext.spring.pojo.Tag;
import com.amazonnext.spring.pojo.User;
import com.amazonnext.spring.pojo.UserAddress;

@Controller
@SessionAttributes("cart")
public class ShoppingController {

	private Stock selectedStock;

	@ModelAttribute("cart")
	public HashMap<Stock, Integer> getCart(
			@ModelAttribute("cart") HashMap<Stock, Integer> cart, Model model) {
		if (cart == null) {
			model.addAttribute("cart", new HashMap<Stock, Integer>());
		}
		return cart;
	}

	@RequestMapping(value = "/shopping/productDetail**", method = RequestMethod.GET)
	public String viewProductDetail(
			@RequestParam("productId") Integer prodcutId,
			@RequestParam(value = "selectedStockId", required = false) Integer selectedStockId,
			Model model) {
		ProductDAO pd = new ProductDAO();
		StockDAO sd = new StockDAO();
		Product p = (Product) pd.find(prodcutId, false);
		HashMap<Location, Stock> map = new HashMap<Location, Stock>();
		String userName = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		User u = (new UserDAO()).findUserByUsername(userName);
		for (Stock s : p.getStocks()) {
			if (s.getStockCount() > 0 && s.getPrice() < p.getRetailPrice()) {
				if (map.containsKey(s.getLocation())) {
					if (s.getPrice() < map.get(s.getLocation()).getPrice()) {
						map.put(s.getLocation(), s);
					}
				} else {
					map.put(s.getLocation(), s);
				}
			}
		}
		ArrayList<Stock> stocks = new ArrayList<Stock>();
		for (Entry<Location, Stock> e : map.entrySet()) {
			stocks.add(e.getValue());
		}
		// Stock selectStock;
		if (selectedStockId != null) {
			selectedStock = (Stock) sd.find(selectedStockId, false);
		} else {
			if (u != null) {
				boolean b =false;
				for (UserAddress ua : u.getUserAddresses()) {
					if (ua.isDefault()) {
						for (Stock s : stocks) {
							if (s.getLocation().getId() == ua.getLocation()
									.getId()) {
								selectedStock = s;
								b=true;
								break;
							}
						}
					}
					if(b) break;
				}
				if(!b){
					selectedStock=stocks.get(0);
				}
			}
			selectedStock = stocks.get(0);
		}
		//check customer order record
		if(u!=null){
			List<Order> ol = (new OrderDAO()).findByProduct(prodcutId, u.getId());
			if(ol!=null && !ol.isEmpty()){
				model.addAttribute("previousOrder", ol.get(0));
			}
			model.addAttribute("isReviewed", pd.checkReviewed(prodcutId, u.getId()));
		}
		model.addAttribute("stocks", stocks);
		model.addAttribute("product", p);
		model.addAttribute("selectedStock", selectedStock);
		return "viewProductDetail";
	}

	@RequestMapping(value = "/shopping/productDetail**", method = RequestMethod.POST)
	public String viewProductDetail(
			@RequestParam("amountToCart") Integer amountToCart,
			@ModelAttribute("cart") Map<Stock, Integer> cart,
			// @ModelAttribute("selectedStock") Stock selectedStock,
			Model model, RedirectAttributes redirectAttributes) {
		boolean find = false;
		for (Entry<Stock, Integer> e : cart.entrySet()) {
			if (e.getKey().getId().equals(selectedStock.getId())) {
				e.setValue(e.getValue() + amountToCart);
				find = true;
				break;
			}
		}
		if (!find) {
			cart.put(selectedStock, amountToCart);
		}
		redirectAttributes.addFlashAttribute("successMessage", selectedStock
				.getProduct().getProductName() + " * " + amountToCart);
		// redirectAttributes.addFlashAttribute("selectStock", selectStock);
		return "redirect:/cart/add";
	}
	
	@RequestMapping(value = "/shopping/review**", method = RequestMethod.POST)
	public String reviewProduct(
			@RequestParam(value="tag",required=false) String tag,
			@RequestParam("productId") Integer productId,
			@RequestParam("score") Integer score,
			@RequestParam(value="review",required=false) String review,
			Model model){
		String userName = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		User u = (new UserDAO()).findUserByUsername(userName);
		ProductDAO pd = new ProductDAO();
		Product p = (Product) pd.find(productId, false);
		if(tag!=null && !tag.equals("")){
			Tag t = (new TagDAO()).findTagByName(tag.toLowerCase());
			if(t==null){
				t=new Tag(tag.toLowerCase());
			}
			t.setRanking(t.getRanking()+1);
			p.getTags().add(t);
		}
		if(review!=null && !review.equals("")){
			Review r = new Review(review,u,p,score);
			p.getReviews().add(r);
			p.updateReviewScore(new Double(score));
		}
		pd.saveOrUpdate(p);
		return "redirect:/shopping/productDetail?productId="+productId;
	}

}
