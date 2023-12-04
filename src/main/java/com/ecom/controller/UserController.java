package com.ecom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.entity.User;
import com.ecom.service.UserService;

import jakarta.annotation.PostConstruct;

@RestController
public class UserController 
{

	@Autowired
	private UserService userService;

    
   /* @PostConstruct
    public void initRoleAndUser() 
    {
        userService.initRoleAndUser();
    }
    */
	
	@PostConstruct
	public void initRoleAndUser() 
	{
	    try 
	    {
	        userService.initRoleAndUser();
	    } 
	    catch (Exception e) 
	    {
	        
	        e.printStackTrace();
	    }
	}
	
	 @GetMapping({"/forAdmin"})
	    
	    public String forAdmin()
	    {
	        return "This URL is only accessible to the admin";
	    }

	    @GetMapping({"/forUser"})
	    
	    public String forUser()
	    {
	        return "This URL is only accessible to the user";
	    }

       @PostMapping({"/registerNewUser"})
    
    public User registerNewUser(@RequestBody User user) 
    {
        return userService.registerNewUser(user);
    }
}
