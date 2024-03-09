package com.example.LinkSharingAppBackend.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.LinkSharingAppBackend.dto.UserPrincipal;
import com.example.LinkSharingAppBackend.service.JwtService;
import com.example.LinkSharingAppBackend.service.UserPrincipalService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserPrincipalService userPrincipalService;

    // Need to rework doFilterInternal to catch errors & throw exceptions when the
    // token is no longer valid

    @Override
    protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        String authHeader = request.getHeader("Authorization");
        String token = null;
        String name = null;
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            name = jwtService.extractUsername(token);
        }

        if (name != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserPrincipal userPrincipal = userPrincipalService.loadUserByUsername(name);
            if (jwtService.validateToken(token, userPrincipal)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userPrincipal,
                        null, userPrincipal.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }

}

/*
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
 * if(!jwtService.validateToken(token, userPrincipal)){
 * response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
 * return;
 * }
 * 
 * if (jwtService.validateToken(token, userPrincipal)) {
 * UsernamePasswordAuthenticationToken authToken = new
 * UsernamePasswordAuthenticationToken(
 * userPrincipal,
 * null, userPrincipal.getAuthorities());
 * authToken.setDetails(new
 * WebAuthenticationDetailsSource().buildDetails(request));
 * SecurityContextHolder.getContext().setAuthentication(authToken);
 * }
 * 
 * }
 * 
 * filterChain.doFilter(request, response);
 * }
 * }
 */

/*
 * 
 * // Can't fix the doFilter without changing the JwtService?
 * // JwtService needs to throw exceptions when token validation fails
 * // I don't like the validateToken method
 * // Methods and signatures need rework
 * // add more exceptions to doFilter
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
 * try {
 * if (name != null && SecurityContextHolder.getContext().getAuthentication() ==
 * null) {
 * UserPrincipal userPrincipal =
 * this.userPrincipalService.loadUserByUsername(name);
 * if (jwtService.validateToken(token, userPrincipal)) {
 * UsernamePasswordAuthenticationToken authToken = new
 * UsernamePasswordAuthenticationToken(
 * userPrincipal,
 * null,
 * userPrincipal.getAuthorities());
 * authToken.setDetails(
 * new WebAuthenticationDetailsSource().buildDetails(request));
 * SecurityContextHolder.getContext().setAuthentication(authToken);
 * }
 * }
 * filterChain.doFilter(request, response);
 * } catch (ExpiredJwtException eje) {
 * response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
 * }
 * }
 */

/*
 * try {
 * Jwts.parser()
 * .setSigningKey(secret.getBytes("UTF-8"))
 * .parseClaimsJws(token.getToken());
 * } catch (JwtException e) {
 * response.sendError(401,"UNAUTHORIZED");
 * }
 */

/*
 * @Component
 * public class JwtExpirationFilter extends OncePerRequestFilter {
 * 
 * @Override
 * protected void doFilterInternal(HttpServletRequest request,
 * HttpServletResponse response, FilterChain filterChain) throws
 * ServletException, IOException {
 * String token = extractTokenFromRequest(request);
 * 
 * if (token != null && isTokenExpired(token)) {
 * response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
 * return;
 * }
 * 
 * filterChain.doFilter(request, response);
 * }
 * 
 * private String extractTokenFromRequest(HttpServletRequest request) {
 * // Extract the JWT token from the request headers or cookies
 * // Implement your logic here
 * }
 * 
 * private boolean isTokenExpired(String token) {
 * // Check if the JWT token is expired
 * // Implement your logic here
 * }
 * }
 */