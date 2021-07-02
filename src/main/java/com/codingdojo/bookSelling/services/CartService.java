package com.codingdojo.bookSelling.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.codingdojo.bookSelling.models.Book;
import com.codingdojo.bookSelling.models.Cart;
import com.codingdojo.bookSelling.repositories.CartRepository;


@Service
public class CartService {

	private final CartRepository cartRepository;
	
	public CartService(CartRepository cartRepository) {
		this.cartRepository = cartRepository;
	}
	
	
	public Cart createNewCart (Cart cart) {
		return cartRepository.save(cart);
	}
	
	
	public Cart addBookToCart(Book b, Cart c) {
		List<Book> books = c.getBooks();
		books.add(b);
		return cartRepository.save(c);
	}
	
	public Cart findCart(Long id) {
        Optional<Cart> optionalCart = cartRepository.findById(id);
        if(optionalCart.isPresent()) {
            return optionalCart.get();
        } else {
            return null;
        }
    }
	
	
}