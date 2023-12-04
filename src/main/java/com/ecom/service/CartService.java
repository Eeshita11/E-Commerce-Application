package com.ecom.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.entity.Cart;
import com.ecom.entity.Product;
import com.ecom.entity.User;
import com.ecom.filter.JwtRequestFilter;
import com.ecom.repository.CartRepository;
import com.ecom.repository.ProductRepository;
import com.ecom.repository.UserRepository;

@Service
public class CartService 
{
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	public List<Cart> getCartDetails()
	{
		String username = JwtRequestFilter.ALREADY_FILTERED_SUFFIX;
		User user = userRepository.findById(username).get();
		return cartRepository.findByUser(user);
		
	}
	
    public Cart addToCart(Integer productId) 
    {
		
		Product product = productRepository.findById(productId).get();
		
		String username = JwtRequestFilter.ALREADY_FILTERED_SUFFIX;
		
		User user = null;
		
		if(username != null) {
			user = userRepository.findById(username).get();
			
		}
		
		List<Cart> cartList = cartRepository.findByUser(user);
		List<Cart> filteredList = cartList.stream().filter(x -> x.getProduct().getProductId() == productId).collect(Collectors.toList());
		
		if(filteredList.size() > 0) {
			return null;
		}
		
		
		if(product != null && user != null) 
		{
			Cart cart = new Cart(product, user);
			return cartRepository.save(cart);
		}
		return null;
	}
    
    public void deleteCartItem(Integer cartId) 
    {
		cartRepository.deleteById(cartId);
	}
	
	
}
