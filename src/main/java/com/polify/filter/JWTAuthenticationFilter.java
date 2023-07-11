package com.polify.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.polify.entity.LoginHistory;
import com.polify.model.UserDTO;
import com.polify.service.LoginHistoryService;
import com.polify.service.UserAccountService;
import com.polify.utils.ProjectUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	public static final String SECRET = "FG^723fhhQW12~123qwert123#@$fr!67DSWa";
	public static final long EXPIRATION_TIME = 100000000;
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";

	private AuthenticationManager authenticationManager;
	
	private LoginHistoryService loginHistoryService;

    private UserAccountService userAccountService;

	private LoginHistory loginHistory;

    private User user;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager,
			LoginHistoryService loginHistoryService, UserAccountService userAccountService) {
		this.authenticationManager = authenticationManager;
		this.loginHistoryService = loginHistoryService;
        this.userAccountService = userAccountService;

    }
	

	public LoginHistory getLoginHistory() {
		return loginHistory;
	}


	public void setLoginHistory(LoginHistory loginHistory) {
		this.loginHistory = loginHistory;
	}




	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
        try {

            UserDTO loginUser = new ObjectMapper().readValue(request.getInputStream(),
                UserDTO.class);
            System.out.println("-------------------------loginUser: " + loginUser);
            this.setLoginHistory(loginHistoryService.save(loginUser.getUsername(), ProjectUtils.PENDING_STATUS));
            com.polify.entity.User user = userAccountService.getUserByUsername(loginUser.getUsername());

            if (user.isVerified()) {
                return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUser.getUsername(),
                    loginUser.getPassword(), new ArrayList<>()));
            } else {
                Map<String, String> errorMap = new HashMap<>();
                errorMap.put("message", "User is not verified");
                response.setStatus(400);
                response.setContentType("application/json");

                String jsonError = null;
                try {
                    jsonError = new ObjectMapper().writeValueAsString(errorMap);
                } catch (JsonProcessingException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    response.getWriter().write(jsonError);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException();
        }
        return null;
    }

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {

		String token = JWT.create().withSubject(((User) authResult.getPrincipal()).getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // JWT token validity time
				.sign(Algorithm.HMAC512(SECRET.getBytes())); // JWT Signature
		this.getLoginHistory().setAttemptStatus(ProjectUtils.SUCCESS_STATUS);
		loginHistoryService.save(this.getLoginHistory());
//		response.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"token\":\"" + TOKEN_PREFIX + token + "\"}");
	}
    
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		SecurityContextHolder.clearContext();
		this.getLoginHistory().setAttemptStatus(ProjectUtils.FAIL_STATUS);
		loginHistoryService.save(this.getLoginHistory());
		super.unsuccessfulAuthentication(request, response, failed);

	}
}
