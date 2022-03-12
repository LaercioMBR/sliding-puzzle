package sliding_puzzle;

import java.util.Scanner;

public class SlidingPuzzle {
	
	private static Integer nextNumberRows;
	private static Integer nextNumberColumns;
	

	public static void main(String[] args) {
		System.out.println(welcome());
		
		while(true) {

			System.out.println(askPlayerNumberRowsColumns("Rows"));
			setNextNumberRows(scanNumberRowsColumns("Rows"));
			System.out.println(askPlayerNumberRowsColumns("Columns"));
			setNextNumberColumns(scanNumberRowsColumns("Columns"));
		}
	}
	
	public static String welcome() {
		return "Welcome to Sliding Puzzle!";
	}

	public static String askPlayerNumberRowsColumns(String option) {
		return "Please choose the number of "+ option +" for the next game!";
	}
	
	public static Integer scanNumberRowsColumns(String option) {
		Scanner sc = new Scanner(System.in);
		
		Integer row_integer = Integer.parseInt("0");
		
		while (row_integer.toString().equals("0")) {
			String input = sc.next();
			
			try{
				row_integer = Integer.decode(input);
				break;
			}
			catch(NumberFormatException e ) {
				System.out.println("Invalid input, please try again with an Integer for the number of " + option + " you want for the next game");
				row_integer = Integer.parseInt("0");
			}
		}
		
		return row_integer;
		
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
	
}
