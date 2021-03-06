package org.academiadecodigo.thunderstructs.charlieteam;

import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Menu implements KeyboardHandler {

private boolean space = false;

    public void menu(){

        Picture welcome = new Picture(10,36,"spr_burgermenu.png");
        welcome.grow(0, 26);
        while (!space){
            welcome.draw();
        }
        welcome.delete();

    }


    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        switch (keyboardEvent.getKey()){

            case KeyboardEvent.KEY_SPACE:
                this.space = true;
                break;
        }


    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }
}
