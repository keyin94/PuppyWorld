
package com.example.ad.controller;


import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
public class PostController {
	@Autowired
	private PostService poservice;
	
	@Autowired
	private PostCommentService pcservice;
	
	@Autowired
	public void setPoService(PostServiceImplementation poServiceImpl) {
		this.poservice = poServiceImpl;
	}
	
	@Autowired
	public void setPCService(PostCommentServiceImplementation pcServiceImpl) {
		this.pcservice = pcServiceImpl;
	}
	
	@Autowired
	private UserService uservice;
	
	@Autowired
	public void setUService(UserServiceImplementation uServiceImpl) {
		this.uservice = uServiceImpl;
	}

	
	@RequestMapping("/postCreate")
	public String showNewPostForm(Model model) {
		Post post = new Post();
		model.addAttribute("post",post);
		return "postCreate";
	}
	
	@RequestMapping(value="/postSave",method=RequestMethod.POST)
	public String savePost(@ModelAttribute("post")Post post, Errors errors, BindingResult bindingResult, @RequestParam("fileImage") MultipartFile multipartFile, HttpServletRequest request) throws IllegalStateException, IOException {
		
		try {
			String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
			System.out.println(fileName);
			File convFile = new File(System.getProperty("java.io.tmpdir") + "/" + fileName);
			multipartFile.transferTo(convFile);
			byte[] fileContent = FileUtils.readFileToByteArray(convFile);
			
			post.setPostImage(fileContent);
		}
		catch(IllegalStateException e) {
			System.out.println(e.toString());
		}
		catch(IOException e) {
			System.out.println(e.toString());
		}

		ArrayList<Post> poList = poservice.findAllPosts();
		for (Iterator <Post>iterator = poList.iterator(); iterator.hasNext();) {
			Post post2 =  iterator.next();
			if(post2.getPostTitle().equalsIgnoreCase(post.getPostTitle())) {
				errors.rejectValue("postTitle", "exist", "Post Exist");
				break;
			}
			
		}
		
		if(post.getPostTitle().isEmpty()) {
			errors.rejectValue("postTitle", "null", "Must be filled");
		}
		if(post.getPostMessage().isEmpty()) {
			errors.rejectValue("postMessage", "null", "Must be filled");
		}
		
		if (bindingResult.hasErrors()) {
			return "postCreate";
		}
		
		
		User user = uservice.findUserByUserName(request.getRemoteUser());
		post.setUser(user);
		
		poservice.savePostService(post);
		
		return "redirect:/postList";

	}
	
	@RequestMapping(value="/postEditSave",method=RequestMethod.POST)
	public String saveEditPost(@ModelAttribute("post")Post post, Errors errors, BindingResult bindingResult, @RequestParam("fileImage") MultipartFile multipartFile, HttpServletRequest request) throws IllegalStateException, IOException {
		
		try {
			String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
			System.out.println(fileName);
			File convFile = new File(System.getProperty("java.io.tmpdir") + "/" + fileName);
			multipartFile.transferTo(convFile);
			byte[] fileContent = FileUtils.readFileToByteArray(convFile);
			
			post.setPostImage(fileContent);
		}
		catch(IllegalStateException e) {
			System.out.println(e.toString());
		}
		catch(IOException e) {
			System.out.println(e.toString());
		}

		
		if(post.getPostTitle().isEmpty()) {
			errors.rejectValue("postTitle", "null", "Must be filled");
		}
		if(post.getPostMessage().isEmpty()) {
			errors.rejectValue("postMessage", "null", "Must be filled");
		}
		
		if (bindingResult.hasErrors()) {
			return "postCreate";
		}
		
		Post dbPost = poservice.findPostById(post.getPostId());
		
		if (dbPost !=null) {
			dbPost.setPostTitle(post.getPostTitle());
			dbPost.setPostMessage(post.getPostMessage());
			dbPost.setPostType(post.getPostType());

			if (post.getPostImage() !=null) {
				dbPost.setPostImage(post.getPostImage());
			}
			
			poservice.savePostService(dbPost);
			
			return "redirect:/postList";
			
		}
		else {
			return "postCreate";
		}
		

	}
	
	@RequestMapping(value="/postList")
	public String list(Model model, HttpServletRequest request)
	{
		model.addAttribute("postList", poservice.findAllPosts());
		
		int postId= 0;
		
		model.addAttribute("postId", postId);
		
		User user = uservice.findUserByUserName(request.getRemoteUser());
		model.addAttribute("user", user);
		
		return "postList";
	}
	
	@GetMapping("/post/image/{id}")
	public void showPostImage(@PathVariable String id, HttpServletResponse response) throws IOException {
		response.setContentType("image/jpeg"); 
		
		int newId = Integer.parseInt(id);
		Post post = poservice.findPostById(newId);

		InputStream is = new ByteArrayInputStream(post.getPostImage());
		IOUtils.copy(is, response.getOutputStream());
		
	}
	
	@RequestMapping(value = "/postDelete/{id}")
	public String deletePost(@PathVariable("id") Integer id) {
		poservice.deletePostById(id);;
		return "redirect:/postList";
	}
	
	@RequestMapping(value = "/postEdit/{id}")
	public String editPost(@PathVariable("id") Integer id, Model model) {
		Post post = poservice.findPostById(id);
		String encodedString = Base64.getEncoder().encodeToString(post.getPostImage());
		model.addAttribute("image", encodedString);
		model.addAttribute("post",post);
		return "postEdit";
	}
	
	@RequestMapping(value = "/viewPostDetails/{id}")
	public String viewPostDetails(@PathVariable("id") Integer id, Model model, HttpServletRequest request) {
		Post post = poservice.findPostById(id);
		String encodedString = Base64.getEncoder().encodeToString(post.getPostImage());
		List<PostComment> postcommentlist = pcservice.findPostCommentsbyPostId(id);
		PostComment postcomment = new PostComment();
		postcomment.setPost(post);
		model.addAttribute("image", encodedString);
		model.addAttribute("post",post);
		model.addAttribute("postcommentlist", postcommentlist);
		model.addAttribute("postcomment", postcomment);
		User user = uservice.findUserByUserName(request.getRemoteUser());
		model.addAttribute("user", user);
		return "postDetails";
	}
}

