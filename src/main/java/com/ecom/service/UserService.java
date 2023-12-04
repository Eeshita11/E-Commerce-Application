package com.ecom.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecom.entity.Role;
import com.ecom.entity.User;
import com.ecom.repository.RoleRepository;
import com.ecom.repository.UserRepository;



@Service
public class UserService 
{
	   @Autowired
	    private UserRepository userRepository;

	    @Autowired
	    private RoleRepository roleRepository;

	    @Autowired
	    private PasswordEncoder passwordEncoder;
	    
	    
	    public void initRoleAndUser() 
	    {

	        Role adminRole = new Role();
	        adminRole.setRoleName("Admin");
	        adminRole.setRoleDescription("Admin role");
	        roleRepository.save(adminRole);

	        Role userRole = new Role();
	        userRole.setRoleName("User");
	        userRole.setRoleDescription("Default role for newly created record");
	        roleRepository.save(userRole);

	        User adminUser = new User();
	        adminUser.setId(123);
	        adminUser.setPassword(getEncodedPassword("eeshita123"));
	        adminUser.setName("admin");
	        
	       Set<Role> adminRoles = new HashSet<>();
	        adminRoles.add(adminRole);
	        adminUser.setRole(adminRoles);
	        userRepository.save(adminUser);
	    }
	    
	     public User registerNewUser(User user) 
	    {
	        Role role = roleRepository.findById("User").get();
	        Set<Role> userRoles = new HashSet<>();
	        userRoles.add(role);
	        user.setRole(userRoles);
	        user.setPassword(getEncodedPassword(user.getPassword()));

	        return userRepository.save(user);
	    }
	        
        private String getEncodedPassword(String password) 
		{
        	return passwordEncoder.encode(password);
	    }
}
		
