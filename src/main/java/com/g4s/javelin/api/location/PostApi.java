package com.g4s.javelin.api.location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;

import com.g4s.javelin.constants.ApiConstants;
import com.g4s.javelin.constants.ServiceConstants;
import com.g4s.javelin.dto.core.post.PostDTO;
import com.g4s.javelin.exception.CustomerLocationException;
import com.g4s.javelin.service.post.PostService;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

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
            name = "customer.location.post.save",
            path = "customer-location/post/save",
            httpMethod = ApiMethod.HttpMethod.POST)
    public PostDTO savePostDetails(final PostDTO postDTO)
            throws CustomerLocationException {
        PostDTO response = postService.savePostDetails(postDTO);
        return response;
    }

}
