package com.project1.starter.Services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.project1.starter.Models.Post;
import com.project1.starter.Repository.PostRepository;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public Optional<Post> getbyid(Long id) {
        return postRepository.findById(id);
    }

    public List<Post> findall() {
        return postRepository.findAll();
    }

    public Page<Post> findAll(int offset, int pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(offset, pageSize, Sort.by(Sort.Direction.ASC, sortBy));
        if (sortBy != null && !sortBy.isEmpty()) {
            pageable = PageRequest.of(offset, pageSize, Sort.by(sortBy));
        } else {
            pageable = PageRequest.of(offset, pageSize);
        }

        return postRepository.findAll(pageable);
    }

    public void delete(Post post) {
        postRepository.delete(post);
    }

    public Post save(Post post) {
        if (post.getId() == null) {
            post.setCreatedAt(LocalDateTime.now());
        }
        post.setUpdatedAt(LocalDateTime.now());
        return postRepository.save(post);
    }
}
