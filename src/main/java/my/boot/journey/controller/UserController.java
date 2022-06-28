package my.boot.journey.controller;

import my.boot.journey.pojo.User;
import my.boot.journey.service.UserService;
import my.boot.journey.utils.AppResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("users")
    public AppResponse getUser(){
        AppResponse<List<User>> appResponse = new AppResponse<>();

        List<User> users = userService.getUsers();
        appResponse.setSuccess(true);
        appResponse.setData(users);
        return appResponse;
    }
}
