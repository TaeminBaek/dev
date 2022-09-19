package com.btem.dev.security.controller;

import com.btem.dev.security.authentication.CustomAuthenticationToken;
import com.btem.dev.security.authentication.CustomSavedRequestAwareAuthenticationSuccessHandler;
import com.btem.dev.security.authentication.UserInfo;
import com.btem.dev.security.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class SecurityController {
    private final LoginService loginService;

    @PostMapping(value = "/login")
    public void login(HttpServletRequest req, HttpServletResponse res, @RequestBody Map<String, Object> param) throws Exception {
        try {
            String id = param.get("id").toString(), pwd = param.get("pwd").toString();
            log.debug("id : {}", id);
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if(authentication != null)  new SecurityContextLogoutHandler().logout(req, res, authentication);

            UserInfo user = loginService.getUserInfo(id, pwd);

            authentication = new CustomAuthenticationToken(user.getId(), user.getPwd(), user);
            AuthenticationSuccessHandler authenticationSuccessHandler = new CustomSavedRequestAwareAuthenticationSuccessHandler("");
            authenticationSuccessHandler.onAuthenticationSuccess(req, res, authentication);
            SecurityContext securityContext = SecurityContextHolder.getContext();
            securityContext.setAuthentication(authentication);
            req.getSession().setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, securityContext);
        } catch (Exception e) {
            throw new AuthenticationException(e.getMessage());
        }
    }
}
