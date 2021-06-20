package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
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

    private Screen() {
    }

    public Screen(int width, int height, String bigText, String background,
                  String playText) {
        this.width = width;
        this.height = height;
        this.bigText = bigText;
        this.background = background;
        playButton = new Button(playText);
        playButton.setId("playButton");
        quitButton = new Button("Quit Game");
        quitButton.setId("quitButton1");
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
            text.setFill(Color.BLACK);
            pane.setCenter(text);
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
