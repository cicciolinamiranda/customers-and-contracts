package com.g4s.javelin.api.custom;

import java.io.IOException;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.g4s.javelin.constants.ExceptionMessageConstants;
import com.g4s.javelin.dto.core.upload.UploadDTO;
import com.g4s.javelin.exception.UploadException;
import com.g4s.javelin.util.CloudStorageUtil;

/**
 * @author Sharlyn Mae Pandes
 * @since 4/20/2016
 * */

@RestController
@RequestMapping("/upload")
public class UploadApi {

    private static final Logger LOGGER = Logger.getLogger(UploadApi.class.getName());

    @Value("${bucket.name}")
    private String imageBucket;

    @RequestMapping(value = "/post/image", method = RequestMethod.POST)
    @ResponseBody
    public UploadDTO uploadPostImage(@RequestParam("file") final MultipartFile file) throws IOException, UploadException {

        LOGGER.info("Inside uploadPostImage() method: " + imageBucket);
        CloudStorageUtil storageHelper = new CloudStorageUtil();
        UploadDTO uploadDto = new UploadDTO();

        if (!file.isEmpty()) {
            uploadDto = storageHelper.getImageUrl(file, imageBucket);
        } else {
            throw new UploadException(ExceptionMessageConstants.FILE_EMPTY);
        }
        return uploadDto;
    }
}
