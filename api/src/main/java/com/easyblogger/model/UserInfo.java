package com.easyblogger.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name="user_info")
public class UserInfo {

    @Id
    @Column(name = "id")
    private int id;

    @NotEmpty
    @Email(message = "Please provide proper email.")
    @Size(min = 3, max = 80)
    @Column(name = "email", unique = true)
    private String email;

    @NotEmpty
    @Size(min = 2, max = 80)
    @Column(name = "username", unique = true)
    private String username;

    @NotEmpty
    @Size(min = 2, max = 80)
    @Column(name = "password")
    private String password;

    @NotEmpty
    @Column(name = "authority")
    private String authority;

    @Column(name = "enabled")
    private boolean enabled;

    @Column(name = "updated_at")
    private Date updated_at;

    @Column(name = "created_at")
    private Date created_at;

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}