package de.htwg.util.observer;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.menschaergerdichnicht.controller.Controller;
import de.htwg.menschaergerdichnicht.model.Player;
import de.htwg.util.observer.IObserver;
import de.htwg.util.observer.Observable;

public class ObserverTest {
	private TestObserver testObserver;
	private boolean e = false;
	private Observable testObservable;

	class TestObserver implements IObserver {
	 
		public void showDice(Player currentplayer, int dice) {
			e = true;
		}
 
		public void update(Player currentPlayer, boolean gameEnded) {
			e = true;

		}

	}

	@Before
	public void setUp() throws Exception {

		testObserver = new TestObserver();
		testObservable = new Controller();
		testObservable.registerObserver(testObserver);

	}

	@Test
	public void testUpdateObservers() {
		assertFalse(e);
		testObservable.updateObservers();

	}

	@Test
	public void testUnregisterObserver() {
		assertFalse(e);

		testObservable.unregisterObserver(testObserver);

		testObservable.updateObservers();
		assertFalse(e);

	}
}
