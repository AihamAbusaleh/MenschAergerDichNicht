package de.htwg.menschaergerdichnicht.observer;


public interface IObservable {
/**
* register an observer
* @param Iobserver
* */
void registerObserver(IObserver observer);
/**
* unregister an observer
* @param Iobserver
* */
void unregisterObserver(IObserver observer);
/**
* update the observers
* */
void updateObservers();

}