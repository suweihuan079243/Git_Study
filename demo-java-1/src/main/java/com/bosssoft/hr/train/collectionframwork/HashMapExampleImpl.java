package com.bosssoft.hr.train.collectionframwork;

import com.bosssoft.hr.train.pojo.Resource;
import com.bosssoft.hr.train.pojo.Role;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author: Suweihuan
 * @date: 2020/7/12 14:01
 */
@Slf4j
public class HashMapExampleImpl implements HashMapExample<Role, Resource> {

    private Map<Role, Resource> map = new HashMap<>();
    
    private String TAG="j2se-basic-example-log:";

    @Override
    public Resource put(Role key, Resource value) {
        return map.put(key, value);
    }

    @Override
    public Resource remove(Role key) {
        return map.remove(key);

    }

    @Override
    public boolean containsKey(Role key) {
        return map.containsKey(key);
    }

    @Override
    public void visitByEntryset() {
        Set<Map.Entry<Role, Resource>> entries = map.entrySet();
        for (Map.Entry<Role, Resource> entry : entries) {
            log.info(TAG+entry.getKey()+"---"+entry.getValue());
        }

    }

    @Override
    public void visitByKeyset() {
        if (map!=null){
            Set<Role> keys = map.keySet();
            for (Role key : keys) {
                log.info(TAG+key+"---"+map.get(key));
            }
        }
        
    }

    @Override
    public void visitByValues() {
        if (map!=null){
            Collection<Resource> values = map.values();
            for (Resource value : values) {
                log.info(TAG+value);
            }
        }
    }
}
