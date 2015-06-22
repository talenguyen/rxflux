package com.tale.rxflux;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Author tale. Created on 6/22/15.
 */
public class Dispatcher {

    private static final Subject<Action, Action> ACTION_SUBJECT = new SerializedSubject<>(PublishSubject.<Action>create());

    public Observable<Action> eventObservable() {
        return ACTION_SUBJECT.asObservable();
    }

    public void dispatchAction(Action action) {
        if (ACTION_SUBJECT.hasObservers()) {
            ACTION_SUBJECT.onNext(action);
        }
    }

}
