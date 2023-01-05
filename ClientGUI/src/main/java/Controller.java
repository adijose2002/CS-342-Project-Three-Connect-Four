import java.io.FileNotFoundException;
import java.util.*;

public class Controller extends JavaFXTemplate {

	static Stack < Main > s = new Stack < > ();

	public static void newBoard(Main[][] myArray) {
		for (int i = 0; i < myArray.length; i++) {
			for (int j = 0; j < myArray[0].length; j++) {
				myArray[i][j].allowed = false;
				myArray[i][j].id = 0;
				myArray[i][j].setStyle("-fx-background-color: white;");
				myArray[i][j].setDisable(false);
			}
		}
		decision = 0;
		s.clear();
		playerNum.setText("Player One Turn");
	}

	public static void discMove(Main[][] myArray, int row, int col) throws FileNotFoundException {
		if (row == 5) {
			checkDiscMove(myArray, row, col);
		}
		if (row < 5) {
			if (myArray[row + 1][col].allowed) {
				checkDiscMove(myArray, row, col);
			}
		}
	}

	public static void checkDiscMove(Main[][] myArray, int row, int col) throws FileNotFoundException {
		moveColor(myArray, row, col);
		if (won(myArray, myArray[row][col].id) || tie(myArray)) {
			scene.setScene(finalWindow());
		}

	}

	public static boolean tie(Main[][] myArray) {
		for (int i = 0; i < myArray.length; i++) {
			for (int j = 0; j < myArray[0].length; j++) {
				if (!myArray[i][j].allowed) {
					return false;
				}
			}
		}
		return true;
	}

	public static boolean won(Main[][] myArray, int a) {
		for (int i = 0; i < myArray.length - 3; i++) {
			for (int j = 0; j < myArray[0].length; j++) {
				if (myArray[i][j].id == a && myArray[i + 1][j].id == a && myArray[i + 2][j].id == a && myArray[i + 3][j].id == a) {
					return true;
				}
			}
		}
		for (int i = 0; i < myArray.length; i++) {
			for (int j = 0; j < myArray[0].length - 3; j++) {
				if (myArray[i][j].id == a && myArray[i][j + 1].id == a && myArray[i][j + 2].id == a && myArray[i][j + 3].id == a) {
					return true;
				}
			}
		}
		for (int i = 0; i < myArray.length - 3; i++) {
			for (int j = 0; j < myArray[0].length - 3; j++) {
				if (myArray[i][j].id == a && myArray[i + 1][j + 1].id == a && myArray[i + 2][j + 2].id == a && myArray[i + 3][j + 3].id == a) {
					return true;
				}
			}
		}
		for (int i = 0; i < myArray.length; i++) {
			for (int j = 0; j < myArray[0].length - 3; j++) {
				if (myArray[i][j].id == a && myArray[i - 1][j + 1].id == a && myArray[i - 2][j + 2].id == a && myArray[i - 3][j + 3].id == a) {
					return true;
				}
			}
		}
		return false;
	}

	public static void moveColor(Main[][] myArray, int row, int col) {
		if (decision == 0) {
			discColor(myArray, row, col);
			myArray[row][col].id = 1;
			myArray[row][col].allowed = true;
			myArray[row][col].setDisable(true);
			s.push(myArray[row][col]);
			playerNum.setText("Player Two Turn");
			decision = 1;
		} else if (decision == 1) {
			discColor(myArray, row, col);
			myArray[row][col].id = 2;
			myArray[row][col].allowed = true;
			myArray[row][col].setDisable(true);
			s.push(myArray[row][col]);
			playerNum.setText("Player One Turn");
			decision = 0;
		} else {
			myArray[row][col].setStyle("-fx-background-color: white;");
		}
	}

	public static void discColor(Main[][] myArray, int row, int col) {
		if (decision == 0) {
			myArray[row][col].setStyle("-fx-background-color: blue;");
		} else {
			myArray[row][col].setStyle("-fx-background-color: green;");
		}
	}
}
