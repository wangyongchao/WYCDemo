package com.example.arithmetic.linear;


/**
 * Created by wangyongchao on 2018/5/30.
 * 线性表测试类
 */

public class LinearTest {
    public static void main(String[] args) {
        yueSeFuHuan();


    }

    /**
     * 约瑟夫环
     */
    private static void yueSeFuHuan() {
        YueSeFuHuan yueSeFuHuan = new YueSeFuHuan(5);
        yueSeFuHuan.iterator();
        yueSeFuHuan.start(3,2);
    }


    /**
     * 测试队列
     */
    private static void testQueue() {
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>(6);
        arrayDeque.offer(0);
        arrayDeque.offer(1);
        arrayDeque.offer(2);
        arrayDeque.offer(3);
        arrayDeque.offer(4);
        arrayDeque.offer(5);
        arrayDeque.offer(6);
        arrayDeque.offer(7);
        arrayDeque.offer(8);


    }


    /**
     * 阶乘，递归
     */
    private static long testN(long n) {
        if (n == 1) {
            return 1;
        }
        return n * testN(n - 1);

    }

    /**
     * 斐波那契数列，递归
     */
    private static long testFbi(long n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return testFbi(n - 1) + testFbi(n - 2);

    }


    /**
     * 自定义栈
     */
    private static void testMyStack() {
        MyStack<Integer> stack = new MyStack<>();
        stack.push(0);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.push(6);
        stack.push(7);
        stack.push(8);
        stack.push(9);
        stack.push(10);

        Integer pop = stack.pop();
        System.out.println(pop);

    }


    private static void testStack() {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.push(6);
        Integer pop = stack.pop();
        System.out.println(pop);


    }


    private static void testLinkedList() {
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("aaa1");
        linkedList.add("aaa2");
        linkedList.add("aaa3");
        linkedList.add("aaa4");
        linkedList.add("aaa5");
        linkedList.add("aaa6");
        linkedList.add("aaa7");

        linkedList.add(7, "aaacccc");

        String s = linkedList.get(5);
        linkedList.remove(2);


    }


    private static void testArrayList() {
        //初始容量空数组
        ArrayList<Integer> arrayList = new ArrayList<>(5);
        arrayList.add(2);
        arrayList.add(100);
        arrayList.add(23);
        arrayList.add(5);
        arrayList.add(6);
        Integer integer = arrayList.get(3);


        arrayList.add(3, 4);
        arrayList.remove(2);

    }

}
