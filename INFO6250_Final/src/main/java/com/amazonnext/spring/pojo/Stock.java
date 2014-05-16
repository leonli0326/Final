package com.amazonnext.spring.pojo;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name="stocks")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Stock {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id",nullable=false,unique=true)
	private Integer id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="location_id",referencedColumnName="id",nullable=false)
	private Location location;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="product_id",referencedColumnName="id",nullable=false)
	private Product product;
	
	@Column(name="stockCount",nullable=false)
	private Integer stockCount;
	
	@Column(name="price",nullable=false)
	private Double price;
	
	@Column(name="taxRate",nullable=false)
	private Double taxRate;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="enterprise_id",referencedColumnName="id",nullable=false)
	private Enterprise enterprise;
	
	public Stock(Location location, Product product, Integer stockCount) {
		super();
		this.location = location;
		this.product = product;
		this.stockCount = stockCount;
	}
	public Stock() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Integer getStockCount() {
		return stockCount;
	}
	public void setStockCount(Integer stockCount) {
		this.stockCount = stockCount;
	}
	public Double getStockPrice() {
		return price;
	}
	public void setStockPrice(Double price) {
		this.price = price;
	}
	public Double getTaxRate() {
		return taxRate;
	}
	public void setTaxRate(Double taxRate) {
		this.taxRate = taxRate;
	}
	public Enterprise getEnterprise() {
		return enterprise;
	}
	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}

	
}
