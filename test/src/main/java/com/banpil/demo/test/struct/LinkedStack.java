package com.banpil.demo.test.struct;

import com.banpil.demo.test.struct.itf.Stack;

import java.io.Serializable;

/**
 * Author: Banpil {@link zlw459@126.com}
 * Date: 2018/1/10 16:06
 */
public class LinkedStack<T> implements Stack<T>, Serializable {

    private static final long serialVersionUID = -8345785862354023723L;

    private Node<T> top;

    private int size;

    public LinkedStack() {
        this.top = new Node();
    }

    @Override
    public boolean isEmpty() {
        return top == null || top.data == null;
    }

    @Override
    public void push(T data) {
        if (data == null) {
            throw new RuntimeException("data can't be null!");
        }

        if (this.top == null) {
            this.top = new Node<>(data);
        } else if (this.top.data == null) {
            this.top.data = data;
        } else {
            Node node = new Node(data, this.top);
            top = node;
        }
        size++;
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Empty stack!");
        }
        return top.data;
    }

    @Override
    public T pop() {
        if (isEmpty()) {
            throw new RuntimeException("Empty stack!");
        }

        T data = top.data;
        top = top.next;
        size--;
        return data;
    }

    @Override
    public int size() {
        return size;
    }
}