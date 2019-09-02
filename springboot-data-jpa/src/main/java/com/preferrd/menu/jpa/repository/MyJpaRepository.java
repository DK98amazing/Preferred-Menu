package com.preferrd.menu.jpa.repository;

import com.preferrd.menu.jpa.entity.Customer;
import com.sun.xml.internal.bind.v2.model.core.ID;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Configuration
public interface MyJpaRepository extends JpaRepository<Customer, ID> {
    @Override
    List<Customer> findAll();
}
