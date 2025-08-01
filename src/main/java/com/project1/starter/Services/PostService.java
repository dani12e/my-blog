package com.project1.starter.Services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project1.starter.Models.Post;
import com.project1.starter.Repository.PostRepository;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public Optional<Post> getbyid(Long id){
        return postRepository.findById(id);
    }

    public List<Post> getall(){
        return postRepository.findAll();
    }

    public void delete(Post post){
        postRepository.delete(post);
    }

    public Post save(Post post){
        if (post.getId() == null){
            post.setCreatedAt(LocalDateTime.now());
        }
        post.setUpdatedAt(LocalDateTime.now());
        return postRepository.save(post);
    }
}
