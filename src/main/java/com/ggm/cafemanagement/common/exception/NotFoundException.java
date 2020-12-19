package com.ggm.cafemanagement.common.exception;

public class NotFoundException extends CafeManagementException {

    public NotFoundException() {
        super();
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, String logMsg) {
        super(message, logMsg);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundException(Throwable cause) {
        super(cause);
    }

}