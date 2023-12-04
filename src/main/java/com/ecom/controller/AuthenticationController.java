package com.ecom.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.dto.AuthenticationDTO;
import com.ecom.dto.AuthenticationResponse;
import com.ecom.service.jwt.UserDetailsServiceImpl;
import com.ecom.util.JwtUtil;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class AuthenticationController 
{

	 @Autowired
	    private AuthenticationManager authenticationManager;
	 
	 @Autowired
	    private UserDetailsServiceImpl userDetailsService;
	 
	   @Autowired
	    private JwtUtil jwtUtil;


	    @PostMapping("/authenticate")
	    public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationDTO authenticationDTO, HttpServletResponse response) throws BadCredentialsException, DisabledException, UsernameNotFoundException, IOException, java.io.IOException {
	        try {
	            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationDTO.getEmail(), authenticationDTO.getPassword()));
	        } catch (BadCredentialsException e) {
	            throw new BadCredentialsException("Incorrect username or password!");
	        } catch (DisabledException disabledException) {
	            response.sendError(HttpServletResponse.SC_NOT_FOUND, "User is not activated");
	            return null;
	        }

	        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationDTO.getEmail());

	        final String jwt = jwtUtil.generateToken(userDetails.getUsername());

	        return new AuthenticationResponse(jwt);

	    }

}


	


