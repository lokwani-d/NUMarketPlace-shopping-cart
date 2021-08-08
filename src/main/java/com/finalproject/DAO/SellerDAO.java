package com.finalproject.DAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.finalproject.POJO.Order;
import com.finalproject.POJO.Person;
import com.finalproject.POJO.Product;
import com.finalproject.POJO.Review;
import com.finalproject.POJO.Seller;
import com.finalproject.POJO.User;

public class SellerDAO {

	public SellerDAO() {
		// TODO Auto-generated constructor stub
	}
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
	   public boolean addSeller(Seller seller) {
		   try {
			   beginTransaction();
			   getEntityManager().persist(seller);
			   commit();
			   return true;
		   }
		   catch(Exception e) {
			   e.printStackTrace();
			   rollback();
			   return false;
		   }
		   finally {
			   close();
		   }
	   }
	   public Seller getSellerByEmail(String email) {
		   Seller seller=null;
		   try {
			   beginTransaction();
			   CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
			   CriteriaQuery<Seller> criteria= builder.createQuery(Seller.class);
			   Root<Seller> sellerRoot= criteria.from(Seller.class);
			   criteria.select(sellerRoot);
			   criteria.where(builder.equal(sellerRoot.get("email"), email));
			   seller= getEntityManager().createQuery(criteria).getSingleResult();
			  commit();
			  return seller;
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
	   public Seller getSellerById(int id) {
		   Seller seller=null;
		   try {
			   beginTransaction();
			   CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
			   CriteriaQuery<Seller> criteria= builder.createQuery(Seller.class);
			   Root<Seller> sellerRoot= criteria.from(Seller.class);
			   criteria.select(sellerRoot);
			   criteria.where(builder.equal(sellerRoot.get("id"), id));
			   seller= getEntityManager().createQuery(criteria).getSingleResult();
			  commit();
			  return seller;
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
	   public boolean addOrderToSeller(Order order,Seller seller) {
		   try {
			   beginTransaction();
			   seller.getOrders().add(order);
			   getEntityManager().merge(seller);
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
	   public boolean addProductToSeller(Product product,Seller seller) {
		   try {
			   beginTransaction();
			   seller.getProducts().add(product);
			   getEntityManager().merge(seller);
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
	   public boolean deleteSeller(Seller person) {
		   try {
			   beginTransaction();
			   Seller seller=getEntityManager().find(Seller.class, person.getId());
			   if(seller==null) {
				   return false;
			   }
			   for(Order order:seller.getOrders()) {
				   Seller temp=null;
				   for(Seller temp2:order.getSellers()) {
					   if(temp2.getId()==seller.getId()) {
						   temp=temp2;
					   }
				   }
				   order.getSellers().remove(temp);
				   getEntityManager().merge(order);
			   }
			   for(Product prod:seller.getProducts()) {
				   prod.setSeller(null);
				   for(Review review :prod.getReviews()) {
						review.setProduct(null);
						getEntityManager().remove(getEntityManager().merge(review));
					}
//				   getEntityManager().remove(getEntityManager().merge(prod));
				   getEntityManager().remove(prod);
			   }
//			   seller.getReviews().clear();
//			   seller.getProducts().clear();
//			   seller.getOrders().clear();
			   getEntityManager().remove(seller);
//			   System.out.println(seller.getOrders().contains(seller));
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
}
