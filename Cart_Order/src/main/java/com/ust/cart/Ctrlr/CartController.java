package com.ust.cart.Ctrlr;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ust.cart.Model.Cart;
import com.ust.cart.Repo.CartRepo;
import com.ust.cart.VO.Order;
import com.ust.cart.VO.Product;
//import com.ust.cart.VO.RestTemplateVO;

@RestController
@RequestMapping("/cart")
@CrossOrigin(origins = "http://localhost:4200/")
//@CrossOrigin("*")
public class CartController {
    private final CartRepo cartItemRepository;
    
    @Autowired
    private RestTemplate template;

    @Autowired
    public CartController(CartRepo cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addToCart(@RequestBody Cart cartItem) {
        cartItemRepository.save(cartItem);
        return ResponseEntity.ok("Item added to cart successfully");
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<String> removeFromCart(@PathVariable long id) {
        cartItemRepository.deleteById(id);
        return ResponseEntity.ok("Item removed from cart successfully");
    }

    @GetMapping("/items")
    public ResponseEntity<List<Cart>> getCartItems() {
        List<Cart> cartItems = cartItemRepository.findAll();
        return ResponseEntity.ok(cartItems);
    }
    
    @GetMapping("/ProductInCart/{id}")
	public Product AddProducttoCart(@PathVariable long id) {
		String url="http://localhost:6000/products/get/{id}";
		ResponseEntity<Product> response =template.getForEntity(url, Product.class,id);
		return response.getBody();
    }
    
    
    @PostMapping("/ordernow")
    public Order placeOrder(@RequestBody Order o) {
    	String url="http://localhost:6001/order/placeorder";
		ResponseEntity<Order> response= template.postForEntity(url, o, Order.class);
		return response.getBody();
    }
    
    
    
}
