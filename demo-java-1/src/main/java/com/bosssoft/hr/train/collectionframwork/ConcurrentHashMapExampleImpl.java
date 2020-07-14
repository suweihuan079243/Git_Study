package com.bosssoft.hr.train.collectionframwork;

import com.bosssoft.hr.train.pojo.Resource;
import com.bosssoft.hr.train.pojo.Role;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description: 该类主要是为了测试 ConcurrentHashmap 用上此类不用担心并发问题了
 * @author: Administrator
 * @create: 2020-05-28 20:58
 * @since
 **/
@Slf4j
public class ConcurrentHashMapExampleImpl implements HashMapExample<Role, Resource> {
    /**
     * 定义 ConcurrentHashMap对象用于测试
     */
    private Map<Role, Resource> map = new ConcurrentHashMap<>();

    /**
     * 然后该方法不用加 synchronized 也是线程安全，但是操作并不因为锁定，这个就是
     * 该类对hashtable的改进
     *
     * @param key   map的键
     * @param value 值
     * @return 返回的是前一个的值或者是null
     */
    @Override
    public Resource put(Role key, Resource value) {
        return map.put(key, value);
    }

    @Override
    public Resource remove(Role key) {
        // 因为 重写了 Role 的 equal 和 hashcode 所以保证根据id删除
        return map.remove(key);
    }

    @Override
    public boolean containsKey(Role key) {
        return map.containsKey(key);
    }

    @Override
    public void visitByEntryset() {
        Set<Map.Entry<Role, Resource>> set = map.entrySet();
        for (Map.Entry<Role, Resource> entry : set) {
            log.info(Constraint.LOG_TAG, entry.getKey() + "####" + entry.getValue());
        }
    }

    /**
     * 请比较 传统的for遍历和 lambda 的 forEach 代码简洁效率高，
     * 请在JDK1.8以上的代码中执行
     */
    public void visitByEntrySetLambda() {
        Set<Map.Entry<Role, Resource>> set = map.entrySet();
        set.stream().forEach(entry -> log.info(Constraint.LOG_TAG, entry.getKey() + "####" + entry.getValue()));
    }


    @Override
    public void visitByKeyset() {
        if (null != map) {
            Set<Role> set = map.keySet();
            Iterator<Role> iterator = set.iterator();
            while (iterator.hasNext()) {
                Role role = iterator.next();
                log.info(Constraint.LOG_TAG, role);
                log.info(Constraint.LOG_TAG, map.get(role));
            }
        }
    }

    @Override
    public void visitByValues() {
        Collection<Resource> collection = map.values();
        Iterator<Resource> iterator = collection.iterator();
        while (iterator.hasNext()) {
            Resource resource = iterator.next();
            log.info(Constraint.LOG_TAG, resource);
        }
    }
}
