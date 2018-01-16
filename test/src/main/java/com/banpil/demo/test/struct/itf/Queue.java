package com.banpil.demo.test.struct.itf;

/**
 * Author: Banpil {@link zlw459@126.com}
 * Date: 2018/1/15 10:41
 */
public interface Queue<T> {

    int size();

    boolean isEmpty();

    boolean add(T data);

    boolean offer(T data);

    T peek();

    T element();

    T poll();

    T remove();

    void clearQueue();
}
