package my.boot.journey.controller;

import my.boot.journey.utils.AppResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class UserController {

    @GetMapping("users")
    public AppResponse getUser(){
        AppResponse<String> appResponse = new AppResponse<>();
        appResponse.setSuccess(true);
        appResponse.setData("list of users");
        return appResponse;
    }
}
