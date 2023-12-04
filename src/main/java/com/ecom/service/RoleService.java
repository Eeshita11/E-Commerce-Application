package com.ecom.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.entity.Role;
import com.ecom.repository.RoleRepository;

@Service
public class RoleService 
{
	@Autowired
    private RoleRepository roleRepository;
	
	  public Role createNewRole(Role role) 
	  {
	        return roleRepository.save(role);
	   }

}
