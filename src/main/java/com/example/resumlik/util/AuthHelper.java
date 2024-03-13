package com.example.resumlik.util;

import com.example.resumlik.model.Resume;
import com.example.resumlik.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.lang.reflect.Method;

public class AuthHelper {

    public static Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new IllegalStateException("User is not authenticated");
        }
        // Implement logic to extract the user's ID based on your authentication mechanism
        // For example, if you're using Spring Security with UserDetails, you might retrieve it like this:
        User userDetails = (User) authentication.getPrincipal();
        return userDetails.getId(); // Assuming UserDetails has a method getId() to retrieve the user's ID
    }

    public static User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new IllegalStateException("User is not authenticated");
        }
        // Implement logic to extract the user based on your authentication mechanism
        // For example, if you're using Spring Security with UserDetails, you might retrieve it like this:
        return (User) authentication.getPrincipal();
    }

    public static boolean checkOwnerShip(Object object) {
        if (object == null) {
            return false;
        }
        if (object instanceof Resume resume) {
            if (!resume.getUser().getId().equals(getCurrentUserId())) {
                return false;
            }
        }else {
            try {
                Method getResumeMethod = object.getClass().getMethod("getResume");
                Resume resume = (Resume) getResumeMethod.invoke(object);
                if (!resume.getUser().getId().equals(getCurrentUserId())) {
                    return false;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}
