package com.g4s.javelin.exception;

import com.google.api.server.spi.ServiceException;

//CSOFF: MagicNumber
public class PostDuplicateException extends ServiceException {

    public PostDuplicateException(final String statusMessage) {
        super(408, statusMessage);
    }
}
//CSON: MagicNumber
