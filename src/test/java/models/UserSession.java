package models;

import lombok.Data;

@Data
public class UserSession {

    private String username;
    private String email;
    private String password;
}