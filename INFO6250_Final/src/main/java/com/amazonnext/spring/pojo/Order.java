package com.amazonnext.spring.pojo;

import java.util.ArrayList;
import java.util.List;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.joda.time.DateTime;

@Entity
@Table(name = "orders")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private Integer id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
	private User user;

	@Column(name = "orderTime", nullable = false)
	private DateTime orderTime;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userAddress_id", referencedColumnName = "id", nullable = false)
	private UserAddress deliverAddress;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<OrderItem> orderItems = new ArrayList<OrderItem>();

	@Column(name = "orderTotal", nullable = false)
	@Min(0)
	private Double orderTotal;

	@Column(name = "orderTotalBeforeTax", nullable = false)
	@Min(0)
	private Double orderTotalBeforeTax;

	@Column(name = "orderStatus", nullable = false)
	private String orderStatus;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userPaymentMethod_id", referencedColumnName = "id")
	private UserPaymentMethod userPaymentMethod;

	public Order(User user, DateTime orderTime, UserAddress deliverAddress,
			List<OrderItem> orderItems, String orderStatus) {
		super();
		this.user = user;
		this.orderTime = orderTime;
		this.deliverAddress = deliverAddress;
		this.orderItems = orderItems;
		this.orderStatus = orderStatus;
	}

	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public DateTime getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(DateTime orderTime) {
		this.orderTime = orderTime;
	}

	public UserAddress getDeliverAddress() {
		return deliverAddress;
	}

	public void setDeliverAddress(UserAddress deliverAddress) {
		this.deliverAddress = deliverAddress;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public Double getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(Double orderTotal) {
		this.orderTotal = orderTotal;
	}

	public Double getOrderTotalBeforeTax() {
		return orderTotalBeforeTax;
	}

	public void setOrderTotalBeforeTax(Double orderTotalBeforeTax) {
		this.orderTotalBeforeTax = orderTotalBeforeTax;
	}

	public String getOrderTimeToString() {
		if (this.orderTime == null)
			return "";
		else
			return orderTime.toString("MMM dd,yyyy");
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public UserPaymentMethod getUserPaymentMethod() {
		return userPaymentMethod;
	}

	public void setUserPaymentMethod(UserPaymentMethod userPaymentMethod) {
		this.userPaymentMethod = userPaymentMethod;
	}

}
