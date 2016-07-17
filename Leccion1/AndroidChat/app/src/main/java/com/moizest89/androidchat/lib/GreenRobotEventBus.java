package com.moizest89.androidchat.lib;

/**
 * Created by @moizest89 in SV on 7/17/16.
 */
public class GreenRobotEventBus implements EventBus{

    private org.greenrobot.eventbus.EventBus eventBus;


    private final static class SingletonHolder{
        private final static GreenRobotEventBus INSTANCE = new GreenRobotEventBus();
    }

    public static GreenRobotEventBus getInstance(){
        return SingletonHolder.INSTANCE;
    }

    public GreenRobotEventBus() {
        this.eventBus = org.greenrobot.eventbus.EventBus.getDefault();
    }

    @Override
    public void register(Object subscriber) {
        this.eventBus.register(subscriber);
    }

    @Override
    public void unregister(Object subscriber) {
        this.eventBus.unregister(subscriber);
    }

    @Override
    public void post(Object event) {
        this.eventBus.post(event);
    }
}
