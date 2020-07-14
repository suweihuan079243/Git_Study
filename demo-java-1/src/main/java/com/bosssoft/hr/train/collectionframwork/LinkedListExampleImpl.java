package com.bosssoft.hr.train.collectionframwork;

import com.bosssoft.hr.train.pojo.User;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author: Suweihuan
 * @date: 2020/7/12 15:18
 */
@Slf4j
public class LinkedListExampleImpl implements LinkedListExample<User> {

    public final static String LOG_TAG="j2se-basic-example-log:";
    /**=============================》ArrayListExample
     * 演示的arrayList对象
     */
    private LinkedList<User> users=new LinkedList<>();
    
    @Override
    public void addFirst(User node) {
        users.addFirst(node);
    }

    @Override
    public boolean offer(User node) {
        return users.offer(node);
    }

    @Override
    public void sychronizedVisit(User node) {
        Collections.synchronizedCollection(users).add(node);
    }

    @Override
    public void push(User node) {
        users.push(node);
    }

    @Override
    public User pop() {
        return users.pop();
    }

    @Override
    public boolean append(User user) {
        return users.add(user);
    }

    @Override
    public User get(Integer index) {
        return index>=0 && index< users.size() ? users.get(index): null;
    }

    @Override
    public boolean remove(Integer position) {
        return position>=0 && position<users.size() ? users.remove(position.intValue())!=null
                : false;
    }

    @Override
    public void listByIndex() {
        for(int i=0;i<users.size();i++){
            log.info(LOG_TAG,users.get(i));
        }
    }
    
    public boolean insert(Integer position, User user) {
        if (position>=0 && position<=users.size()) {
            users.add(position,user);
            return true;
        }else {
            return false;
        }

    }
    

    @Override
    public void listByIterator() {
        Iterator<User> iterator = users.iterator();
        while(iterator.hasNext()){
            User user=iterator.next();
            log.info(LOG_TAG,user);
        }
    }

    @Override
    public User[] toArray() {
        return users!=null?users.toArray(new User[0]):new User[0];
    }

    @Override
    public void sort() {
        users.sort((o1,o2)->o1.getId()-o2.getId());
    }
}
