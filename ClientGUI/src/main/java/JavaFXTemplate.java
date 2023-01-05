import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.application.*;
import javafx.event.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.Stage;

public class JavaFXTemplate extends Application {

	static Text playerNum = new Text();
	static Text welcome = new Text();
	static int decision = 0;
	static Main myArray[][] = new Main[6][7];
	static VBox button = new VBox();
	static Stage scene;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		scene = primaryStage;
		primaryStage.setTitle("Connect 4");
		primaryStage.setScene(welcomeScene());
		primaryStage.show();
	}

	public Scene welcomeScene() throws FileNotFoundException {

		welcome.setText("Welcome to Connect Four!");
		welcome.setStyle("-fx-fill: red;" + "-fx-font-weight: bold");

		Button bOne = new Button("Start");
		bOne.setOnAction(e -> {
			scene.setScene(connectFourScene());
			scene.show();
		});

		FileInputStream file = new FileInputStream("src/main/resources/wBackground.jpg");
		Image jpg = new Image(file);
		BackgroundImage backgroundPicture = new BackgroundImage(jpg,
				BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.DEFAULT,
				BackgroundSize.DEFAULT);
		Background background = new Background(backgroundPicture);

		button = new VBox(30, welcome, bOne);
		button.setAlignment(Pos.CENTER);
		button.setBackground(background);
		return new Scene(button, 800, 800);
	}

	public static Scene connectFourScene() {
		playerNum.setText("Player One Turn!");
		playerNum.setStyle("-fx-fill: red;" + "-fx-font-weight: bold");
		FileInputStream file = null;
		try {
			file = new FileInputStream("src/main/resources/metal.jpg");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Image jpg = new Image(file);
		BackgroundImage backgroundPicture = new BackgroundImage(jpg,
				BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.DEFAULT,
				BackgroundSize.DEFAULT);
		Background b = new Background(backgroundPicture);

		GridPane board = new GridPane();
		grid(board);
		board.setHgap(3);
		board.setVgap(3);
		board.setAlignment(Pos.CENTER);

		button = new VBox(30, playerNum, board);
		button.setAlignment(Pos.TOP_CENTER);
		button.setBackground(b);
		return new Scene(button, 800, 800);
	}

	public static Scene finalWindow() throws FileNotFoundException {

		if (Controller.tie(myArray)) {
			playerNum.setText("Tie");
		} else if (decision == 0) {
			playerNum.setText("Player Two Won!");
		} else if (decision == 1) {
			playerNum.setText("Player One Won!");
		}

		playerNum.setStyle("-fx-fill: red;" + "-fx-font-weight: bold");

		Button pA = new Button("Play Again");
		Button eG = new Button("Exit Game");
		pA.setOnAction(e -> {
			Controller.newBoard(myArray);
			scene.setScene(connectFourScene());
			scene.show();
		});

		eG.setOnAction(e -> Platform.exit());
		FileInputStream input = new FileInputStream("src/main/resources/green.jpeg");
		Image image = new Image(input);
		BackgroundImage backgroundimage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.DEFAULT,
				BackgroundSize.DEFAULT);
		Background background = new Background(backgroundimage);

		HBox buttons = new HBox(30, pA, eG);
		VBox text = new VBox(playerNum);
		text.setAlignment(Pos.CENTER);
		buttons.setAlignment(Pos.CENTER);
		button = new VBox(100, text, buttons);
		button.setAlignment(Pos.CENTER);
		button.setBackground(background);

		return new Scene(button, 600, 600);
	}

	public static void grid(GridPane grid) {
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 6; j++) {
				Main bOne = new Main(j, i);
				bOne.setMinSize(100, 100);
				bOne.setStyle("-fx-background-color: white;");
				EventHandler < ActionEvent > a = e -> {
					Main bTwo = (Main) e.getSource();
					int row = bTwo.Row;
					int col = bTwo.Col;
					try {
						Controller.discMove(myArray, row, col);
					} catch (FileNotFoundException b) {

						b.printStackTrace();
					}
				};
				bOne.setOnAction(a);
				myArray[j][i] = bOne;
				grid.add(bOne, i, j);
			}
		}
	}
}