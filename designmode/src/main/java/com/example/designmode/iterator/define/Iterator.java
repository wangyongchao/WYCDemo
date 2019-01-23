package com.example.designmode.iterator.define;

public interface Iterator<E> {
    //遍历到下一个元素
    public E next();

    //是否已经遍历到尾部
    public boolean hasNext();

    //删除当前指向的元素
    public boolean remove();
}
