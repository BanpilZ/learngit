package com.banpil.demo.test.struct;

/**
 * Author: Banpil {@link zlw459@126.com}
 * Date: 2018/1/10 16:37
 */
public class Node<T> {

    public T data;

    public Node<T> next;

    public Node(T data, Node<T> next) {
        this.data = data;
        this.next = next;
    }

    public Node(T data) {
        this.data = data;
    }

    public Node() {

    }
}
