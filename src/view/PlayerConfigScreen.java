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
import javafx.scene.image.ImageView;
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
    private Color chtr = Color.BLACK;
    private Image spriteImg;
    private String input;
    private ToggleButton orange;
    private ToggleButton pink;
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
        options.setPadding(new Insets(65, 0, 0, 0));
        options.setSpacing(20);
        options.setAlignment(Pos.CENTER);
        configPane.setCenter(options);

        Image image0 = new Image("file:resources/images/backgrounds/player_screen.png");
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

        // old text background color = ORANGERED, text color = DARKSLATEGRAY

        name = new TextField();
        name.setId("name");
        name.setPrefWidth(215);
        name.setMaxWidth(215);
        Text instr1 = new Text("Type Your Name");
        instr1.setFill(Color.LIGHTGREEN);
        instr1.setFont(new Font("Copperplate Gothic Bold", 20));
        instr1.setTextAlignment(TextAlignment.CENTER);
        StackPane instruction1 = new StackPane();

        instruction1.setBackground(new Background(new
            BackgroundFill(Color.DARKGREEN, CornerRadii.EMPTY,
            new Insets(0, 140, 0, 140))));
        instruction1.getChildren().add(instr1);
        options.getChildren().add(instruction1);
        options.getChildren().add(name);

        Text instr3 = new Text("Choose a Character");
        instr3.setFill(Color.LIGHTGREEN);
        instr3.setFont(new Font("Copperplate Gothic Bold", 18));
        StackPane instruction3 = new StackPane();
        instruction3.setBackground(new Background(new
            BackgroundFill(Color.DARKGREEN, CornerRadii.EMPTY,
            new Insets(0, 140, 0, 140))));
        instruction3.getChildren().add(instr3);
        options.getChildren().add(instruction3);
        HBox chtrs = new HBox();
        chtrs.setSpacing(10);
        chtrs.setAlignment(Pos.CENTER);
        ToggleGroup chtr = new ToggleGroup();

        ImageView knightImg = new ImageView("file:resources/images/sprites/knight.png");
        knightImg.setFitWidth(60); knightImg.setFitHeight(60);
        orange = new ToggleButton("Knight", knightImg);
        orange.getStyleClass().add("orangeKnight");
        orange.getStyleClass().add("button");
        orange.setId("orange");
        orange.setMinSize(80,100); orange.setMaxSize(80,100);
        orange.setContentDisplay(ContentDisplay.TOP);
        chtrs.getChildren().add(orange);
        orange.setToggleGroup(chtr);

        ImageView vampireImg = new ImageView("file:resources/images/sprites/vampire.png");
        vampireImg.setFitWidth(60); vampireImg.setFitHeight(65);
        purple = new ToggleButton("Vampire", vampireImg);
        purple.getStyleClass().add("purpleVampire");
        purple.getStyleClass().add("button");
        purple.setId("purple");
        purple.setContentDisplay(ContentDisplay.TOP);
        purple.setMinSize(80,100); purple.setMaxSize(80,100);
        chtrs.getChildren().add(purple);
        purple.setToggleGroup(chtr);

        ImageView reaperImg = new ImageView("file:resources/images/sprites/reaper.png");
        reaperImg.setFitWidth(65); reaperImg.setFitHeight(70);
        yellow = new ToggleButton("Reaper", reaperImg);
        yellow.getStyleClass().add("yellowReaper");
        yellow.getStyleClass().add("button");
        yellow.setId("yellow");
        yellow.setMinSize(80,100); yellow.setMaxSize(80,100);
        yellow.setContentDisplay(ContentDisplay.TOP);
        chtrs.getChildren().add(yellow);
        yellow.setToggleGroup(chtr);

        ImageView priestImg = new ImageView("file:resources/images/sprites/priest.png");
        priestImg.setFitWidth(65); priestImg.setFitHeight(70);
        pink = new ToggleButton("Priest", priestImg);
        pink.getStyleClass().add("pinkPriest");
        pink.getStyleClass().add("button");
        pink.setId("pink");
        pink.setMinSize(80,100); pink.setMaxSize(80,100);
        pink.setContentDisplay(ContentDisplay.TOP);
        chtrs.getChildren().add(pink);
        pink.setToggleGroup(chtr);

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
        configScene.getStylesheets().add("file:resources/css/PlayerScreen.css");
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
            if (orange.isSelected()) {
                chtr = Color.ORANGE;
                spriteImg = new Image("file:resources/images/sprites/knight.png", 100, 100, false, false);
            } else if (pink.isSelected()) {
                chtr = Color.PINK;
                spriteImg = new Image("file:resources/images/sprites/priest.png", 115, 120, false, false);
            } else if (yellow.isSelected()) {
                chtr = Color.YELLOW;
                spriteImg = new Image("file:resources/images/sprites/reaper.png", 110, 115, false, false);
            } else if (purple.isSelected()) {
                chtr = Color.PURPLE;
                spriteImg = new Image("file:resources/images/sprites/vampire.png", 105, 105, false, false);
            } else {
                Alert invalidW = new Alert(Alert.AlertType.ERROR);
                invalidW.setTitle("Error Dialog");
                invalidW.setHeaderText("Invalid character");
                invalidW.setContentText("No character chosen\nPlease "
                    + "select a character");
                invalidW.show();
            }

            if (chtr != Color.BLACK) {
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

    public Image getSpriteImg() { return spriteImg; }

}