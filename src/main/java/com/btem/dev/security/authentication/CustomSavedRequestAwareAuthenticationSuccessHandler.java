package com.btem.dev.security.authentication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;

public class CustomSavedRequestAwareAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
	
	private RequestCache requestCache = new HttpSessionRequestCache();
	
	public CustomSavedRequestAwareAuthenticationSuccessHandler() {
	}
	
	public CustomSavedRequestAwareAuthenticationSuccessHandler(String string) {
		setDefaultTargetUrl(string);
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
		request.getSession().setMaxInactiveInterval(60 * 60);	//60 MINUTES
		response.sendRedirect(request.getContextPath() + getDefaultTargetUrl());
	}
	
}
