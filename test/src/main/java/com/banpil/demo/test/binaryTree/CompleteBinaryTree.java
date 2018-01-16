package com.banpil.demo.test.binaryTree;

import com.banpil.demo.test.struct.LinkedQueue;

/**
 * Author: Banpil {@link zlw459@126.com}
 * Date: 2018/1/15 13:44
 */
public class CompleteBinaryTree<T extends Comparable> extends BinarySearchTree<T> {

    public CompleteBinaryTree(BinaryNode<T> root) {
        super();
    }

    //根据一个层次遍历数组创建完全二叉树
    public CompleteBinaryTree(T[] levelOrderArray) {
        this.root = create(levelOrderArray, 0);
    }

    private BinaryNode<T> create(T[] levelOrderArray, int i) {
        if(levelOrderArray ==null){
            throw new RuntimeException("the param 'array' of create method can\'t be null !");
        }
        BinaryNode<T> p = null;

        if (i < levelOrderArray.length) {
            p = new BinaryNode<>(levelOrderArray[i], null, null);
            //2i+1<n则为i的左子节点，否则无左子节点
            p.left = create(levelOrderArray, 2*i + 1);
            //2i+2>n则为i的右子节点，否则无右子节点
            p.right = create(levelOrderArray, 2*i + 2);
        }
        return p;
    }

    @Override
    public void insert(T data) {}

    @Override
    public void remove(T data) {}

    @Override
    public boolean contains(T data){
        LinkedQueue<BinaryNode<T>> queue = new LinkedQueue<>();
        BinaryNode<T> p = this.root;

        while (p != null) {
            if (data.compareTo(p.data) == 0) {
                return true;
            }
            if (p.left != null) {
                queue.offer(p.left);
            }
            if (p.right != null) {
                queue.offer(p.right);
            }
            p = queue.poll();
        }
        return false;
    }

    //根据先根和中根遍历顺序确定一个二叉树
    public BinaryNode<T> createBinaryTreeByPreInOrder(T[] preList, T[] inList, int preStart, int preEnd, int inStart, int inEnd) {
        BinaryNode<T> p = new BinaryNode<>(preList[preStart]);
        if (preStart == preEnd && inStart == inEnd) {
            return p;
        }
        int root;
        for (root = inStart; root < inEnd; root++) {
            //如果中根次序中的元素值与先根次序的根结点相当,则该下标index即为inList中的根结点下标
            if (preList[preStart].compareTo(inList[root])==0){
                break;
            }
        }
        int leftLength = root - inStart;
        int rightLength = inEnd - root;

        if (leftLength > 0) {
            p.left = createBinaryTreeByPreInOrder(preList, inList, preStart + 1, preStart + leftLength, inStart, root - 1);
        }
        if (rightLength > 0) {
            p.right = createBinaryTreeByPreInOrder(preList, inList, preStart + leftLength + 1, preEnd, root + 1, inEnd);
        }
        return p;
    }

    //根据后根和中根遍历顺序确定一个二叉树
    public BinaryNode<T> createBinaryTreeByPostInOrder(T[] postList, T[] inList, int postStart, int postEnd, int inStart, int inEnd) {
        BinaryNode p = new BinaryNode(postList[postStart]);
        if (postStart == postEnd && inStart == inEnd) {
            return p;
        }
        int root;
        for (root = inStart;root < inEnd; root++) {
            if (postList[postEnd].compareTo(inList[root]) == 0) {
                break;
            }
        }
        int leftLength = root - inStart;
        int rightLength = inEnd - root;

        if (leftLength > 0) {
            p.left = createBinaryTreeByPostInOrder(postList, inList, postStart, postStart + leftLength - 1, inStart, root - 1);
        }
        if (rightLength > 0) {
            p.right = createBinaryTreeByPostInOrder(postList, inList, postStart + leftLength, postEnd - 1, root + 1, inEnd);
        }
        return p;
    }

    public static void main(String[] args) {
        String[] levelOrderArray = {"A","B","C","D","E","F"};
        CompleteBinaryTree<String> cbTree = new CompleteBinaryTree<>(levelOrderArray);
        System.out.println("先根遍历:"+cbTree.preOrder());
        System.out.println("非递归先根遍历:"+cbTree.preOrderUnrecurse());
        System.out.println("中根遍历:"+cbTree.inOrder());
        System.out.println("非递归中根遍历:"+cbTree.inOrderUnrecurse());
        System.out.println("后根遍历:"+cbTree.postOrder());
        System.out.println("非递归后根遍历:"+cbTree.postOrderUnrecurse());
        System.out.println("查找最大结点(根据搜索二叉树):"+cbTree.findMax());
        System.out.println("查找最小结点(根据搜索二叉树):"+cbTree.findMin());
        System.out.println("判断二叉树中是否存在E:"+cbTree.contains("E"));
    }
}
