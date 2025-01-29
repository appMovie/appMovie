package mindera.porto.AppMovie.controller;

import mindera.porto.AppMovie.model.User;
import mindera.porto.AppMovie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path= "/api/v1/user")

public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService=userService;
    }

    @PostMapping("/add")
    public void addNewUser(@RequestBody User user){
        userService.addNewUser(user);
    }

    @GetMapping("/list")
    public List<User> getUsers(){
        return userService.getUsers();
    }


}
