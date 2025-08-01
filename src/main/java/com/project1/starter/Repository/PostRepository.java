package com.project1.starter.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project1.starter.Models.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{
    
}
