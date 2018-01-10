package com.banpil.demo.test.struct.itf;

/**
 * Author: Banpil {@link zlw459@126.com}
 * Date: 2018/1/10 16:12
 */
public interface Stack<T> {

    boolean isEmpty();

    void push(T data);

    T peek();

    T pop();

    int size();
}
