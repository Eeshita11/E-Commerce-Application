package com.ecom.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class OrderDetails 
{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer orderId;
	private String orderName;
    private String orderStatus;
	private Double orderAmount;
	
	@OneToOne
	private Product product;
	
	@OneToOne
	private User user;

	public OrderDetails(Integer orderId, String orderName, String orderStatus, Double orderAmount, Product product,
			User user) {
		super();
		this.orderId = orderId;
		this.orderName = orderName;
		this.orderStatus = orderStatus;
		this.orderAmount = orderAmount;
		this.product = product;
		this.user = user;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Double getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(Double orderAmount) {
		this.orderAmount = orderAmount;
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

	public OrderDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
	
	
	