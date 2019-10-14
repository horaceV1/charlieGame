package org.academiadecodigo.thunderstructs.charlieteam;

import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.thunderstructs.charlieteam.field.Field;
import org.academiadecodigo.thunderstructs.charlieteam.field.FieldPosition;
import org.academiadecodigo.thunderstructs.charlieteam.gameObjects.Apple;
import org.academiadecodigo.thunderstructs.charlieteam.gameObjects.Cheeseburguer;
import org.academiadecodigo.thunderstructs.charlieteam.gameObjects.GameObject;
import org.academiadecodigo.thunderstructs.charlieteam.gameObjects.Salad;

public class Game {
    private Field field;
    private Player player;
    private int points;
    private GameObject object;

    public void init() {
        createField();
        Picture background = new Picture(10, 10, "spr_background.png");
        background.draw();
        createPlayer();
        menu();

    }

    public void start() throws InterruptedException {
        levelOne();
        levelTwo();
        /*while (points < 10) {
            int objectRandom = (int) (Math.random() * 2);
            switch (objectRandom) {
                case 0:
                    object = new Cheeseburguer(field, new FieldPosition(randomPos(), -10, field));
                case 1:
                    object = new Apple(field, new FieldPosition(randomPos(), -10, field));
                default:
                    object = new Cheeseburguer(field, new FieldPosition(randomPos(), -10, field));
            }

            object.getFieldPos().setPicture(object.getPicture());
            object.getFieldPos().show();
            while (object.getFieldPos().getY() < field.getHeight() - object.getPicture().getHeight()) {
                object.getPicture().translate(0, 1);
                object.getFieldPos().setY(1);
                collision();
                Thread.sleep(2);

            }
            object.getFieldPos().hide();
            continue;
        }*/

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
        return (int) (Math.random() * 400);
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

    public void collision() {
        //System.out.println("Player x: " + player.getX() + " Player maxX: " + (player.getX() + player.getWidth()));
        //System.out.println("Object x: " + object.getFieldPos().getX() + " Object maxX: " + (object.getFieldPos().getX() + object.getFieldPos().getWidth()));
        if (object.getFieldPos().getY() + object.getFieldPos().getHeight() == player.getY()) {
            if (player.getX() - 60 < object.getFieldPos().getX() && player.getX() + player.getWidth() > object.getFieldPos().getX() + object.getFieldPos().getWidth()) {
                System.out.println("Collision");
                if (object instanceof Cheeseburguer) {
                    points++;
                    System.out.println(points);
                }
                if (object instanceof Apple) {
                    System.out.println("NOOOOOOOOOOOOOOOOOOOO");
                }
                object.getFieldPos().hide();
            }
        }
    }

    public void levelOne() throws InterruptedException {
        while (points < 10) {
            object = new Cheeseburguer(field, new FieldPosition(randomPos(), -10, field));

            object.getFieldPos().setPicture(object.getPicture());
            object.getFieldPos().show();
            while (object.getFieldPos().getY() < field.getHeight() - object.getPicture().getHeight()) {
                object.getPicture().translate(0, 1);
                object.getFieldPos().setY(1);
                collision();
                Thread.sleep(2);

            }
            object.getFieldPos().hide();
        }

    }

    public void levelTwo() throws InterruptedException {
        while (points < 20) {
            int objectRandom = (int) (Math.random() * 2);
            switch (objectRandom) {
                case 0:
                    object = new Cheeseburguer(field, new FieldPosition(randomPos(), -10, field));
                    break;
                case 1:
                    object = new Apple(field, new FieldPosition(randomPos(), -10, field));
                    break;
                default:
                    object = new Cheeseburguer(field, new FieldPosition(randomPos(), -10, field));
                    break;
            }
            object.getFieldPos().setPicture(object.getPicture());
            object.getFieldPos().show();
            while (object.getFieldPos().getY() < field.getHeight() - object.getPicture().getHeight()) {
                object.getPicture().translate(0, 1);
                object.getFieldPos().setY(1);
                collision();
                Thread.sleep(2);

            object.getFieldPos().hide();
            continue;
        }

    }
    }

        public void leveThree() throws InterruptedException{
            while (points < 30) {
                int objectRandom = (int) (Math.random() * 2);
                switch (objectRandom) {
                    case 0:
                        object = new Cheeseburguer(field, new FieldPosition(randomPos(), -10, field));
                        break;
                    case 1:
                        object = new Apple(field, new FieldPosition(randomPos(), -10, field));
                        break;
                    default:
                        object = new Cheeseburguer(field, new FieldPosition(randomPos(), -10, field));
                        break;
                }
                object.getFieldPos().setPicture(object.getPicture());
                object.getFieldPos().show();
                fall();
                    object.getFieldPos().hide();
                    continue;
                }
        }

    public void levelFour() throws InterruptedException {
        while (points < 40) {
            int objectRandom = (int) (Math.random() * 3);
            switch (objectRandom) {
                case 0:
                    object = new Cheeseburguer(field, new FieldPosition(randomPos(), -10, field));
                    break;
                case 1:
                    object = new Apple(field, new FieldPosition(randomPos(), -10, field));
                    break;
                case 2:
                    object = new Salad(field, new FieldPosition(randomPos(), -10, field));
                    break;
                default:
                    object = new Cheeseburguer(field, new FieldPosition(randomPos(), -10, field));
                    break;
            }

            object.getFieldPos().setPicture(object.getPicture());
            object.getFieldPos().show();
            fall();
            object.getFieldPos().hide();
            continue;
        }
    }


    public void fall() throws InterruptedException {
        while (object.getFieldPos().getY() < field.getHeight() - object.getPicture().getHeight()) {
            object.getPicture().translate(0, 1);
            object.getFieldPos().setY(1);
            collision();
            Thread.sleep(1, 499999);

        }


    }

}



