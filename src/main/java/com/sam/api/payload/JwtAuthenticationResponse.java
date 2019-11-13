package com.sam.api.payload;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class JwtAuthenticationResponse {

    private String accessToken;
    private String tokenType;

    public JwtAuthenticationResponse(String accessToken) {
        this.accessToken = accessToken;
        this.tokenType = "Bearer";
    }

}
