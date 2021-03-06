package com.ashish.org.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="categorytable")
public class Category {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="categoryid", unique = true, nullable = false)
    private long categoryId;
	
	@Column(name="title",unique = true, nullable = false)
    private String title;
    
	@OneToMany(fetch=FetchType.EAGER,mappedBy="category",targetEntity=Product.class,
			cascade = CascadeType.ALL)
	private Set<Product> products = new HashSet<Product>();

    public Category(String title) {
        this.title = title;
        this.products = new HashSet<Product>();
    }

    Category() {
    }

    

    public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	public void addProduct(Product product) {
        getProducts().add(product);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

    
}