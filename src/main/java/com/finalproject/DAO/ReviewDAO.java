package com.finalproject.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.finalproject.POJO.Product;
import com.finalproject.POJO.Review;

public class ReviewDAO {
	private static EntityManagerFactory entityManagerFactory= Persistence.createEntityManagerFactory("orm");
	   private EntityManager entityManager= null;
	   public EntityManager getEntityManager() {
		   if(entityManager==null||!entityManager.isOpen()) {
			   entityManager=entityManagerFactory.createEntityManager();
		   }
		   return entityManager;
	   }
	   public void beginTransaction() {
		   getEntityManager().getTransaction().begin();;
	   }
	   public void commit() {
		   getEntityManager().getTransaction().commit();
	   }
	   public void rollback() {
		   getEntityManager().getTransaction().rollback();
	   }
	   public void close() {
		   getEntityManager().close();
	   }
	public ReviewDAO() {
		// TODO Auto-generated constructor stub
	}
	public boolean addReview(Review review) {
		try {
			beginTransaction();
			getEntityManager().persist(review);
			commit();
			return true;
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			rollback();
			return false;
		}
		finally {
			close();
		}
	}
	public List<Review> getReviewsByProduct(int id,int page){
		try {
			 beginTransaction();
			 Product product= getEntityManager().find(Product.class, id);
			  CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
			   CriteriaQuery<Review> criteria= builder.createQuery(Review.class);
			   Root<Review> reviewRoot= criteria.from(Review.class);
			   criteria.select(reviewRoot);
			   criteria.distinct(true);
			   criteria.where(builder.equal(reviewRoot.get("product"), product));
			   criteria.orderBy(builder.desc(reviewRoot.get("addedAt")));
//			   criteria
			   TypedQuery<Review> query= getEntityManager().createQuery(criteria);
			   query.setFirstResult((page-1)*5);
			   query.setMaxResults(5);
			   List<Review> reviews= query.getResultList();
			   commit();
			   return reviews;
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			rollback();
			return null;
		}
		finally {
			close();
		}
	}
}
