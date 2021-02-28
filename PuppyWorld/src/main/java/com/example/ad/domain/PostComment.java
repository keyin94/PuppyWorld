package com.example.ad.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class PostComment {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int commentId;
	
	private String commentMessage;
	
	private LocalDateTime commentDateTime;
	
	@ManyToOne
	private User user;
	
	@ManyToOne
	private Post post;

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public String getCommentMessage() {
		return commentMessage;
	}

	public void setCommentMessage(String commentMessage) {
		this.commentMessage = commentMessage;
	}

	public LocalDateTime getCommentDateTime() {
		return commentDateTime;
	}

	public void setCommentDateTime(LocalDateTime commentDateTime) {
		this.commentDateTime = commentDateTime;
	}


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public PostComment() {
		super();
	}

	public PostComment(String commentMessage, LocalDateTime commentDateTime, User user, Post post) {
		super();
		this.commentMessage = commentMessage;
		this.commentDateTime = commentDateTime;
		this.user = user;
		this.post = post;
	}

	public PostComment(String commentMessage, User user, Post post) {
		super();
		this.commentMessage = commentMessage;
		this.user = user;
		this.post = post;
		this.commentDateTime = LocalDateTime.now();
	}

	@Override
	public String toString() {
		return "PostComment [commentId=" + commentId + ", commentMessage=" + commentMessage + ", commentDateTime="
				+ commentDateTime + ", user=" + user + ", post=" + post + "]";
	}

}
