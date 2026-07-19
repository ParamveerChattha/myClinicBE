package com.dental.clinic.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

@Service
public class JwtService {

    // UNDER THE HOOD: Cryptographic signing keys must be at least 256-bits long.
    // Keys.hmacShaKeyFor converts our plain text string into a secure cryptographic byte array.
    private static final String SECRET_STRING = "your-super-secret-safe-and-long-key-that-is-at-least-256-bits-long";
    private final SecretKey secretKey = Keys.hmacShaKeyFor(SECRET_STRING.getBytes(StandardCharsets.UTF_8));

    private static final long ACCESS_TOKEN_EXPIRATION_MS = 15 * 60 * 1000; // 15 Minutes
    public static final long REFRESH_TOKEN_EXPIRATION_MS = 12 * 60 * 60 * 1000; // 12 Hours

    // DATA FLOW: Input Username -> Output Signed Encrypted String
    public String generateAccessToken(String username, Map<String, Object> extraClaims) {
        return Jwts.builder()
                .claims(extraClaims) // Custom data (e.g., roles/permissions)
                .subject(username)   // Who ownership belongs to
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRATION_MS))
                .signWith(secretKey) // Seal it with our secret key so it cannot be tampered with
                .compact();          // Turn into a string
    }

    public String generateRefreshToken(String username) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRATION_MS))
                .signWith(secretKey)
                .compact();
    }

    // DATA FLOW: Input encrypted Token -> Decrypts using SecretKey -> Outputs Claims (the data inside)
    public Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(secretKey) // If anyone altered even 1 character, this line throws an exception immediately
                .build()
                .parseSignedClaims(token)
                .getPayload(); // Extracts the body json dictionary
    }

    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject(); // Grabs the "sub" field from the decrypted payload
    }

    public boolean isTokenValid(String token, String userDetailsUsername) {
        final String username = extractUsername(token);
        return (username.equals(userDetailsUsername) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }
}
