package com.gardeny.gardenboard.springboot.domain.contents;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

//    @Query("SELECT p FROM Post p WHERE p.isRemoved = false ORDER BY p.id DESC")
    List<Post> findAllByIsRemovedFalseOrderByIdDesc();

    List<Post> findByUser_idAndIsRemovedFalseOrderByCreatedDesc(@Param("user_id") Long user_id);
}
