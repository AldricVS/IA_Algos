package avs.aldricvs.test.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import avs.aldricvs.node.point.Position;
import avs.aldricvs.node.state.Direction;
import avs.aldricvs.node.state.State;
import avs.aldricvs.node.state.StateFactory;

public class StateTest {

	private State state;
	
	@Before
	public void setUp() {
		int [][] initialLayout = {
				{ 4, 1, 3 },
				{ 7, 2, 6 },
				{ 5, 8, State.BLANK_BOX_VALUE }
		};
		
		state = StateFactory.create(initialLayout);
	}
	
	@Test
	public void checkNumbersPositions() {
		assertEquals(4, state.getNumberAt(new Position(0, 0)));
		assertEquals(2, state.getNumberAt(new Position(1, 1)));
		assertEquals(7, state.getNumberAt(new Position(1, 0)));
		assertEquals(5, state.getNumberAt(new Position(2, 0)));
	}
	
	@Test
	public void swapBlankBoxSuccess() {
		// 6 must be down-right
		Optional<State> swapBlankBox = state.swapBlankBox(Direction.UP);
		assertTrue(swapBlankBox.isPresent());
		State swappedState = swapBlankBox.get();
		assertEquals(6, swappedState.getNumberAt(new Position(2, 2)));
		
		// 8 must be down-right
		swapBlankBox = state.swapBlankBox(Direction.LEFT);
		assertTrue(swapBlankBox.isPresent());
		swappedState = swapBlankBox.get();
		assertEquals(8, swappedState.getNumberAt(new Position(2, 2)));
	}
}
