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
		// @Override
		public void showDice(Player currentplayer, int dice) {
			e = true;
		}
		// @Override

		public void update(Player currentPlayer, boolean gameEnded) {
			e = true;

		}

	}

	@Before
	public void setUp() throws Exception {

		testObserver = new TestObserver();
		testObservable = new Observable();
		testObservable.registerObserver(testObserver);

	}

	@Test
	public void testUpdateObservers() {
		assertFalse(e);
		testObservable.updateObservers();
		// assertTrue(e);

	}

	@Test
	public void testUnregisterObserver() {
		assertFalse(e);

		testObservable.unregisterObserver(testObserver);

		testObservable.updateObservers();
		assertFalse(e);

	}
}
