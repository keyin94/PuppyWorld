package com.example.ad.service;

import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ad.domain.Post;
import com.example.ad.repo.PostRepository;

@Service
@Transactional
public class PostServiceImplementation implements PostService {
	
	@Autowired
	PostRepository ptrepo;

	@Override
	public void savePostService(Post post) {
		// TODO Auto-generated method stub
		ptrepo.save(post);
	}

	@Override
	public ArrayList<Post> findAllPosts() {
		// TODO Auto-generated method stub
		return (ArrayList<Post>) ptrepo.findAll();
	}

	@Override
	public Post findPostById(int Id) {
		// TODO Auto-generated method stub
		ArrayList<Post> pList = findAllPosts();
		Post searchPost = null;
		for (Iterator <Post> iterator = pList.iterator(); iterator.hasNext();) {
			Post post = iterator.next();
			if(post.getPostId() == Id) {
				searchPost = post;
			}
		}
		return searchPost;
	}

	@Override
	public void deletePostById(int Id) {
		// TODO Auto-generated method stub
		Post searchPost = findPostById(Id);
		ptrepo.delete(searchPost);
	}

}
