package gameOfRails;

import gameOfRails.game.Tile;
import gameOfRails.gui.EntryGui;
import gameOfRails.util.fileUtil;
import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.stage.Stage;

/*  Rıdvan San 150117029
    Ahmet Akıl 150118038
*/

public class Main extends Application {

    public static IntegerProperty NUMBER_OF_MOVES = new SimpleIntegerProperty(0);

    public static final int MAX_LEVEL = fileUtil.getMaxLevel(); // Read the number of files in levels folder
    public static int LEVEL = 1;

    private static Stage primaryStage;

    public static void main(String[] args) {

        launch(args);


    }

    @Override
    public void start(Stage primaryStage) {

        Main.primaryStage = primaryStage;

        Tile[][] grid = fileUtil.createGrid(LEVEL);

        new EntryGui(grid);
        primaryStage.setResizable(false);

    }


    public static Stage getStage() {
        return primaryStage;
    }
}