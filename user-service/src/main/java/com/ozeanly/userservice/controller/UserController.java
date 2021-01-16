package com.ozeanly.userservice.controller;

import com.ozeanly.userservice.VO.ResponseTemplateVO;
import com.ozeanly.userservice.entity.User;
import com.ozeanly.userservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/")
    private User saverUser(@RequestBody User user) {
      log.info("UserController:saveUser");
      return userService.saveUser(user);
    }


    @GetMapping("/{id}")
    public ResponseTemplateVO getUserWithDepartment(@PathVariable("id") Long userId) {
        log.info("UserController:getUserWithDepartment");
        return userService.getUserWithDepartment(userId);
    }


}
