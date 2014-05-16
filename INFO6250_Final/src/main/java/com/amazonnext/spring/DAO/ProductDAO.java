package com.amazonnext.spring.DAO;

import java.util.List;

import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.hibernate.Query;
import org.hibernate.search.FullTextQuery;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.DatabaseRetrievalMethod;
import org.hibernate.search.query.ObjectLookupMethod;
import org.hibernate.search.query.dsl.QueryBuilder;

import com.amazonnext.spring.pojo.Product;
import com.amazonnext.spring.pojo.Review;
import com.amazonnext.spring.pojo.Tag;

public class ProductDAO extends HibernateDAO<Product, Integer> {

	public ProductDAO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public void rebuildIndex() throws InterruptedException{
		FullTextSession fullTextSession = Search.getFullTextSession(getSession());
		fullTextSession.createIndexer().startAndWait();
	}
	
	public List<Product> searchProductByWordOnlyName(String word,String sortFileName,boolean reverse) {
		getSession().beginTransaction();
		FullTextSession fullTextSession = Search
				.getFullTextSession(getSession());
		QueryBuilder qb = fullTextSession.getSearchFactory()
				.buildQueryBuilder().forEntity(Product.class).get();
		org.apache.lucene.search.Query luceneQuery = qb
				.keyword()
				.onFields("productName").matching(word)
				.createQuery();
		FullTextQuery q = fullTextSession.createFullTextQuery(luceneQuery,
				Product.class);
		if(sortFileName.equals("reviewScore")){
			org.apache.lucene.search.Sort sort = new Sort(
				    new SortField("reviewScore", SortField.DOUBLE,reverse));
			q.setSort(sort);
		}else if(sortFileName.equals("retailPrice")){
			org.apache.lucene.search.Sort sort = new Sort(
				    new SortField("retailPrice", SortField.DOUBLE,reverse));
			q.setSort(sort);
		}else if(sortFileName.equals("lastUpdateTime")){
			org.apache.lucene.search.Sort sort = new Sort(
				    new SortField("lastUpdateTime", SortField.STRING,reverse));
			q.setSort(sort);
		}
		q.initializeObjectsWith(
			    ObjectLookupMethod.SECOND_LEVEL_CACHE,
			    DatabaseRetrievalMethod.QUERY
			);
		
		List<Product> result = q.list();
		getSession().getTransaction().commit();
		return result;
	}
	
	public List<Product> searchProductAllName(String sortFileName,boolean reverse) {
		getSession().beginTransaction();
		FullTextSession fullTextSession = Search
				.getFullTextSession(getSession());
//		
		QueryBuilder qb = fullTextSession.getSearchFactory()
				.buildQueryBuilder().forEntity(Product.class).get();
		org.apache.lucene.search.Query luceneQuery = qb
				.keyword().wildcard()
				.onField("productName").matching("*")
				.createQuery();
		FullTextQuery q = fullTextSession.createFullTextQuery(luceneQuery,
				Product.class);
		if(sortFileName.equals("reviewScore")){
			org.apache.lucene.search.Sort sort = new Sort(
				    new SortField("reviewScore", SortField.DOUBLE,reverse));
			q.setSort(sort);
		}else if(sortFileName.equals("retailPrice")){
			org.apache.lucene.search.Sort sort = new Sort(
				    new SortField("retailPrice", SortField.DOUBLE,reverse));
			q.setSort(sort);
		}else if(sortFileName.equals("lastUpdateTime")){
			org.apache.lucene.search.Sort sort = new Sort(
				    new SortField("lastUpdateTime", SortField.STRING,reverse));
			q.setSort(sort);
		}
		q.initializeObjectsWith(
			    ObjectLookupMethod.SECOND_LEVEL_CACHE,
			    DatabaseRetrievalMethod.QUERY
			);
		List<Product> result = q.list();
		getSession().getTransaction().commit();
		return result;
	}
	
	public List<Product> searchProductByWordOnlyTag(String tag,String sortFileName,boolean reverse) {
		getSession().beginTransaction();
		FullTextSession fullTextSession = Search
				.getFullTextSession(getSession());
		QueryBuilder qb = fullTextSession.getSearchFactory()
				.buildQueryBuilder().forEntity(Product.class).get();
		org.apache.lucene.search.Query luceneQuery = qb
				.keyword()
				.onFields("tags.tagContent").matching(tag)
				.createQuery();
		FullTextQuery q = fullTextSession.createFullTextQuery(luceneQuery,
				Product.class);
		if(sortFileName.equals("reviewScore")){
			org.apache.lucene.search.Sort sort = new Sort(
				    new SortField("reviewScore", SortField.DOUBLE,reverse));
			q.setSort(sort);
		}else if(sortFileName.equals("retailPrice")){
			org.apache.lucene.search.Sort sort = new Sort(
				    new SortField("retailPrice", SortField.DOUBLE,reverse));
			q.setSort(sort);
		}else if(sortFileName.equals("lastUpdateTime")){
			org.apache.lucene.search.Sort sort = new Sort(
				    new SortField("lastUpdateTime", SortField.STRING,reverse));
			q.setSort(sort);
		}
		q.initializeObjectsWith(
			    ObjectLookupMethod.SECOND_LEVEL_CACHE,
			    DatabaseRetrievalMethod.QUERY
			);
		List<Product> result = q.list();
		getSession().getTransaction().commit();
		return result;
	}

