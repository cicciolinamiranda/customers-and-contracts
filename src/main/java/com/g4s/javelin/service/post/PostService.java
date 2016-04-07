package com.g4s.javelin.service.post;

import java.util.List;

import com.g4s.javelin.dto.core.post.PostDTO;
import com.g4s.javelin.exception.PostDuplicateException;
import com.g4s.javelin.exception.PostException;

public interface PostService {

    PostDTO savePostDetails(PostDTO post) throws PostException, PostDuplicateException;
    List<PostDTO> getPostByCustomerLocation(Long customerLocationId);
    PostDTO getPostDetails(Long id);
}
