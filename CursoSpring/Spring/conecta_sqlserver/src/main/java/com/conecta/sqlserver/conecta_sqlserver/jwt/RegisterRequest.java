package com.conecta.sqlserver.conecta_sqlserver.jwt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    String userName;
    String password;
    String name;
    String lastName;
}
