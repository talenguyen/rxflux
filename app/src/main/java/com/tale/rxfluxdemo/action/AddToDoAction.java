package com.tale.rxfluxdemo.action;

import com.tale.rxflux.Action;

/**
 * Author tale. Created on 6/22/15.
 */
public class AddToDoAction implements Action {
    public final String todo;

    public AddToDoAction(String todo) {
        this.todo = todo;
    }
}
