package com.cloud.spring.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cloud.spring.user.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long>{

}
