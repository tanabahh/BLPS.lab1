package blp.lab1.controller;

import blp.lab1.model.User;
import blp.lab1.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public UserController (UserService userService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

//    @ApiOperation(value = "${UserController.login}")
//    @PostMapping("login")
//    public HttpStatus login (@ApiParam("name") @RequestParam(name = "name") String name, @ApiParam("password") @RequestParam(name = "password") String password) {
//        Authorization auth = authenticationManager.authenticate(UsernamePasswordAuthenticationToken(name, password));
//        SecurityContextHolder.getContext().getAuthentication() = auth;
//
//    }

    @ApiOperation(value = "${UserController.addUser}")
    @PostMapping("/add")
    public User addUser (@ApiParam("name") @RequestParam(name = "name") String name, @ApiParam("password") @RequestParam(name = "password") String password) {
        System.out.println("Add User " + name);
        return userService.saveUser(new User().setUsername(name).setPassword(password));
    }
}
