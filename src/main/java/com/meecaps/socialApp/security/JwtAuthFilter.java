package com.meecaps.socialApp.security;

import com.meecaps.socialApp.serviceImplements.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtAuthFilter extends OncePerRequestFilter {

        private final JwtService jwtService ;

        private final CustomUserDetailsService customDetailsService ;

        public JwtAuthFilter(JwtService jwtService, CustomUserDetailsService customDetailsService) {
            this.jwtService = jwtService;

            this.customDetailsService = customDetailsService;
   }

   @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException, IOException {

            String authorization = request.getHeader("Authorization");
       String token =null ;
       String email = null ;
       if(authorization!=null && authorization.startsWith("beaner")){
           token = authorization.substring(7);
           email=jwtService.extractEmail(token);
       }
       if (email != null && SecurityContextHolder.getContext().getAuthentication() == null){

           UserDetails userDetails = customDetailsService.loadUserByUsername(email);

           if (jwtService.isTokenValid(token)){
               UsernamePasswordAuthenticationToken authenticationToken =
                       new UsernamePasswordAuthenticationToken(
                               userDetails, null, userDetails.getAuthorities()
                       );
               SecurityContextHolder.getContext().setAuthentication(authenticationToken);
           }
       }
       filterChain.doFilter(request,response);


   }
}
