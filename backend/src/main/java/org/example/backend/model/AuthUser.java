package org.example.backend.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Objects;

public class AuthUser implements UserDetails {

    private Integer id;
    private String username;
    private String password;
    private Boolean active;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return getActive();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthUser authUser = (AuthUser) o;
        return Objects.equals(username, authUser.username) &&
                Objects.equals(password, authUser.password) &&
                Objects.equals(active, authUser.active) &&
                Objects.equals(id, authUser.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, active, id);
    }

    @Override
    public String toString() {
        return "AuthUser{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", isActive=" + active +
                '}';
    }
}
