package com.example.arithmetic.link;

public class LinkNode<E> {

    Node<E> header = new Node<E>(null);
    Node<E> tail = new Node<E>(null);

    public LinkNode() {
        this.header.next = null;
    }

    public static class Node<E> {
        public Node(E data) {
            this.data = data;
        }

        E data;
        Node next;
    }

    /**
     * 尾插法 从头开始遍历到链表最后一个元素，然后最后一个元素指向新元素
     * @param node
     */
    public void add(Node<E> node) {
        Node temp = header;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = node;
    }

    /**
     * 尾插法，尾指针始终指向最后一个元素
     * @param node
     */
    public void insertNodeTail(Node node) {
        tail.next = node;
        tail = node;
    }

    /**
     * 头插法
     * @param node
     */
    public void insertNodeHeader(Node node) {

    }






    @Override
    public String toString() {
        String result = "";
        Node temp = header;
        while (temp.next != null) {
            temp = temp.next;
            result = result + temp.data+">";
        }

        return result;
    }

    public Node getHeaderNode() {
        return header;
    }

    public static LinkNode create() {
        LinkNode<Integer> linkNode = new LinkNode<>();
        for (int i = 1; i < 11; i++) {
            linkNode.insertNodeTail(new Node(i));
        }
        return linkNode;

    }


}
