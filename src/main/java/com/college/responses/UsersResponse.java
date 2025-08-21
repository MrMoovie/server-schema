package com.college.responses;

import com.college.entities.User;

import java.util.List;

public class UsersResponse extends BasicResponse {
    private List<User> users;

    public UsersResponse(boolean success, Integer errorCode, List<User> users) {
        super(success, errorCode);
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
