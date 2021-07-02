package com.codingdojo.bookSelling.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.codingdojo.bookSelling.models.Book;
import com.codingdojo.bookSelling.repositories.BookRepository;

@Service
public class BookService {
	private final BookRepository bookRepository;
	
	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}
	
	 // returns all the books
    public List<Book> allBooks() {
        return bookRepository.findAll();
    }
    // creates a book
    public Book createBook(Book b) {
        return bookRepository.save(b);
    }
    // retrieves a book
    public Book findBook(Long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if(optionalBook.isPresent()) {
            return optionalBook.get();
        } else {
            return null;
        }
    }
    
    public Book updateBook(Long id, String genre, double price, String author, String title, String desc, String language) {
    	Book b = this.findBook(id);
    	
    	b.setId(id);
    	b.setGenre(genre);
    	b.setPrice(price);
    	b.setAuthor(author);
    	b.setTitle(title);
    	b.setDescription(desc);
    	b.setLanguage(language);
    	
    	return bookRepository.save(b);
    }

}
