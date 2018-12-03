package com.example.arithmetic.tree;

public class BinaryTree<E> {

    private Entry root;
    private String[] treeNodes;
    private int index = 0;


    /**
     * AB#D##C##
     */
    public BinaryTree(String tree) {
        root = new Entry("");
        treeNodes = tree.split(",");
        index = 0;
        createTree(root);
    }

    /**
     * 二叉链表
     *
     * @param <E>
     */
    private static class Entry<E> {
        public Entry(E data) {
            this.data = data;
        }

        E data;
        Entry leftChild;
        Entry rightChild;
    }

    public void add(E e, E left, E right) {


    }

    public Entry getRoot() {
        return root;
    }


    /**
     * 前序遍历
     */
    public void preOrderTraverse(Entry entry) {
        if (entry == null) {
            return;
        }
        System.out.print(entry.data);
        preOrderTraverse(entry.leftChild);
        preOrderTraverse(entry.rightChild);
    }


    /**
     * 先序创建
     */
    public Entry createTree(Entry entry) {
        String data = treeNodes[index];
        System.out.println("index " + index + " data " + data);
        index++;

        if (data.equals("#")) {
            return null;
        } else {
            entry.data = data;
            entry.leftChild = createTree(new Entry(""));
            entry.rightChild = createTree(new Entry(""));
            return entry;
        }


    }
}
