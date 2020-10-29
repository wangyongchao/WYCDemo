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
     *
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
     *
     * @param node
     */
    public void insertNodeTail(Node node) {
        tail.next = node;
        tail = node;
    }

    /**
     * 头插法
     *
     * @param node
     */
    public void insertNodeHeader(Node node) {
        node.next = header.next;
        header.next = node;
    }


    /**
     * 反转链表 迭代法
     * 时间复杂度：O(n)，假设 nn 是列表的长度，时间复杂度是 O(n)O(n)。
     * 空间复杂度：O(1)
     * 1->2->3->4->5  采用头插法
     * node.next = header.next;
     * header.next = node;
     *
     * @return
     */
    public Node reverseList(Node header) {
        if (header == null) {
            return null;
        }
        Node node = header.next;//从第一个结点开始,要不老的头结点会在最后一个输出
        Node newHeader = new Node(null);
        while (node != null) {
            //先记录当前结点的下一个结点
            Node temp = node.next;

            //当前结点的下一个结点始终指向新的链表的第一个结点。
            node.next = newHeader.next;

            newHeader.next = node;
            node = temp;
        }
        return newHeader;
    }

    /**
     * 反转链表 递归
     * 1->2->3->4->5
     *
     * @return
     */
    public Node reverseListByRecursion(Node node) {
        if (node == null || node.next == null) {
            return node;
        }
        Node next = node.next;
        Node newHeader = reverseListByRecursion(next);
        //下一个结点的下一个指向当前结点 比如4的下一个结点指向3，就能反转链表.然后然后当前结点的下一个指向null
        next.next = node;
        node.next = null;
        return newHeader;//始终返回新结点
    }


    /**
     * 遍历列表
     *
     * @param header
     */
    public void traversList(Node header) {
        Node node = header;
        while (node != null) {
            System.out.print(node.data + ">");
            node = node.next;
        }

    }


    /**
     * 删除链表的倒数第k个结点
     * 设置两个指针，第一个指针从第一个结点开始先移动k个结点,然后第二个结点指向第一个结点。此时，两个结点
     * 同时移动，他们始终相差k个结点。当第一个指针移动到末尾的时候，第二指针指向的位置就是要删除的结点的前一个结点，然后
     * 直接把当前结点node.next=node.next.next即可。
     * header->1->2->3->4->5->6->7->8->9->10
     * 时间复杂度：O(L)，该算法对含有 L 个结点的列表进行了一次遍历。因此时间复杂度为 O(L)。
     * 空间复杂度：O(1)，我们只用了常量级的额外空间。
     *
     * @param header
     */
    public Node removeKthFromEnd(Node header, int k) {
        if (header == null || header.next == null || k <= 0) {
            return header;
        }

        Node p1 = header;
        Node p2 = header;

        while (k > 0 && p1 != null) {
            p1 = p1.next;
            k--;
        }
        if (p1 == null) {
            //k越界，k大于链表的长度
            return header;
        }
        while (p1 != null && p1.next != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        p2.next = p2.next.next;
        return header;

    }

    /**
     * a1 → a2 ->c1 ->c2 -> c3
     * b1 → b2 → b3 ->c1 → c2 → c3
     * 找出两个链表的交点 a结点是2个，c结点是3个，b结点也是3个
     * a+c+b=b+c+a a链表遍历完成后相当于经历了a+c然后再遍历3个b结点，b链表遍历完后相当于经历了b+c，在遍历2个a结点，那么
     * 下一个结点就是相交的结点。
     *
     * @param headerA
     * @param headerB
     * @return
     */
    public Node getIntersectionNode(Node headerA, Node headerB) {
        Node nodea = headerA;
        Node nodeb = headerB;
        while (nodea != nodeb) {
            nodea = nodea == null ? nodeb : nodea.next;
            nodeb = nodeb == null ? nodea : nodeb.next;
        }
        return nodea;
    }


    @Override
    public String toString() {
        String result = "";
        Node temp = header;
        while (temp.next != null) {
            temp = temp.next;
            result = result + temp.data + ">";
        }

        return result;
    }

    public Node getHeaderNode() {
        return header;
    }

    public static LinkNode create() {
        LinkNode<Integer> linkNode = new LinkNode<>();
        for (int i = 1; i <= 10; i++) {
            linkNode.add(new Node(i));
        }
        return linkNode;

    }


}
