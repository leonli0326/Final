package com.amazonnext.spring.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.amazonnext.spring.DAO.ProductDAO;
import com.amazonnext.spring.DAO.TagDAO;
import com.amazonnext.spring.pojo.Product;
import com.amazonnext.spring.pojo.Tag;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private boolean reverse;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = { "/", "/home" })
	public String home(
			@RequestParam(value="searchInput",required=false) String searchInput,
			@RequestParam(value="selectedTags",required=false) ArrayList<String> selectedTags,
			@RequestParam(value="sortFileName",required=false) String sortFileName,
			@RequestParam(value="reverse",required=false) boolean reverse,
			@RequestParam(value="priceRange",required=false) Double priceRange,
			Model model) {
		ProductDAO pd = new ProductDAO();

		TagDAO td = new TagDAO();
		StringBuilder sb = new StringBuilder();
		ArrayList<Tag> tags = (ArrayList<Tag>) td.findPopularTag(5);
		if (selectedTags != null) {
			for (String tag : selectedTags) {
				sb.append(tag).append(" ");
			}
		}
		if(sortFileName==null){
			sortFileName="";
		}
		reverse=(reverse == this.reverse);
		this.reverse=reverse;
		List<Product> lp = new ArrayList<Product>();
		if (searchInput == null || searchInput.equals("")) {
			if (selectedTags == null || selectedTags.size() <= 0) {
				lp=pd.searchProductAllName(sortFileName,reverse);
				
			} else {
				lp=pd.searchProductByWordOnlyTag(sb.toString(),sortFileName,reverse);
				
			}
		} else {
			if (selectedTags == null || selectedTags.size() <= 0) {
				lp = pd.searchProductByWordFuzzy(searchInput,sortFileName,reverse);
				
			} else {
				lp=pd.searchProductByWordFuzzyJoinTag(searchInput,sb.toString(),sortFileName,reverse);
				
			}
		}
		if(priceRange!=null && priceRange>0){
			Iterator i = lp.iterator();
			while(i.hasNext()){
				Product p = (Product) i.next();
				if(p.getRetailPrice()>priceRange){
					i.remove();
				}
			}
		}
		model.addAttribute("products", lp);
		model.addAttribute("searchInput", searchInput);
		model.addAttribute("tags", tags);
		return "main";
	}
	
	@RequestMapping(value = "/rebuildindex")
	public String rebuildInder(){
			try {
				(new ProductDAO()).rebuildIndex();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		return "redirect:/";
	}
	
}
