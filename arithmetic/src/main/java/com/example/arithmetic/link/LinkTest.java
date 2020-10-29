package com.example.arithmetic.link;

public class LinkTest {

    public static void main(String[] args) {
        LinkNode linkNode = LinkNode.create();
        linkNode.traversList(linkNode.getHeaderNode());
        System.out.println();
        linkNode.traversList(linkNode.removeKthFromEnd(linkNode.getHeaderNode(),12));
    }

}
