package com.trip.repository.Member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trip.entity.Member.CustomerServiceAnswerEntity;

@Repository
public interface CustomerServiceAnswerRepository extends JpaRepository<CustomerServiceAnswerEntity, Long>{

}
