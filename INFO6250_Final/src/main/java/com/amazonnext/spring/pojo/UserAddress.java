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
@Table(name="userAddresses")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class UserAddress {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id",nullable=false,unique=true)
	private Integer id;
	
	@Column(name="address",nullable=false)
	private String address;
	
	@Column(name="isDefault")
	private Boolean isDefault = false;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="location_id",referencedColumnName="id",nullable=false)
	private Location location;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="user_id",referencedColumnName="id",nullable=false)
	private User user;

	public UserAddress() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserAddress(String address, Location location, User user) {
		super();
		this.address = address;
		this.location = location;
		this.user = user;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isDefault() {
		return isDefault;
	}

	public void setDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}

	public Boolean getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Boolean isDefault) {
		this.isDefault = isDefault;
	}

	@Override
	public String toString() {
		return this.address + "<br>" + this.location.toString();
	}
	
	

}
