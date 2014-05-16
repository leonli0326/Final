package com.amazonnext.spring.pojo;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "orderItems")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class OrderItem {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id",nullable=false,unique=true)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="order_id",referencedColumnName="id",nullable=false)
	private Order order;
	
	@ManyToOne
	@JoinColumn(name="stock_id",referencedColumnName="id",nullable=false)
	private Stock stock;
	
	@Column(name="orderedPrice",nullable=false)
	@Min(0)
	private Double orderedPrice;
	
	@Column(name="orderedTaxRate",nullable=false)
	@Min(0)
	private Double orderedTaxRate;
	
	@Column(name="orderedCount",nullable=false)
	@Min(0)
	private Integer orderedCount;
	
	@ManyToOne(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="deliveryPackage_id",referencedColumnName="id")
	private DeliveryPackage deliveryPackage;

	public OrderItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrderItem(Order order, Stock stock, Double orderedPrice,
			Double orderedTaxRate, Integer orderedCount) {
		super();
		this.order = order;
		this.stock = stock;
		this.orderedPrice = orderedPrice;
		this.orderedTaxRate = orderedTaxRate;
		this.orderedCount = orderedCount;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public Double getOrderedPrice() {
		return orderedPrice;
	}

	public void setOrderedPrice(Double orderedPrice) {
		this.orderedPrice = orderedPrice;
	}

	public Double getOrderedTaxRate() {
		return orderedTaxRate;
	}

	public void setOrderedTaxRate(Double orderedTaxRate) {
		this.orderedTaxRate = orderedTaxRate;
	}

	public Integer getOrderedCount() {
		return orderedCount;
	}

	public void setOrderedCount(Integer orderedCount) {
		this.orderedCount = orderedCount;
	}

	public DeliveryPackage getDeliveryPackage() {
		return deliveryPackage;
	}

	public void setDeliveryPackage(DeliveryPackage deliveryPackage) {
		this.deliveryPackage = deliveryPackage;
	}
	
	
	
}
