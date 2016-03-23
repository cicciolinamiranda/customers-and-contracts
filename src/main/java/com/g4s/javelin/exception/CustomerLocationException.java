package com.g4s.javelin.exception;

import com.google.api.server.spi.ServiceException;

public class CustomerLocationException extends ServiceException {

    public CustomerLocationException(final String statusMessage) {
        super(408, statusMessage);
    }
}
