package com.amazonnext.spring.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.amazonnext.spring.DAO.EnterpriseDAO;
import com.amazonnext.spring.DAO.LocationDAO;
import com.amazonnext.spring.DAO.ProductDAO;
import com.amazonnext.spring.DAO.RoleDAO;
import com.amazonnext.spring.DAO.UserDAO;
import com.amazonnext.spring.pojo.Enterprise;
import com.amazonnext.spring.pojo.Location;
import com.amazonnext.spring.pojo.Product;
import com.amazonnext.spring.pojo.Role;
import com.amazonnext.spring.pojo.Stock;
import com.amazonnext.spring.pojo.User;

@Controller
public class AdminController {
	
	@RequestMapping(value="/admin*",method=RequestMethod.GET)
	public String adminPage(Model model){
		UserDAO ud = new UserDAO();
		RoleDAO rd = new RoleDAO();
		model.addAttribute("users",ud.findAll());
		model.addAttribute("allRoles",rd.findAll());
		return "admin";
	}
	
	@RequestMapping(value="/admin/grantRole*")
	public String grantRole(
			@RequestParam("userId") Integer userId,
			@RequestParam("roleId") Integer roleId,
			Model model){
		UserDAO ud = new UserDAO();
		RoleDAO rd = new RoleDAO();
		User u = (User) ud.find(userId, false);
		u.getRoles().add((Role) rd.find(roleId, false));
		ud.saveOrUpdate(u);
		return "redirect:/admin";
	}
	
	@RequestMapping(value="/admin/revokeRole*")
	public String revokeRole(
			@RequestParam("userId") Integer userId,
			@RequestParam("roleId") Integer roleId,
			Model model){
		UserDAO ud = new UserDAO();
		RoleDAO rd = new RoleDAO();
		User u = (User) ud.find(userId, false);
		u.getRoles().remove((Role) rd.find(roleId, false));
		ud.saveOrUpdate(u);
		return "redirect:/admin";
	}
	
	@RequestMapping(value="/enterprise*",method=RequestMethod.GET)
	public String enterprisePage(Model model){
		EnterpriseDAO ed = new EnterpriseDAO();
		model.addAttribute("enterprises",ed.findAll());
		return "enterpriseManagement";
	}
	
	@RequestMapping(value="/enterprise/removeUser*")
	public String removeUser(
			@RequestParam("userId") Integer userId,
			@RequestParam("enterpriseId") Integer enterpriseId,
			Model model){
		EnterpriseDAO ed = new EnterpriseDAO();
		UserDAO ud = new UserDAO();
		Enterprise e = (Enterprise) ed.find(enterpriseId, false);
		User u = (User) ud.find(userId, false);
		e.getUsers().remove(u);
		ed.saveOrUpdate(e);
		return "redirect:/enterprise";
	}
	
	@RequestMapping(value="/enterprise/addUser*")
	public String addUser(
			@RequestParam("username") String username,
			@RequestParam("enterpriseId") Integer enterpriseId,
			Model model){
		EnterpriseDAO ed = new EnterpriseDAO();
		UserDAO ud = new UserDAO();
		Enterprise e = (Enterprise) ed.find(enterpriseId, false);
		User u = (User) ud.findUserByUsername(username);
		if(u!=null){
			e.getUsers().add(u);
			ed.saveOrUpdate(e);
		}
		return "redirect:/enterprise";
	}
	
	@RequestMapping(value="/enterprise/addStock*")
	public String addStock(
			@RequestParam("productId") Integer productId,
			@RequestParam("enterpriseId") Integer enterpriseId,
			Model model){
		EnterpriseDAO ed = new EnterpriseDAO();
		ProductDAO pd = new ProductDAO();
		Enterprise e = (Enterprise) ed.find(enterpriseId, false);
		Product p = (Product) pd.find(productId, false);
		if(p!=null){
			Stock s = new Stock((Location) (new LocationDAO().find(1, false)), p, 0);
			s.setPrice(0.0);
			s.setTaxRate(0.0);
			s.setEnterprise(e);
			e.getStocks().add(s);
			ed.saveOrUpdate(e);
		}
		return "redirect:/enterprise";
	}

}
