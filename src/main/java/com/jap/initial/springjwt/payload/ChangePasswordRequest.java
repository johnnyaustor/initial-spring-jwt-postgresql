/* by JAPBuilder */
package com.jap.initial.springjwt.payload;

import javax.validation.constraints.NotBlank;

public class ChangePasswordRequest {

    @NotBlank(message = "Email cannot be blank")
    private String email;
    @NotBlank(message = "Old Password cannot be blank")
    private String oldPassword;
    @NotBlank(message = "New Password cannot be blank")
    private String newPassword;

    public String getEmail() {
        return email;
    }
    public String getOldPassword() {
        return oldPassword;
    }
    public String getNewPassword() {
        return newPassword;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
