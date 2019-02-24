package com.testapp.test;

public class Label {
    private String labelText;
    private int type;

    public Label(String labelText, int type) {
        this.labelText = labelText;
        this.type = type;
    }

    public String getLabelText() {
        return labelText;
    }

    public void setLabelText(String labelText) {
        this.labelText = labelText;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
