package com.jap.initial.springjwt.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Table(name = "users")
public class Users implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "fullName is required")
    private String fullName;
    @Email(message = "email needs to be an correct email")
    @NotBlank(message = "email is required")
    @Column(unique = true)
    private String email;
    @NotBlank(message = "phone is required")
    @Column(unique = true)
    private String phone;
    @NotBlank(message = "password is required")
    private String password;

    private Timestamp createAt;
    private Timestamp updateAt;

    public Users() {
    }

    public Long getId() {
        return id;
    }
    public String getFullName() {
        return fullName;
    }
    public String getEmail() {
        return email;
    }
    public String getPhone() {
        return phone;
    }
    @JsonIgnore
    public String getPassword() {
        return password;
    }
    public Timestamp getCreateAt() {
        return createAt;
    }
    public Timestamp getUpdateAt() {
        return updateAt;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    @JsonProperty("password")
    public void setPassword(String password) {
        this.password = password;
    }
    public void setCreateAt(Timestamp createAt) {
        this.createAt = createAt;
    }
    public void setUpdateAt(Timestamp updateAt) {
        this.updateAt = updateAt;
    }

    @PrePersist
    protected void onCreate() { this.createAt = new Timestamp(System.currentTimeMillis()); }
    @PreUpdate
    protected void onUpdate() { this.updateAt = new Timestamp(System.currentTimeMillis()); }

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    @JsonIgnore
    public String getUsername() {
        return this.getEmail();
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }
}
