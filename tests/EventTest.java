import controller.Controller;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.PlayerModel;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class EventTest extends ApplicationTest {

    public <T extends Node> T find(final String query) {
        return lookup(query).query();
    }

    private Controller controller;

    @Override
    public void start(Stage primaryStage) throws Exception {
        controller = new Controller();
        controller.start(primaryStage);
    }

    @Before
    public void getToBoard() {
        clickOn("Click Here to Begin");
        clickOn("#name").write("Bob's Game");
        clickOn("#$4");
        clickOn("#2");
        clickOn("Begin Game");
        clickOn("#name").write("Bob");
        clickOn("#orange");
        clickOn("Advance");
        clickOn("#name").write("Ann");
        clickOn("#yellow");
        clickOn("Advance");
    }

    //---------------------------Milestone 4 Tests--------------------------

    @Test //Test that Win Screen is displayed upon completion
    public void testWinScreen() {

    }

    @Test //Test that the players' placements are correctly displayed
    public void testWinStats() {

    }

    @Test //Test Chance Event 1
    public void testChance1() {

    }

    @Test //Test Chance Event 2
    public void testChance2() {

    }

    @Test //Test Chance Event 3
    public void testChance3() {

    }

    @Test //Test Chance Event 4
    public void testChance4() {

    }

    @Test //Test Chance Event 5
    public void testChance5() {

    }

    @Test //Test Chance Event 6
    public void testChance6() {

    }

}