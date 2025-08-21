package com.college.controllers;


import com.college.entities.User;
import com.college.responses.AvailableResponse;
import com.college.responses.BasicResponse;
import com.college.responses.UsersResponse;
import com.college.utils.DbUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
public class GeneralController {

    private List<User> allUsers = new ArrayList<>();

    @Autowired
    private DbUtils dbUtils;

    @RequestMapping ("/users")
    public List<User> getAllUsers () {
        return dbUtils.fetchAllUsers();
    }

    @RequestMapping("/add-user")
    public void addUser (String username, String password) {
        dbUtils.addUser(username, password);
    }

}
