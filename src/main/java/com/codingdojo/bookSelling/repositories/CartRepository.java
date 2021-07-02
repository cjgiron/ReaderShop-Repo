package com.codingdojo.bookSelling.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.codingdojo.bookSelling.models.Book;
import com.codingdojo.bookSelling.models.Cart;

public interface CartRepository extends CrudRepository<Cart, Long>{
	

}