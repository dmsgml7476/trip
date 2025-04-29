package com.trip.repository.Lets;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trip.entity.Lets.CommentEntity;

public interface CommentRepository extends JpaRepository<CommentEntity, Long>{

}
