package com.dapperdrakes.dimo.model;

public class UserLoginRequest {

    String email;
    String password;

    public UserLoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
/*   public UserLoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }*/

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

}
