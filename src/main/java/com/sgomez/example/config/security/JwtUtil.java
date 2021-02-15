package com.sgomez.example.config.security;

import com.google.gson.Gson;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JwtUtil {

	public static String KEYSECRET = "laclavesecreta";

    // Metodo para crear el JWT y enviarlo al cliente en el header de la respuesta
    static void addAuthentication(HttpServletResponse res, String username, List<String> roles) {
        String token = Jwts.builder()
            .setSubject(username)
            .setExpiration(new Date(System.currentTimeMillis() + 43200000))// Vamos a asignar un tiempo de expiracion de 12 horas
            .signWith(SignatureAlgorithm.HS512, KEYSECRET)// Hash con el que firmaremos la clave
                .compact();
        //agregamos al encabezado el token
        res.addHeader("Authorization", token);
      
      //set body
      Gson gson = new Gson();
      Map<String,Object> json = new HashMap<String,Object>();
      json.put("username", username);
      json.put("token", token);
      String userJsonString = gson.toJson(json);
      res.setContentType("application/json");
      res.setCharacterEncoding("UTF-8");
      res.setStatus(HttpServletResponse.SC_CREATED);
      try {
		res.getWriter().print(userJsonString);
	} catch (IOException e) {
		e.printStackTrace();
	}
    }

    // Metodo para validar el token enviado por el cliente
    static Authentication getAuthentication(HttpServletRequest request, List<String> roles) throws IOException {

        // Obtenemos el token que viene en el encabezado de la peticion
        String token = request.getHeader("Authorization");

        // si hay un token presente, entonces lo validamos
        if (token != null) {
            String user = Jwts.parser()
                    .setSigningKey(KEYSECRET)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();

            String[] stringRoles = roles.toArray(new String[0]);
            
            return user != null ?
                    new UsernamePasswordAuthenticationToken(user, null, AuthorityUtils.createAuthorityList(stringRoles)) :
                    null;
        }

        return null;
    }
}
