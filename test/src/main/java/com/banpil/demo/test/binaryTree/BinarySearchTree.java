package com.banpil.demo.test.binaryTree;

import com.banpil.demo.test.struct.LinkedStack;

import java.util.Objects;

/**
 * Author: Banpil {@link zlw459@126.com}
 * Date: 2018/1/9 16:59
 */
public class BinarySearchTree<T extends Comparable> implements Tree<T> {

    protected  BinaryNode<T> root;

    public BinarySearchTree(BinaryNode<T> root) {
        this.root = root;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(BinaryNode<T> p) {
        if (p == null) {
            return 0;
        } else {
            return size(p.left) + size(p.right) + 1;
        }
    }

    @Override
    public int height() {
        return height(root);
    }

    private int height(BinaryNode<T> p) {
        if (p == null) {
            return 0;
        } else {
            int l = height(p.left);
            int r = height(p.right);
            return l>r ? l+1 : r+1;
        }
    }

    @Override
    public String preOrder() {
        String sb = preOrder(root);
        if (sb.length() > 0) {
            sb = sb.substring(0, sb.length() - 1);
        }
        return sb;
    }

    private String preOrder(BinaryNode<T> p) {
        StringBuffer sb = new StringBuffer();
        if (p != null) {
            sb.append(p.data + ",");
            sb.append(preOrder(p.left));
            sb.append(preOrder(p.right));
        }
        return sb.toString();
    }

    public String preOrderUnrecurse() {
        StringBuffer sb = new StringBuffer();
        LinkedStack<BinaryNode<T>> stack = new LinkedStack<>();
        BinaryNode<T> p = this.root;

        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                sb.append(p.data + ",");
                stack.push(p);
                p = p.left;
            } else {
                p = stack.pop();
                p = p.right;
            }
        }
        if (sb.length() > 0) {
            return sb.substring(0, sb.length() - 1);
        } else {
            return sb.toString();
        }
    }

    @Override
    public String inOrder() {
        String sb = inOrder(root);
        if (sb.length() > 0) {
            sb = sb.substring(0, sb.length() - 1);
        }
        return sb;
    }

    private String inOrder(BinaryNode<T> p) {
        StringBuffer sb = new StringBuffer();
        if (p != null) {
            sb.append(inOrder(p.left));
            sb.append(p.data + ",");
            sb.append(inOrder(p.right));
        }
        return sb.toString();
    }

    public String inOrderUnrecurse() {
        StringBuffer sb = new StringBuffer();
        LinkedStack<BinaryNode<T>> stack = new LinkedStack<>();
        BinaryNode<T> p = this.root;

        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                stack.push(p);
                p = p.left;
            } else {
                p = stack.pop();
                sb.append(p.data + ",");
                p = p.right;
            }
        }

        if (sb.length() > 0) {
            return sb.substring(0, sb.length() - 1);
        } else {
            return sb.toString();
        }
    }

    @Override
    public String postOrder() {
        String sb = postOrder(root);
        if (sb.length() > 0) {
            sb = sb.substring(0, sb.length() - 1);
        }
        return sb.toString();
    }

    private String postOrder(BinaryNode<T> p) {
        StringBuffer sb = new StringBuffer();

        if (p != null) {
            sb.append(postOrder(p.left));
            sb.append(postOrder(p.right));
            sb.append(p.data + ",");
        }
        return sb.toString();
    }

    public String postOrderUnrecurse() {
        StringBuffer sb = new StringBuffer();
        LinkedStack<BinaryNode<T>> stack = new LinkedStack<>();
        BinaryNode<T> current = this.root;
        BinaryNode<T> prev = this.root;

        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            if (!stack.isEmpty()) {
                BinaryNode<T> temp = stack.peek().right;
                if (temp == null || temp == prev) {
                    current = stack.pop();
                    sb.append(current.data + ",");
                    prev = current;
                    current = null;
                } else {
                    current = temp;
                }
            }
        }

        if (sb.length() > 0) {
            return sb.substring(0, sb.length() - 1);
        } else {
            return sb.toString();
        }
    }

    @Override
    public String levelOrder() {
        return null;
    }

    @Override
    public void insert(T data) {
        if (Objects.isNull(data)) {
            throw new RuntimeException("insert data can't be none!");
        }
        root = insert(data, root);
    }

    private BinaryNode<T> insert(T data, BinaryNode<T> p) {
        if (p == null) {
            p = new BinaryNode<>(data, null, null);
        }

        int result = data.compareTo(p.data);
        if (result < 0) {
            p.left = insert(data, p.left);
        } else if (result > 0 ) {
            p.right = insert(data, p.right);
        }
        return p;
    }

    @Override
    public void remove(T data) {
        if (data == null) {
            throw new RuntimeException("remove data can't be none!");
        }
        root = remove(data, root);
    }

    private BinaryNode<T> remove(T data, BinaryNode<T> p) {
        if (p == null) {
            return p;
        }

        int result = data.compareTo(p.data);
        if (result < 0) {
            p.left = remove(data, p.left);
        } else if (result > 0) {
            p.right = remove(data, p.right);
        } else if (p.left != null && p.right != null) {
            p.data = findMin(p.right).data;
            p.right = remove(p.data, p.right);
        } else {
            p = (p.left != null) ? p.left : p.right;
        }
        return p;
    }

    @Override
    public T findMin() {
        if (isEmpty()) {
            throw new RuntimeException("Binary search tree is empty!");
        }
        return findMin(root).data;
    }

    private BinaryNode<T> findMin(BinaryNode<T> p) {
        if (p == null) {
            return null;
        } else if (p.left == null) {
            return p;
        }
        return findMin(p.left);
    }

    @Override
    public T findMax() {
        if (isEmpty()) {
            throw new RuntimeException("Binary search tree is empty!");
        }
        return findMax(root).data;
    }

    private BinaryNode<T> findMax(BinaryNode<T> p) {
        if (p == null) {
            return null;
        } else if (p.right == null) {
            return p;
        }
        return findMax(p.right);
    }

    @Override
    public BinaryNode findNode(T data) {
        if (isEmpty()) {
            throw new RuntimeException("Binary search tree is empty!");
        }
        return findNode(data, root);
    }

    private BinaryNode findNode(T data, BinaryNode<T> p) {
        if (data == null) {
            return null;
        }

        int result = data.compareTo(p.data);
        if (result < 0) {
            p = findNode(data, p.left);
        } else if (result > 0) {
            p = findNode(data, p.right);
        }
        return p;
    }

    @Override
    public boolean contains(T data) throws Exception {
        return findNode(data) == null;
    }

    @Override
    public void clear() {
        root = null;
    }
    public T removeUnrecurse(T data) {
        if (data == null) {
            throw new RuntimeException("data can't be null!");
        }

        BinaryNode<T> current = this.root;
        BinaryNode<T> parent = this.root;
        boolean isLeft = true;
        int result = data.compareTo(current.data);

        while (result != 0) {
            parent = current;
            result = data.compareTo(current.data);

            if (result < 0) {
                isLeft = true;
                current = current.left;
            } else if (result > 0) {
                isLeft = false;
                current = current.right;
            }
            if (current == null) {
                return null;
            }
        }

        if (current.left == null && current.right == null) {
            if (current == this.root) {
                this.root = null;
            } else if (isLeft) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        } else if (current.left == null) {
            if (current == this.root) {
                this.root = current.right;
            } else if (isLeft) {
                parent.left = current.right;
            } else {
                parent.right = current.right;
            }
        } else if (current.right == null) {
            if (current == this.root) {
                this.root = current.left;
            } else if (isLeft) {
                parent.left = current.left;
            } else {
                parent.right = current.left;
            }
        } else {
            BinaryNode successor = findSuccessor(current);

            if (current == root) {
                this.root = successor;
            } else if (isLeft) {
                parent.left = successor;
            } else {
                parent.right = successor;
            }
        }
        return current.data;
    }

    public BinaryNode findSuccessor(BinaryNode delNode) {
        BinaryNode successor = delNode;
        BinaryNode parent = delNode;
        BinaryNode current = delNode;

        while (current != null) {
            parent = successor;
            successor = current;
            current = current.left;
        }
        if (successor != delNode.right) {
            parent.left = successor.right;
            successor.right = delNode.right;
        }
        return successor;
    }
}