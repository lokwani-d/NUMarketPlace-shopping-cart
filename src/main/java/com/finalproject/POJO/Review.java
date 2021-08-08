package com.finalproject.POJO;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.google.gson.annotations.Expose;

@Entity
@Table(name="reviews", uniqueConstraints=
@UniqueConstraint(columnNames ={"user_id", "product_id"}) )
public class Review {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@ManyToOne(cascade = {CascadeType.MERGE})
	@JoinColumn(name="product_id")
	@LazyCollection(LazyCollectionOption.FALSE)
	private Product product;
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="user_id")
	@LazyCollection(LazyCollectionOption.FALSE)
	private User user;
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="seller_id")
	@LazyCollection(LazyCollectionOption.FALSE)
	private Seller seller;
	@Expose
	private String text;
	@Expose
	private Timestamp addedAt;
	@Expose
	private String username;
	public Review() {
		// TODO Auto-generated constructor stub
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Seller getSeller() {
		return seller;
	}
	public void setSeller(Seller seller) {
		this.seller = seller;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Timestamp getAddedAt() {
		return addedAt;
	}
	public void setAddedAt(Timestamp addedAt) {
		this.addedAt = addedAt;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

}
