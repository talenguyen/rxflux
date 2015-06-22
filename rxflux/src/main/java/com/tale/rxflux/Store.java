package com.tale.rxflux;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Author tale. Created on 6/22/15.
 */
public abstract class Store<T> {

    private static final Subject<Object, Object> CHANGES_SUBJECT = new SerializedSubject<>(PublishSubject.create());

    public void registerWithDispatcher(Dispatcher dispatcher) {
    }

    /**
     * Observable which will emit any change happened.
     *
     * @return {@link Observable<T>} object.
     */
    public Observable<T> changeObservable() {
        return (Observable<T>) CHANGES_SUBJECT.asObservable();
    }

    protected void emitChange(T t) {
        CHANGES_SUBJECT.onNext(t);
    }
}
