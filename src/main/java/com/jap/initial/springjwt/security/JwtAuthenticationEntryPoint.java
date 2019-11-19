package com.jap.initial.springjwt.security;

import com.google.gson.Gson;
import com.jap.initial.springjwt.payload.ApiResponse;
import com.jap.initial.springjwt.payload.LoginInvalidResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {
        LoginInvalidResponse loginInvalidResponse = new LoginInvalidResponse();
        String json = new Gson().toJson(new ApiResponse(HttpStatus.UNAUTHORIZED, loginInvalidResponse));

        httpServletResponse.setContentType("application/json");
        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        httpServletResponse.getWriter().print(json);
    }
}
