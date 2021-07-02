package com.codingdojo.bookSelling.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="books")
public class Book {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotEmpty(message="Title is required!")
	@Size(min=2, max=255, message="Title must be between 2 and 255 characters")
	private String title;
	
	@NotEmpty(message="Genre is required!")
    private String genre;
	
	@NotNull
    @Size(min = 5, max = 200)
    private String description;
	
	@NotNull
    @Size(min = 5, max = 200)
    private String author;
	
	@NotNull
    @Size(min = 3, max = 40)
    private String language;
	
	@Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;
    
    
    @ManyToMany
    @JoinTable(
        name = "carts_books", 
        joinColumns = @JoinColumn(name = "book_id"), 
        inverseJoinColumns = @JoinColumn(name = "cart_id")
    )
    private List<Cart> carts;
    
    
    private double price;

 
 
	

	public Book(
			@NotEmpty(message = "Title is required!") @Size(min = 2, max = 255, message = "Title must be between 2 and 255 characters") String title,
			@NotEmpty(message = "Genre is required!") String genre,
			@NotNull @Size(min = 5, max = 200) String description, @NotNull @Size(min = 5, max = 200) String author,
			@NotNull @Size(min = 3, max = 40) String language, Date createdAt, Date updatedAt, User user,
			double price) {
		super();
		this.title = title;
		this.genre = genre;
		this.description = description;
		this.author = author;
		this.language = language;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.price = price;
	}


	public Book () {
    	
    }



	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}





	public double getPrice() {
		return price;
	}



	public void setPrice(double price) {
		this.price = price;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
    
	@PrePersist
    protected void onCreate() {
    	this.createdAt = new Date();
    }
    
    @PreUpdate
    protected void onUpdate() {
    	this.updatedAt = new Date();
    }
    
}
