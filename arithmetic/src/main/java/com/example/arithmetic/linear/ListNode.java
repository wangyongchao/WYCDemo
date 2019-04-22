package com.example.arithmetic.linear;

public class ListNode<E> {
    private Node header = new Node();

    public void addNode(Node node) {
        Node temp = header;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = node;
    }

    public Node getHeaderNode() {
        return header;
    }

    public static class Node<E> {
        E data;
        Node<E> next;
    }

}



