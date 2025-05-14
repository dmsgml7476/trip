package com.trip.repository.Member;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trip.entity.Member.CustomerServiceEntity;

@Repository
public interface CustomerServiceRepository extends JpaRepository<CustomerServiceEntity, Long>{
	
	List<CustomerServiceEntity> findAllByOrderByQuestionTimeDesc();

}
