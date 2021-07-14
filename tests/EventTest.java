import controller.Controller;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;
import model.PlayerModel;
import org.junit.Before;
import org.junit.Test;
import org.testfx.api.FxRobotException;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;
import org.testfx.matcher.base.WindowMatchers;

import static org.junit.Assert.*;
import static org.testfx.api.FxAssert.verifyThat;

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
        clickOn("#gameName").write("Bob's Game");
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

    @Test //Test that Win Screen is displayed upon completion with player ranks
    public void testWinScreen() { //Cody Kantor
        controller.setGameWon(true);
        sleep(2000);
        verifyThat("#rank", NodeMatchers.isNotNull());
    }

    @Test //Test that the player(s) can play again after completing a game
    public void testWinPlay() { //Cody Kantor
        controller.setGameWon(true);
        clickOn("Click Here to Play Again");
        verifyThat(window("Your New Favorite Board Game"),
            WindowMatchers.isShowing());
    }

    @Test //Test that the player(s) can quit the game after completion
    public void testWinQuit() { //Cody Kantor
        controller.setGameWon(true);
        clickOn("Quit Game");
        assertEquals(Window.getWindows().toString(), "[]");
    }

    /*@Test //Test Chance Event 1 -- Alistair Sequeira
    public void testChance1() {

    }

    @Test //Test Chance Event 2 -- Alistair Sequiera
    public void testChance2() {

    }

    @Test //Test Chance Event 3 -- Aayush Dixit
    public void testChance3() {

    }

    @Test //Test Chance Event 4 -- Aayush Dixit
    public void testChance4() {

    }

    @Test //Test Chance Event 5 -- Thomas Crawford
    public void testChance5() {

    }

    @Test //Test Chance Event 6 -- Thomas Crawford
    public void testChance6() {

    }*/

}