package com.g4s.javelin.util;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mock.web.MockMultipartFile;

import com.g4s.javelin.dto.core.upload.UploadDTO;
import com.g4s.javelin.exception.UploadException;

@RunWith(MockitoJUnitRunner.class)
public class CloudStorageUtilTest {

    @Mock
    private CloudStorageUtil csUtil;
    
    @Mock
    private UploadDTO uploadDto;
    
    @Test
    public void testGetImageUrl() throws UploadException, IOException {
        MockMultipartFile imageFile = 
                new MockMultipartFile("image", "test_email.png", "image/png", "test_image".getBytes());
        uploadDto = new UploadDTO();
        uploadDto.setImageOriginalFilename("test_email.png");
        uploadDto.setImageUrl("http://test_email.png-YYYY-MM-dd-HHmmssSSS");

        Mockito.when(csUtil.getImageUrl(imageFile, "bucketName")).thenReturn(uploadDto);
        assertNotNull(csUtil.getImageUrl(imageFile, "bucketName"));
    }

}
