package my.boot.journey.controller;

import my.boot.journey.pojo.User;
import my.boot.journey.service.UserService;
import my.boot.journey.utils.AppResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("users/{id}")
    public AppResponse getUserDetail(@PathVariable int id){
        AppResponse<User> appResponse = new AppResponse<>();

        User user = userService.getUserDetail(id);
        appResponse.setSuccess(true);
        appResponse.setData(user);
        return appResponse;
    }

    @PostMapping("users")
    public AppResponse createUser(@RequestBody User user){
        AppResponse<String> appResponse = new AppResponse<>();

        int numberCreated = userService.createUser(user);
        if(numberCreated > 0) {
            appResponse.setSuccess(true);
            appResponse.setData("User has been created");
        }else{
            appResponse.setSuccess(false);
            appResponse.setData("Error! Cannot create a new user");
        }

        return appResponse;
    }

    @PutMapping("users")
    public AppResponse updateUser(@RequestBody User user){
        AppResponse<String> appResponse = new AppResponse<>();

        int numberCreated = userService.updateUser(user);
        if(numberCreated > 0) {
            appResponse.setSuccess(true);
            appResponse.setData("User has been updated");
        }else{
            appResponse.setSuccess(false);
            appResponse.setData("Error! Cannot update a user");
        }

        return appResponse;
    }

    @DeleteMapping("users")
    public AppResponse deleteUser(@PathVariable int id){
        AppResponse<String> appResponse = new AppResponse<>();

        int numberDeleted = userService.deleteUser(id);
        if(numberDeleted > 0) {
            appResponse.setSuccess(true);
            appResponse.setData("User has been deleted");
        }else{
            appResponse.setSuccess(false);
            appResponse.setData("Error! Cannot delete a user");
        }

        return appResponse;
    }
}
