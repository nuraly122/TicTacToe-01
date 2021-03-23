package org.example.web.controller;

import org.example.backend.model.AuthUser;
import org.example.backend.service.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(value = "/", produces = {MediaType.APPLICATION_JSON_VALUE})
public class UserController {

    @Autowired
    private AuthUserService authUserService;

    @GetMapping("/user")
    public Principal getPrincipal(Principal user) {
        return user;
    }

    @GetMapping("/getUserBy/{username}")
    public AuthUser getUserByUsername(@PathVariable String username) {
        return authUserService.findAuthUserByUsername(username);
    }

    @DeleteMapping("/deleteUserBy/{username}")
    public void deleteUser(@PathVariable String username) {
        authUserService.deleteUserByUsername(username);
    }

    @PostMapping("/registration")
    public AuthUser saveUser(@RequestBody AuthUser authUser) {
        authUserService.saveAuthUser(authUser);
        return authUserService.findAuthUserByUsername(authUser.getUsername());
    }

    @GetMapping("/users")
    public List<AuthUser> findAll() {
        return authUserService.findAll();
    }
}
