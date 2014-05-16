package com.amazonnext.spring;

import java.util.List;

import org.hibernate.Query;

import com.amazonnext.spring.DAO.ProductDAO;
import com.amazonnext.spring.pojo.Product;

public class TEST {

	public static void main(String[] args) {
		ProductDAO pd = new ProductDAO();
//		try {
//			pd.rebuildIndex();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		List<Product> lp = pd.searchProductByWordFuzzy("beef");
////		System.out.println(lp.size());
////		List<Product> lp = pd.findAll();
////		Query q = pd.getSession().createQuery("select * from Product");
////		List<Product> lp = q.list();
//		for(Product p:lp){
//			System.out.println(p.getProductName());
//		}
		
//		ProductDAOTest pdt = new ProductDAOTest();
//		TagDAO td = new TagDAO();
//		Tag t1 = new Tag("beef");
//		Tag t2 = new Tag("fruit");
//		Tag t3 = new Tag("chicken");
//		Tag t4 = new Tag("fish");
//		Tag t5 = new Tag("seafood");
//		Tag t6 = new Tag("meat");
////		td.saveOrUpdate(t6);
//		
//		Product p1 = new Product("1234567891237",
//				"Canter's Deli, Corned Beef Sliced Lean, 3/4 lb",
//				"Canter's Deli",
//				"Our most popular Deli Meat for over 80 years.",
//				"Lean, 3/4 lb", 14.25, 0.0625);
//		ImageLink i1 = new ImageLink(p1, "https://images-na.ssl-images-amazon.com/images/I/41HKIMxxgAL.01._SR40,40_.jpg");
//		p1.getTags().add(t1);
//		p1.getTags().add(t6);
//		p1.getImageLinks().add(i1);
//		
//		Product p2 = new Product("1234567891232",
//				"Santa Monica Seafood, Farm Raised Scottish Salmon fillet",
//				"Santa Monica Seafood",
//				"One of our most popular items. Our Scottish salmon is responsibly farm raised in Scotland.",
//				"Skin On, Fresh, 1 lb", 18.99, 0.0625);
//		ImageLink i2 = new ImageLink(p2, "https://images-na.ssl-images-amazon.com/images/I/41QiQnd0giL.01._SR40,40_.jpg");
//		p2.getTags().add(t4);
//		p2.getTags().add(t5);
//		p2.getImageLinks().add(i2);
//		
//		Product p3 = new Product("1234567891233",
//				"Organic Chicken, Boneless Skinless Breast",
//				"Sweetwater Creek",
//				"Freshness Guarantee: AmazonFresh guarantees every product will be delivered to your home or ready for pickup well within the manufacturer's recommended use by, sell by, best by, or expiration date.",
//				"Air Chilled, Fresh, 16 oz", 9.99, 0.0625);
//		ImageLink i3 = new ImageLink(p3, "https://images-na.ssl-images-amazon.com/images/I/31lOVcIILOL.01._SR40,40_.jpg");
//		p3.getTags().add(t3);
//		p3.getTags().add(t6);
//		p3.getImageLinks().add(i3);
//		
//		Product p4 = new Product("1234567891234",
//				"Bananas, 5 Count Bunch, Green",
//				"Chiquita",
//				"ASIN: B000NOC0FQ",
//				"Ecuador or Guatemala", 1.49, 0.0625);
//		ImageLink i4 = new ImageLink(p4, "https://images-na.ssl-images-amazon.com/images/I/41yuLzrQH6L.01._SR40,40_.jpg");
//		p4.getTags().add(t2);
//		p4.getImageLinks().add(i4);
////		pdt.saveOrUpdate(p4);
////		
//		Product p5 = new Product("1234567891235",
//				"Bananas, 5 Count Bunch, Ripe",
//				"Chiquita",
//				"ASIN: B000NOGLY2",
//				"Ecuador or Guatemala", 1.49, 0.0625);
//		ImageLink i5 = new ImageLink(p5, "https://images-na.ssl-images-amazon.com/images/I/41sLpQs9TCL.01._SR40,40_.jpg");
//		p5.getTags().add(t2);
//		p5.getImageLinks().add(i5);
//		
//		Product p6 = new Product("1234567891236",
//				"Oscar Mayer, Grilled Chicken Breast Strips",
//				"Oscar Mayer",
//				"Chicken Breast Meat, Water, Contains less than 2% of Sodium Lactate, Chicken Flavor (Contains Chicken Broth, Salt, Wheat Starch, Flavor, Thiamine Hydrochloride), Salt, Potassium Lactate",
//				"6 oz", 2.99, 0.0625);
//		ImageLink i6 = new ImageLink(p6, "https://images-na.ssl-images-amazon.com/images/I/510uP7NBd3L.01._SR40,40_.jpg");
//		ImageLink i7 = new ImageLink(p6, "https://images-na.ssl-images-amazon.com/images/I/51mg4E6FBjL.01._SR40,40_.jpg");
//		p6.getTags().add(t3);
//		p6.getTags().add(t6);
//		p6.getImageLinks().add(i6);
//		p6.getImageLinks().add(i7);
//		
//		
////		pd.saveOrUpdateProduct(p6);
////		User u = new User("123435", "123456", "asdasd", "asdasdasd", "123-456-7897", "asdsad@asdasd");
////		u.setPassword(new ShaPasswordEncoder(256).encodePassword(u.getPassword(), u.getUsername()));
////		System.out.println("isSuccess:"+(new UserDAO()).saveUserOnly(u));
////		pd.saveOrUpdateProduct(p1);
////		pd.saveOrUpdateProduct(p2);
////		pd.saveOrUpdate(p3);
////		pd.saveOrUpdateProduct(p4);
////		pd.saveOrUpdateProduct(p5);
////		pd.saveOrUpdate(p6);
////		pd.getSession().beginTransaction();
////		try {
////			pd.getSession().save(p6);
////		} catch (Exception e) {
////			e.printStackTrace();
////		}		
////		pd.getSession().getTransaction().commit();
//		pd.saveOrUpdate(p1);
//		pd.saveOrUpdate(p2);
//		pd.saveOrUpdate(p3);
//		pd.saveOrUpdate(p4);
//		pd.saveOrUpdate(p5);
//		pd.saveOrUpdate(p6);
	}
}
