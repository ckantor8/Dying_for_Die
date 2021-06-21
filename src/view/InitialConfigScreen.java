package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class InitialConfigScreen {
    private int width;
    private int height;
    private Button advanceButton;
    private Button returnButton;
    private VBox options;
    private TextField name;
    private int numPlayers = 0;
    private String input;
    private ToggleButton easy;
    private ToggleButton medium;
    private ToggleButton hard;
    private ToggleButton vhard;
    private ToggleButton two;
    private ToggleButton three;
    private ToggleButton four;
    private int gold;

    private InitialConfigScreen() {
    }

    public InitialConfigScreen(int width, int height) {
        this.width = width;
        this.height = height;
        returnButton = new Button("Back");
        advanceButton = new Button("Begin Game");
    }

    public Scene setupScene() {
        BorderPane configPane = new BorderPane();
        Scene configScene = new Scene(configPane, width, height);
        options = new VBox();
        options.setPadding(new Insets(100, 0, 0, 0));
        options.setSpacing(20);
        options.setAlignment(Pos.CENTER);
        configPane.setCenter(options);

        Image image0 = new Image("file:resources/images/backgrounds/config_screen.png");
        BackgroundSize backgroundSize0 = new BackgroundSize(
            BackgroundSize.AUTO, BackgroundSize.AUTO,
            true, true, true, true);
        BackgroundImage backgroundImage0 = new BackgroundImage(image0,
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
            backgroundSize0);
        Background background1 = new Background(backgroundImage0);
        configPane.setBackground(background1);

        return getScene(configPane, configScene);
    }

    public Scene getScene(BorderPane configPane, Scene configScene) {

        name = new TextField();
        name.setId("name");
        name.setPrefWidth(215);
        name.setMaxWidth(215);
        Text instr1 = new Text("Title Your Game");
        instr1.setFill(Color.DARKSLATEGRAY);
        instr1.setFont(new Font("Copperplate Gothic Bold", 20));
        instr1.setTextAlignment(TextAlignment.CENTER);
        StackPane instruction1 = new StackPane();

        instruction1.setBackground(new Background(new
            BackgroundFill(Color.ORANGERED, CornerRadii.EMPTY,
            new Insets(0, 140, 0, 140))));
        instruction1.getChildren().add(instr1);
        options.getChildren().add(instruction1);
        options.getChildren().add(name);
        HBox difficulties = new HBox();
        difficulties.setSpacing(10);
        difficulties.setAlignment(Pos.CENTER);
        Text instr2 = new Text("Select Your Initial $$$");
        instr2.setFill(Color.DARKSLATEGRAY);
        instr2.setFont(new Font("Copperplate Gothic Bold", 17));
        StackPane instruction2 = new StackPane();
        instruction2.setBackground(new Background(new
            BackgroundFill(Color.ORANGERED, CornerRadii.EMPTY,
            new Insets(0, 140, 0, 140))));
        instruction2.getChildren().add(instr2);
        options.getChildren().add(instruction2);
        options.getChildren().add(difficulties);

        ToggleGroup difficulty = new ToggleGroup();
        easy = new ToggleButton("$4");
        easy.setId("$4");
        easy.getStyleClass().add("bronze");
        easy.getStyleClass().add("button");
        difficulties.getChildren().add(easy);
        easy.setToggleGroup(difficulty);
        medium = new ToggleButton("$3");
        medium.setId("$3");
        medium.getStyleClass().add("silver");
        medium.getStyleClass().add("button");
        difficulties.getChildren().add(medium);
        medium.setToggleGroup(difficulty);
        hard = new ToggleButton("$2");
        hard.setId("$2");
        hard.getStyleClass().add("gold");
        hard.getStyleClass().add("button");
        difficulties.getChildren().add(hard);
        hard.setToggleGroup(difficulty);
        vhard = new ToggleButton("$1");
        vhard.setId("$1");
        vhard.getStyleClass().add("plat");
        vhard.getStyleClass().add("button");
        difficulties.getChildren().add(vhard);
        vhard.setToggleGroup(difficulty);

        Text instr3 = new Text("Number of Players");
        instr3.setFill(Color.DARKSLATEGRAY);
        instr3.setFont(new Font("Copperplate Gothic Bold", 18));
        StackPane instruction3 = new StackPane();
        instruction3.setBackground(new Background(new
            BackgroundFill(Color.ORANGERED, CornerRadii.EMPTY,
            new Insets(0, 140, 0, 140))));
        instruction3.getChildren().add(instr3);
        options.getChildren().add(instruction3);
        HBox players = new HBox();
        players.setSpacing(10);
        players.setAlignment(Pos.CENTER);
        ToggleGroup numPlayers = new ToggleGroup();

        two = new ToggleButton("2");
        two.setId("2");
        two.setContentDisplay(ContentDisplay.TOP);
        players.getChildren().add(two);
        two.setToggleGroup(numPlayers);

        three = new ToggleButton("3");
        three.setId("3");
        three.setContentDisplay(ContentDisplay.TOP);
        players.getChildren().add(three);
        three.setToggleGroup(numPlayers);

        four = new ToggleButton("4");
        four.setId("4");
        four.setContentDisplay(ContentDisplay.TOP);
        players.getChildren().add(four);
        four.setToggleGroup(numPlayers);

        options.getChildren().add(players);

        HBox buttons = new HBox();
        buttons.setSpacing(25);
        returnButton.getStyleClass().add("return");
        returnButton.setMinSize(100, 40);
        returnButton.setMaxSize(100, 40);
        advanceButton.getStyleClass().add("advance");
        advanceButton.setMinSize(100, 40);
        advanceButton.setMaxSize(100, 40);
        buttons.getChildren().add(returnButton);
        buttons.getChildren().add(advanceButton);
        configPane.setBottom(buttons);
        buttons.setAlignment(Pos.CENTER);

        configPane.setMargin(options, new Insets(0, 0, 0, 0));
        configPane.setMargin(buttons, new Insets(0, 0, 10, 0));
        configScene.getStylesheets().add("file:resources/css/ConfigScreen.css");
        return configScene;
    }

    public int checkSelections() {
        input = name.getText();
        if (input == null || input.isEmpty() || input.trim().isEmpty()) {
            Alert invalidName = new Alert(Alert.AlertType.ERROR);
            invalidName.setTitle("Error Dialog");
            invalidName.setHeaderText("Invalid Name");
            invalidName.setContentText("Invalid name " + '"' + input + '"'
                + " entered\nPlease enter a valid name");
            invalidName.show();
        } else {
            if (easy.isSelected()) {
                gold = 4;
            } else if (medium.isSelected()) {
                gold = 3;
            } else if (hard.isSelected()) {
                gold = 2;
            } else if (vhard.isSelected()) {
                gold = 1;
            } else {
                Alert invalidDiff = new Alert(Alert.AlertType.ERROR);
                invalidDiff.setTitle("Error Dialog");
                invalidDiff.setHeaderText("Invalid Difficulty");
                invalidDiff.setContentText("No difficulty chosen\nPlease "
                    + "select a difficulty");
                invalidDiff.show();
            }

            if (two.isSelected()) {
                numPlayers = 2;
            } else if (three.isSelected()) {
                numPlayers = 3;
            } else if (four.isSelected()) {
                numPlayers = 4;
            } else {
                Alert invalidW = new Alert(Alert.AlertType.ERROR);
                invalidW.setTitle("Error Dialog");
                invalidW.setHeaderText("Invalid character");
                invalidW.setContentText("No character chosen\nPlease "
                    + "select a character");
                invalidW.show();
            }

            if (numPlayers != 0 && gold != 0) {
                return 1;
            }

        }
        return -1;
    }

    public int getNumPlayers() {
        return numPlayers;
    }

    public String getInput() {
        return input;
    }

    public int getGold() {
        return gold;
    }

    public Button getAdvanceButton() {
        return advanceButton;
    }

    public Button getReturnButton() {
        return returnButton;
    }

}
