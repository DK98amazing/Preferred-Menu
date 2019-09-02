package com.preferrd.menu.jpa.repository;

import com.preferrd.menu.jpa.entity.Customer;
import org.hibernate.annotations.SQLInsert;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Configuration
public interface MyJpaRepository extends JpaRepository<Customer, Integer> {
    @Override
    List<Customer> findAll();

    @Override
    <S extends Customer> S save(S s);

    @Override
    void deleteAll();

    @Override
    long count();
}
