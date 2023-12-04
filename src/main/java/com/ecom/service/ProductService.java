package com.ecom.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import com.ecom.entity.Cart;
import com.ecom.entity.Product;
import com.ecom.entity.User;
import com.ecom.filter.JwtRequestFilter;
import com.ecom.repository.CartRepository;
import com.ecom.repository.ProductRepository;
import com.ecom.repository.UserRepository;

@Service
public class ProductService 
{
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CartRepository cartRepository;
	
	public Product addNewProduct(Product product) 
	{
		return productRepository.save(product);		
	}
	
	public List<Product> getAllProducts(int pageNumber, String searchKey)
	{
		Pageable pageable = PageRequest.of(pageNumber, 8);
		
		if(searchKey.equals("")) 
		{
			return (List<Product>) productRepository.findAll(pageable);
		}else
		{
			return (List<Product>)productRepository.findByProductNameContainingIgnoreCaseOrProductDescriptionContainingIgnoreCase(searchKey, searchKey, pageable);
		}
		
	}
	
	public void deleteProductDetails(Integer productId) 
	{
		productRepository.deleteById(productId);
	}
	
     public Product getProductDetailsById(Integer productId) 
     {
		
		return productRepository.findById(productId).get();
     }
     
     public List<Product> getProductDetails(boolean isSingeProductCheckout, Integer productId) 
     {
    		
 		if(isSingeProductCheckout && productId != 0) {
 			List<Product> list= new ArrayList<>();
 			Product product = productRepository.findById(productId).get();
 			list.add(product);
 			return list;
 		}else {
 		
 			String username = JwtRequestFilter.ALREADY_FILTERED_SUFFIX;
 			User user = userRepository.findById(username).get();
 			List<Cart>  carts= cartRepository.findByUser(user);
 			
 			return carts.stream().map(x -> x.getProduct()).collect(Collectors.toList());
 			
 		}
 		
    }

}
