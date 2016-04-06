package com.g4s.javelin.data.repository.post;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.g4s.javelin.data.model.post.PostModel;

public interface PostRepository extends JpaRepository<PostModel, Long> {

    List<PostModel> findByCustomerLocationId(Long customerLocationId);
}
