package com.example.arithmetic.tree;

import java.util.Arrays;
import java.util.List;

public class HuffmanTree<E> {

    public HuffmanTree(int[] weights) {
        Arrays.sort(weights);




    }

    private static class Entry<E> {
        int weight;//权重
        HuffmanTree.Entry leftChild;
        HuffmanTree.Entry rightChild;
        HuffmanTree.Entry parent;
    }


}
