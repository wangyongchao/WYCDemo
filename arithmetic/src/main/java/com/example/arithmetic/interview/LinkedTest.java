package com.example.arithmetic.interview;

import com.example.arithmetic.linear.ListNode;
import com.example.arithmetic.linear.Stack;

import java.util.ArrayList;

public class LinkedTest {

    public static void main(String[] args) {
        testCustomLinkedList();


    }

    private static void testCustomLinkedList() {
        ListNode listNode = createLinkedListByHead();
        ListNode.Node headerNode = listNode.getHeaderNode();



    }

    /**
     * 从头到尾打印列表
     *
     * @param listNode
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
     * @param listNode
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
     * @param listNode
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
}
