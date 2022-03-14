package sliding_puzzle;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class SlidingPuzzle {
	
	private static Integer nextNumberRows;
	private static Integer nextNumberColumns;
	

	public static void main(String[] args) {
		informPlayer(welcome());
		
		while(true) {
			defineDimensionsNextGame();

			Game game = new Game(nextNumberRows,nextNumberColumns);

			game.run();
						
			Boolean playAgain = loopPlayAgain();

			if(playAgain.booleanValue())
				continue;
			else
				informPlayer(sayGoodBye());
				break;
		}
	}
	

	public static Boolean loopPlayAgain() {
		
		informPlayer(askPlayerPlayAgainMessage());		
		
		String answer = userInput();
		Boolean playAgain = null;
		
		String error_message = "Invalid input, please input either > 1 <  or > 0 <"; 

		while(playAgain == null){
				
			try {
				switch(Integer.parseInt(answer) ){
					case 1:
						playAgain = true;						
						break;
					case 0:
						playAgain = false;
						break;
					default:
						informPlayer(error_message);
				}
						
			} catch (NumberFormatException e) {
				informPlayer(error_message);
				answer = userInput();
			}

		}
		
		
		String message = playAgainMessage(playAgain);
		System.out.println(message);
		
		return playAgain ;
	}


	public static String playAgainMessage(Boolean playAgain) {

		String message = null;

		if(playAgain) {
			message = "Let's play again!";			
		}else {
			message = "Very well.";
		}
		
		return message;
		
	}


	public static String welcome() {
		return "Welcome to Sliding Puzzle!";
	}

	public static String askPlayerNumberRowsColumns(String option) {
		return "Please choose the number of " + option + "(above 1) for the next game!";
	}
	
	public static String defineDimensionsNextGame() {
		
		Integer rows = 0;
		Integer columns = 0;
		
		while(rows.intValue() <= 1) {
			System.out.println(askPlayerNumberRowsColumns("Rows"));
			rows = scanNumberRowsColumns("Rows");
		}

		setNextNumberRows(rows);
		
		while(columns.intValue() <= 1) {
			System.out.println(askPlayerNumberRowsColumns("Columns"));
			columns = scanNumberRowsColumns("Columns");
		}
		
		setNextNumberColumns(columns);

		String return_message = "You have chosen " + rows.toString() + " Rows and " + columns.toString() + " Columns";
				
		return return_message;
	}
	
	public static Integer scanNumberRowsColumns(String option) {
		
		Integer integer = Integer.parseInt("0");
				
		String input = userInput();
			
		try{
			integer = Integer.decode(input);
		}
		catch(NumberFormatException e ) {
			informPlayer("Invalid input > " + input);				
			informPlayer("Please try again with an Integer for the number of " + option + " you want for the next game");
			integer = Integer.parseInt("0");
		}
						
		return integer;
		
	}
	
	public static Integer getNextNumberRows() {
		return nextNumberRows;
	}

	public static void setNextNumberRows(Integer nextNumberRows) {
		SlidingPuzzle.nextNumberRows = nextNumberRows;
	}

	public static Integer getNextNumberColumns() {
		return nextNumberColumns;
	}

	public static void setNextNumberColumns(Integer nextNumberColumns) {
		SlidingPuzzle.nextNumberColumns = nextNumberColumns;
	}
	
	public static String sayGoodBye() {
		String good_bye = "Thanks for playing! Closing now.";
		return good_bye;
	}

	public static String askPlayerPlayAgainMessage() {
		
		String askPlayAgain = "Do you want to create another game? Type > 1 < for Yes or > 0 < for No";
		
		return askPlayAgain;
	}
	
	public static String userInput() {
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		return input;
	}
	
	public static void informPlayer(String message) {		
		System.out.println(message);
	}

}
