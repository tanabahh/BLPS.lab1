package blp.lab1.controller;

import blp.lab1.model.User;
import blp.lab1.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController (UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "${UserController.addUser}")
    @PostMapping("/add")
    public User addUser (@ApiParam("name") @RequestParam(name = "name") String name) {
        System.out.println("Add User " + name);
        return userService.saveUser(new User().setName(name));
    }
}
