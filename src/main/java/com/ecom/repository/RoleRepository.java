package com.ecom.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

	Role save(Role role);
	 //boolean existsByRoleName(String roleName);

	Optional<Role> findByRoleName(String string);
	

}
