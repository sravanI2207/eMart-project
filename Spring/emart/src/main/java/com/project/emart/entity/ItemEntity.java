package com.project.emart.entity;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "item_table")

public class ItemEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "item_id")
	Integer id;

	@Column(name = "item_name")
	String name;

	@Column(name = "item_image")
	String image;

	@Column(name = "item_price")
	Integer price;

	@Column(name = "item_stock")
	Integer stock;

	@Column(name = "item_description")
	String description;

	@Column(name = "item_remarks")
	String remarks;

	@ManyToOne
	@JoinColumn(name = "seller_id")
	SellerSignupEntity seller;

	@ManyToOne
	@JoinColumn(name = "subcategory_id")
	SubCategoryEntity subcategory;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public SellerSignupEntity getSeller() {
		return seller;
	}

	public void setSeller(SellerSignupEntity seller) {
		this.seller = seller;
	}

	public SubCategoryEntity getSubcategory() {
		return subcategory;
	}

	public void setSubcategory(SubCategoryEntity subcategory) {
		this.subcategory = subcategory;
	}

	public ItemEntity(Integer id, String name, String image, Integer price, Integer stock, String description,
			String remarks, SellerSignupEntity seller, SubCategoryEntity subcategory) {
		super();
		this.id = id;
		this.name = name;
		this.image = image;
		this.price = price;
		this.stock = stock;
		this.description = description;
		this.remarks = remarks;
		this.seller = seller;
		this.subcategory = subcategory;
	}

	public ItemEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "ItemEntity [id=" + id + ", name=" + name + ", image=" + image + ", price=" + price + ", stock=" + stock
				+ ", description=" + description + ", remarks=" + remarks + ", seller=" + seller + ", subcategory="
				+ subcategory + "]";
	}

}
