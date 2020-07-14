package com.bosssoft.hr.train.collectionframwork;

import com.bosssoft.hr.train.pojo.User;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * @author: Suweihuan
 * @date: 2020/7/11
 */
@Slf4j
public class ArrayListExampleImpl implements ArrayListExample<User> {

    private List<User> users = new ArrayList<>();

    public static final String TAG = "USER:";

    @Override
    public boolean insert(Integer position, User node) {
        if (position >= 0 && position <= users.size()) {
            users.add(node);
            return true;
        }
        return false;
    }

    @Override
    public boolean append(User user) {
        return users.add(user);
    }

    @Override
    public User get(Integer index) {
        if (index >= 0 && index < users.size()) {
            return users.get(index);
        }
        return null;
    }

    @Override
    public boolean remove(Integer position) {
        if (position >= 0 && position < users.size()) {
            users.remove(position.intValue());
            return true;
        }
        return false;
    }

    @Override
    public void listByIndex() {
        int size = users.size();
        for (int i = 0; i < size; i++) {
            log.info(TAG, users.get(i));
        }
    }

    @Override
    public void listByIterator() {
        Iterator<User> iterator = users.iterator();
        User user = null;
        while (iterator.hasNext()) {
            user = iterator.next();
            log.info(TAG, user);
        }

    }

    @Override
    public User[] toArray() {
        if (users != null) {
            return users.toArray(new User[0]);
        }
        return new User[0];
    }

    @Override
    public void sort() {
        users.sort((o1, o2) -> o1.getId() - o2.getId()
        );
    }
}
