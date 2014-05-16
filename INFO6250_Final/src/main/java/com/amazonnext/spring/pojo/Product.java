package com.amazonnext.spring.pojo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.joda.time.DateTime;

@Entity
@Table(name="products")
@Indexed
@Analyzer
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Product {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id",nullable=false,unique=true)
	private Integer id;
	
	@Column(name="productEAN",nullable=false,unique=true)
//	@EAN
	@Field(analyze=Analyze.NO)
	private String productEAN;
	
	@Column(name="productName",nullable=false,unique=true)
	@Field
	private String productName;
	
	@Column(name="productMake",nullable=false)
	@Field(analyze=Analyze.NO)
	private String productMake;
	
	@Column(name="productDescribe",nullable=false)
	@Field
	private String productDescribe;
	
	@Column(name="productSpec")
	@Field
	private String productSpec;
	
	@Column(name="lastUpdateTime")
	@Field(analyze=Analyze.NO)
	@FieldBridge(impl = DateTimeBridge.class)
	@Type(type="org.joda.time.DateTime")
	private DateTime lastUpdateTime;
	
	@Column(name="retailPrice",nullable=false)
	@Field(analyze=Analyze.NO)
	private Double retailPrice;
	
	@Column(name="reviewScore")
	@Field(analyze=Analyze.NO)
	private Double reviewScore = 3.0;
	
	@OneToMany(mappedBy="product",cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	private List<ImageLink> imageLinks = new ArrayList<ImageLink>();
	
	@ManyToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinTable(name = "product_tag",
	joinColumns={@JoinColumn(name="product_id",referencedColumnName="id",nullable=false)},
	inverseJoinColumns={@JoinColumn(name="tag_id",referencedColumnName="id",nullable=false)}
	)
	@IndexedEmbedded
	private Set<Tag> tags = new HashSet<Tag>();
	
	@OneToMany(mappedBy="reviewProduct",cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	private Set<Review> reviews = new HashSet<Review>();
	
	@OneToMany(mappedBy="product",cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	private Set<Stock> stocks = new HashSet<Stock>();

	
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Product(String productEAN, String productName, String productMake,
			String productDescribe, String productSpec, Double retailPrice) {
		super();
		this.productEAN = productEAN;
		this.productName = productName;
		this.productMake = productMake;
		this.productDescribe = productDescribe;
		this.productSpec = productSpec;
		this.retailPrice = retailPrice;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductMake() {
		return productMake;
	}
	public void setProductMake(String productMake) {
		this.productMake = productMake;
	}
	public String getProductDescribe() {
		return productDescribe;
	}
	public void setProductDescribe(String productDescribe) {
		this.productDescribe = productDescribe;
	}
	public String getProductSpec() {
		return productSpec;
	}
	public void setProductSpec(String productSpec) {
		this.productSpec = productSpec;
	}

	public String getProductEAN() {
		return productEAN;
	}

	public void setProductEAN(String productEAN) {
		this.productEAN = productEAN;
	}

	public Double getRetailPrice() {
		return retailPrice;
	}

	public void setRetailPrice(Double retailPrice) {
		this.retailPrice = retailPrice;
	}

	public Set<Stock> getStocks() {
		return stocks;
	}

	public void setStocks(Set<Stock> stocks) {
		this.stocks = stocks;
	}

	public DateTime getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(DateTime lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public String getLastUpdateTimeToString(){
		return this.lastUpdateTime.toString("MMM dd, yyyy hh:mm");
	}

	public List<ImageLink> getImageLinks() {
		return imageLinks;
	}

	public void setImageLinks(List<ImageLink> imageLinks) {
		this.imageLinks = imageLinks;
	}

	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

	public Double getReviewScore() {
		return reviewScore;
	}

	public void setReviewScore(Double reviewScore) {
		this.reviewScore = reviewScore;
	}

	public Set<Review> getReviews() {
		return reviews;
	}

	public void setReviews(Set<Review> reviews) {
		this.reviews = reviews;
	}
	
	public void updateReviewScore(Double newScore){
		int c = this.reviews.size();
		this.reviewScore=(this.reviewScore*c+newScore)/(c+1);
	}

	public List<Tag> getTagByRanking(){
		ArrayList<Tag> a = new ArrayList<Tag>(this.tags);
		Collections.sort(a, new Comparator<Tag>() {

			@Override
			public int compare(Tag o1, Tag o2) {
				// TODO Auto-generated method stub
				return o1.getRanking()-o2.getRanking();
			}
		});
		return a;
	}
	
}
