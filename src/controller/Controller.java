package controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.PlayerModel;
import view.InitialConfigScreen;
import view.PlayerConfigScreen;
import view.Screen;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Controller extends Application {
    @FXML
    private GridPane grid;
    private Integer[][] bonusSquares = new Integer[7][7];
    private Stage stage;
    private final int width = 500;
    private final int height = 500;
    private String title;
    private int numPlayers;
    private int startingGold;
    private ArrayList<PlayerModel> players = new ArrayList<>();
    private PlayerModel currPlayer = new PlayerModel("null", Color.BLACK, 0);
    private HBox toolbar;
    private int turn;
    private VBox vbox;
    private Label p1;
    private Label currGold;
    private Label currentTurn;
    private ImageView currSpriteImg;
    private Button quitButton;
    private Boolean gameWon = false;
    private VBox diceBox;
    private Group diceImg;
    private Timeline timeline;
    private ImageView diceOne;
    private ImageView diceTwo;
    private ImageView diceThree;
    private ImageView diceFour;
    private ImageView diceFive;
    private ImageView diceSix;
    private ImageView diceRoll;
    private Label rolled;
    private VBox otherPlayers;
    private Label other1 = new Label();
    private Label other2 = new Label();
    private Label other3 = new Label();
    private Label[] others = {other1, other2, other3};
    private ArrayList<ImageView> dice = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        stage.setTitle("Your New Favorite Dungeon Crawler");
        initWelcomeScreen();
    }

    private void initWelcomeScreen() {
        gameWon = false;
        players = new ArrayList<>();
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
                player.setSpriteImg(configScreen.getSpriteImg());
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

        returnButton.setOnAction(e -> goToInitialConfigScreen());

        Scene configScene = configScreen.setupScene();
        stage.setScene(configScene);
        stage.show();

    }

    public void generateBoard() throws IOException {
        //Randomize player order
        Collections.shuffle(players);
        currPlayer = players.get(0);
        // Create the FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        // Path to the FXML File
        String fxmlDocPath = "Board.fxml";
        FileInputStream fxmlStream = new FileInputStream(fxmlDocPath);

        setupToolbar();

        // Create the Pane and all Details
        grid = loader.load(fxmlStream);

        /*Image image0 = new Image("file:resources/images/backgrounds/SquareImage.jpg");
        BackgroundSize backgroundSize0 = new BackgroundSize(
            BackgroundSize.AUTO, BackgroundSize.AUTO,
            true, true, true, true);
        BackgroundImage backgroundImage0 = new BackgroundImage(image0,
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
            backgroundSize0);
        Background background1 = new Background(backgroundImage0);
        int i = 0;
        while (i != 5) {
            Random rand = new Random();
            int red = rand.nextInt(255);
            int green = rand.nextInt(255);
            int blue = rand.nextInt(255);
            Rectangle rec = new Rectangle();
            rec.setWidth(75);
            rec.setHeight(57);
            rec.setArcWidth(5);
            rec.setArcHeight(5);
            rec.setFill(Color.rgb(red, green, blue, .99));
            int[] intArray = {1, 3, 5};
            int column = rand.nextInt(7);
            int row = intArray[rand.nextInt(3)];
            if (bonusSquares[column][row] == null) {
                grid.add(rec, rand.nextInt(7), intArray[rand.nextInt(3)]);
                bonusSquares[column][row] = 1;
                i++;
            }
        }
        grid.setBackground(background1);*/
        grid.setBackground(new Background(new BackgroundFill(Color.BLACK,
            new CornerRadii(0), new Insets(0))));

        grid.getStyleClass().add("mygridStyle");
        vbox.getChildren().add(grid);
        // Create the Scene
        Scene scene = new Scene(vbox);
        // Set the Scene to the Stage
        stage.setScene(scene);
        // Set the Title to the Stage
        stage.setTitle("Game Board");

        int x = 0;
        for (PlayerModel player : players) {
            Circle sprite = new Circle(10);
            sprite.setFill(player.getCharacter());
            player.setSprite(sprite);
            grid.add(sprite, 0, 1);
            if (x == 0) {
                GridPane.setHalignment(sprite, HPos.LEFT);
                GridPane.setValignment(sprite, VPos.TOP);
            } else if (x == 1) {
                GridPane.setHalignment(sprite, HPos.LEFT);
                GridPane.setValignment(sprite, VPos.BOTTOM);
            } else if (x == 2) {
                GridPane.setHalignment(sprite, HPos.RIGHT);
                GridPane.setValignment(sprite, VPos.TOP);
            } else {
                GridPane.setHalignment(sprite, HPos.RIGHT);
                GridPane.setValignment(sprite, VPos.BOTTOM);
            }
            GridPane.setMargin(sprite, new Insets(3, 3, 3, 3));
            x++;
        }

        // Display the Stage
        stage.show();
        while (!gameWon) {
            for (PlayerModel player : players) {
                takeTurn(player);
            }
        }
    }

    public void setupToolbar() {
        //Vbox for organizing toolbar and board
        vbox = new VBox();
        toolbar = new HBox(80);
        vbox.getChildren().add(toolbar);

        //toolbar configurations
        toolbar.setBackground(new Background(new BackgroundFill(currPlayer.getCharacter(),
            CornerRadii.EMPTY, Insets.EMPTY)));
        toolbar.setMinHeight(100);
        toolbar.setAlignment(Pos.CENTER);

        // Sprite Configuration on Left of Toolbar
        currSpriteImg = new ImageView();
        currSpriteImg.setImage(currPlayer.getSpriteImg());
        VBox spriteDisp = new VBox(currSpriteImg);
        spriteDisp.setPadding(new Insets(10, 10, 10, 10));


        //Display current player's info
        VBox playerInfo = new VBox();
        p1 = new Label("Current Player: " + currPlayer.getName());
        currGold = new Label("Gold: " + currPlayer.getGold());
        Label message = new Label("It's your turn!");
        playerInfo.setAlignment(Pos.CENTER_LEFT);
        playerInfo.setMinHeight(100);
        playerInfo.setMinWidth(75);
        playerInfo.getChildren().addAll(p1, currGold, message);

        //Displaying other player's info
        otherPlayers = new VBox();
        int j = 0;
        for (PlayerModel player : players) {
            if (player != currPlayer) {
                others[j].setText(player.getName() + ": "
                    + player.getGold() + " Gold");
                j++;
            }
        }
        Label txt = new Label("Other players:");
        otherPlayers.setAlignment(Pos.CENTER_LEFT);
        otherPlayers.setMinHeight(100);
        otherPlayers.setMinWidth(60);
        otherPlayers.getChildren().addAll(txt, other1, other2, other3);

        //Setting up dice
        diceBox = new VBox();
        diceOne = new ImageView("file:resources/images/sprites/one.png");
        diceTwo = new ImageView("file:resources/images/sprites/two.png");
        diceThree = new ImageView("file:resources/images/sprites/three.png");
        diceFour = new ImageView("file:resources/images/sprites/four.png");
        diceFive = new ImageView("file:resources/images/sprites/five.png");
        diceSix = new ImageView("file:resources/images/sprites/one.png");
        dice.add(diceOne);
        dice.add(diceTwo);
        dice.add(diceThree);
        dice.add(diceFour);
        dice.add(diceFive);
        dice.add(diceSix);
        for (ImageView diceImage : dice) {
            diceImage.setFitHeight(45);
            diceImage.setFitWidth(45);
        }
        diceImg = new Group(diceOne);
        timeline = new Timeline();
        timeline.setCycleCount(2);
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(100),
            (ActionEvent e) -> {
                diceImg.getChildren().setAll(diceTwo);
            }
        ));

        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(200),
            (ActionEvent e) -> {
                diceImg.getChildren().setAll(diceThree);
            }
        ));

        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(300),
            (ActionEvent e) -> {
                diceImg.getChildren().setAll(diceFour);
            }
        ));

        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(400),
            (ActionEvent e) -> {
                diceImg.getChildren().setAll(diceFive);
            }
        ));

        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(500),
            (ActionEvent e) -> {
                diceImg.getChildren().setAll(diceSix);
            }
        ));

        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(600),
            (ActionEvent e) -> {
                diceImg.getChildren().setAll(diceOne);
            }
        ));
        diceRoll = new ImageView();
        diceRoll.setFitWidth(45);
        diceRoll.setFitHeight(45);
        rolled = new Label();
        diceBox.setMinWidth(80);
        diceBox.getChildren().add(diceImg);
        diceBox.getChildren().add(diceRoll);
        diceBox.getChildren().add(rolled);

        //Display current turn and quit button
        turn = 1;
        VBox currTurn = new VBox();
        currentTurn = new Label("Turn: " + (turn / players.size()));
        currTurn.setAlignment(Pos.CENTER_RIGHT);
        currTurn.setMinWidth(50);
        currTurn.setMinHeight(100);
        quitButton = new Button("Quit");
        quitButton.setId("quitButton2");
        quitButton.setOnAction(e -> {
            Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
            confirmation.setContentText("Are you sure you want to quit the game?");
            confirmation.setHeaderText("Quit");
            confirmation.setTitle("Quit");
            confirmation.showAndWait();
            if (confirmation.getResult() == ButtonType.OK) {
                initWelcomeScreen();
            }
        });
        currTurn.getChildren().addAll(currentTurn, quitButton);
        spriteDisp.setMaxWidth(50);
        spriteDisp.setMaxHeight(50);
        toolbar.getChildren().addAll(spriteDisp, playerInfo, otherPlayers, diceBox, currTurn);
    }

    public void takeTurn(PlayerModel player) {
        currPlayer = player;
        //updateToolbar();
        Alert choose = new Alert(Alert.AlertType.CONFIRMATION);
        choose.setX(170);
        choose.setY(400);
        choose.setContentText("Make a move!");
        choose.setHeaderText("Hurry!");
        choose.setTitle("Move");
        ((Button) choose.getDialogPane().lookupButton(ButtonType.OK)).setText("Roll");
        ((Button) choose.getDialogPane().lookupButton(ButtonType.CANCEL)).setText("Pass");
        if (!gameWon) {
            choose.showAndWait();
        }

        if (choose.getResult() == ButtonType.OK) {
            diceImg.setVisible(true);
            diceRoll.setVisible(false);
            rollDie(player);
        }

        /////////FOR TESTING/DEBUGGING PURPOSES/////////
//        if (choose.getResult() == ButtonType.CANCEL) {
//            gameWon = true;
//            stage.close();
//        }
        ///////////////////////////////////////////////

        int c = GridPane.getColumnIndex(player.getSprite());
        int r = GridPane.getRowIndex(player.getSprite());

        if (r % 2 == 0) {
            chanceTile();
        } else if (c % 2 == 0) {
            player.setGold(player.getGold() - 1);
        } else {
            player.setGold(player.getGold() + 1);
        }

    }

    public void moveOneSquare(PlayerModel player) {
        Circle user = player.getSprite();
        int c = GridPane.getColumnIndex(user);
        int r = GridPane.getRowIndex(user);
        grid.getChildren().remove(user);

        if ((c == 10 && (r == 1 || r == 5)) || (c == 0 && (r == 3 || r == 7)) || (r % 2 == 0)) {
            r++;
        } else if (r == 3 || r == 7) {
            c--;
        } else if (c == 10 && r == 9) {
            gameWon = true;
            youWin();
        } else {
            c++;
        }

        grid.add(user, c, r);
    }

    private void rollDie(PlayerModel player) {
        timeline.play();
        timeline.setOnFinished((ActionEvent e) -> {
            diceRoll.setVisible(true);
            diceImg.setVisible(false);
            updateToolbar();
        });
        Random random = new Random();
        int roll = random.nextInt(6) + 1;
        if (roll == 1) {
            diceRoll.setImage(new Image("file:resources/images/sprites/one.png"));
        } else if (roll == 2) {
            diceRoll.setImage(new Image("file:resources/images/sprites/two.png"));
        } else if (roll == 3) {
            diceRoll.setImage(new Image("file:resources/images/sprites/three.png"));
        } else if (roll == 4) {
            diceRoll.setImage(new Image("file:resources/images/sprites/four.png"));
        } else if (roll == 5) {
            diceRoll.setImage(new Image("file:resources/images/sprites/five.png"));
        } else {
            diceRoll.setImage(new Image("file:resources/images/sprites/six.png"));
        }
        rolled.setText(currPlayer.getName() + " rolled...");
        for (int i = 1; i <= roll; i++) {
            moveOneSquare(player);
        }

    }

    private void updateToolbar() {
        turn++;
        p1.setText("Player: " + currPlayer.getName());
        currGold.setText("Gold: " + currPlayer.getGold());
        currentTurn.setText("Turn: " + (turn / players.size()));
        currSpriteImg.setImage(currPlayer.getSpriteImg());
        int j = 0;
        for (PlayerModel player : players) {
            if (player != currPlayer) {
                others[j].setText(player.getName() + ": "
                    + player.getGold() + " Gold");
                j++;
            }
        }
        toolbar.setBackground(new Background(new BackgroundFill(currPlayer.getCharacter(),
            CornerRadii.EMPTY, Insets.EMPTY)));

    }

    private void chanceTile() {
        //System.out.println("You've landed on a Chance Tile!");
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

    public int getPlayers() {
        return numPlayers;
    }

    public Label getP1() {
        return p1;
    }

    public PlayerModel getCurrPlayer() {
        return currPlayer;
    }

    public Label getCurrGold() {
        return currGold;
    }

    public Image getCurrSpriteImg() {
        return currSpriteImg.getImage();
    }

    public Label getCurrTurn() {
        return currentTurn;
    }
}
