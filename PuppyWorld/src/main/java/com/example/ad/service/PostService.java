package com.example.ad.service;

import java.util.ArrayList;

import com.example.ad.domain.Post;

public interface PostService {
	
	public void savePostService(Post post);
	public ArrayList<Post> findAllPosts();
	public Post findPostById (int Id);
	public void deletePostById (int Id);

}
