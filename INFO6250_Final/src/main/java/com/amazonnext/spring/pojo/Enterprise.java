package com.amazonnext.spring.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
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

@Entity
@Table(name = "enterprises")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Enterprise {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id",nullable=false,unique=true)
	private Integer id;
	
	@Column(name="enterpriseName",nullable=false)
	private String enterpriseName;
	
	@OneToMany(mappedBy="enterprise",cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	private Set<Stock> stocks = new HashSet<Stock>();
	
	@OneToMany(mappedBy="enterprise",cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	private Set<User> users = new HashSet<User>();
	
	@Column(name="balance",nullable=false)
	private Double balance;

	public Enterprise(String enterpriseName, Set<Stock> stocks,
			Set<User> users, Double balance) {
		super();
		this.enterpriseName = enterpriseName;
		this.stocks = stocks;
		this.users = users;
		this.balance = balance;
	}

	public Enterprise() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	public Set<Stock> getStocks() {
		return stocks;
	}

	public void setStocks(Set<Stock> stocks) {
		this.stocks = stocks;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

}
