package com.finalproject.POJO;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name="sellers")
@PrimaryKeyJoinColumn(name="person_id")
public class Seller extends Person {
	@OneToMany(mappedBy="seller",cascade = {CascadeType.MERGE,CascadeType.REMOVE},orphanRemoval = true)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Product> products;
	@ManyToMany(cascade = {CascadeType.MERGE,CascadeType.REMOVE},mappedBy = "sellers")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Order> orders;
	@OneToMany(mappedBy = "seller",cascade = {CascadeType.MERGE,CascadeType.REMOVE},orphanRemoval = true)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Review> reviews;
	private String type;
	public Seller() {
		// TODO Auto-generated constructor stub
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	public List<Order> getOrders() {
		return orders;
	}
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	public List<Review> getReviews() {
		return reviews;
	}
	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

}
