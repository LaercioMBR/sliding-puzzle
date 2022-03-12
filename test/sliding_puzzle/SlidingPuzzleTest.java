/**
 * 
 */

package sliding_puzzle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * @author laerc
 *
 */
public class SlidingPuzzleTest {
	
	private static SlidingPuzzle game;

	@BeforeAll
	public static void setUp() {
		game = new SlidingPuzzle();		
	}
	
	@Test
	public void gameExists() {
		assertNotNull(game);
	}
	
	@Test
	public void SlidingPuzzleWelcomesPlayers() {
		assertEquals(game.welcome(),"Welcome to Sliding Puzzle!");
	}
	
	@Test
	public void SlidingPuzzleOffersChoiceOfNumberOfRowsColumnsForNextGame() {
		String option = "Rows";
		assertEquals(game.askPlayerNumberRowsColumns(""),"Please choose the number of " + option + " for the next game!");
	}
	
	@Test
	public void SlidingPuzzleReceivesInputNumberOfRowsColumns() {
		Integer int_temp = Integer.parseInt("0");
		String option = "Rows";
		assertTrue(game.scanNumberRowsColumns(option).getClass().equals(int_temp.getClass() ) );
	}
	
}
