package com.kttest2.ktclass;

import org.jetbrains.annotations.NotNull;

public class JButton implements View {

    @NotNull
    @Override
    public State getCurrentState() {
        return new JButtonState();
    }

    @Override
    public void restoreState(@NotNull State state) {


    }

    public class JButtonState implements State {

    }
}