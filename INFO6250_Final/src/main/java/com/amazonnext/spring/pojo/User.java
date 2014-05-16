package com.amazonnext.spring.pojo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;


@Entity
@Table(name = "users")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id",nullable=false,unique=true)
	private Integer id;
	
	@Column(name="username",nullable = false, unique= true)
	@Length(min=1,max=45,message="User name must be less than 45 characters")
	private String username;
	
	@Column(name="password",nullable = false)
	@Length(min=6,max=64, message="Password must be at least 6 characters")
	private String password;
	
	@ManyToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinTable(name = "user_role",
		joinColumns={@JoinColumn(name="user_id",referencedColumnName="id",nullable=false)},
		inverseJoinColumns={@JoinColumn(name="role_id",referencedColumnName="id",nullable=false)}
	)
	private Set<Role> roles = new HashSet<Role>();
	
	@ManyToOne(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinTable(name = "user_enterprise",
		joinColumns={@JoinColumn(name="user_id",referencedColumnName="id",unique=true,nullable=false)},
		inverseJoinColumns={@JoinColumn(name="enterprise_id",referencedColumnName="id",nullable=false)}
	)
	private Enterprise enterprise;
	
	@Column(name="firstName")
	private String firstName;
	
	@Column(name="lastName")
	private String lastName;
	
	@Column(name="phone")
	@Pattern(regexp="[0-9]{3}-[0-9]{3}-[0-9]{4}",message="please enter a valid phone number in XXX-XXX-XXXX format")
	private String phone;
	
	@Column(name="email")
	@Email(message="please provide a valid e-mail(e.g. ABC@XYZ.com)")
	private String email;
	
	@OneToMany(mappedBy="user",cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	private Set<UserAddress> userAddresses  = new HashSet<UserAddress>();
	
	@OneToMany(mappedBy="user",cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	private Set<UserPaymentMethod> userPaymentMethods  = new HashSet<UserPaymentMethod>();
	
	@OneToMany(mappedBy="user",cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	private List<Order> orders  = new ArrayList<Order>();
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public User(String username, String password, String firstName,
			String lastName, String phone, String email) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Enterprise getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}

	public Set<UserAddress> getUserAddresses() {
		return userAddresses;
	}

	public void setUserAddresses(Set<UserAddress> userAddresses) {
		this.userAddresses = userAddresses;
	}

	public Set<UserPaymentMethod> getUserPaymentMethods() {
		return userPaymentMethods;
	}

	public void setUserPaymentMethods(Set<UserPaymentMethod> userPaymentMethods) {
		this.userPaymentMethods = userPaymentMethods;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
	public String getRolesToString(){
		StringBuilder sb = new StringBuilder();
		for(Role r:this.roles){
			sb.append(r.getRole());
		}
		return sb.toString();
	}

}
