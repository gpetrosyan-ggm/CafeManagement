package com.ggm.cafemanagement.common.exception;

import lombok.Getter;

/**
 * Base class for all application exceptions
 */
@Getter
public class CafeManagementException extends RuntimeException {
    private String logMsg;

    public CafeManagementException() {
        super();
    }

    public CafeManagementException(String message) {
        super(message);
    }

    public CafeManagementException(String message, String logMsg) {
        super(message);
        this.logMsg = logMsg;
    }

    public CafeManagementException(String message, Throwable cause) {
        super(message, cause);
    }

    public CafeManagementException(Throwable cause) {
        super(cause);
    }

    protected CafeManagementException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
