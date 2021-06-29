import controller.Controller;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;
import org.testfx.matcher.base.WindowMatchers;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.testfx.api.FxAssert.verifyThat;

public class BoardTest extends ApplicationTest {

    public <T extends Node> T find(final String query) {
        return lookup(query).query();
    }

    private Controller controller;

    @Override
    public void start(Stage primaryStage) throws Exception {
        controller = new Controller();
        controller.start(primaryStage);
    }

//---------------------------Milestone 2 Tests--------------------------

    @Test //Test that players can move across tiles //Cody Kantor
    public void testMovement() {

    }

    @Test //Test that players actual take turns rolling the dice //Cody Kantor
    public void testTurns() {

    }

    @Test // Test that green squares give money //Thomas Crawford
    public void testGetMoney() {

    }

    @Test // Test that red squares take money //Thomas Crawford
    public void testLoseMoney() {

    }

    @Test // Test that chance tiles function correctly //Alistair Sequeira
    public void testChanceTiles() {

    }

    @Test // Test that one of the chance events, going again, works //Alistair Sequeira
    public void testGoAgain() {

    }

    @Test //Test that the Main Game Screen is visible // Dezarae Amelia
    public void testMainScreen() {
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

    @Test // Test that toolbar labels are correct //Dezarae Amelia
    public void testToolbarLabels() {

    }

    @Test // Test that player labels correctly update //Aayush Dixit
    public void testLabelsUpdate() {

    }

    @Test // Test that dice rolling correctly functions //Aayush Dixit
    public void testDiceRolling() {

    }

}