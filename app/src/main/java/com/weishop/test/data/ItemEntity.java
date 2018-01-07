package com.weishop.test.data;

public class ItemEntity {
    private String mTitle;

    private String mContent;

    public ItemEntity(String pTitle, String pContent) {
        mTitle = pTitle;
        mContent = pContent;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getContent() {
        return mContent;
    }
}