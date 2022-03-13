package sliding_puzzle;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GameTest {
	
	private static Game game;
		
	@BeforeEach
	public void setUp() {
		Integer rows = Integer.decode("3");
		Integer columns = Integer.decode("3");
		game = new Game(rows,columns);
		
	}
	
	@Test
	public void gameCorrectlyPopulatesBoard() {
	
		Integer rows = Integer.decode("3");
		Integer columns = Integer.decode("3");
		
		int[][] test_game = {	{1,2,3},
								{4,5,6},
								{7,8,0},
							};

		assertArrayEquals(test_game,game.populateGameBoard(rows,columns));
	}
	
}
