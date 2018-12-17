package com.example.arithmetic.tree;

import com.example.arithmetic.linear.LinkedList;
import com.example.arithmetic.linear.Stack;

/**
 * 二叉树创建和遍历
 *
 * @param <E>
 */
public class BinaryTree<E> {

    private Entry root;
    private String[] treeNodes;
    private int index = 0;
    private Entry pre;
    private Entry header = new Entry("header");

    /**
     *
     */
    public BinaryTree(String tree) {
        root = new Entry("");
        treeNodes = tree.split(",");
        index = 0;
        createTreeByPreOrder(root);
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
        int ltag;//为0时，指向左孩子，为1时，指向前驱
        int rtag;//为0时，指向右孩子，为1时，指向后继

        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("data:" + data);
            stringBuilder.append(",");
            stringBuilder.append("left:" + (leftChild != null ? leftChild.data : null));
            stringBuilder.append(",");
            stringBuilder.append("right:" + (rightChild != null ? rightChild.data : null));
            stringBuilder.append(",");
            stringBuilder.append("ltag:" + ltag);
            stringBuilder.append(",");
            stringBuilder.append("rtag:" + rtag);
            return stringBuilder.toString();
        }

    }


    public Entry getRoot() {
        return root;
    }

    public Entry getHeader() {
        return header;
    }

    /**
     * 层次遍历
     * ABCDEFGHIJK
     *
     * @param root
     */
    public void orderTraverseUnRecursion(Entry root) {
        LinkedList<Entry> linkedList = new LinkedList<>();
        linkedList.add(root);
        while (!linkedList.isEmpty()) {
            Entry entry = linkedList.poll();
            System.out.print(entry.data);
            if (entry.leftChild != null) {
                linkedList.offer(entry.leftChild);
            }
            if (entry.rightChild != null) {
                linkedList.offer(entry.rightChild);
            }

        }

    }


    /**
     * 前序遍历非递归算法
     * ABDHKECFIGJ
     */
    public void preOrderTraverseUnRecursion(Entry root) {
        Stack<Entry> stack = new Stack<>();

        Entry entry = root;
        while (entry != null || stack.size() != 0) {
            if (entry != null) {
                System.out.print(entry);
                stack.push(entry);
                entry = entry.leftChild;
            } else {
                entry = stack.pop();
                entry = entry.rightChild;
            }

        }
    }

    /**
     * 前序遍历递归算法
     * ABDHKECFIGJ
     * 方法压栈
     */
    public void preOrderTraverse(Entry entry) {
        if (entry == null) {
            return;
        }
        System.out.println(entry);
        preOrderTraverse(entry.ltag == 0 ? entry.leftChild : null);
        preOrderTraverse(entry.rtag == 0 ? entry.rightChild : null);
    }


    /**
     * 中序遍历非递归算法
     * HKDBEAIFCGJ
     */
    public void inOrderTraverseUnRecursion(Entry root) {
        Stack<Entry> stack = new Stack<>();

        Entry entry = root;
        while (entry != null || stack.size() != 0) {
            if (entry != null) {
                stack.push(entry);
                entry = entry.leftChild;
            } else {
                entry = stack.pop();
                System.out.print(entry);
                entry = entry.rightChild;
            }

        }
    }

    /**
     * 中序遍历递归算法
     * HKDBEAIFCGJ
     *
     * @param entry
     */
    public void inOrderTraverse(Entry entry) {
        if (entry == null) {
            return;
        }
        inOrderTraverse(entry.ltag == 0 ? entry.leftChild : null);
        System.out.println(entry);
        inOrderTraverse(entry.rtag == 0 ? entry.rightChild : null);
    }

    /**
     * 后序遍历非递归算法
     * KHDEBIFJGCA
     */
    public void postOrderTraverseUnRecursion(Entry root) {
        Stack<Entry> stack = new Stack<>();
        Stack<Entry> output = new Stack<>();

        Entry entry = root;
        while (entry != null || stack.size() != 0) {
            if (entry != null) {
                output.push(entry);
                stack.push(entry);
                entry = entry.rightChild;
            } else {
                entry = stack.pop();
                entry = entry.leftChild;
            }
        }
        while (output.size() > 0) {
            System.out.print(output.pop());
        }
    }


    /**
     * 后序遍历递归算法
     * KHDEBIFJGCA
     *
     * @param entry
     */
    public void postOrderTraverse(Entry entry) {
        if (entry == null) {
            return;
        }
        postOrderTraverse(entry.leftChild);
        postOrderTraverse(entry.rightChild);
        System.out.print(entry.data);
    }


    /**
     * 前序创建二叉树
     * A,B,D,H,#,K,#,#,#,E,#,#,C,F,I,#,#,#,G,#,J,#,#
     */
    public Entry createTreeByPreOrder(Entry entry) {
        String data = treeNodes[index];
        index++;

        if (data.equals("#")) {
            return null;
        } else {
            entry.data = data;
            entry.leftChild = createTreeByPreOrder(new Entry(""));
            entry.rightChild = createTreeByPreOrder(new Entry(""));
            return entry;
        }

    }

    /**
     * 中序创建二叉树
     * #H#K#D#B#E#A#I#F#C#G#J#
     */
    public Entry createTreeByInOrder(Entry entry) {
        entry.leftChild = createTreeByInOrder(new Entry(""));
        String data = treeNodes[index];
        index++;
        entry.data = data;
        entry.rightChild = createTreeByInOrder(new Entry(""));

        return entry;

    }

    /**
     * 中序线索化二叉树
     *
     * @param entry
     * @return
     */

    public void threadBinaryTreeByInOrder(Entry root) {
        Entry entry = root;
        if (entry == null) {
            return;
        }
        threadBinaryTreeByInOrder(entry.leftChild);
        if (entry.leftChild == null && pre != null) {
            entry.leftChild = pre;
            entry.ltag = 1;
        }
        if (pre != null && pre.rightChild == null) {
            pre.rightChild = entry;
            pre.rtag = 1;
        }
        pre = entry;
        threadBinaryTreeByInOrder(entry.rightChild);
    }

    /**
     * 线索二叉树添加头结点
     *
     * @param root
     */
    public void addThreadBinaryTreeHeader(Entry root) {
        header.rtag = 1;
        header.rightChild = header;

        if (root == null) {
            header.ltag = 1;
            header.leftChild = header;
        } else {
            pre = header;
            header.ltag = 1;
            header.leftChild = root;
            threadBinaryTreeByInOrder(root);
            pre.rightChild = header;
            pre.rtag = 1;
            header.rightChild = pre;
        }
    }

    /**
     * 正向遍历线索二叉树
     *
     * @param header
     */
    public void traverseByHeader(Entry header) {
        Entry entry = header.leftChild;
        while (entry != header) {
            while (entry.ltag == 0) {
                entry = entry.leftChild;
            }
            System.out.println(entry);
            while (entry.rtag == 1 && entry.rightChild != header) {
                entry = entry.rightChild;
                System.out.println(entry);
            }
            entry = entry.rightChild;
        }
        System.out.println(header);

    }

    /**
     * 逆向遍历线索二叉树
     *
     * @param header
     */
    public void revertTraverseByHeader(Entry header) {
        Entry entry = header.rightChild;
        while (entry != header) {
            while (entry.rtag == 0) {
                entry = entry.rightChild;
            }
            System.out.println(entry);
            while (entry.ltag == 1 && entry.leftChild != header) {
                entry = entry.leftChild;
                System.out.println(entry);
            }
            entry = entry.leftChild;
        }
        System.out.println(header);

    }


    /**
     * 前序线索化二叉树
     *
     * @param entry
     * @return
     */
    public void threadBinaryTreeByPreOrder(Entry root) {
        Entry entry = root;
        if (entry == null) {
            return;
        }
        if (entry.leftChild == null) {
            entry.leftChild = pre;
            entry.ltag = 1;
        }
        if (pre != null && pre.rightChild == null) {
            pre.rightChild = entry;
            pre.rtag = 1;
        }
        pre = entry;
        threadBinaryTreeByPreOrder(entry.ltag == 0 ? entry.leftChild : null);
        threadBinaryTreeByPreOrder(entry.rtag == 0 ? entry.rightChild : null);
    }




}
