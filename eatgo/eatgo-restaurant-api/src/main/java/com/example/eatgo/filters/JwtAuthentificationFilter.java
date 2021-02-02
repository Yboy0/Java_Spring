package com.example.eatgo.filters;

import com.example.eatgo.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthentificationFilter extends BasicAuthenticationFilter {

    private JwtUtil jwtUtil;

    public JwtAuthentificationFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        super(authenticationManager);
        this.jwtUtil = jwtUtil;
    }

    //TODO: 실제로 jwt 분석 필요.

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain
    ) throws IOException, ServletException {
        Authentication authentication = getAuthentication(request);
        // TODO: JWT

        if(authentication != null) {
           SecurityContext context= SecurityContextHolder.getContext();
           context.setAuthentication(authentication);
        }

        //token 유무상관없이 dofilter는 항상 실행
        chain.doFilter(request,response);
    }

    private Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token == null) {
            return null;
        }
        //TODO: JWT;

        Claims claims = jwtUtil.getClaims(token.substring("Bearer ".length()));

        Authentication authentication =
                new UsernamePasswordAuthenticationToken(claims,null);
        return authentication;


    }



}
