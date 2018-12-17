package com.example.arithmetic.tree;

import java.util.Arrays;
import java.util.List;

/**
 * Created by wangyongchao on 2018/8/14.
 */

public class TreeTest {

    public static void main(String[] args) {
        testHuffmanTree();


    }

    private static void testHuffmanTree() {
        int[] weights = {3, 5, 4, 6, 9};
        HuffmanTree huffmanTree = new HuffmanTree(weights);

    }


    /**
     * 构建二叉树
     */
    private static void testBinaryTree() {
        String tree = "A,B,D,H,#,K,#,#,#,E,#,#,C,F,I,#,#,#,G,#,J,#,#";
        BinaryTree binaryTree = new BinaryTree(tree);
        binaryTree.orderTraverseUnRecursion(binaryTree.getRoot());
    }

    /**
     * 线索二叉树
     */
    private static void testThreadBinaryTree() {
        String tree = "A,B,D,H,#,#,I,#,#,E,J,#,#,#,C,F,#,#,G,#,#";
        BinaryTree binaryTree = new BinaryTree(tree);
        binaryTree.addThreadBinaryTreeHeader(binaryTree.getRoot());
        binaryTree.revertTraverseByHeader(binaryTree.getHeader());
    }


    /**
     * 双亲表示法
     */
    private static void testTreeParent() {
        TreeParent<String> tree = new TreeParent<String>("A");
        tree.add("B", "A");
        tree.add("C", "A");
        tree.add("D", "B");
        tree.add("E", "C");
        tree.add("F", "C");
        tree.add("J", "E");
        tree.add("G", "D");
        tree.add("H", "D");
        tree.add("I", "D");

        List<String> children = tree.getChildren("D");
        System.out.println(children);
    }

    /**
     * 孩子表示法
     */
    private static void testChildren() {
        TreeChildren<String> tree = new TreeChildren<String>("A");
        tree.add("B", "A");
        tree.add("C", "A");
        tree.add("D", "B");
        tree.add("E", "C");
        tree.add("F", "C");
        tree.add("J", "E");
        tree.add("G", "D");
        tree.add("H", "D");
        tree.add("I", "D");

        List<String> children = tree.getChildren("B");
        System.out.println(children);
    }


}
