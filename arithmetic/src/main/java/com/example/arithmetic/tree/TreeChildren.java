package com.example.arithmetic.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangyongchao on 2018/8/20.
 * 孩子表示法
 */

public class TreeChildren<E> {
    private static final int DEFAULT_CAPACITY = 12;

    ChildBox<E>[] childBoxes;
    int size;

    public TreeChildren(int size, E rootElement) {
        if (size < 0) {
            throw new IllegalArgumentException("size < 0: " + size);
        }
        childBoxes = new ChildBox[size];

    }

    public TreeChildren(E rootElement) {
        childBoxes = new ChildBox[DEFAULT_CAPACITY];
        ChildBox childBox = new ChildBox(rootElement);
        childBoxes[size++] = childBox;

    }

    /**
     * 先查找父元素，如果父元素存在，则取父元素的第一个孩子，
     * 如果孩子为null，则第一个孩子为新建的元素。如果孩子不为null，
     * ，则指针指向孩子结点，然后判断孩子结点的下一个兄弟结点是否为空 ，如果为空，则下一个结点为新的结点。
     *
     * @param element
     * @param parent
     */
    public void add(E element, E parent) {
        int index = indexOf(parent);
        if (index != -1) {
            ChildBox parentBox = childBoxes[index];
            ChildNode firstChild = parentBox.firstChild;
            ChildNode childNode = new ChildNode(element);
            if (firstChild == null) {
                parentBox.firstChild = childNode;
            } else {
                ChildNode nextChild = firstChild;
                while (nextChild.next != null) {
                    nextChild = nextChild.next;
                }
                nextChild.next = childNode;
            }
        }

        ChildBox childBox = new ChildBox(element);
        childBoxes[size++] = childBox;
    }

    /**
     * 获取某个结点的孩子结点
     *
     * @param element
     * @return
     */
    public List getChildren(E element) {
        List list = new ArrayList<>();
        int pos = indexOf(element);
        if (pos != -1) {
            ChildBox childBox = childBoxes[pos];
            ChildNode childNode = childBox.firstChild;
            while (childNode != null) {
                list.add(childNode.element);
                childNode = childNode.next;
            }
        }
        return list;
    }

    /**
     * 获取兄弟结点
     *
     * @param element
     * @return
     */
    public List getSibling(E element) {

        return null;
    }


    /**
     * 获取某个结点的双亲
     *
     * @param element
     * @return
     */
    public E getParent(E element) {
        ChildBox<E>[] childBoxes = this.childBoxes;
        for (int i = 0; i < childBoxes.length; i++) {
            ChildBox<E> childBox = childBoxes[i];
            ChildNode<E> nextChild = childBox.firstChild;
            while (nextChild.element != element) {
                nextChild = nextChild.next;
            }
        }


        return null;
    }


    public int indexOf(E element) {
        ChildBox<E>[] a = childBoxes;
        int s = size;
        if (element != null) {
            for (int i = 0; i < s; i++) {
                if (element.equals(a[i].parent)) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < s; i++) {
                if (a[i].parent == null) {
                    return i;
                }
            }
        }
        return -1;
    }


    private static class ChildBox<E> {
        E parent;
        ChildNode<E> firstChild;

        ChildBox(E e) {
            parent = e;
        }
    }


    private static class ChildNode<E> {
        E element;
        ChildNode<E> next;

        public ChildNode(E element) {
            this.element = element;
        }


    }


}
