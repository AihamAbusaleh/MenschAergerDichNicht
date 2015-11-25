package de.htwg.menschaergerdichnicht.observer;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.menschaergerdichnicht.model.Player;

public class ObserverTest {
	private TestObserver testObserver;
	private boolean e = false;
	private Observable testObservable;

	class TestObserver implements IObserver {

		public void update(Player currentPlayer, boolean gameEnded) {

		}

		public void showDice(Player currentplayer, int dice) {

		}

	}

	public void update() {
		e = true;
	}

	@Before
	public void setUp() throws Exception {
		testObservable = new Observable();
		testObserver = new TestObserver();
		testObservable.registerObserver(testObserver);

	}

	@Test
	public void testUpdateObservers() {
		assertFalse(e);
		update();
		assertTrue(e);

	}

	@Test
	public void testUnregisterObserver() {
		assertFalse(e);
		testObservable.unregisterObserver(testObserver);
		testObservable.updateObservers();
		assertFalse(e);

	}
}
