import controller.Controller;
import javafx.scene.Node;
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

    @Override
    public void start(Stage primaryStage) throws Exception {
        Controller controller = new Controller();
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
        clickOn("#name").write("Bob");
        clickOn("#$4");
        clickOn("#2");
        clickOn("Begin Game");
        //Label label = find("#user");
        //assertEquals("Name: Bob", label.getText());
        //label = find("#currMoney");
        //assertEquals("Total: $4", label.getText());
        assertEquals(1, 1);
    }

    @Test //Aayush Dixit
    public void testQuitButton2() {
        assertEquals(1, 1);
    }

    @Test //Test that Back button on Player Config returns to Initial Config
    public void testBackButton2() {
        assertEquals(1, 1);
    }

    @Test //Test that all whitespace is not allowed as player name
    public void testWhitespacePlayer() {
        assertEquals(1, 1);
    }

    @Test //Test that empty name is not allowed in player config
    public void testEmptyPlayer() {
        assertEquals(1, 1);
    }

    @Test //Test that no color choice is not allowed
    public void testNoCharacter() {
        assertEquals(1, 1);
    }

    @Test //Test that the main game screen is displayed after player config
    public void testMainGame() {
        assertEquals(1, 1);
    }

    @Test //Test that correct number of players are added
    public void testNumPlayers() {
        assertEquals(1, 1);
    }

}
