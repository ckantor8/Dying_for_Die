package model;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class PlayerModel {
    private String name;
    private Color character;
    private int gold;
    private Circle sprite;
    private Image spriteImg;
    private Integer score = 0;
    private boolean cursed = false;

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

    public void setSpriteImg(Image spriteImg) {
        this.spriteImg = spriteImg;
    }

    public Image getSpriteImg() {
        return this.spriteImg;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getScore() {
        return this.score;
    }

    public void setCursed(boolean cursed) {
        this.cursed = cursed;
    }

    public boolean getCursed() {
        return this.cursed;
    }

}
