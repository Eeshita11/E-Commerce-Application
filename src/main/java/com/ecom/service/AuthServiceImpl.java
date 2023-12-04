package com.ecom.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecom.dto.SignUpDTO;
import com.ecom.dto.UserDTO;
import com.ecom.entity.User;
import com.ecom.repository.UserRepository;

@Service
public class AuthServiceImpl implements AuthService
{
	   @Autowired
	   private UserRepository userRepository;


	@Override
	public UserDTO createUser(SignUpDTO signupDTO) {
		  User user = new User();
	       user.setName(signupDTO.getName());
	       user.setEmail(signupDTO.getEmail());
	       user.setPassword(new BCryptPasswordEncoder().encode(signupDTO.getPassword()));
	       User createdUser = userRepository.save(user);
	       UserDTO userDTO = new UserDTO();
	       userDTO.setId(createdUser.getId());
	       userDTO.setEmail(createdUser.getEmail());
	       userDTO.setName(createdUser.getName());
	       return userDTO;
	   }
	  

	}


