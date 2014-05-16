package com.amazonnext.spring.pojo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.search.annotations.ContainedIn;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;


@Entity
@Table(name="tags")
public class Tag {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id",nullable=false,unique=true)
	private Integer id;
	
	@Column(name="tagContent",nullable=false,unique=true)
	@Field
//	@ContainedIn
	private String tagContent;
	
	@Column(name="ranking",nullable=false)
	private Integer ranking;
	
	@ManyToMany(mappedBy="tags",fetch=FetchType.LAZY)
	private List<Product> products = new ArrayList<Product>();
	
	public Tag(String tagContent) {
		super();
		this.tagContent = tagContent;
		this.ranking =1;
	}

	public Tag() {
		super();
		this.ranking =0;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTagContent() {
		return tagContent;
	}

	public void setTagContent(String tagContent) {
		this.tagContent = tagContent;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Integer getRanking() {
		return ranking;
	}

	public void setRanking(Integer ranking) {
		this.ranking = ranking;
	}
	
	
}
