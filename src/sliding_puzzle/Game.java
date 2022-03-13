package sliding_puzzle;

public class Game {

	private Integer numberRows;
	private Integer numberColumns;
	private Integer maxValues;
	private Integer numberDigits;
	private Integer charactersPerCell;
	private Integer rowSize;
	private int[][] gameMatrix;
	private boolean solved;
	
	
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
		this.rowSize = charactersPerCell.intValue() * numberColumns.intValue() + ( 1 * numberColumns.intValue() ) - 1  ;
	}

	public void run() {
		populateGameBoard(this.numberRows,this.numberColumns);
		this.gameMatrix = scrambleGameBoard(this.gameMatrix);

		while(!solved) {
			printGameBoard();
			askPlayerMovementChoice();
			
			if( isGameSolved())
				break;
			
		}
		congratulatePlayer();
		printGameBoard();
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

	public boolean isSolved() {
		return solved;
	}

	public void setSolved(boolean solved) {
		this.solved = solved;
	}

	public void congratulatePlayer() {
		// TODO Auto-generated method stub
		
	}

	public int[][] scrambleGameBoard(int[][] gameMatrix) {
		int[][] scrambledGame = gameMatrix;// TODO Auto-generated method stub
		return scrambledGame;
	}


	public void populateGameBoard(Integer numberRows, Integer numberColumns) {
		
		int max = numberRows.intValue() * numberColumns.intValue();
		
		int[][] game = new int[numberRows.intValue()][numberColumns.intValue()];
		
		int counter = 1;
		for(int i = 0; i < numberRows ; i++) {
			for (int j = 0; j < numberColumns; j++) {
				if(counter == max)
					game[i][j] = 0;
				else
					game[i][j] = counter;
				counter++;				
			}
		}
		
		setGameMatrix(game);
		Integer temp = game[0][0];
		System.out.println(temp.toString());
		System.out.println(this.gameMatrix[0][0]);
		
	}


	public boolean isGameSolved() {
		// TODO Auto-generated method stub
		return false;
	}


	public void askPlayerMovementChoice() {
		// TODO Auto-generated method stub
		
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
		sb.append(cellValueString);
		sb.append(" ");
		
		System.out.print(sb.toString());
				
	}

	private void printBorder() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("|");
		for(int i = 0; i < rowSize.intValue(); i++) {
			sb.append("=");
		}
		sb.append("|");
		
		System.out.println(sb.toString());
 		
	}	
	

}

/*
	if(gameMatrix[i][j] != 0) {
			
			sb.append("|");
			sb.append(gameMatrix[i][j]);

			sb.append("|");
				1  |  2  |  3  |");
			
		}
			
		sb.append("|=====|=====|=====|");
		sb.append("|  4  |  5  |  6  |");
		sb.append("|=====|=====|=====|");
		sb.append("|  7  |  8  | [ ] |");

	System.out.print(gameMatrix[i][j]);
	
}

*/