package com.example.ad.repo;

import java.util.ArrayList;

import javax.persistence.NamedNativeQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.ad.domain.PostComment;

public interface PostCommentRepository extends JpaRepository<PostComment, Integer> {

	
	@Query("SELECT p FROM PostComment p WHERE p.post.postId = :postid ORDER by comment_date_time DESC")
	public ArrayList<PostComment> findPostCommentsbyPostId(int postid);

}
