package com.example.ad.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Post {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int postId;
	
	private PostType postType;
	
	private String postTitle, postMessage;
	@Lob
	private byte[] postImage;
	
	@ManyToOne
	private User user;
	
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="post")
	private List<PostComment> postcommentlist;
	
	public Post(PostType postType, String postTitle, String postMessage, byte[] postImage, User user) {
		super();
		this.postType = postType;
		this.postTitle = postTitle;
		this.postMessage = postMessage;
		this.postImage = postImage;
		this.user = user;
	}

	public Post() {
		// TODO Auto-generated constructor stub
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public PostType getPostType() {
		return postType;
	}

	public void setPostType(PostType postType) {
		this.postType = postType;
	}

	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public String getPostMessage() {
		return postMessage;
	}

	public void setPostMessage(String postMessage) {
		this.postMessage = postMessage;
	}

	public byte[] getPostImage() {
		return postImage;
	}

	public void setPostImage(byte[] postImage) {
		this.postImage = postImage;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	@Override
	public String toString() {
		return "Post [postId=" + postId + ", postType=" + postType + ", postTitle=" + postTitle + ", postMessage="
				+ postMessage + ", user=" + user + "]";
	}

	public List<PostComment> getPostcommentlist() {
		return postcommentlist;
	}

	public void setPostcommentlist(List<PostComment> postcommentlist) {
		this.postcommentlist = postcommentlist;
	}
}
