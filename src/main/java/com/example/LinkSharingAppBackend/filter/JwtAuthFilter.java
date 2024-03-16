package com.example.LinkSharingAppBackend.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import com.example.LinkSharingAppBackend.dto.UserPrincipal;
import com.example.LinkSharingAppBackend.service.JwtService;
import com.example.LinkSharingAppBackend.service.UserPrincipalService;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@SuppressWarnings("deprecation")
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserPrincipalService userPrincipalService;

    private HandlerExceptionResolver exceptionResolver;

    @Autowired
    public JwtAuthFilter(HandlerExceptionResolver exceptionResolver) {
        this.exceptionResolver = exceptionResolver;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;
        try {
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                token = authHeader.substring(7);
                username = jwtService.extractUsername(token);
            }

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserPrincipal userPrincipal = userPrincipalService.loadUserByUsername(username);
                if (jwtService.validateToken(token, userPrincipal)) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userPrincipal, null, userPrincipal.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException | SignatureException ex) {
            exceptionResolver.resolveException(request, response, null, ex);
        }
    }
}

/*
 * package com.example.LinkSharingAppBackend.filter;
 * 
 * import java.io.IOException;
 * 
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.security.authentication.
 * UsernamePasswordAuthenticationToken;
 * import org.springframework.security.core.context.SecurityContextHolder;
 * import org.springframework.security.web.authentication.
 * WebAuthenticationDetailsSource;
 * import org.springframework.stereotype.Component;
 * import org.springframework.web.filter.OncePerRequestFilter;
 * 
 * import com.example.LinkSharingAppBackend.dto.UserPrincipal;
 * import com.example.LinkSharingAppBackend.exception.InvalidTokenException;
 * import com.example.LinkSharingAppBackend.service.JwtService;
 * import com.example.LinkSharingAppBackend.service.UserPrincipalService;
 * 
 * import io.jsonwebtoken.ExpiredJwtException;
 * import jakarta.servlet.FilterChain;
 * import jakarta.servlet.ServletException;
 * import jakarta.servlet.http.HttpServletRequest;
 * import jakarta.servlet.http.HttpServletResponse;
 * 
 * @Component
 * public class JwtAuthFilter extends OncePerRequestFilter {
 * 
 * @Autowired
 * private JwtService jwtService;
 * 
 * @Autowired
 * private UserPrincipalService userPrincipalService;
 * 
 * @Override
 * protected void doFilterInternal(HttpServletRequest request,
 * HttpServletResponse response, FilterChain filterChain)
 * throws IOException, ServletException {
 * String authHeader = request.getHeader("Authorization");
 * String token = null;
 * String name = null;
 * 
 * if (authHeader != null && authHeader.startsWith("Bearer ")) {
 * token = authHeader.substring(7);
 * name = jwtService.extractUsername(token);
 * }
 * 
 * if (name != null && SecurityContextHolder.getContext().getAuthentication() ==
 * null) {
 * UserPrincipal userPrincipal = userPrincipalService.loadUserByUsername(name);
 * 
 * try {
 * if (jwtService.validateToken(token, userPrincipal)) {
 * UsernamePasswordAuthenticationToken authToken = new
 * UsernamePasswordAuthenticationToken(
 * userPrincipal, null, userPrincipal.getAuthorities());
 * authToken.setDetails(new
 * WebAuthenticationDetailsSource().buildDetails(request));
 * SecurityContextHolder.getContext().setAuthentication(authToken);
 * }
 * filterChain.doFilter(request, response);
 * } catch (ExpiredJwtException ex) {
 * // Handle expired JWT exception here
 * throw new InvalidTokenException("JWT token has expired");
 * }
 * }
 * }
 * }
 */