import controller.Controller;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.PlayerModel;
import org.junit.Before;
import org.junit.Test;
import org.testfx.api.FxRobotException;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;
import org.testfx.matcher.base.WindowMatchers;

import java.util.NoSuchElementException;

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
        while (!controller.getGameWon()) {
            if (!controller.getAlert().isShowing()) {
                try {
                    clickOn("Roll");
                } catch (FxRobotException e) {
                    clickOn("OK");
                }
            } else {
                clickOn("OK");
            }
        }
        verifyThat("#congrats", NodeMatchers.isNotNull());
        verifyThat("#rank", NodeMatchers.isNotNull());
    }

    @Test //Test that the player(s) can play again after completing a game
    public void testWinPlay() { //Cody Kantor
        while (!controller.getGameWon()) {
            if (!controller.getAlert().isShowing()) {
                try {
                    clickOn("Roll");
                } catch (FxRobotException e) {
                    clickOn("OK");
                }
            } else {
                clickOn("OK");
            }
        }
        Button button = find("#replayButton");
        clickOn(button);
        verifyThat(window("Your New Favorite Board Game"),
            WindowMatchers.isShowing());
    }

    @Test //Test that the player(s) can quit the game after completion
    public void testWinQuit() { //Cody Kantor
        while (!controller.getGameWon()) {
            if (!controller.getAlert().isShowing()) {
                try {
                    clickOn("Roll");
                } catch (FxRobotException e) {
                    clickOn("OK");
                }
            } else {
                clickOn("OK");
            }
        }
        Button button = find("#quitButton1");
        clickOn(button);
        try {
            verifyThat(window("You Win!"), WindowMatchers.isNotShowing());
        } catch (NoSuchElementException e) {
            assertEquals(1, 1);
        }
    }

    @Test //Test Chance Event 1 -- Alistair Sequeira
    public void testChance1() {
        controller.setChance(1);
        PlayerModel player = controller.getCurrPlayer();
        clickOn("Roll");
        int expectedPos = 1;
        clickOn("OK");
        assertEquals((long) expectedPos, (long) GridPane.getRowIndex(player.getSprite()));
    }

    @Test //Test Chance Event 2 -- Alistair Sequeira
    public void testChance2() {
        controller.setChance(2);
        PlayerModel player = controller.getCurrPlayer();
        clickOn("Roll");
        int expectedPos = 3;
        clickOn("OK");
        assertEquals((long) expectedPos, (long) GridPane.getRowIndex(player.getSprite()));
    }

    @Test //Test Chance Event 3 -- Aayush Dixit
    public void testChance3() {
        PlayerModel player = controller.getCurrPlayer();
        controller.setChance(3);
        clickOn("Roll");
        assertEquals(controller.getCurrPlayer(), player);
    }

    @Test //Test Chance Event 4 -- Aayush Dixit
    public void testChance4() {
        clickOn("Roll");
        PlayerModel player = controller.getCurrPlayer();
        controller.setChance(4);
        clickOn("Roll");
        assertEquals(player, controller.getCurrPlayer());
    }

    @Test //Test Chance Event 5 -- Thomas Crawford
    public void testChance5() {
        clickOn("Roll");
        PlayerModel player = controller.getCurrPlayer();
        controller.setChance(5);
        clickOn("Roll");
        assertEquals(player, controller.getCurrPlayer());
    }

    @Test //Test Chance Event 6 -- Thomas Crawford
    public void testChance6() {
        clickOn("Roll");
        PlayerModel player = controller.getCurrPlayer();
        controller.setChance(6);
        clickOn("Roll");
        assertEquals(player, controller.getCurrPlayer());
    }

}
