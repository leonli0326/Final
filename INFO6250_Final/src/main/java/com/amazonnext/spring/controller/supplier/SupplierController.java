package com.amazonnext.spring.controller.supplier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.joda.time.DateTime;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.amazonnext.spring.DAO.ImageLinksDAO;
import com.amazonnext.spring.DAO.LocationDAO;
import com.amazonnext.spring.DAO.ProductDAO;
import com.amazonnext.spring.DAO.StockDAO;
import com.amazonnext.spring.DAO.TagDAO;
import com.amazonnext.spring.DAO.UserDAO;
import com.amazonnext.spring.pojo.ImageLink;
import com.amazonnext.spring.pojo.Location;
import com.amazonnext.spring.pojo.Product;
import com.amazonnext.spring.pojo.Stock;
import com.amazonnext.spring.pojo.Tag;
import com.amazonnext.spring.pojo.User;

@Controller
public class SupplierController {
	
	@ModelAttribute("updateProduct")
	public Product modelUpdateProduct(){
		return new Product();
	}
	
	@ModelAttribute("newProduct")
	public Product modelNewProduct(){
		return new Product();
	}
	
	@RequestMapping(value = "/product/update*", method = RequestMethod.GET)
	public String updateProduct(
			@RequestParam(value="searchInput",required=false) String searchInput,
			Model model){
		ProductDAO pd = new ProductDAO();
		if(searchInput == null || searchInput.equals("")){
			model.addAttribute("products", pd.findAll());
		}else{
			List<Product> lp = pd.searchProductByWord(searchInput,"",false);
			model.addAttribute("products", lp);
		}		
		return "updateProduct";
	}
	
	@RequestMapping(value = "/product/update*", method = RequestMethod.POST)
	public String updateProduct(@ModelAttribute("updateProduct") Product updateProduct,
			BindingResult bindingResult,
			Model model){
		ProductDAO pd = new ProductDAO();
		Product p = (Product) pd.find(updateProduct.getId(), false);
		p.setLastUpdateTime(DateTime.now());
		p.setProductDescribe(updateProduct.getProductDescribe());
		p.setProductEAN(updateProduct.getProductEAN());
		p.setProductName(updateProduct.getProductName());
		p.setProductMake(updateProduct.getProductMake());
		p.setProductSpec(updateProduct.getProductSpec());
		p.setRetailPrice(updateProduct.getRetailPrice());
		pd.saveOrUpdate(p);
		return "redirect:/product/update";
	}
	
	@RequestMapping(value = "/product/addNew*", method = RequestMethod.POST)
	public String addProduct(@ModelAttribute("newProduct") Product newProduct,
			BindingResult bindingResult,
			Model model){
		ProductDAO pd = new ProductDAO();
		newProduct.setLastUpdateTime(DateTime.now());
		pd.saveOrUpdate(newProduct);
		return "redirect:/product/update";
	}
	
	@RequestMapping(value = "/product/updateImg*")
	public String addImage(@RequestParam("imageLink") String imageLink,
			@RequestParam("productId") Integer productId,
			Model model){
		ProductDAO pd = new ProductDAO();
		Product p = (Product) pd.find(productId, false);
		p.getImageLinks().add(new ImageLink(p,imageLink));
		pd.saveOrUpdate(p);
		return "redirect:/product/update";
	}
	
	@RequestMapping(value = "/product/deleteImg*")
	public String deleteImg(@RequestParam("imageId") Integer imageId,
			Model model){
		ImageLinksDAO ild = new ImageLinksDAO();
		ild.delete((ImageLink) ild.find(imageId, false));
		return "redirect:/product/update";
	}
	
	@RequestMapping(value = "/product/addTag*", method = RequestMethod.GET)
	public String addTag(@RequestParam("newTag") String newTag,
			@RequestParam("productId") Integer productId,
			Model model){
		ProductDAO pd = new ProductDAO();
		Tag t = (new TagDAO()).findTagByName(newTag.toLowerCase());
		if(t==null){
			t=new Tag(newTag.toLowerCase());
		}
		Product p = (Product) pd.find(productId, false);
		p.getTags().add(t);
		pd.saveOrUpdate(p);
		return "redirect:/product/update";
	}
	
	@RequestMapping(value = "/stock/update*", method = RequestMethod.GET)
	public String viewStock(Model model){
		String userName = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		User u = (new UserDAO()).findUserByUsername(userName);
		HashMap<Product,ArrayList<Stock>> map = new HashMap<Product,ArrayList<Stock>>();
		if(u.getEnterprise()==null){
			return "redirect:/spring";
		}else{
			for(Stock s:u.getEnterprise().getStocks()){
				if(map.containsKey(s.getProduct())){
					map.get(s.getProduct()).add(s);					
				}else{
					map.put(s.getProduct(), new ArrayList<Stock>());
					map.get(s.getProduct()).add(s);
				}
			}
			model.addAttribute("map",map);
		}
		model.addAttribute("locations",(new LocationDAO()).findAll());
		return "updateStock";
	}
	
	@RequestMapping(value = "/stock/update*", method = RequestMethod.POST)
	public String viewStock(@RequestParam MultiValueMap<String, String> requestParams,Model model){
		StockDAO sd = new StockDAO();
		for(Entry<String, List<String>> e:requestParams.entrySet()){
			sd.updateStock(Integer.valueOf(e.getKey()), Integer.valueOf(e.getValue().get(0)),Double.valueOf(e.getValue().get(1)));
		}
		return "redirect:/stock/update";
	}
	
	@RequestMapping(value = "/stock/add*", method = RequestMethod.POST)
	public String addStock(
			@RequestParam("stockCount") Integer stockCount,
			@RequestParam("price") Double price,
			@RequestParam("taxRate") Double taxRate,
			@RequestParam("selectedLocationId") Integer selectedLocationId,
			@RequestParam("productId") Integer productId,
			Model model
			){
		String userName = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		User u = (new UserDAO()).findUserByUsername(userName);
		if(u.getEnterprise()==null){
			return "redirect:/spring";
		}else{
			Location l = (Location) (new LocationDAO()).find(selectedLocationId, false);
			Product p = (Product) (new ProductDAO()).find(productId, false);
			Stock s = new Stock(l, p, stockCount);
			s.setPrice(price);
			s.setTaxRate(taxRate/100);
			s.setEnterprise(u.getEnterprise());
			(new StockDAO()).saveOrUpdate(s);
		}
		return "redirect:/stock/update";
	}
}
