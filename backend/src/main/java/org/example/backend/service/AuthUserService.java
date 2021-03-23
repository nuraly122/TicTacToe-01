package org.example.backend.service;

import org.example.backend.dao.AuthUserDao;
import org.example.backend.model.AuthUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthUserService implements UserDetailsService {

    @Autowired
    private AuthUserDao authUserDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthUser authUser = authUserDao.findByUsername(username);
        return (UserDetails) authUser;
    }

    public void saveAuthUser(AuthUser authUser) {
        authUserDao.saveAuthUser(authUser);
    }

    public AuthUser findAuthUserByUsername(String username) {
        return authUserDao.findByUsername(username);
    }

    public void deleteUserByUsername(String username) {
        authUserDao.deleteUserByUsername(username);
    }

    public List<AuthUser> findAll() {
        return authUserDao.findAll();
    }
}
