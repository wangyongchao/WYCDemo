package com.example.arithmetic.interview;

public class ListNode<E> {
    private Node header = new Node();
    private Node tail = new Node();

    /**
     * 尾插法1
     *
     * @param node
     */
    public void addNode(Node node) {
        Node temp = header;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = node;
    }

    /**
     * 尾插法2
     *
     * @param node
     */
    public void insertNodeTail(Node node) {
        tail.next = node;
        tail = node;
    }

    /**
     * 头插法
     */
    public void insertNodeHeader(Node node) {
        node.next = header.next;
        header.next = node;
    }


    public Node getHeaderNode() {
        return header;
    }

    public static class Node<E> {
        public E data;
        public Node<E> next;

        @Override
        public int hashCode() {
            if(data==null){
                return -1;
            }
            return data.hashCode();
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || !(o instanceof Node)) {
                return false;
            }
            return data.equals(((Node) o).data);
        }
    }

}



