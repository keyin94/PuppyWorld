package com.example.ad.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ad.domain.Post;

public interface PostRepository extends JpaRepository<Post, Integer>{

}
