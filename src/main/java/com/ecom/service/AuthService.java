package com.ecom.service;

import com.ecom.dto.SignUpDTO;
import com.ecom.dto.UserDTO;

public interface AuthService 
{
  UserDTO createUser(SignUpDTO signupDTO);
}
