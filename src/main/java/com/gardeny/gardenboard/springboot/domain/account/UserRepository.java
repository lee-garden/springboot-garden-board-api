package com.gardeny.gardenboard.springboot.domain.account;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
