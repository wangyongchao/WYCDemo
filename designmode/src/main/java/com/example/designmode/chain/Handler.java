package com.example.designmode.chain;

public abstract class Handler {
    private Handler nextHandler;


    public final Response handleMessage(Request request) {
        Response response = null;
        if (this.getHandlerLevel() == request.getRequestLevel()) {
            echo(request);
        } else if (nextHandler != null) {
            nextHandler.handleMessage(request);
        } else {

        }

        return response;
    }

    //设置下一个处理者是谁
    public void setNext(Handler _handler) {
        this.nextHandler = _handler;
    }

    //每个处理者都有一个处理级别
    protected abstract Level getHandlerLevel();

    //每个处理者都必须实现处理任务
    protected abstract Response echo(Request request);


}
