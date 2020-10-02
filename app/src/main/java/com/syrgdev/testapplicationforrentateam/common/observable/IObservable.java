package com.syrgdev.testapplicationforrentateam.common.observable;

public interface IObservable<OBSERVER_TYPE> {

    void registerObserver(OBSERVER_TYPE observer);

    void unregisterObserver(OBSERVER_TYPE observer);
}
