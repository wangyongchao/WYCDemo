package com.example.arithmetic.interview;

import com.example.arithmetic.linear.Stack;

import java.util.ArrayList;

public class LinkedTest {

    public static void main(String[] args) {
//        ListNode listNode = createLinkedListByHead();
//        printListByStack(listNode.getHeaderNode().next);
//        createIntersectionNode();

        createCycleList();
    }

    /**
     * 创建环链表
     */
    private static void createCycleList() {
        ListNode listNodeA = new ListNode<String>();

        ListNode.Node<String> node = new ListNode.Node<>();
        node.data = "a1";
        listNodeA.addNode(node);

        node = new ListNode.Node<>();
        node.data = "a2";
        listNodeA.addNode(node);

        node = new ListNode.Node<>();
        node.data = "a3";
        listNodeA.addNode(node);

        ListNode.Node<String> node4 = new ListNode.Node<>();
        node4.data = "a4";
        listNodeA.addNode(node4);

        node = new ListNode.Node<>();
        node.data = "a5";
        listNodeA.addNode(node);

        node = new ListNode.Node<>();
        node.data = "a6";
        listNodeA.addNode(node);

        node = new ListNode.Node<>();
        node.data = "a7";
        listNodeA.addNode(node);

        node = new ListNode.Node<>();
        node.data = "a8";
        node.next = node4;
        listNodeA.addNode(node);

        ListNode.Node cycleEnter = searchCycleEnter(listNodeA.getHeaderNode());
        int len = cycleLen(cycleEnter);
        System.out.println("len=" + len);
    }

    /**
     * 环的长度,入口结点再走一圈就是长度
     *
     * @param node
     * @return
     */
    private static int cycleLen(ListNode.Node<String> node) {
        ListNode.Node<String> walker = node;
        int len = 1;
        while (walker.next != node) {
            walker = walker.next;
            len++;
        }
        return len;

    }


    /**
     * 判断链表是否有环
     *
     * @return
     */
    private static boolean hasCycle(ListNode.Node<String> header) {
        ListNode.Node<String> walker = header;
        ListNode.Node<String> runner = header;
        while (walker != null && runner.next != null) {
            walker = walker.next;
            runner = runner.next.next;
            if (walker == runner) {
                return true;
            }
        }
        return false;
    }

    /**
     * 寻找环入口
     *
     * @return
     */
    private static ListNode.Node<String> searchCycleEnter(ListNode.Node<String> header) {
        ListNode.Node<String> walker = header;
        ListNode.Node<String> runner = header;

        while (walker != null && runner.next != null) {
            walker = walker.next;
            runner = runner.next.next;
            if (walker == runner) {
                break;
            }
        }
        if (walker == null || runner == null) {
            return null;
        }
        ListNode.Node<String> crossHeader = walker;
        ListNode.Node<String> walkHeader = header;
        while (crossHeader != walkHeader) {
            crossHeader = crossHeader.next;
            walkHeader = walkHeader.next;
        }
        return walkHeader;
    }

    /**
     * 创建有交叉结点的两个链表
     */
    private static void createIntersectionNode() {

        ListNode listNodeA = new ListNode<String>();
        ListNode listNodeB = new ListNode<String>();

        ListNode.Node<String> node = new ListNode.Node<>();
        node.data = "a1";
        listNodeA.addNode(node);

        node = new ListNode.Node<>();
        node.data = "a2";
        listNodeA.addNode(node);

        node = new ListNode.Node<>();
        node.data = "a3";
        listNodeA.addNode(node);


        ListNode.Node<String> nodeb = new ListNode.Node<>();
        nodeb.data = "b1";
        listNodeB.addNode(nodeb);

        nodeb = new ListNode.Node<>();
        nodeb.data = "b2";
        listNodeB.addNode(nodeb);


        ListNode.Node<String> nodec = new ListNode.Node<>();
        nodec.data = "c1";
        listNodeA.addNode(nodec);
        listNodeB.addNode(nodec);

        nodec = new ListNode.Node<>();
        nodec.data = "c2";
        listNodeA.addNode(nodec);

        nodec = new ListNode.Node<>();
        nodec.data = "c3";
        listNodeA.addNode(nodec);

        ListNode.Node intersectionNode = getIntersectionNode(listNodeA, listNodeB);
        System.out.println(intersectionNode.data);
    }


    /**
     * 获取两个链表的交叉点
     *
     * @return
     */
    private static ListNode.Node getIntersectionNode(ListNode<String> listNode1, ListNode<String> listNode2) {

        ListNode.Node headerNode1 = listNode1.getHeaderNode();
        ListNode.Node headerNode2 = listNode2.getHeaderNode();

        ListNode.Node node1 = headerNode1;
        ListNode.Node node2 = headerNode2;

        while (node1 != node2) {
            node1 = node1 == null ? headerNode2 : node1.next;
            node2 = node2 == null ? headerNode1 : node2.next;
        }
        return node1;
    }


    /**
     * 使用递归 从尾部到头部打印链表
     *
     * @param node
     */
    private static void printListByTail(ListNode.Node node) {
        if (node == null) {
            return;
        }
        printListByTail(node.next);
        System.out.print(node.data + ",");

    }

    /**
     * 采用头插法反转链表
     *
     * @param node
     */
    private static ListNode.Node reverseList(ListNode.Node node) {
        if (node == null) {
            return null;
        }
        ListNode.Node header = new ListNode.Node();
        ListNode.Node temp = node;
        while (temp != null) {
            temp = temp.next;
            node.next = header.next;
            header.next = node;
            node = temp;
        }
        return header;
    }

    /**
     * 使用栈
     *
     * @param node
     */
    private static void printListByStack(ListNode.Node<String> node) {
        Stack<String> stack = new Stack<>();
        while (node != null) {
            stack.push(node.data);
            node = node.next;
        }
        while (!stack.empty()) {
            System.out.print(stack.pop());
        }
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
