package com.kttest2.ktclass.java;

import com.kttest2.ktclass.State;
import com.kttest2.ktclass.View;

import org.jetbrains.annotations.NotNull;

public class ButtonJava implements View {
    @NotNull
    @Override
    public State getCurrentState() {
        return new ButtonStateJava();
    }

    @Override
    public void restoreState(@NotNull State state) {

    }

    public static class ButtonStateJava implements State {

    }

}
