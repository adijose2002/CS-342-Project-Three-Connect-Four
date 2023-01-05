import javafx.scene.control.Button;

public class Main extends Button {

	boolean allowed;
	int id;
	int Col;
	int Row;

	public Main(int row, int col) {
		this.Col = col;
		this.Row = row;
		allowed = false;
		id = 0;
	}
}