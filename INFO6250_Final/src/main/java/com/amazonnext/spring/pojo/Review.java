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
@Table(name="reviews")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Review {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id",nullable=false,unique=true)
	private Integer id;

	@Column(name="reviewContent",nullable=false)
	private String reviewContent;
	
	@Column(name="score",nullable=false)
	private Integer score;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="reviewUser_id",referencedColumnName="id",nullable=false)
	private User reviewUser;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="reviewProduct_id",referencedColumnName="id",nullable=false)
	private Product reviewProduct;

	public Review() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Review(String reviewContent, User reviewUser, Product reviewProduct, Integer score) {
		super();
		this.reviewContent = reviewContent;
		this.reviewUser = reviewUser;
		this.reviewProduct = reviewProduct;
		this.score = score;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getReviewContent() {
		return reviewContent;
	}

	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}

	public User getReviewUser() {
		return reviewUser;
	}

	public void setReviewUser(User reviewUser) {
		this.reviewUser = reviewUser;
	}

	public Product getReviewProduct() {
		return reviewProduct;
	}

	public void setReviewProduct(Product reviewProduct) {
		this.reviewProduct = reviewProduct;
	}

	@Override
	public String toString() {
		return "from:"+this.reviewUser.getUsername()+"<br>"+this.reviewContent;
	}
	
	
}
