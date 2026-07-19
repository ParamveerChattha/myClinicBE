package com.dental.clinic.DTO;

public record TokenResponse(String accessToken, String tokenType, long expiresInMs) {

}
