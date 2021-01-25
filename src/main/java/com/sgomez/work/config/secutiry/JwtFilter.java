package com.sgomez.work.config.secutiry;

import com.google.gson.JsonObject;
import com.sgomez.work.service.LoginService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class JwtFilter extends GenericFilterBean  {
	
	private LoginService loginService;
	
	@Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain filterChain)
            throws IOException, ServletException {

    	// Obtenemos el token que viene en el encabezado de la peticion
        String token = ((HttpServletRequest) request).getHeader("Authorization");
        List<String> list_roles = new ArrayList<String>();
        
        if(loginService ==null){
            ServletContext servletContext = request.getServletContext();
            WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
            loginService = webApplicationContext.getBean(LoginService.class);
        }

        try {
            if (token != null) {
                String username = Jwts.parser()
                        .setSigningKey(JwtUtil.KEYSECRET) //la clave secreta esta declara en JwtUtil
                        .parseClaimsJws(token) //este metodo es el que valida
                        .getBody()
                        .getSubject();        //extraigo el nombre de usuario del token

                UserDetails userDetails = loginService.loadUserByUsername(username);

                for (GrantedAuthority rol : userDetails.getAuthorities()) {
                    list_roles.add(rol.getAuthority());
                }
            }

            Authentication authentication = JwtUtil.getAuthentication((HttpServletRequest) request, list_roles);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request, response);

        }catch (ExpiredJwtException ex) {
            JsonObject json = new JsonObject();
            json.addProperty("message", "Error autenticando: El token ha expirado");
            json.addProperty("errorCode", "401");
            ((HttpServletResponse) response).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json.toString());
        }

    }

}