	public List<Product> searchProductByWord(String word,String sortFileName,boolean reverse) {
		getSession().beginTransaction();
		FullTextSession fullTextSession = Search
				.getFullTextSession(getSession());
		QueryBuilder qb = fullTextSession.getSearchFactory()
				.buildQueryBuilder().forEntity(Product.class).get();
		org.apache.lucene.search.Query luceneQuery = qb
				.keyword()
				.onField("productName").boostedTo(5).andField("productMake")
				.andField("productDescribe").andField("productSpec").andField("productEAN")
				.andField("tags.tagContent").boostedTo(3).matching(word)
				.createQuery();
		FullTextQuery q = fullTextSession.createFullTextQuery(luceneQuery,
				Product.class);
		if(sortFileName.equals("reviewScore")){
			org.apache.lucene.search.Sort sort = new Sort(
				    new SortField("reviewScore", SortField.DOUBLE,reverse));
			q.setSort(sort);
		}else if(sortFileName.equals("retailPrice")){
			org.apache.lucene.search.Sort sort = new Sort(
				    new SortField("retailPrice", SortField.DOUBLE,reverse));
			q.setSort(sort);
		}else if(sortFileName.equals("lastUpdateTime")){
			org.apache.lucene.search.Sort sort = new Sort(
				    new SortField("lastUpdateTime", SortField.STRING,reverse));
			q.setSort(sort);
		}
		q.initializeObjectsWith(
			    ObjectLookupMethod.SECOND_LEVEL_CACHE,
			    DatabaseRetrievalMethod.QUERY
			);
		List<Product> result = q.list();
		getSession().getTransaction().commit();
		return result;
	}

	public List<Product> searchProductByWordFuzzy(String word,String sortFileName,boolean reverse) {
		getSession().beginTransaction();
		FullTextSession fullTextSession = Search
				.getFullTextSession(getSession());
		QueryBuilder qb = fullTextSession.getSearchFactory()
				.buildQueryBuilder().forEntity(Product.class).get();
		org.apache.lucene.search.Query luceneQuery = qb
				.keyword()
				.fuzzy()
				.withThreshold(0.5f)
				.withPrefixLength(1)
				.onField("productName").boostedTo(5).andField("productMake")
				.andField("productDescribe").andField("productSpec")
				.andField("tags.tagContent").boostedTo(3).matching(word)
				.createQuery();
		FullTextQuery q = fullTextSession.createFullTextQuery(luceneQuery,
				Product.class);
		if(sortFileName.equals("reviewScore")){
			org.apache.lucene.search.Sort sort = new Sort(
				    new SortField("reviewScore", SortField.DOUBLE,reverse));
			q.setSort(sort);
		}else if(sortFileName.equals("retailPrice")){
			org.apache.lucene.search.Sort sort = new Sort(
				    new SortField("retailPrice", SortField.DOUBLE,reverse));
			q.setSort(sort);
		}else if(sortFileName.equals("lastUpdateTime")){
			org.apache.lucene.search.Sort sort = new Sort(
				    new SortField("lastUpdateTime", SortField.STRING,reverse));
			q.setSort(sort);
		}
		q.initializeObjectsWith(
			    ObjectLookupMethod.SECOND_LEVEL_CACHE,
			    DatabaseRetrievalMethod.QUERY
			);
		List<Product> result = q.list();
//		System.out.println(result);
		getSession().getTransaction().commit();
		return result;
	}

