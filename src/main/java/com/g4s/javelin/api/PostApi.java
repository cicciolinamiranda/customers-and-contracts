package com.g4s.javelin.api;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;

import com.g4s.javelin.constants.ApiConstants;
import com.g4s.javelin.constants.ServiceConstants;
import com.g4s.javelin.dto.core.post.PostDTO;
import com.g4s.javelin.exception.PostDuplicateException;
import com.g4s.javelin.exception.PostException;
import com.g4s.javelin.service.post.PostService;
import com.g4s.javelin.util.ServletRequestUtil;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;

@Api(
        name = ApiConstants.API_NAME,
        version = ApiConstants.API_VERSION,
        namespace = @ApiNamespace(ownerDomain = ApiConstants.API_NAMESPACE_OWNER_DOMAIN,
                ownerName = ApiConstants.API_NAMESPACE_OWNER_NAME),
        description = ApiConstants.API_DESCRIPTION)
public class PostApi {
    @Autowired
    @Lazy
    @Qualifier(ServiceConstants.POST_SERVICE)
    private PostService postService;

    @ApiMethod(
            name = "workorder.customer.location.post.save",
            path = "workorder/customer-location/post/save",
            httpMethod = ApiMethod.HttpMethod.POST)
    public PostDTO savePostDetails(final PostDTO postDTO, final HttpServletRequest request)
            throws PostException, PostDuplicateException {
        postDTO.setIpAddress(ServletRequestUtil.extractIpAddress(request));
        PostDTO response = postService.savePostDetails(postDTO);
        return response;
    }

    @ApiMethod(
            name = "workorder.customer.location.post.update",
            path = "workorder/customer-location/post/update",
            httpMethod = ApiMethod.HttpMethod.POST)
    public PostDTO updatePostDetails(final PostDTO postDTO, final HttpServletRequest request)
            throws PostException, PostDuplicateException {
        postDTO.setIpAddress(ServletRequestUtil.extractIpAddress(request));
        PostDTO response = postService.savePostDetails(postDTO);
        return response;
    }

    @ApiMethod(
            name = "workorder.customer.location.post.get",
            path = "workorder/customer-location/post/get",
            httpMethod = ApiMethod.HttpMethod.GET)
    public PostDTO getPostDetails(@Named("id") final Long id) {
        PostDTO response = postService.getPostDetails(id);
        return response;
    }

    @ApiMethod(
            name = "workorder.customer.location.post.list",
            path = "workorder/customer-location/post/list",
            httpMethod = ApiMethod.HttpMethod.GET)
    public List<PostDTO> listPostDetails(@Named("locationId") final Long id) {
        List<PostDTO> response = postService.getPostByCustomerLocation(id);
        return response;
    }
}
