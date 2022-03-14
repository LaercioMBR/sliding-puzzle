package sliding_puzzle;

public class Move {
	private String moveName;
	private Boolean isPossible; // TODO REFACTOR - IT'S NOT USED
	private Integer swapDirection;
	private Integer relativeSwapPositionRow;
	private Integer relativeSwapPositionColumn;
	
	
	public Move(String moveName, Integer swapDirection) {		
		this.moveName = moveName;
		this.swapDirection = swapDirection;
		this.isPossible  = false;
		discoverRelativeSwapPositions();
	}
	
		
	private void discoverRelativeSwapPositions() {
		
		switch (swapDirection.intValue()) {
			case 1:
				setRelativeSwapPositionRow(Integer.decode("1"));
				setRelativeSwapPositionColumn(Integer.decode("-1"));
				break;
			case 2:
				setRelativeSwapPositionRow(Integer.decode("1"));
				setRelativeSwapPositionColumn(Integer.decode("0"));
				break;
			case 3:
				setRelativeSwapPositionRow(Integer.decode("1"));
				setRelativeSwapPositionColumn(Integer.decode("1"));
				break;
			case 4:
				setRelativeSwapPositionRow(Integer.decode("0"));
				setRelativeSwapPositionColumn(Integer.decode("-1"));
				break;
			case 5:
				setRelativeSwapPositionRow(Integer.decode("0"));
				setRelativeSwapPositionColumn(Integer.decode("0"));
				break;
			case 6:
				setRelativeSwapPositionRow(Integer.decode("0"));
				setRelativeSwapPositionColumn(Integer.decode("1"));
				break;
			case 7:
				setRelativeSwapPositionRow(Integer.decode("1"));
				setRelativeSwapPositionColumn(Integer.decode("-1"));
				break;
			case 8:
				setRelativeSwapPositionRow(Integer.decode("-1"));
				setRelativeSwapPositionColumn(Integer.decode("0"));
				break;
			case 9:
				setRelativeSwapPositionRow(Integer.decode("-1"));
				setRelativeSwapPositionColumn(Integer.decode("1"));
				break;
		}
	}


	public Integer getRelativeSwapPositionRow() {
		return relativeSwapPositionRow;
	}


	public void setRelativeSwapPositionRow(Integer relativeSwapPositionRow) {
		this.relativeSwapPositionRow = relativeSwapPositionRow;
	}


	public Integer getRelativeSwapPositionColumn() {
		return relativeSwapPositionColumn;
	}

	public void setRelativeSwapPositionColumn(Integer relativeSwapPositionColumn) {
		this.relativeSwapPositionColumn = relativeSwapPositionColumn;
	}


	public String getMoveName() {
		return moveName;
	}

	public void setMoveName(String moveName) {
		this.moveName = moveName;
	}

	public Boolean getIsPossible() {
		return isPossible;
	}

	public void setIsPossible(Boolean isPossible) {
		this.isPossible = isPossible;
	}

	public Integer getSwapDirection() {
		return swapDirection;
	}

	public void setSwapDirection(Integer swapDirection) {
		this.swapDirection = swapDirection;
	}
	
	

	
	
}
