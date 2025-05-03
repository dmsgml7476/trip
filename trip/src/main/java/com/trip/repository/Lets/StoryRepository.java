package com.trip.repository.Lets;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.stereotype.Repository;

import com.trip.entity.Lets.StoryEntity;
@Repository
public interface StoryRepository extends JpaRepository<StoryEntity,Long> {
	
	// 공개 스토리 중 좋아요 수 많은 순 (비로그인용)
	@Query("""
		    SELECT s FROM StoryEntity s
		    JOIN FETCH s.user
		    LEFT JOIN LikeEntity l ON s = l.story
		    WHERE s.openArea = com.trip.enumType.OpenArea.PUBLIC
		    GROUP BY s
		    ORDER BY COUNT(l) DESC, s.writeAt DESC
		""")
		List<StoryEntity> findTop8PublicStoriesByLikes(Pageable pageable);

    // 특정 유저 ID 리스트의 공개 스토리 중 좋아요 수 많은 순 (로그인용)
    @Query("""
        SELECT s FROM StoryEntity s
        LEFT JOIN LikeEntity l ON s = l.story
        WHERE s.openArea = com.trip.enumType.OpenArea.PUBLIC
          AND s.user.id IN :userIds
        GROUP BY s
        ORDER BY COUNT(l) DESC, s.writeAt DESC
        """)
    List<StoryEntity> findTop8PublicStoriesByUserIdsOrderByLikes(@Param("userIds") List<Long> userIds, Pageable pageable);
}
