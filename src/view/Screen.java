package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class Screen {
    private int width;
    private int height;
    private Button playButton;
    private Button quitButton;
    private VBox buttons;
    private String bigText;
    private String background;
    private String playText;
    private String ranks;
    private Image sprite;

    public Screen() {
    }
    public Screen(int width, int height, String bigText, String background,
                  String playText, String ranks) {
        this.width = width;
        this.height = height;
        this.bigText = bigText;
        this.background = background;
        if (ranks != null) {
            this.ranks = ranks;
        }
        playButton = new Button(playText);
        quitButton = new Button("Quit Game");
    }

    public Screen(int width, int height, String bigText, Image sprite, String background,
                  String playText, String ranks) {
        this.width = width;
        this.height = height;
        this.bigText = bigText;
        this.sprite = sprite;
        this.background = background;
        if (ranks != null) {
            this.ranks = ranks;
        }
        playButton = new Button(playText);
        quitButton = new Button("Quit Game");
    }

    public Scene getScene() {

        BorderPane pane = new BorderPane();
        Scene scene = new Scene(pane, 500, 350);

        // Change the url here to change the background image.
        Image image = new Image(background);

        // Sets the image url above to the background of the pane.
        BackgroundSize backgroundSize = new BackgroundSize(BackgroundSize.AUTO,
            BackgroundSize.AUTO, true, true, true, true);
        BackgroundImage backgroundImage = new BackgroundImage(image,
            BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT, backgroundSize);
        Background bg = new Background(backgroundImage);
        pane.setBackground(bg);

        buttons = new VBox();
        buttons.setSpacing(15);
        buttons.getChildren().add(playButton);
        buttons.getChildren().add(quitButton);
        buttons.setAlignment(Pos.CENTER);

        //Text
        Text text = new Text(100, 100, bigText);
        text.setFont(Font.font("Verdana", 30));
        text.getStyleClass().add("bigText");
        text.setTextAlignment(TextAlignment.CENTER);
        if (bigText.contains("Welcome")) {
            pane.setPadding(new Insets(40, 10, 10, 10));
            text.setFill(Color.GOLD);
            pane.setCenter(text);
            pane.setBottom(buttons);
            pane.setMargin(buttons, new Insets(0, 0, 10, 0));
        } else if (bigText.contains("Congratulations")) {
            VBox winInfo = new VBox();
            winInfo.getChildren().add(text);
            ImageView img = new ImageView(sprite);
            img.setFitWidth(50);
            img.setFitHeight(50);
            winInfo.getChildren().add(img);
            winInfo.setSpacing(15);
            winInfo.setAlignment(Pos.CENTER);
            text.setFill(Color.BLACK);
            text.setId("congrats");
            pane.setCenter(winInfo);
            Text rank = new Text(ranks);
            rank.setId("rank");
            rank.setStyle("-fx-font: 14 System");
            rank.setFill(Color.BLACK);
            pane.setAlignment(rank, Pos.TOP_CENTER);
            pane.setTop(rank);
            pane.setBottom(buttons);
            pane.setMargin(buttons, new Insets(0, 0, 60, 0));
        } else {
            pane.setBottom(buttons);
            pane.setMargin(buttons, new Insets(0, 0, 5, 0));
        }

        playButton.getStyleClass().add("playButton");
        quitButton.getStyleClass().add("quitButton");
        scene.getStylesheets().add("file:resources/css/Screen.css");

        return scene;
    }

    public Button getQuitButton() {
        return quitButton;
    }

    public Button getPlayButton() {
        return playButton;
    }
}
