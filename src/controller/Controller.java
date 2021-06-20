package controller;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import model.PlayerModel;
import view.InitialConfigScreen;
import view.PlayerConfigScreen;
import view.Screen;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class Controller extends Application {
    @FXML
    private GridPane grid;
    private Button moveOne = new Button("Move 1");
    private Button moveThree = new Button("Move 3");
    private Stage stage;
    private final int width = 500;
    private final int height = 500;
    private String title;
    private int numPlayers;
    private int startingGold;
    private ArrayList<PlayerModel> players = new ArrayList<>();
    private PlayerModel currPlayer;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        stage.setTitle("Your New Favorite Dungeon Crawler");
        initWelcomeScreen();
    }

    private void initWelcomeScreen() {
        stage.setTitle("Your New Favorite Dungeon Crawler");
        String bigText = new String("Welcome to \n Dying for Die");
        String bg = new String("file:resources/"
            + "images/backgrounds/welcome_screen.png");
        String playText = new String("Click Here to Begin");
        String stats = null;
        Screen welcomeScreen = new Screen(width, height, bigText, bg, playText);

        Button quitButton = welcomeScreen.getQuitButton();
        quitButton.setOnAction(e -> stage.close());

        Button playButton = welcomeScreen.getPlayButton();
        playButton.setOnAction(e -> goToInitialConfigScreen());

        Scene welcomeScene = welcomeScreen.getScene();
        stage.setScene(welcomeScene);
        stage.show();
    }

    private void goToInitialConfigScreen() {
        stage.setTitle("Choose Very Carefully");
        InitialConfigScreen initialConfigScreen = new InitialConfigScreen(width, height);

        Button returnButton = initialConfigScreen.getReturnButton();
        returnButton.setOnAction(e -> initWelcomeScreen());
        Button advanceButton = initialConfigScreen.getAdvanceButton();

        advanceButton.setOnAction(e -> {
            if (initialConfigScreen.checkSelections() > 0) {
                title = initialConfigScreen.getInput();
                numPlayers = initialConfigScreen.getNumPlayers();
                startingGold = initialConfigScreen.getGold();
                playerConfigScreens();
            } else {
                goToInitialConfigScreen();
            }
        });

        Scene configScene = initialConfigScreen.setupScene();
        stage.setScene(configScene);
        stage.show();
    }

    private void playerConfigScreens() {
        stage.setTitle("Choose Very Carefully");
        PlayerConfigScreen configScreen = new PlayerConfigScreen(width, height);

        Button returnButton = configScreen.getReturnButton();
        returnButton.setOnAction(e -> initWelcomeScreen());
        Button advanceButton = configScreen.getAdvanceButton();

        advanceButton.setOnAction(e -> {
            if (configScreen.checkSelections() > 0) {
                PlayerModel player =
                    new PlayerModel(configScreen.getInput(),
                        configScreen.getChtr(), startingGold);
                players.add(player);
                if (players.size() == numPlayers) {
                    try {
                        generateBoard();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                } else {
                    playerConfigScreens();
                }
            } else {
                playerConfigScreens();
            }
        });

        Scene configScene = configScreen.setupScene();
        stage.setScene(configScene);
        stage.show();

    }

    public void generateBoard() throws IOException {
        // Create the FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        // Path to the FXML File
        String fxmlDocPath = "C:\\Users\\royal\\One"
            + "Drive\\Desktop\\CS3300\\CS3300-SU21\\Board.fxml";
        FileInputStream fxmlStream = new FileInputStream(fxmlDocPath);

        //loader.setController(this);

        // Create the Pane and all Details
        grid = loader.load(fxmlStream);

        grid.setBackground(new Background(new BackgroundFill(Color.BLACK,
            new CornerRadii(0), new Insets(0))));
        grid.getStyleClass().add("mygridStyle");

        // Create the Scene
        Scene scene = new Scene(grid);
        // Set the Scene to the Stage
        stage.setScene(scene);
        // Set the Title to the Stage
        stage.setTitle(title);

        for (PlayerModel player : players) {
            Circle sprite = new Circle(5);
            sprite.setFill(player.getCharacter());
            player.setSprite(sprite);
            grid.add(sprite, 0, 1);
        }

        grid.add(moveOne, 1, 6, 2, 1);
        grid.add(moveThree, 5, 6, 2, 1);

        // Display the Stage
        stage.show();

        for (PlayerModel player : players) {
            takeTurn(player);
        }
    }

    public void takeTurn(PlayerModel player) {
        moveOne.setOnAction(e -> moveOneSquare(player));
        moveThree.setOnAction(e -> move3Squares(player));
    }

    public void moveOneSquare(PlayerModel player) {
        Circle user = player.getSprite();
        int c = GridPane.getColumnIndex(user);
        int r = GridPane.getRowIndex(user);
        grid.getChildren().remove(user);

        if ((c == 7 && r == 1) || (c == 0 && r == 3) || (r == 2 || r == 4)) {
            r++;
        } else if (r == 3) {
            c--;
        } else if (c == 7 && r == 5) {
            youWin();
        } else {
            c++;
        }

        //user.setFill(playerModel.getCharacter());
        grid.add(user, c, r);
        //user.setFill(playerModel.getCharacter());

    }

    public void move3Squares(PlayerModel player) {
        for (int i = 1; i <= 3; i++) {
            moveOneSquare(player);
        }
    }

    private void youWin() {
        stage.setTitle("You Win!");
        String bigText = new String("Congratulations on winning \n "
            + "Dying for Die, " + currPlayer.getName() + "!");
        String bg = new String("file:resources/"
            + "images/backgrounds/win_screen.jpg");
        String playText = new String("Click Here to Play Again");
        Screen winScreen = new Screen(width, height, bigText, bg, playText);

        Button quitButton = winScreen.getQuitButton();
        quitButton.setOnAction(e -> stage.close());

        Button replayButton = winScreen.getPlayButton();
        replayButton.setId("replaybutton");
        replayButton.setOnAction(e -> initWelcomeScreen());

        Scene winScene = winScreen.getScene();
        stage.setScene(winScene);
        stage.show();
    }

}