package gameOfRails.util;


import gameOfRails.game.*;
import javafx.scene.control.Button;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.PathElement;

import java.util.ArrayList;

public class gameUtil {

    public static final int GAME_SIZE = 400;
    private static ArrayList<PathElement> paths = new ArrayList<>();
    static final int offset = 40;

    public static ArrayList<PathElement> getPaths() {
        return paths;
    }

    /**
     * This method both checks if the pathConstructed
     * then if it is constructed it also creates
     * the path objects that will later be used
     * in path Animation.
     */
    public static boolean isPathConstructed(Tile[][] tiles) {

        int x;
        int y;
        LastAction lastAction = null;


        Pipe starterTile = null;

        // Finding starting tile
        outerLoop:
        for (int i = 0; i < tiles[0].length; i++) {
            for (int j = 0; j < tiles.length; j++) {
                if (tiles[i][j] instanceof Starter) {
                    starterTile = (Pipe) tiles[i][j];
                    break outerLoop;
                }
            }
        }


        x = starterTile.getxGrid(); // X of starter tile
        y = starterTile.getyGrid(); // Y of starter tile


        /*
        This loop will iterate until either it finds the end tile or
            we came across a dead-end which
         */
        while (!(tiles[x][y] instanceof End)) {

            Tile currentTile = tiles[x][y];
        /*
        Our current tile is downEdged so we check if the tile below is upEdged if not we immediately return false.
        Same for all possibilities
         */
            if (currentTile.isDownEdge() && lastAction != LastAction.UP) {
                if (isInsideBoundaries(x, (y + 1)) && tiles[x][y + 1].isUpEdge()) { //Current pipe is downEdged and pipe below is upEdge() we can move there.

                    y++; // Go below
                    lastAction = LastAction.DOWN;
                    paths.add(new LineTo((x * 100) + offset, (y * 100) + offset));

                    continue;

                } else {
                    paths.clear();
                    return false;
                }
            }

            if (currentTile.isRightEdge() && lastAction != LastAction.LEFT) {
                if (isInsideBoundaries((x + 1), y) && tiles[x + 1][y].isLeftEdge()) {

                    x++; // Go right
                    lastAction = LastAction.RIGHT;
                    paths.add(new LineTo((x * 100) + offset, (y * 100) + offset));
                    continue;
                } else {
                    paths.clear();
                    return false;
                }
            }

            if (currentTile.isUpEdge() && lastAction != LastAction.DOWN) {
                if (isInsideBoundaries(x, (y - 1)) && tiles[x][y - 1].isDownEdge()) {
                    y--; // Go up
                    lastAction = LastAction.UP;
                    paths.add(new LineTo((x * 100) + offset, (y * 100) + offset));
                    continue;
                } else {
                    paths.clear();
                    return false;
                }
            }

            if (currentTile.isLeftEdge() && lastAction != LastAction.RIGHT) {
                if (isInsideBoundaries((x - 1), y) && tiles[x - 1][y].isRightEdge()) {
                    x--; // Go left
                    lastAction = LastAction.LEFT;
                    paths.add(new LineTo((x * 100) + offset, (y * 100) + offset));
                    continue;
                } else {
                    paths.clear();
                    return false;
                }
            }

            // END OF MAIN LOOP
        }
        return true;

    }

    /*
    Checks for a single tile if that tile is movable
     */
    public static boolean isSwappableTile(Tile tile) {
        return tile.isMovable();
    }


    public static boolean isSwappableTiles(Tile tile1, Tile tile2) {

        if (!(tile1 instanceof Free || tile2 instanceof Free)) {

            return false;
        }

        int xGrid1 = tile1.getxGrid();
        int yGrid1 = tile1.getyGrid();
        int xGrid2 = tile2.getxGrid();

        int yGrid2 = tile2.getyGrid();

        if ((Math.abs(xGrid1 - xGrid2) + Math.abs(yGrid1 - yGrid2) != 1)) {
            return false;
        }

        if (isSwappableTile(tile1) || isSwappableTile(tile2)) {
            return true;
        }

        return false;
    }

    public static Tile getTileFromMouse(Tile[][] tiles, double sceneX, double sceneY) {


        int xGrid = (int) (sceneX - 1) / (GAME_SIZE / 4);
        int yGrid = (int) (sceneY - 1) / (GAME_SIZE / 4);
        if (xGrid < 0 || xGrid > 3 || yGrid < 0 || yGrid > 3) {
            return null;
        }
        return tiles[xGrid][yGrid];

    }

    private static boolean isInsideBoundaries(int x, int y) {

        if (x < 0 || x > 3 || y < 0 || y > 3) {
            return false;
        }
        return true;
    }

    public static Button createMuteButton() {
        Button muteButton = new Button(audioUtil.muteText.toString());
        muteButton.textProperty().bind(audioUtil.muteText);
        muteButton.setLayoutX(20);
        muteButton.setLayoutY(20);
        muteButton.setOnMousePressed(e -> audioUtil.mute());
        return muteButton;
    }
}

