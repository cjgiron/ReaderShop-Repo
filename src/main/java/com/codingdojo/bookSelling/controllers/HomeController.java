package com.codingdojo.bookSelling.controllers;
    
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.codingdojo.bookSelling.models.Book;
import com.codingdojo.bookSelling.models.Cart;
import com.codingdojo.bookSelling.models.LoginUser;
import com.codingdojo.bookSelling.models.User;
import com.codingdojo.bookSelling.services.BookService;
import com.codingdojo.bookSelling.services.CartService;
import com.codingdojo.bookSelling.services.UserService;
    
@Controller
public class HomeController {
    
    @Autowired
    private UserService userServ;
    
    @Autowired
    private BookService bookServ;
    
    @Autowired
    private CartService cartServ;
    
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("newUser", new User());
        model.addAttribute("newLogin", new LoginUser());
        return "index.jsp";
    }
    
    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("newUser") User newUser, 
            BindingResult result, Model model, HttpSession session) {
        userServ.register(newUser, result);
        if(result.hasErrors()) {
            model.addAttribute("newLogin", new LoginUser());
            return "index.jsp";
        }
        session.setAttribute("user_id", newUser.getId());
        return "redirect:/home";
    }
    
    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, 
            BindingResult result, Model model, HttpSession session) {
        User user = userServ.login(newLogin, result);
        if(result.hasErrors()) {
            model.addAttribute("newUser", new User());
            return "index.jsp";
        }
        session.setAttribute("user_id", user.getId());
        return "redirect:/home";
    }
    
    @RequestMapping("/home")
    public String home(Model model, HttpSession session) {
    	Long id = (Long) session.getAttribute("user_id");
    	User user = userServ.findUserById(id);
    	List<Book> books = bookServ.allBooks();
    	model.addAttribute("user", user);
    	model.addAttribute("books", books);
    	return "homePage.jsp";
    }
    
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
    	session.invalidate();
    	return "redirect:/";
    }
    
    @RequestMapping("/books/new")
    public String createBookPage(@ModelAttribute("book") Book book, HttpSession session, Model model) {
    	return "/newBook.jsp";
    }
    
    @RequestMapping(value="/books", method=RequestMethod.POST)
    public String create(@Valid @ModelAttribute("book") Book book, BindingResult result) {
        if (result.hasErrors()) {
            return "/newBook.jsp";
        } else {
            bookServ.createBook(book);
            return "redirect:/home";
        }
    }
    
    @RequestMapping("/addToCart/{book_id}")
    public String add(@PathVariable("book_id") Long book_id, HttpSession session, Model model) {
    	Book book = bookServ.findBook(book_id);
    	Long id = (Long) session.getAttribute("user_id");
    	User user = userServ.findUserById(id);
    	Cart cart = user.getCart();
    	cartServ.addBookToCart(book, cart);
    	
    	return "redirect:/showCart";
    }
    
    
    @RequestMapping("/showCart")
    public String show(HttpSession session, Model model) {
    	Long id = (Long) session.getAttribute("user_id");
    	User user = userServ.findUserById(id);
    	Cart cart = user.getCart();
    	List<Book> books = cart.getBooks();
    	model.addAttribute("books", books);
    	model.addAttribute("user", user);
    	return "/cart.jsp";
    }

}