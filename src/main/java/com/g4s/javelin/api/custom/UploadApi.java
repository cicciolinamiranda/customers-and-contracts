package com.g4s.javelin.api.custom;

import java.io.IOException;
import java.util.logging.Logger;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Sharlyn Mae Pandes
 * @since 4/20/2016
 * */

@RestController
@RequestMapping("/upload")
public class UploadApi {

    private static final Logger LOGGER = Logger.getLogger(UploadApi.class.getName());

    @RequestMapping(value = "/post/image", method = RequestMethod.POST)
    public void uploadPostImage(@RequestParam("file") final MultipartFile file) throws IOException {
        LOGGER.info("Inside uploadPostImage() method: ");
        if (!file.isEmpty()) {
            LOGGER.info("Name: " + file.getOriginalFilename());
            LOGGER.info("You successfully uploaded file=" + file.getName());
            //TODO: Call CloudStorage Util
        } else {
            LOGGER.info("You failed to upload " + file.getName()
                    + " because the file was empty.");
        }
    }
}
