package blp.lab1.controller;

import blp.lab1.model.User;
import blp.lab1.service.RoleService;
import blp.lab1.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final RoleService roleService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public UserController (UserService userService, AuthenticationManager authenticationManager, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
        this.authenticationManager = authenticationManager;
    }

//    @ApiOperation(value = "${UserController.login}")
//    @PostMapping("login")
//    public HttpStatus login (@ApiParam("name") @RequestParam(name = "name") String name, @ApiParam("password") @RequestParam(name = "password") String password) {
//        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(name, password));
//        SecurityContextHolder.getContext().setAuthentication(auth);
//        return HttpStatus.OK;
//    }

    @ApiOperation(value = "${UserController.addUser}")
    @PostMapping("/add")
    public User addUser (@ApiParam("name") @RequestParam(name = "name") String name, @ApiParam("password") @RequestParam(name = "password") String password, @ApiParam("Roles") @RequestParam("Roles") Set<Long> roles) {
        //System.out.println("Add User " + name);
        return userService.saveUser(new User().setUsername(name).setPassword(password).setRoles(roleService.fetchRolesdBySetOfId(roles)));
    }
}
