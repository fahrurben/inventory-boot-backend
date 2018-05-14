package com.kyrosoft.inventory.security;

import com.kyrosoft.inventory.model.ServiceContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class PrincipalInterceptor extends HandlerInterceptorAdapter {

    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) throws Exception {

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(userDetails != null) {
            ServiceContext.setCurrentUser(userDetails.getUsername());
        }
        return true;
    }

}
