package gameOfRails.game;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.io.File;


public abstract class Tile extends Rectangle {
    private int xGrid; // Location of the tile at x-axis in the grid pane
    private int yGrid; // Location of the tile at y-axis in the grid pane
    private boolean movable;
    private Image image;


    public Tile(int x, int y, boolean movable) { //Construction of the object
        super(100, 100);
        this.xGrid = x;
        this.yGrid = y;
        this.movable = movable;
        this.image = new Image(new File("img/emptyFree.jpeg").toURI().toString());
        setFill();
        setStroke(Color.WHITE);

    }


    //Added these because to use this in the gameUtil with no harm.
    public abstract boolean isUpEdge();

    public abstract boolean isLeftEdge();

    public abstract boolean isRightEdge();

    public abstract boolean isDownEdge();

    public abstract void setUpEdge(boolean upEdge);

    public abstract void setLeftEdge(boolean leftEdge);

    public abstract void setRightEdge(boolean rightEdge);

    public abstract void setDownEdge(boolean downEdge);

    public boolean isMovable() {
        return movable;
    }

    public void setFill() {
        this.setFill(new ImagePattern(this.image));
    }

    public int getxGrid() {
        return xGrid;
    }

    public int getyGrid() {
        return yGrid;
    }

    public void setxGrid(int xGrid) {
        this.xGrid = xGrid;
    }

    public void setyGrid(int yGrid) {
        this.yGrid = yGrid;
    }

    @Override
    public String toString() {
        return String.format("Tile at : %d , %d", xGrid, yGrid);
    }

}