package com.kttest2.lambda;

import com.kttest2.ktclass.construct.View;

public class Button {
    private OnClickListener listener;

    public interface OnClickListener {
        void onClick(View view);

    }

    public void setOnClickListener(OnClickListener listener){
        this.listener=listener;
    }


    public static void main(String[] args) {
        Button button = new Button();
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    public void postponeComputation(int count,Runnable runnable){
        System.out.println("postponeComputation count="+count);
        if(runnable!=null){
            runnable.run();
        }

    }
}
