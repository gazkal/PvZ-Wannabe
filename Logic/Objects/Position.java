package tp.pr3.Logic.Objects;

public class Position {
	private int row;
	private int column;
	
	public Position (int r, int c){
		this.row = r;
		this.column = c;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}
	
}
