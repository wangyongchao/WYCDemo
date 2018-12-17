package com.example.designmode.proxy;

public class GamePlayerProxy implements IGamePlayer {
    private IGamePlayer iGamePlayer;

    public GamePlayerProxy(IGamePlayer iGamePlayer) {
        this.iGamePlayer = iGamePlayer;
    }

    /**
     * @param userName
     * @param password
     */
    @Override
    public void login(String userName, String password) {
        iGamePlayer.login(userName, password);
    }

    @Override
    public void killBoss() {
        iGamePlayer.killBoss();

    }

    @Override
    public void upgrade() {
        iGamePlayer.upgrade();

    }
}
