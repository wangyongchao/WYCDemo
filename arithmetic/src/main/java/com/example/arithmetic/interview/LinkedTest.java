package com.example.arithmetic.interview;

import com.example.arithmetic.linear.Stack;

import java.util.ArrayList;

public class LinkedTest {

    public static void main(String[] args) {
        ListNode listNode = createLinkedListByHead();
        printListFromHeadToTail(listNode.getHeaderNode().next);
        ListNode.Node next = listNode.getHeaderNode().next;
        ListNode.Node head = headInsert(next);
        printListFromHeadToTail(head.next);

    }

    private static void testCustomLinkedList() {
        ListNode listNode = createLinkedListByHead();
        ListNode.Node headerNode = listNode.getHeaderNode();
        ListNode.Node node = headerNode.next;


    }

    /**
     * 头插法关键代码
     * 新结点p,头结点L
     * p.next=L.next;
     * L.next=p;
     *
     * @param node
     * @return
     */
    public static ListNode.Node headInsert(ListNode.Node node) {
        ListNode.Node header = new ListNode.Node();

        while (node != null) {
            ListNode.Node next = node.next;
            node.next = header.next;
            header.next = node;
            node = next;
        }

        return header;
    }

    /**
     * 从头到尾打印列表
     *
     * @param node
     * @return
     */
    public static void printListFromHeadToTail(ListNode.Node<String> node) {
        while (node != null) {
            System.out.print(node.data);
            node = node.next;
        }
    }

    /**
     * 从尾到头打印列表
     * 栈
     *
     * @param node
     * @return
     */
    public static Stack<String> printListFromTailToHeadByStack(ListNode.Node<String> node) {
        Stack<String> ret = new Stack<>();
        while (node != null) {
            ret.push(node.data);
            node = node.next;
        }
        return ret;
    }


    /**
     * 从尾到头打印列表
     * 递归
     *
     * @param node
     * @return
     */
    public static ArrayList<String> printListFromTailToHeadByRecursion(ListNode.Node<String> node) {
        ArrayList<String> ret = new ArrayList<>();
        if (node != null) {
            ret.addAll(printListFromTailToHeadByRecursion(node.next));
            ret.add(node.data);
        }
        return ret;
    }

    private static ListNode createLinkedListByHead() {
        ListNode<String> listNode = new ListNode<>();

        for (int i = 0; i < 10; i++) {
            ListNode.Node node = new ListNode.Node();
            node.data = String.valueOf(i);
            listNode.addNode(node);
        }
        return listNode;

    }

    private static ListNode createLinkedList() {
        ListNode<String> listNode = new ListNode<>();
        ListNode.Node headerNode = listNode.getHeaderNode();

        for (int i = 0; i < 10; i++) {
            ListNode.Node node = new ListNode.Node();
            node.data = String.valueOf(i);
            node.next = headerNode.next;
            headerNode.next = node;
        }
        return listNode;

    }

    private static ListNode createLinkedList1() {
        ListNode<String> listNode = new ListNode<>();
        for (int i = 0; i < 10; i++) {
            ListNode.Node node = new ListNode.Node();
            node.data = String.valueOf(i);
            listNode.insertNodeTail(node);
        }
        return listNode;

    }
}
