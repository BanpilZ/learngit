package com.banpil.demo.test.binaryTree;

import java.io.Serializable;
import java.util.Objects;

/**
 * Author: Banpil {@link zlw459@126.com}
 * Date: 2018/1/9 16:45
 */
public class BinaryNode<T extends Comparable> implements Serializable {

    private static final long serialVersionUID = -2123478006040323175L;

    public BinaryNode<T> left;

    public BinaryNode<T> right;

    public T data;

    public BinaryNode(T data, BinaryNode<T> left, BinaryNode<T> right) {
        this.left = left;
        this.right = right;
        this.data = data;
    }

    public BinaryNode(T data) {
        this.data = data;
    }

    public boolean isLeaf() {
        return Objects.isNull(this.left) && Objects.isNull(this.right);
    }
}