package br.login.api.apilogin.components;
import java.util.Date;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.InvalidKeyException;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;

public class JWTutils {
    private final static String jwtSecret = "=====================AtitusSecretJWT=====================";
    private final static int jwtExpirationMs = 86400000;

    private static SecretKey key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    public static String generateTokenFromUsername(String username) throws InvalidKeyException {
        return Jwts.builder().subject(username).issuedAt(new Date())
                .expiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(key()).compact();
    }

    public static String getJwtFromRequest(HttpServletRequest request) {
        String jwt = request.getHeader("Authorization");
        if (jwt == null || jwt.isEmpty())
            jwt = request.getHeader("authorization");
        if (jwt != null && !jwt.isEmpty()) {
            return jwt.substring(7);
        }
        return null;
    }

    public static boolean validaJwtToken(String jwt) {
        try {
            //Jwts.parser().setSigningKey(key()).build().parse(jwt);
            Jwts.parser().verifyWith(key()).build().parse(jwt);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static String getUserNameFromJwtToken(String token) {
        //return Jwts.parser().setSigningKey(key()).build().parseClaimsJws(token).getBody().getSubject();
        return Jwts.parser().verifyWith(key()).build().parseSignedClaims(token).getPayload().getSubject();
    }

}
