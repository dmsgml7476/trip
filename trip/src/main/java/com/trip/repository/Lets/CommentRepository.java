package com.trip.repository.Lets;

import java.util.List;

import javax.xml.stream.events.Comment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long>{
	// 특정 스토리에 달린 댓글 수를 반환
	int countByStoryId(Long storyId);

	// 특정 스토리에 달린 댓글 목록 조회
	List<Comment> findByStoryId(Long storyId);

	// (선택) 사용자별 댓글 조회
	List<Comment> findByUserId(Long userId);
}
