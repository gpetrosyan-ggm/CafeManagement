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

//
//    public static String retrieveUserName() {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (auth != null) {
//            Object details = ((OAuth2Authentication) auth).getUserAuthentication().getDetails();
//            if (details != null) {
//                return ((Author) details).getName();
//            }
//        }
//        return null;
//    }
//
//    public static Author retrieveAuthor() {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (auth != null) {
//            Object details = ((OAuth2Authentication) auth).getUserAuthentication().getDetails();
//            if (details != null) {
//                return ((Author) details);
//            }
//        }
//        return null;
//    }
//
//    public static List<String> retrieveRoles() {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//
//        if (auth != null) {
//            return auth.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
//        }
//        return Collections.emptyList();
//    }
//
//    public static String retrieveToken() {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (auth != null) {
//            return ((OAuth2AuthenticationDetails) (auth.getDetails())).getTokenValue();
//        }
//        return null;
//    }
//
//    public static void checkAccess(String userId) {
//        if (!userId.equals(retrieveUserId()))
//            throw new AccessDeniedException(String.format("Access denied for userId = %s. ", userId));
//    }

}
