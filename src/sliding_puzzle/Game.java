package sliding_puzzle;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Game {

	private Integer numberRows;
	private Integer numberColumns;
	private Integer maxValues;
	private Integer numberDigits;
	private Integer charactersPerCell;
	private Integer printRowSize;
	private int[][] gameMatrix;
	private Integer blankPositionRow;
	private Integer	blankPositionColumn;
	private Boolean solved;
	private Boolean quitGame;
	private Boolean gameStarted;
	private ArrayList<Move> possibleMovesCurrentState;
	private ArrayList<Move> allLegalMoves;
	
	public Game(Integer numberRows, Integer numberColumns) {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("Creating a fresh new ");
		sb.append(numberRows.intValue());
		sb.append(" x ");
		sb.append(numberColumns.intValue());				
		sb.append(" game for you!");

		System.out.println(sb.toString());
		
		this.numberRows = numberRows;
		this.numberColumns = numberColumns;		
		this.gameMatrix = new int[numberRows.intValue()][numberColumns.intValue()];
		this.maxValues = numberRows.intValue() * numberColumns.intValue();	
				
		int length = (int) (Math.log10(maxValues) + 1 );
		this.numberDigits = length;
		
		this.charactersPerCell = numberDigits.intValue() + 2;
		this.printRowSize = charactersPerCell.intValue() * numberColumns.intValue() + ( 1 * numberColumns.intValue() ) - 1  ;
		this.solved = false;
		this.quitGame = false;
		this.gameStarted = false;
		
		populateGameBoard(this.numberRows,this.numberColumns);
		definePossibleMoves();
	}

	public void run() {		
		
		loopBeforeStartGame();
			
		loopGameStart();
		
		gameEnded();
						
	}


	private void gameEnded() {
		
		Boolean gameStarted = false;
		setGameStarted(gameStarted);
		
		Boolean quitGame = askWantQuitGame();

		if(quitGame) {
			wantQuitGameConfirm();
		} else {
			this.run();
		}
		
	}

	private void loopGameStart() {
			
		while(!this.getSolved().booleanValue() && getGameStarted()) {
			printGameBoard();
			askPlayerMovementChoice();
			if( isGameSolved().booleanValue()) {
				congratulatePlayer();				
				break;
			}
		}

		printGameBoard();
				
	}
	
	

	private void loopBeforeStartGame() {
		
		while(!this.getGameStarted().booleanValue()) {
			
			printGameBoard();
			
			Boolean answerScramble = askPlayerScramble();			
			
			if(answerScramble) {
				scrambleGameBoard();				
			}
			
			printGameBoard();
			Boolean gameStart = askPlayerStartGame();
			setGameStarted(gameStart);
			
			if(! gameStart.booleanValue())
				askPlayerMovementChoice();
		}
		
	}

	private Boolean askWantQuitGame() {
		String message = informPlayer("Do you want to QUIT AND CLOSE this instance of the game?");
		Boolean quitAnswer = askPlayerTrueFalse(message);
		return quitAnswer;
	}

	private Boolean askPlayerStartGame() {
		
		String message = informPlayer("Do you want to START the game now?");		
		Boolean startGame = askPlayerTrueFalse(message);
						
		return startGame;
		
	}

	public Boolean getQuitGame() {
		return quitGame;
	}

	public void setQuitGame(Boolean quitGame) {
		this.quitGame = quitGame;
	}

	public Boolean getGameStarted() {
		return gameStarted;
	}

	public void setGameStarted(Boolean gameStarted) {
		this.gameStarted = gameStarted;
	}

	private void startGame() {		
		Boolean gameStarted = true;
		setGameStarted(gameStarted);				
	}

	private Boolean askPlayerScramble() {
		String message = informPlayer("Do you want to scramble this game?");
		Boolean scrambleStart = askPlayerTrueFalse(message);
		
		return scrambleStart;
	}

	private void wantQuitGameConfirm() {
		
		Boolean quitGame = false;
		
		informPlayer("Are you SURE you want to quit and close this game instance? Type > YES < or > yes < to confirm, anything else will cancel this operation");
			
		String confirm = userInput();
		if(confirm.toLowerCase().strip().equals("yes")) {
			quitGame = true;					
		}
		
		setQuitGame(quitGame);
	}

	private String informPlayer(String message) {
		System.out.println(message);
		return message;
	}

	private String userInput() {
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		return input;
	}
	
	private Boolean askPlayerTrueFalse(String askMessage) {
				
		String trueFalseMessage = informPlayer("Type > 1 < for YES, > 0 < for NO");
		String invalidInput = "Invalid input.";
		String input = userInput();
		
		Boolean booleanAnswer = false;

		
		while( ! input.equals("1") && ! input.equals("0") ) {
			informPlayer(invalidInput);
			informPlayer(askMessage);
			informPlayer(trueFalseMessage);
			input = userInput();
		}
		
		if(input.equals("1"))
			booleanAnswer = true;
		if(input.equals("0"))
			booleanAnswer = false;
		
		return booleanAnswer;
	}

	public Integer getNumberRows() {
		return numberRows;
	}

	public void setNumberRows(Integer numberRows) {
		this.numberRows = numberRows;
	}

	public Integer getNumberColumns() {
		return numberColumns;
	}

	public void setNumberColumns(Integer numberColumns) {
		this.numberColumns = numberColumns;
	}

	public int[][] getGameMatrix() {
		return gameMatrix;
	}

	public void setGameMatrix(int[][] gameMatrix) {
		this.gameMatrix = gameMatrix;
	}

	private void setSolved(boolean solved) {
		this.solved = solved;
	}

	public void congratulatePlayer() {
		String message = informPlayer("Congratulations on solving the puzzle!");		
	}

	public void scrambleGameBoard() {
						
		int rows = this.getNumberRows().intValue();
		int columns = this.getNumberColumns().intValue();
		int gameSize = rows * columns;
		
	
		for(int i = 0 ;  i < (gameSize * 3 ); i++ ) {
			Move move = getRandomPossibleMove();
			executeMovement(move);
		}
	}


	private void executeMovement(Move move) {
		int[][] gameMatrix = this.getGameMatrix();
		
		Integer relativeSwapPositionRow = move.getRelativeSwapPositionRow();
		Integer relativeSwapPositionColumn = move.getRelativeSwapPositionColumn();
		
		Integer blankPositionRow = this.getblankPositionRow();
		Integer blankPositionColumn = this.getblankPositionColumn();
		
		Integer absoluteSwapPositionRow = blankPositionRow + relativeSwapPositionRow ;
		Integer absoluteSwapPositionColumn = blankPositionColumn + relativeSwapPositionColumn;

		int temp = gameMatrix[absoluteSwapPositionRow.intValue()][absoluteSwapPositionColumn.intValue()];
		
				
		gameMatrix[blankPositionRow.intValue()][blankPositionColumn] = temp ;
		gameMatrix[absoluteSwapPositionRow.intValue()][absoluteSwapPositionColumn.intValue()] = 0 ;
		
		this.setGameMatrix(gameMatrix);
		this.setblankPositionRow(absoluteSwapPositionRow);
		this.setblankPositionColumn(absoluteSwapPositionColumn);
		this.definePossibleMoves();
		
		defineGameSolved();
	}

	private void defineGameSolved() {
		int counter = 1;
		
		int[][] gameMatrix = this.getGameMatrix();
		int rows = gameMatrix.length ;
		int columns = gameMatrix[0].length ;
		

		Boolean solved = null;
		for(int i = 0; i < rows ; i++) {
			for(int j = 0 ; j < columns ; j++) {
				int cell = gameMatrix[i][j];
				
				//If there's 0 anywhere but the last cell, then false
				if(cell == 0 && !(i == (rows - 1)) && !(j == (columns - 1) ) ){
					solved = false;
				}
				
				//If any cell doesn't contain the expected value
				if( cell != counter && !(i == (rows - 1)) && !(j == (columns - 1) )) {
					solved = false;
				}
				

									
				counter++;
					
			}				
		}
		
		if(solved == null)
			solved = true;
		
		this.setSolved(solved);
	}

	private Move getRandomPossibleMove() {
		
		ArrayList<Move> possibleMove = this.getPossibleMovesCurrentState();
		
		Random generator = new Random();

		int drawn = generator.nextInt(possibleMove.size());
		
		Move move = possibleMove.get(drawn);
		
		return move;
	}

	public void populateGameBoard(Integer numberRows, Integer numberColumns) {
		
		int max = numberRows.intValue() * numberColumns.intValue();
		
		int[][] game = new int[numberRows.intValue()][numberColumns.intValue()];
		
		int counter = 1;
		for(int i = 0; i < numberRows ; i++) {
			for (int j = 0; j < numberColumns; j++) {
				if(counter == max) {
					game[i][j] = 0;
					setblankPositionRow(i);
					setblankPositionColumn(j);
				}
				else {
					game[i][j] = counter;
				}
				counter++;				
			}
		}
		
		setGameMatrix(game);
		
	}


	public Boolean isGameSolved() {
		Boolean solved = this.getSolved();
		return solved;
	}
	

	public void askPlayerMovementChoice() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("Please choose one of the following moves\n ");
		sb.append("It will swap the blank cell with the cell that you choose\n");

		ArrayList<Move> possibleMoves = this.getPossibleMovesCurrentState();
				
		for (Move move : possibleMoves) {
			sb.append(" | ");
			sb.append(move.getMoveName());
			sb.append(" | Input > ");
			sb.append(move.getSwapDirection());
			sb.append(" <\n");			
			
		}
				
		String message = informPlayer(sb.toString());		

		Move move = userInputMove(possibleMoves, message);
				
		executeMovement(move);
		
	}
	
	public void definePossibleMoves() {
		Integer blankPositionRow = this.getblankPositionRow();
		Integer blankPositionColumn = this.getblankPositionColumn();
		Integer numberRows = this.getNumberRows();
		Integer numberColumns = this.getNumberColumns();
				
		ArrayList<Move> possibleMoves = new ArrayList<Move>();

		if(blankPositionRow.intValue() != 0) {
			Move move = new Move("UP", 8);
			possibleMoves.add(move);
		}

		if(blankPositionRow.intValue() != ( numberColumns.intValue() - 1 ) ) {
			Move move = new Move("DOWN", 2);
			possibleMoves.add(move);
		}
		
		if(blankPositionColumn.intValue() != 0) {
			Move move = new Move("LEFT", 4);
			possibleMoves.add(move);
		}
		
		if(blankPositionColumn.intValue() != ( numberColumns.intValue() - 1 ) ) {
			Move move = new Move("RIGHT", 6);
			possibleMoves.add(move);
		}
		
		this.setPossibleMovesCurrentState(possibleMoves);
		
	}


	private Move userInputMove(ArrayList<Move> possibleMoves, String message) {
		
		Scanner sc = new Scanner(System.in);
		
		String input = sc.next();
		
		ArrayList<Integer> swapDirections = new ArrayList<Integer>(); 

		for(Move move : possibleMoves) {
			
			Integer swapDirectionPossibleMoves = move.getSwapDirection();
			swapDirections.add(swapDirectionPossibleMoves);
		}
		
		Boolean MoveInputValid =  isMoveInputValid(input);
		
		while(!MoveInputValid) {
			printGameBoard();
			informPlayer("Invalid input");
			informPlayer(message);
			input = sc.next();
			
			MoveInputValid = isMoveInputValid(input);
		}
				
		
		
		Move userMove = null;
		
		for(Move move : possibleMoves) {
			Integer swapDirection = move.getSwapDirection();
			Integer inputDecode = Integer.decode(input);
			if(swapDirection.intValue() == inputDecode.intValue() )
				userMove = move;
		}
		
		return userMove;
		
		/*	
		
		while(!MoveInputValid.booleanValue()) {
			informPlayer("Invalid input");
			informPlayer("Invalid input");
			
			for(int i = 0; i < possibleMoves.size() ; i++) {
				if(input.equals(possibleMoves.get(i).getSwapDirection()))
				
				return userMove;
			}
		}
		
		forEach(legalMove move : legalMoves ){
			if(input.equals(move)) {
				doLegalMove(move);
			}
			else {
				String message = informPlayer("Invalid input");
			}
				
				
		}
		*/
		
	}

	private Boolean isMoveInputValid(String input) {
	
		ArrayList<Move> possibleMoves = this.getPossibleMovesCurrentState();
		
		Integer integerInput = Integer.decode(input);

		Boolean moveInputValid = false;
		
		for(Move move : possibleMoves) {
			if(move.getSwapDirection().equals(integerInput) ){
				 moveInputValid = true;
				 break;
			}
		}
		
		return moveInputValid;
		
		
	}

	public void printGameBoard() {
				
		for(int i = 0; i < numberRows.intValue() ; i++) {
			
			Integer row = i;
			printRow(row);
			 				
		}
		
	}

	private void printRow(Integer row) {
		
		printBorder();

		for(int i = 0; i < this.getNumberColumns().intValue(); i++) {
			Integer column = i;
			printCell(row,column);
			if( i + 1 == getNumberColumns().intValue())
				System.out.println("|");
		}
		
		//Close the board downside
		if(row.intValue() + 1 == numberRows.intValue() )
			printBorder();
	}

	private void printCell(Integer row,Integer column) {
		StringBuilder sb = new StringBuilder();
		
		
		int tempRow = row.intValue();
		int tempColumn = column.intValue(); 
		
		
		int tempCellValue = this.gameMatrix[tempRow][tempColumn];
		Integer cellValue = tempCellValue;

		String cellValueString;
		if(numberDigits.intValue() > 1) {
			sb.append("%0");
			sb.append(numberDigits.intValue());
			sb.append("d");
			String format = sb.toString();
			cellValueString = String.format(format, cellValue.intValue());			
		}
		else {
			cellValueString = cellValue.toString();
		}
				
		sb.setLength(0);
		
		sb.append("|");
		sb.append(" ");
		if(tempCellValue == 0) {
			for(int i = 0; i < this.numberDigits.intValue() ; i++ )
				sb.append(" ");
		}else {
			sb.append(cellValueString);
		}
		
		sb.append(" ");
		
		
		System.out.print(sb.toString());

	}
	
	private void printBorder() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("|");
		for(int i = 0; i < this.getprintRowSize().intValue(); i++) {
			sb.append("=");
		}
		sb.append("|");
		
		System.out.println(sb.toString()); 		
	}


	public Integer getMaxValues() {
		return maxValues;
	}

	public void setMaxValues(Integer maxValues) {
		this.maxValues = maxValues;
	}

	public Integer getNumberDigits() {
		return numberDigits;
	}

	public void setNumberDigits(Integer numberDigits) {
		this.numberDigits = numberDigits;
	}

	public Integer getCharactersPerCell() {
		return charactersPerCell;
	}

	public void setCharactersPerCell(Integer charactersPerCell) {
		this.charactersPerCell = charactersPerCell;
	}

	public Integer getprintRowSize() {
		return printRowSize;
	}

	public void setprintRowSize(Integer printRowSize) {
		this.printRowSize = printRowSize;
	}

	public Integer getblankPositionRow() {
		return blankPositionRow;
	}

	public void setblankPositionRow(Integer blankPositionRow) {
		this.blankPositionRow = blankPositionRow;
	}

	public Integer getblankPositionColumn() {
		return blankPositionColumn;
	}

	public void setblankPositionColumn(Integer blankPositionColumn) {
		this.blankPositionColumn = blankPositionColumn;
	}

	public Boolean getSolved() {
		return solved;
	}

	public void setSolved(Boolean solved) {
		this.solved = solved;
	}
	
	public ArrayList<Move> getPossibleMovesCurrentState() {
		return possibleMovesCurrentState;
	}
	
	public void setPossibleMovesCurrentState(ArrayList<Move> possibleMovesCurrentState) {
		this.possibleMovesCurrentState = possibleMovesCurrentState;
	}
	
	public ArrayList<Move> getAllLegalMoves() {
		return allLegalMoves;
	}
	
	public void setAllLegalMoves(ArrayList<Move> allLegalMoves) {
		this.allLegalMoves = allLegalMoves;
	}

	
}
