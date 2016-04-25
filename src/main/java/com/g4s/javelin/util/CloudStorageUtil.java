package com.g4s.javelin.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.web.multipart.MultipartFile;

import com.g4s.javelin.constants.ExceptionMessageConstants;
import com.g4s.javelin.dto.core.upload.UploadDTO;
import com.g4s.javelin.enums.AllowableExtensionEnum;
import com.g4s.javelin.exception.UploadException;
import com.google.cloud.storage.Acl;
import com.google.cloud.storage.Acl.Role;
import com.google.cloud.storage.Acl.User;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

/**
 * @author Sharlyn Mae Pandes
 * @since 4/20/2016
 * */

public class CloudStorageUtil {
    private static final Logger LOGGER = Logger.getLogger(CloudStorageUtil.class.getName());
    private static Storage storage = null;

    static {
        storage = StorageOptions.defaultInstance().service();
    }

    /**
     * Obtains the URL returned by Cloud Storage
     * @param file Image to be uploaded
     * @param bucket Bucket name in Cloud Storage
     * */
    public UploadDTO getImageUrl(final MultipartFile file, final String bucket) throws UploadException, IOException {
        LOGGER.info("Inside getImageUrl() method");
        UploadDTO uploadDto = new UploadDTO();
        uploadDto.setImageOriginalFilename(file.getOriginalFilename());

        final String fileName = file.getOriginalFilename();
        boolean isValidImageFormat = false;

        // Check extension of file
        if (fileName != null && !fileName.isEmpty() && fileName.contains(".")) {
            final String extension = fileName.substring(fileName.lastIndexOf('.') + 1);
            for (AllowableExtensionEnum ext : AllowableExtensionEnum.values()) {
                if (extension.equals(ext.getValue())) {
                    isValidImageFormat = true;
                    break;
                }
            }

            if (isValidImageFormat) {
                uploadDto.setImageUrl(uploadFile(file, bucket));
            } else {
                throw new UploadException(ExceptionMessageConstants.INVALID_IMAGE_FILE);
            }
        }
        return uploadDto;
    }

    // TODO: Once getting CSRF token is implemented, update acl part to restrict users who can
    // upload file
    private String uploadFile(final MultipartFile file, final String bucketName) throws IOException {
        LOGGER.info("Inside uploadFile() method");

        DateTimeFormatter dtf = DateTimeFormat.forPattern("-YYYY-MM-dd-HHmmssSSS");
        DateTime dt = DateTime.now(DateTimeZone.UTC);
        String dtString = dt.toString(dtf);
        final String fileName = file.getOriginalFilename() + dtString;

        BlobInfo blobInfo = storage.create(
                BlobInfo.builder(bucketName, fileName)
                        .acl(new ArrayList<>(Arrays.asList(Acl.of(User.ofAllUsers(), Role.READER)))).build(),
                file.getInputStream());
        LOGGER.log(
            Level.INFO, "Uploaded file {0} as {1}", new Object[]{
                file.getOriginalFilename(), fileName});

        return blobInfo.mediaLink();
    }
}
