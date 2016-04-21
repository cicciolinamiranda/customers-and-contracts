package com.g4s.javelin.exception;

import com.google.api.server.spi.ServiceException;

/**
 * @author Sharlyn Mae P Pandes
 * @since April 21, 2016
 * */
//CSOFF: MagicNumber
public class UploadException extends ServiceException {

    public UploadException(final String statusMessage) {
        super(408, statusMessage);
    }

}
//CSON: MagicNumber
