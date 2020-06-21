package sample;


import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle {

    public Tile(int x, int y){
        setWidth(Jeudeloie.Tile_Size);
        setHeight(Jeudeloie.Tile_Size);

        setFill(Color.LIGHTCORAL);
        setStroke(Color.BLACK);
    }
}