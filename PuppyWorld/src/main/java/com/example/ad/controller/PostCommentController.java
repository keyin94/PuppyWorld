package com.example.ad.controller;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.ad.domain.Post;
import com.example.ad.domain.PostComment;
import com.example.ad.domain.User;
import com.example.ad.service.PostCommentService;
import com.example.ad.service.PostCommentServiceImplementation;
import com.example.ad.service.PostService;
import com.example.ad.service.PostServiceImplementation;
import com.example.ad.service.UserService;
import com.example.ad.service.UserServiceImplementation;

@Controller
public class PostCommentController {
	
	@Autowired
	private PostService poservice;
	
	@Autowired
	private PostCommentService pcservice;
	
	@Autowired
	public void setPCService(PostCommentServiceImplementation pcServiceImpl) {
		this.pcservice = pcServiceImpl;
	}
	
	@Autowired
	public void setPoService(PostServiceImplementation poServiceImpl) {
		this.poservice = poServiceImpl;
	}
	
	@Autowired
	private UserService uservice;
	
	@Autowired
	public void setUService(UserServiceImplementation uServiceImpl) {
		this.uservice = uServiceImpl;
	}
	

	
	@RequestMapping(value="/postCommentSave",method=RequestMethod.POST)
	public String savePost(@ModelAttribute("postcomment")PostComment postcomment, Model model, Errors errors, BindingResult bindingResult, HttpServletRequest request)  {
		
		Post linkedpost = poservice.findPostById(postcomment.getPost().getPostId());
		
		User user = uservice.findUserByUserName(request.getRemoteUser());
		postcomment.setUser(user);
		postcomment.setPost(linkedpost);
		postcomment.setCommentDateTime(LocalDateTime.now());

		int postid = linkedpost.getPostId();
		model.addAttribute("postcomment",postcomment);
		
		if(postcomment.getCommentMessage().isEmpty()) {
			errors.rejectValue("postCommentMessage", "null", "Must be filled");
		}
		
		if (bindingResult.hasErrors()) {
			return "forward:/viewPostDetails/"+postid;
		}
		
		pcservice.savePostComment(postcomment);
		
		return "forward:/viewPostDetails/"+postid;

	}
	

	
	
	
	@RequestMapping(value = "/postCommentDelete/{commentId}")
	public String deletePostComment(@PathVariable("commentId") int commentId, Model model, HttpServletRequest request) {
		PostComment currentcomment = pcservice.findPostCommentById(commentId);
		Post post = currentcomment.getPost();
		model.addAttribute("post", post);
		int postid = post.getPostId();
		
		pcservice.deletePostCommentById(commentId);
		return "forward:/viewPostDetails/"+postid;
	}
	
	
	@RequestMapping(value = "/postCommentEdit/{commentId}")
	public String editPostComment(@PathVariable("commentId") int commentId, Model model, HttpServletRequest request) {
		PostComment currentcomment = pcservice.findPostCommentById(commentId);
		Post post = currentcomment.getPost();
		model.addAttribute("post", post);
		model.addAttribute("postcomment", currentcomment);
		User user = uservice.findUserByUserName(request.getRemoteUser());
		model.addAttribute("user", user);
		List<PostComment> postcommentlist = pcservice.findPostCommentsbyPostId(post.getPostId());
		model.addAttribute("postcommentlist", postcommentlist);
		
		String encodedString = Base64.getEncoder().encodeToString(post.getPostImage());
		model.addAttribute("image", encodedString);
					
		return "postCommentEdit";
	}
	
	@RequestMapping(value="/postCommentEditSave",method=RequestMethod.POST)
	public String saveeditedPost(@ModelAttribute("postcomment")PostComment postcomment, Model model, Errors errors, BindingResult bindingResult, HttpServletRequest request)  {
		
		Post linkedpost = poservice.findPostById(postcomment.getPost().getPostId());
		int postid = linkedpost.getPostId();
		
		if(postcomment.getCommentMessage().isEmpty()) {
			errors.rejectValue("postCommentMessage", "null", "Must be filled");
		}
		
		if (bindingResult.hasErrors()) {
			return "forward:/viewPostDetails/"+postid;
		}
		
		pcservice.savePostComment(postcomment);
		
		return "forward:/viewPostDetails/"+postid;

	}
	
}