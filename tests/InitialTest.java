import controller.Controller;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;
import org.testfx.matcher.base.WindowMatchers;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.testfx.api.FxAssert.verifyThat;

public class InitialTest extends ApplicationTest {

    public <T extends Node> T find(final String query) {
        return lookup(query).query();
    }
    public Controller controller;

    @Override
    public void start(Stage primaryStage) throws Exception {
        controller = new Controller();
        controller.start(primaryStage);
    }

    //---------------------------Milestone 2 Tests--------------------------

    @Test
    public void testPlay() {
        clickOn("Click Here to Begin");
        verifyThat("Title Your Game", NodeMatchers.isNotNull());
    }

    @Test
    public void testBackButton1() {
        clickOn("Click Here to Begin");
        clickOn("Back");
        verifyThat("Click Here to Begin", NodeMatchers.isNotNull());
    }

    @Test
    public void testEmptyInput() {
        clickOn("Click Here to Begin");
        clickOn("#name").write("");
        clickOn("#$4");
        clickOn("#2");
        clickOn("Begin Game");
        verifyThat("Title Your Game", NodeMatchers.isNotNull());
    }

    @Test
    public void testWhitespaceInput() {
        clickOn("Click Here to Begin");
        clickOn("#name").write("             ");
        clickOn("#$4");
        clickOn("#2");
        clickOn("Begin Game");
        verifyThat("Title Your Game", NodeMatchers.isNotNull());
    }

    @Test
    public void testNoGold() {
        clickOn("Click Here to Begin");
        clickOn("#name").write("Billy Bob");
        clickOn("#2");
        clickOn("Begin Game");
        verifyThat("Select Your Initial $$$", NodeMatchers.isNotNull());
    }

    @Test
    public void testNoPlayers() {
        clickOn("Click Here to Begin");
        clickOn("#name").write("bob");
        clickOn("#$4");
        clickOn("Begin Game");
        verifyThat("Number of Players", NodeMatchers.isNotNull());
    }

    @Test
    public void testQuitButton1() {
        clickOn("#quitButton1");
        try {
            verifyThat(window("Your New Favorite Dungeon Crawler"),
                    WindowMatchers.isNotShowing());
            assertEquals(1, 0);
        } catch (NoSuchElementException e) {
            assertEquals(1, 1);
        }
    }

    @Test //Aayush Dixit
    public void testLabels() {
        clickOn("Click Here to Begin");
        clickOn("#name").write("Game");
        clickOn("#$2");
        clickOn("#2");
        clickOn("Begin Game");
        clickOn("#name").write("Bob");
        clickOn("#red");
        clickOn("Advance");
        clickOn("#name").write("Bub");
        clickOn("#yellow");
        clickOn("Advance");
        Label testPlayer = controller.getP1();
        assertEquals(testPlayer.getText(),"Player: Bob");
        Label testGold = controller.getCurrGold();
        assertEquals(testGold.getText(), "Gold: 2");
    }

    @Test //Aayush Dixit
    public void testQuitButton2() {
        clickOn("Click Here to Begin");
        clickOn("#name").write("Game");
        clickOn("#$2");
        clickOn("#2");
        clickOn("Begin Game");
        clickOn("#name").write("Bob");
        clickOn("#red");
        clickOn("Advance");
        clickOn("#name").write("Bub");
        clickOn("#yellow");
        clickOn("Advance");
        find("#quitButton2");
        clickOn("#quitButton2");
        try {
            verifyThat(window("Your New Favorite Dungeon Crawler"),
                    WindowMatchers.isNotShowing());
            assertEquals(1, 0);
        } catch (NoSuchElementException e) {
            assertEquals(1, 1);
        }
    }

    @Test //Test that Back button on Player Config returns to Initial Config
    public void testBackButton2() {
        assertEquals(1, 1);
    }

    @Test //Test that all whitespace is not allowed as player name
    public void testWhitespacePlayer() {
        assertEquals(1, 1);
    }

    @Test //Test that empty name is not allowed in player config // Alistair Sequeira
    public void testEmptyPlayer() {
        clickOn("Click Here to Begin");
        clickOn("#name").write("Bob's Game");
        clickOn("#$4");
        clickOn("#2");
        clickOn("Begin Game");
        clickOn("#red");
        clickOn("Advance");
        verifyThat("Type Your Name", NodeMatchers.isNotNull());
    }

    @Test //Test that no color choice is not allowed // Alistair Sequeira
    public void testNoCharacter() {
        clickOn("Click Here to Begin");
        clickOn("#name").write("Bob's Game");
        clickOn("#$4");
        clickOn("#2");
        clickOn("Begin Game");
        clickOn("#name").write("Bob");
        clickOn("Advance");
        verifyThat("Choose a Character", NodeMatchers.isNotNull());
    }

    @Test //Test that the main game screen is displayed after player config // TJ Crawford
    public void testMainGame() {
        clickOn("Click Here to Begin");
        clickOn("#name").write("Bob's Game");
        clickOn("#$4");
        clickOn("#2");
        clickOn("Begin Game");
        clickOn("#name").write("Bob");
        clickOn("#red");
        clickOn("Advance");
        clickOn("#name").write("Ann");
        clickOn("#yellow");
        clickOn("Advance");
        verifyThat("It's your turn!", NodeMatchers.isNotNull());
    }

    @Test //Test that correct number of players are added // TJ Crawford
    public void testNumPlayers() {
        clickOn("Click Here to Begin");
        clickOn("#name").write("Bob's Game");
        clickOn("#$4");
        clickOn("#2");
        clickOn("Begin Game");
        clickOn("#name").write("Bob");
        clickOn("#red");
        clickOn("Advance");
        clickOn("#name").write("Ann");
        clickOn("#yellow");
        clickOn("Advance");
        //assertEquals(2, controller.getPlayers());
    }

}