	public List<Product> searchProductByPhrase(String phrase,String sortFileName,boolean reverse) {
		getSession().beginTransaction();
		FullTextSession fullTextSession = Search
				.getFullTextSession(getSession());
		QueryBuilder qb = fullTextSession.getSearchFactory()
				.buildQueryBuilder().forEntity(Product.class).get();
		org.apache.lucene.search.Query luceneQuery = qb.phrase().withSlop(3)
				.onField("productName").boostedTo(5).andField("productMake")
				.andField("productDescribe").andField("productSpec")
				.andField("tags.tagContent").boostedTo(3).sentence(phrase).createQuery();
		FullTextQuery q = fullTextSession.createFullTextQuery(luceneQuery,
				Product.class);
		if(sortFileName.equals("reviewScore")){
			org.apache.lucene.search.Sort sort = new Sort(
				    new SortField("reviewScore", SortField.DOUBLE,reverse));
			q.setSort(sort);
		}else if(sortFileName.equals("retailPrice")){
			org.apache.lucene.search.Sort sort = new Sort(
				    new SortField("retailPrice", SortField.DOUBLE,reverse));
			q.setSort(sort);
		}else if(sortFileName.equals("lastUpdateTime")){
			org.apache.lucene.search.Sort sort = new Sort(
				    new SortField("lastUpdateTime", SortField.STRING,reverse));
			q.setSort(sort);
		}
		q.initializeObjectsWith(
			    ObjectLookupMethod.SECOND_LEVEL_CACHE,
			    DatabaseRetrievalMethod.QUERY
			);
		List<Product> result = q.list();
		getSession().getTransaction().commit();
		return result;
	}
	
	public List<Product> searchProductByWordFuzzyJoinTag(String word,String tag,String sortFileName,boolean reverse) {
		getSession().beginTransaction();
		FullTextSession fullTextSession = Search
				.getFullTextSession(getSession());
		QueryBuilder qb = fullTextSession.getSearchFactory()
				.buildQueryBuilder().forEntity(Product.class).get();
		org.apache.lucene.search.Query luceneQuery = qb.bool().must(qb
				.keyword()
				.onFields("productName").matching(tag).createQuery()).should(qb
						.keyword()
						.fuzzy()
						.withThreshold(0.5f)
						.withPrefixLength(1)
						.onField("productName").boostedTo(5).andField("productMake")
						.andField("productDescribe").andField("productSpec")
						.andField("tags.tagContent").boostedTo(3).matching(word)
						.createQuery()).createQuery();
		FullTextQuery q = fullTextSession.createFullTextQuery(luceneQuery,
				Product.class);
		if(sortFileName.equals("reviewScore")){
			org.apache.lucene.search.Sort sort = new Sort(
				    new SortField("reviewScore", SortField.DOUBLE,reverse));
			q.setSort(sort);
		}else if(sortFileName.equals("retailPrice")){
			org.apache.lucene.search.Sort sort = new Sort(
				    new SortField("retailPrice", SortField.DOUBLE,reverse));
			q.setSort(sort);
		}else if(sortFileName.equals("lastUpdateTime")){
			org.apache.lucene.search.Sort sort = new Sort(
				    new SortField("lastUpdateTime", SortField.STRING,reverse));
			q.setSort(sort);
		}
		q.initializeObjectsWith(
			    ObjectLookupMethod.SECOND_LEVEL_CACHE,
			    DatabaseRetrievalMethod.QUERY
			);
		List<Product> result = q.list();
//		System.err.println(result);
		getSession().getTransaction().commit();
		return result;
	}
	
	public List<Product> searchProductByPriceRange(double minPrice,double maxPrice,String sortFileName,boolean reverse) {
		getSession().beginTransaction();
		FullTextSession fullTextSession = Search
				.getFullTextSession(getSession());
		QueryBuilder qb = fullTextSession.getSearchFactory()
				.buildQueryBuilder().forEntity(Product.class).get();
		org.apache.lucene.search.Query luceneQuery = qb.range().onField("retailPrice").from(minPrice).to(maxPrice).excludeLimit().createQuery();
		FullTextQuery q = fullTextSession.createFullTextQuery(luceneQuery,
				Product.class);
		if(sortFileName.equals("reviewScore")){
			org.apache.lucene.search.Sort sort = new Sort(
				    new SortField("reviewScore", SortField.DOUBLE,reverse));
			q.setSort(sort);
		}else if(sortFileName.equals("retailPrice")){
			org.apache.lucene.search.Sort sort = new Sort(
				    new SortField("retailPrice", SortField.DOUBLE,reverse));
			q.setSort(sort);
		}else if(sortFileName.equals("lastUpdateTime")){
			org.apache.lucene.search.Sort sort = new Sort(
				    new SortField("lastUpdateTime", SortField.STRING,reverse));
			q.setSort(sort);
		}
		q.initializeObjectsWith(
			    ObjectLookupMethod.SECOND_LEVEL_CACHE,
			    DatabaseRetrievalMethod.QUERY
			);
		List<Product> result = q.list();
//		System.out.println(result);
		getSession().getTransaction().commit();
		return result;
	}
	
	
	public boolean checkReviewed(Integer productId,Integer userId){
		getSession().beginTransaction();
		Query q = getSession().createQuery("from Review review where review.reviewProduct.id = :productId and review.reviewUser.id = :userId");
		q.setInteger("productId", productId);
		q.setInteger("userId", userId);
		List<Review> lr = q.list();
		getSession().getTransaction().commit();
		if(lr.isEmpty()){
			return false;
		}else{
			return true;
		}
	}
	
}
