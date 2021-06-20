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

public class PlayerConfigScreen {
    private int width;
    private int height;
    private Button advanceButton;
    private Button returnButton;
    private VBox options;
    private TextField name;
    private Color chtr;
    private String input;
    private ToggleButton red;
    private ToggleButton blue;
    private ToggleButton yellow;
    private ToggleButton purple;

    private PlayerConfigScreen() {
    }

    public PlayerConfigScreen(int width, int height) {
        this.width = width;
        this.height = height;
        returnButton = new Button("Back");
        advanceButton = new Button("Advance");
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
        Text instr1 = new Text("Type Your Name");
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

        Text instr3 = new Text("Choose a Character");
        instr3.setFill(Color.DARKSLATEGRAY);
        instr3.setFont(new Font("Copperplate Gothic Bold", 18));
        StackPane instruction3 = new StackPane();
        instruction3.setBackground(new Background(new
            BackgroundFill(Color.ORANGERED, CornerRadii.EMPTY,
            new Insets(0, 140, 0, 140))));
        instruction3.getChildren().add(instr3);
        options.getChildren().add(instruction3);
        HBox chtrs = new HBox();
        chtrs.setSpacing(10);
        chtrs.setAlignment(Pos.CENTER);
        ToggleGroup chtr = new ToggleGroup();

        red = new ToggleButton("Red");
        red.setId("red");
        red.setContentDisplay(ContentDisplay.TOP);
        chtrs.getChildren().add(red);
        red.setToggleGroup(chtr);

        blue = new ToggleButton("Blue");
        blue.setId("blue");
        blue.setContentDisplay(ContentDisplay.TOP);
        chtrs.getChildren().add(blue);
        blue.setToggleGroup(chtr);

        yellow = new ToggleButton("Yellow");
        yellow.setId("yellow");
        yellow.setContentDisplay(ContentDisplay.TOP);
        chtrs.getChildren().add(yellow);
        yellow.setToggleGroup(chtr);

        purple = new ToggleButton("Purple");
        purple.setId("purple");
        purple.setContentDisplay(ContentDisplay.TOP);
        chtrs.getChildren().add(purple);
        purple.setToggleGroup(chtr);

        options.getChildren().add(chtrs);

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
            if (red.isSelected()) {
                chtr = Color.RED;
            } else if (blue.isSelected()) {
                chtr = Color.BLUE;
            } else if (yellow.isSelected()) {
                chtr = Color.YELLOW;
            } else if (purple.isSelected()) {
                chtr = Color.PURPLE;
            } else {
                Alert invalidW = new Alert(Alert.AlertType.ERROR);
                invalidW.setTitle("Error Dialog");
                invalidW.setHeaderText("Invalid character");
                invalidW.setContentText("No character chosen\nPlease "
                    + "select a character");
                invalidW.show();
            }

            if (!chtr.equals("0")) {
                return 1;
            }

        }
        return -1;
    }

    public Color getChtr() {
        return chtr;
    }

    public String getInput() {
        return input;
    }

    public Button getAdvanceButton() {
        return advanceButton;
    }

    public Button getReturnButton() {
        return returnButton;
    }

}