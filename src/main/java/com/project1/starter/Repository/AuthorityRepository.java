package com.project1.starter.Repository;

import org.springframework.stereotype.Repository;


import com.project1.starter.Models.Authority;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
     Optional<Authority> findByName(String name);
}
