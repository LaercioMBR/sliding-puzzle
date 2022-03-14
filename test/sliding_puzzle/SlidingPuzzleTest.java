/**
 * 
 */

package sliding_puzzle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author laerc
 *
 */
public class SlidingPuzzleTest {
	
	private static SlidingPuzzle game;

	@BeforeEach
	public void setUp() {
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
		
	    String input = "Rows";
	    InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
		
		assertEquals(game.askPlayerNumberRowsColumns(input),"Please choose the number of " + input + "(above 1) for the next game!");
	}
	
	//TODO
	@Test
	public void SlidingPuzzleDefinesDimensionNextGame() {
		
		String simulatedUserInput = "3";

		System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));
		
		assertEquals(game.defineDimensionsNextGame(),"You have chosen 3 Rows and 3 Columns");
	
	}
	
	@Test
	public void SlidingPuzzleReceivesInputNumberOfRowsColumns() {		
	    String input = "3";
	    String option = "Rows";
	    
	    InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);

		assertEquals(game.scanNumberRowsColumns(option),Integer.parseInt(input));
	}

	@Test
	public void SlidingPuzzleSaysGoodBye() {		
		assertEquals(game.sayGoodBye(),"Thanks for playing! Closing now.");
	}
	
	@Test
	public void SlidingPuzzleAskPlayerToPlayAgain() {
		assertEquals(game.askPlayerPlayAgainMessage(),"Do you want to create another game? Type > 1 < for Yes or > 0 < for No");
	}
	
	@Test
	public void SlidingPuzzleUserInput() {
	    String input = "random_input";
	    InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
		
		assertEquals(game.userInput(),"random_input");
	}

	
	@Test
	public void SlidingPuzzleScanPlayAgainMessageTrue() {
		Boolean playAgain = true;
		assertEquals(game.playAgainMessage(playAgain),"Let's play again!");
	}
	
	@Test
	public void SlidingPuzzleScanPlayAgainMessageFalse() {
		Boolean playAgain = false;
		assertEquals(game.playAgainMessage(playAgain),"Very well.");
	}
	
//	@Test
//	public void SlidingPuzzleScanPlayAgainInvalidInput() {
//	    String input = "2"; 
//	    InputStream in = new ByteArrayInputStream(input.getBytes());
//	    System.setIn(in);
//	    
//		assertEquals(,"Invalid input, please input either > 1 <  or > 0 <");
//	}
	
	
	@Test
	public void SlidingPuzzleSayGoodBye() {		
		assertEquals(game.sayGoodBye(),"Thanks for playing! Closing now.");
	}
	
	
}
