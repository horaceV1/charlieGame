package org.academiadecodigo.thunderstructs.charlieteam;

import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.thunderstructs.charlieteam.field.Field;
import org.academiadecodigo.thunderstructs.charlieteam.field.FieldPosition;
import org.academiadecodigo.thunderstructs.charlieteam.gameObjects.*;

public class Game {
    private Field field;
    private Player player;
    private int points = 0;
    private GameObject object;
    private ObjectFactory factory = new ObjectFactory(field);
    private Text textScore = new Text(420, 50, "");
    Picture life;
    Picture life2;
    Picture life3;

    public void init() {
        createField();
        menu();
        Picture background = new Picture(10, 10, "spr_background.png");
        background.draw();
        createPlayer();
        textScore.grow(35, 35);
        textScore.draw();

    }

    public void start() throws InterruptedException {
        points();
        life();
        levels(10, 2, 0, 1);
        levelUp();
        levels(20, 1, 0, 2);
        levelUp();
        levels(30, 1, 5, 4);
        levelUp();
        levels(40, 1, 9, 4);
        levelUp();
        levels(60, 1, 9, 3);
        win();
    }


    public Field createField() {
        this.field = new Field(500, 800);
        return field;
    }

    public Player createPlayer() {

        this.player = new Player(field, new FieldPosition(225, 735, field));

        Keyboard keyboard = new Keyboard(player);

        KeyboardEvent left = new KeyboardEvent();
        player.keyPressed(left);
        left.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        left.setKey(KeyboardEvent.KEY_LEFT);
        keyboard.addEventListener(left);

        KeyboardEvent right = new KeyboardEvent();
        player.keyPressed(right);
        right.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        right.setKey(KeyboardEvent.KEY_RIGHT);
        keyboard.addEventListener(right);
        return player;
    }


    public int randomPos() {
        return (int) ((Math.random() * 400) + 15);
    }

    public void menu() {
        Menu menu = new Menu();
        Keyboard keyboard = new Keyboard(menu);

        KeyboardEvent space = new KeyboardEvent();
        space.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        space.setKey(KeyboardEvent.KEY_SPACE);

        keyboard.addEventListener(space);

        menu.menu();
    }

    public void gameOver() throws InterruptedException {
        GameOver gameOver = new GameOver();
        Keyboard keyboard = new Keyboard(gameOver);

        KeyboardEvent r = new KeyboardEvent();
        r.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        r.setKey(KeyboardEvent.KEY_R);

        keyboard.addEventListener(r);

        points = 0;
        points();
        gameOver.gameOver();

        player.setHealth(3);
        points = 0;
        menu();

        start();

    }

    public boolean collision() {
        if (object.getFieldPos().getY() + object.getFieldPos().getHeight() == player.getY()) {
            if (player.getX() - 60 < object.getFieldPos().getX() && player.getX() + player.getWidth() > object.getFieldPos().getX() + object.getFieldPos().getWidth()) {
                if (object instanceof Cheeseburguer) {
                    points++;
                    points();
                    player.setHealth(1);
                    object.getFieldPos().hide();
                    return true;
                }
                if (object instanceof Apple || object instanceof Salad) {
                    player.setHealth(-1);
                    removeLife();
                    object.getFieldPos().hide();
                    return true;
                }
            }
        }
        return false;
    }


    public void levels(int pointLimit, int threadMillis, int threadNano, int random) throws InterruptedException {
        while (points < pointLimit && player.getHealth() > 0) {
            object = factory.createObject(random, randomPos());
            object.getFieldPos().setPicture(object.getPicture());
            object.getFieldPos().show();
            while (object.getFieldPos().getY() < field.getHeight() - object.getPicture().getHeight()) {
                object.getPicture().translate(0, 1);
                object.getFieldPos().setY(1);

                Thread.sleep(threadMillis, threadNano);

                collision();

            }
            object.getFieldPos().hide();
            if (object instanceof Cheeseburguer) {
                player.setHealth(-1);
                removeLife();
            }

            if (player.getHealth() == 0) {
                gameOver();
            }
            continue;
        }

    }

    public void win() throws InterruptedException {
        Win win = new Win();
        Keyboard keyboard = new Keyboard(win);
        KeyboardEvent restart = new KeyboardEvent();

        restart.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        restart.setKey(KeyboardEvent.KEY_R);
        keyboard.addEventListener(restart);

        win.win();
        player.setHealth(3);
        points = 0;

        menu();
        start();


    }

    public void life() {

        life = new Picture(20, 20, "spr_heart.png");
        life2 = new Picture(60, 20, "spr_heart.png");
        life3 = new Picture(100, 20, "spr_heart.png");
        life.draw();
        life2.draw();
        life3.draw();
    }

    public void removeLife() {
        if (player.getHealth() == 2) {
            life3.delete();
        }
        if (player.getHealth() == 1) {
            life2.delete();
        }
        if (player.getHealth() == 0) {
            life.delete();
        }

    }

    public void levelUp() {
        LevelUp levelUp = new LevelUp();
        Keyboard keyboard = new Keyboard(levelUp);

        KeyboardEvent space = new KeyboardEvent();
        space.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        space.setKey(KeyboardEvent.KEY_SPACE);

        keyboard.addEventListener(space);

        levelUp.levelUp();
    }

    public void points() {
        textScore.setText("Score: " + (points));
    }

}




