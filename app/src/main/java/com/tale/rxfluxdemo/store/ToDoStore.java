package com.tale.rxfluxdemo.store;

import com.tale.rxflux.Action;
import com.tale.rxflux.Dispatcher;
import com.tale.rxflux.Store;
import com.tale.rxfluxdemo.action.AddToDoAction;
import com.tale.rxfluxdemo.action.LoadToDoAction;

import java.util.ArrayList;
import java.util.List;

import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Author tale. Created on 6/22/15.
 */
public class ToDoStore extends Store<List<String>> {

    public List<String> todos = new ArrayList<>();
    private Subscription addToDoSubscription;
    private Subscription loadToDoSubscription;

    @Override
    public void registerWithDispatcher(Dispatcher dispatcher) {
        super.registerWithDispatcher(dispatcher);

        addToDoSubscription = dispatcher.eventObservable()
                .filter(new Func1<Action, Boolean>() {
                    @Override
                    public Boolean call(Action action) {
                        return action instanceof AddToDoAction;
                    }
                })
                .map(new Func1<Action, AddToDoAction>() {
                    @Override
                    public AddToDoAction call(Action action) {
                        return ((AddToDoAction) action);
                    }
                })
                .map(new Func1<AddToDoAction, String>() {
                    @Override
                    public String call(AddToDoAction action) {
                        return action.todo;
                    }
                })
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String action) {
                        todos.add(action);
                        emitChange(todos);
                    }
                });

        loadToDoSubscription = dispatcher.eventObservable()
                .filter(new Func1<Action, Boolean>() {
                    @Override
                    public Boolean call(Action action) {
                        return action instanceof LoadToDoAction;
                    }
                })
                .subscribe(new Action1<Action>() {
                    @Override
                    public void call(Action action) {
                        emitChange(todos);
                    }
                });
    }

    public void unregisterFromDispatcher() {
        if (addToDoSubscription != null) {
            addToDoSubscription.unsubscribe();
        }
        if (loadToDoSubscription != null) {
            loadToDoSubscription.unsubscribe();
        }
    }
}
