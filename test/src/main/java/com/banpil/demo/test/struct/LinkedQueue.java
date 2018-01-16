package com.banpil.demo.test.struct;

import com.banpil.demo.test.struct.itf.Queue;

import java.io.Serializable;

/**
 * Author: Banpil {@link zlw459@126.com}
 * Date: 2018/1/15 10:42
 */
public class LinkedQueue<T> implements Queue<T>, Serializable {

    private static final long serialVersionUID = -2854840234401722950L;

    private Node<T> head, tail;

    private int size;

    private int maxSize = 128;

    public LinkedQueue() {
        this.head = this.tail = null;
    }

    @Override
    public int size() {
        return size;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    @Override
    public boolean isEmpty() {
        return head == null && tail == null;
    }

    @Override
    public boolean add(T data) {
        Node node = new Node(data, null);
        if (size < 128) {
            if (head == null) {
                head = node;
            } else {
                tail.next = node;
            }
            tail = node;
            size++;
            return true;
        }
        return false;
    }

    @Override
    public boolean offer(T data) {
        if (data==null)
            throw new NullPointerException("The data can't be null");
        if (size>=maxSize)
            throw new IllegalArgumentException("The capacity of LinkedQueue has reached its maxSize:128");

        Node node = new Node(data, null);
        if (head == null) {
            head = node;
        } else {
            tail.next = node;
        }
        tail = node;
        size++;
        return true;
    }

    @Override
    public T peek() {
        return this.isEmpty() ? null : head.data;
    }

    @Override
    public T element() {
        if (isEmpty()) {
            throw new RuntimeException("The LinkedQueue is empty");
        }
        return this.head.data;
    }

    @Override
    public T poll() {
        if (isEmpty()) {
            return null;
        }
        T x = head.data;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        size--;
        return x;
    }

    @Override
    public T remove() {
        if (isEmpty()){
            throw new RuntimeException("The LinkedQueue is empty");
        }
        T x = head.data;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        size--;
        return x;
    }

    @Override
    public void clearQueue() {
        head = tail = null;
        size = 0;
    }
}
