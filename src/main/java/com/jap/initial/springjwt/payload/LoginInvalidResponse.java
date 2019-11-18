/* by JAPBuilder */
package com.jap.initial.springjwt.payload;

public class LoginInvalidResponse {
    private String email;
    private String password;

    public LoginInvalidResponse() {
        this.email = "Invalid Email";
        this.password = "Invalid Password";
    }

    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
