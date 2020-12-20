package com.ggm.cafemanagement.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityHelper {

    private SecurityHelper() {
        throw new IllegalStateException("Helper class");
    }

    public static String retrieveUserName() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth != null ? auth.getName() : null;
    }

}
