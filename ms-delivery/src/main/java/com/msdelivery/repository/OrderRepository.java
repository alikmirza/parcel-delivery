package com.msdelivery.repository;

import com.msdelivery.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<Order> getByIdAndCustomerUsername(Long id, String customerUsername);
    List<Order> getAllByCustomerUsername(String username);
}
