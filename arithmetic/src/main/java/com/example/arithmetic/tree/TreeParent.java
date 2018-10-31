package com.example.arithmetic.tree;

import com.example.arithmetic.linear.LinkedList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangyongchao on 2018/8/14.
 * 树的双亲表示法
 */

public class TreeParent<E> {

    private static final int DEFAULT_CAPACITY = 12;

    Entry<E>[] entries;

    /**
     * The number of elements in this list.
     */
    int size;

    public TreeParent(int size, E rootElement) {
        if (size < 0) {
            throw new IllegalArgumentException("size < 0: " + size);
        }
        entries = new Entry[size];
        add(rootElement, null);

    }

    public TreeParent(E rootElement) {
        entries = new Entry[DEFAULT_CAPACITY];
        add(rootElement, null);
    }

    public boolean add(E element, E parent) {
        Entry<E>[] e = entries;
        int s = size;
        if (s == e.length) {
            Entry<E>[] newArray = new Entry[s +
                    (s < (DEFAULT_CAPACITY / 2) ?
                            DEFAULT_CAPACITY : s >> 1)];
            System.arraycopy(e, 0, newArray, 0, s);
            entries = e = newArray;
        }

        Entry<E> entry = new Entry<E>(element, indexOf(parent));
        entries[s] = entry;
        size++;
        return true;
    }

    public void traverse() {
        for (int i = 0; i < size; i++) {
            System.out.print(entries[i].element + ",");
        }
    }

    public List<E> getChildren(E element) {
        int pos = indexOf(element);
        List<E> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            if (entries[i].parent == pos) {
                list.add(entries[i].element);
            }
        }
        return list;
    }


    public int indexOf(E element) {
        Entry<E>[] a = entries;
        int s = size;
        if (element != null) {
            for (int i = 0; i < s; i++) {
                if (element.equals(a[i].element)) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < s; i++) {
                if (a[i].element == null) {
                    return i;
                }
            }
        }
        return -1;
    }


    private static class Entry<E> {
        E element;
        int parent;

        Entry(E element, int parent) {
            this.element = element;
            this.parent = parent;
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof Entry) {
                Entry entry = (Entry) o;
                return this.element.equals(entry.element);
            }
            return super.equals(o);
        }
    }


}
