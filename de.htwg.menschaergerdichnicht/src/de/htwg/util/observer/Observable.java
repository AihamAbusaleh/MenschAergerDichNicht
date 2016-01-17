package de.htwg.util.observer;

public abstract class Observable {

	/**
	 * to register the observer pattern
	 */
	public abstract void registerObserver(IObserver observer);

	/**
	 * to unregister the observer pattern
	 */
	public abstract void unregisterObserver(IObserver observer);

	/**
	 * This function update the gamefiled, when a stone is moving
	 */
	public abstract void updateObservers();

}