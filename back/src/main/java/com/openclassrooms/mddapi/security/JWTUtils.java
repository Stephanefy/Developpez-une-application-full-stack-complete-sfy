package com.openclassrooms.mddapi.security;

import com.openclassrooms.mddapi.exceptions.AccessDeniedException;
import com.openclassrooms.mddapi.exceptions.InvalidTokenException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;


public class JWTUtils {

    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private static final int MINUTES = 60;

    /*
    *
    *
    * */
    public static String generateToken(String email, String username, Long userId) {
        var now = Instant.now();

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(Date.from(now))
                .claim("username", username)
                .claim("userId", userId)
                .setExpiration(Date.from(now.plus(MINUTES, ChronoUnit.MINUTES)))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }
    /**
     * Extracts the username from the provided JWT token.
     *
     * @param  token  The JWT token from which to extract the username
     * @return        The extracted username
     */
    public static String extractUsername(String token) {
        return getTokenBody(token).getSubject();
    }
    /**
     * Extracts the user ID from the provided JWT token.
     *
     * @param  token  The JWT token from which to extract the user ID
     * @return        The extracted user ID
     */
    public static Integer extractId(String token) throws InvalidTokenException {
        try {
            Claims claims = getTokenBody(token);
            return claims.get("userId", Integer.class);
        } catch (MalformedJwtException e) {
            throw new InvalidTokenException("Invalid JWT token");
        }
    }

    /**
     * Validates the provided token against the user details.
     *
     * @param  token        The JWT token to validate
     * @param  userDetails  The user details to validate the token against
     * @return             True if the token is valid, false otherwise
     */
    public static Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    /**
     * Retrieves the body of the JWT token.
     *
     * @param  token  The JWT token from which to retrieve the body
     * @return        The body of the JWT token
     */
    private static Claims getTokenBody(String token) {
        try {
            Jws<Claims> claimsJws = Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token);
            return claimsJws.getBody();
        } catch (SignatureException | ExpiredJwtException e) { // Invalid signature or expired token
            throw new AccessDeniedException("Access denied: " + e.getMessage());
        }
    }

    /**
     * Checks if the provided token has expired.
     *
     * @param  token  The JWT token to check for expiration
     * @return        True if the token has expired, false otherwise
     */
    private static boolean isTokenExpired(String token) {
        Claims claims = getTokenBody(token);
        return claims.getExpiration().before(new Date());
    }
}
