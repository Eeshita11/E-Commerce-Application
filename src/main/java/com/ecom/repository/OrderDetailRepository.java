package com.ecom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.entity.OrderDetails;
import com.ecom.entity.User;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetails, Integer>{

	List<OrderDetails> findByUser(User user);

}
