package model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class PlayerModel {
    private String name;
    private Color character;
    private int gold;
    private Circle sprite;

    public PlayerModel(String name, Color character, int gold) {
        this.name = name;
        this.character = character;
        this.gold = gold;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCharacter(Color character) {
        this.character = character;
    }

    public Color getCharacter() {
        return character;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getGold() {
        return gold;
    }

    public void setSprite(Circle sprite) {
        this.sprite = sprite;
    }

    public Circle getSprite() {
        return sprite;
    }

}
