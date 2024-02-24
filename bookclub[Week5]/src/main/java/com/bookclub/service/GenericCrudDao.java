package com.bookclub.service;

import java.util.List;

public interface GenericCrudDao<E, K> {
    void add (E entity);        // C- crud create
    List<E> list();             // R - crud read - return a list of objects E
        E find(K key);          //   - find the value of K in a list of E
    void update(E entity);      // U - crud Update
    boolean remove(E entity);   // D-  crud Delete
}
