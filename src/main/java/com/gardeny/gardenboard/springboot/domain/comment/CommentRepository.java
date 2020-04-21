package com.gardeny.gardenboard.springboot.domain.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("SELECT c FROM Comment c WHERE c.post.id = :post_id and c.parentComment = null")
    List<Comment> findComments(@Param("post_id") Long post_id);
}
