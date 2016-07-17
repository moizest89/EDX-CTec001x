package com.moizest89.androidchat.lib;

/**
 * Created by @moizest89 in SV on 7/17/16.
 */
public interface EventBus {

    void register(Object subscriber);
    void unregister(Object subscriber);
    void post(Object event);

}
