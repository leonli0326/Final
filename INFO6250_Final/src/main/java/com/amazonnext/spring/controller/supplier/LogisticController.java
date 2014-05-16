package com.amazonnext.spring.controller.supplier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.joda.time.DateTime;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.amazonnext.spring.DAO.DeliveryPackageDAO;
import com.amazonnext.spring.DAO.UserDAO;
import com.amazonnext.spring.pojo.DeliveryPackage;
import com.amazonnext.spring.pojo.User;

@Controller
public class LogisticController {
	
	@RequestMapping(value = "/logistic/package*", method = RequestMethod.GET)
	public String packageDelivery(Model model){
		String userName = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		User u = (new UserDAO()).findUserByUsername(userName);
		DeliveryPackageDAO dpd = new DeliveryPackageDAO();
		ArrayList<DeliveryPackage> dpList = new ArrayList<DeliveryPackage>();
		for(DeliveryPackage dp:dpd.findAll()){
			if(dp.getEnterprise().getId()==u.getEnterprise().getId()){
				dpList.add(dp);
			}
		}
		Collections.sort(dpList, new Comparator<DeliveryPackage>() {

			@Override
			public int compare(DeliveryPackage o1, DeliveryPackage o2) {
				return -o1.getStartTime().compareTo(o2.getStartTime());
			}
			
		});
		model.addAttribute("deliveryPackages",dpList);
		return "packageDelivery";
	}
	
	@RequestMapping(value = "/logistic/process*", method = RequestMethod.GET)
	public String processPackageDelivery(
			@RequestParam("packageId") Integer packageId,
			Model model){
		DeliveryPackageDAO dpd = new DeliveryPackageDAO();
		DeliveryPackage dp = (DeliveryPackage) dpd.find(packageId, false);
		dp.setPackageStatus("Delivered");
		dp.setEndTime(DateTime.now());
		dpd.saveOrUpdate(dp);
		return "redirect:package";
	}
}
