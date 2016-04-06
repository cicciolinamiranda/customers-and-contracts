package com.g4s.javelin.service.post;

import java.util.List;

import com.g4s.javelin.dto.core.post.PostDTO;

public interface PostService {

    PostDTO savePostDetails(PostDTO post);
    List<PostDTO> getPostByCustomerLocation(Long customerLocationId);
    PostDTO getPostDetails(Long id);
}
