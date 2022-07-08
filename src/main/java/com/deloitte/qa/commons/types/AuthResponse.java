package com.deloitte.qa.commons.types;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthResponse {

    private String status;
    private String token;
    private int expiry;
}
