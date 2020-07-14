package com.bosssoft.hr.train.collectionframwork;



import com.bosssoft.hr.train.pojo.User;

import java.util.Stack;

public class StackExampleImpl implements StackExample<User> {
    Stack<User> users = new Stack<>();

    @Override
    public User push(User item) {
        return users.push(item);
    }

    @Override
    public User pop() {
        return users.pop();
    }

    @Override
    public User peek() {
        return users.peek();
    }

    @Override
    public boolean empty() {
        return users.empty();
    }

    @Override
    public int search(User user) {
        return users.search(user);
    }

    public Stack<User> getUsers() {
        return users;
    }

    public void setUsers(Stack<User> users) {
        this.users = users;
    }
}
