package com.example.designmode.proxy;

public class GamePlayer implements IGamePlayer {

    public GamePlayer() {
    }

    /**
     * @param userName
     * @param password
     */
    @Override
    public void login(String userName, String password) {

        System.out.println("login userName="+userName+",password="+password);
    }

    @Override
    public void killBoss() {
        System.out.println("killBoss");

    }

    @Override
    public void upgrade() {
        System.out.println("upgrade");

    }
}
