package com.g4s.javelin.util;

import java.util.logging.Logger;

import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

public class CloudStorageUtil {
    private static final Logger LOGGER = Logger.getLogger(CloudStorageUtil.class.getName());
    private static Storage storage = null;

    // [START init]
    static {
        storage = StorageOptions.defaultInstance().service();
    }
}
