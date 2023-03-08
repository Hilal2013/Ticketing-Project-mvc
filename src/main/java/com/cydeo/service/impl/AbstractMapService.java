package com.cydeo.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractMapService<T, ID> {//template//I dont need object of this

    //save where we gonna save?database
    public Map<ID, T> map = new HashMap<>(); //database//we dont have layer date now Im creating by myself
    //T is value ID(String or Long) is key
    T save(ID id, T object) {

        map.put(id, object);
        return object;

    }
    List<T> findAll(){

       return new ArrayList<>(map.values());
    }
    T findById(ID id){

        return map.get(id);
    }
    void deleteById(ID id){
map.remove(id);
    }


}
