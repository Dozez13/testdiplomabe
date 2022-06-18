package com.example.projectbe.web.security.auth.ajax;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserLoginDto {

    private String email;

    private String password;


}
