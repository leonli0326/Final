package com.amazonnext.spring.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.joda.time.DateTime;

@Entity
@Table(name = "deliveryPackages")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DeliveryPackage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private Integer id;

	@Column(name = "startTime")
	private DateTime startTime;

	@Column(name = "endTime")
	private DateTime endTime;

	@Column(name = "packageStatus", nullable = false)
	private String packageStatus;


	@OneToMany(mappedBy = "deliveryPackage", fetch = FetchType.EAGER)
	private Set<OrderItem> orderItems = new HashSet<OrderItem>();

	public DeliveryPackage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DeliveryPackage(DateTime startTime, DateTime endTime,
			String packageStatus, Set<OrderItem> orderItems) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
		this.packageStatus = packageStatus;
		this.orderItems = orderItems;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public DateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(DateTime startTime) {
		this.startTime = startTime;
	}

	public DateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(DateTime endTime) {
		this.endTime = endTime;
	}

	public String getPackageStatus() {
		return packageStatus;
	}

	public void setPackageStatus(String packageStatus) {
		this.packageStatus = packageStatus;
	}

	public Set<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(Set<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public String getStartTimeToString() {
		if (this.startTime == null)
			return "";
		else
		return this.startTime.toString("MMM dd,yyyy hh:mm");
	}

	public String getEndTimeToString() {
		if (this.endTime == null)
			return "";
		else
			return this.endTime.toString("MMM dd,yyyy hh:mm");
	}

	public Enterprise getEnterprise() {
		Enterprise e = null;
		for (OrderItem oi : orderItems) {
			e = oi.getStock().getEnterprise();
			if (e != null)
				break;
		}
		return e;
	}

	public UserAddress getAddress() {
		UserAddress ua = null;
		for (OrderItem oi : orderItems) {
			ua = oi.getOrder().getDeliverAddress();
			if (ua != null)
				break;
		}
		return ua;
	}

}
