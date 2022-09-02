package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {
    GamePanel gamePanel;
    Tile[] tile;

    int[][] mapTileNum;

    public TileManager(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        tile = new Tile[10];
        mapTileNum = new int[gamePanel.maxScreenColumn][gamePanel.maxScreenRow];
        getTileImage();
        loadMap("/maps/map_default.txt");
    }

    public void loadMap(String txtMapPath){
        try {
            InputStream stream = getClass().getResourceAsStream(txtMapPath); // import map
            assert stream != null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream)); // read content of the stream

            int col = 0;
            int row = 0;

            while (col < gamePanel.maxScreenColumn && row < gamePanel.maxScreenRow){
                String line = reader.readLine(); // read line and store it in variable

                while(col < gamePanel.maxScreenColumn){
                    String[] numbers = line.split(" "); // split the string at a space

                    int number = Integer.parseInt(numbers[col]); // parsing string to int

                    mapTileNum[col][row] = number;
                    col++;
                }
                if(col == gamePanel.maxScreenColumn){
                    col = 0;
                    row++;
                }
            }
            reader.close();

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void getTileImage(){
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/dark_ground.png")));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/wall.png")));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/tv.png")));

        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D graphics2D){

        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (col < gamePanel.maxScreenColumn && row < gamePanel.maxScreenRow){
            // draw tile at x=0 y=0 position
            int tileNumber = mapTileNum[col][row]; // extract a tile number which is stored in mapTileNumber[0][0]
            graphics2D.drawImage(tile[tileNumber].image,x,y,gamePanel.tileSize,gamePanel.tileSize,null);
            col++;
            x += gamePanel.tileSize;

            if(col == gamePanel.maxScreenColumn){
                col = 0;
                x = 0;
                row++;
                y += gamePanel.tileSize;
            }
        }
    }
}
