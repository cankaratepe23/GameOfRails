package gameOfRails.game;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

import java.io.File;

public class End extends Pipe {


    public End(int x, int y, boolean upEdge, boolean downEdge, boolean leftEdge, boolean rightEdge) {

        super(x, y, false, upEdge, downEdge, leftEdge, rightEdge);
        /*
        We need to call setFill() here again because the constructor takes place before we assign up,down,right,left values
        because of that it it doesn't work the first time (same as static).
         */
        setFill();
    }


    @Override
    public void setFill() {
        if (this.isLeftEdge()) {
            this.setFill(new ImagePattern(new Image(new File("img/endHorizontal.png").toURI().toString())));
        } else {
            this.setFill(new ImagePattern(new Image(new File("img/endVertical.png").toURI().toString())));
        }
    }

    @Override
    public String toString() {
        return String.format("End pipe at %d %d ", getxGrid(), getyGrid());
    }
}
