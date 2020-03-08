package com.gardeny.gardenboard.springboot.domain.contents;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
