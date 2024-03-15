package AkashGMaze;
import java.awt.Color;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.image.*;
import javax.imageio.ImageIO;

public class UDB extends MazeElement{
    private boolean progressed;
    public UDB(Location loc, int size, String imgString)
        {
            super(loc,size,imgString);
            progressed = false;
        }
    @Override
    public void move(int key, char[][] maze) {
        progressed = false;
        int r = getLoc().getR();
        int c = getLoc().getC();

        while(!progressed){
            int random = (int) (Math.random()*4);

            if(random == 0){
                if(c>0 && maze[r-1][c] == ' '){
                    getLoc().incR(-1);
                    progressed = true;
                }
      
            }
            else if(random == 1){
                if(c<maze[0].length-1 && maze[r+1][c] == ' '){
                    getLoc().incR(1);
                    progressed = true;
                }
            }
            else if(random == 2){
                if(r>0 && maze[r][c-1] == ' '){
                    getLoc().incC(-1);
                    progressed = true;
                }
            }
            else{
                if(r<maze[0].length-1 && maze[r][c+1] == ' '){
                    getLoc().incC(1);
                    progressed = true;
                }
            }
        }
        
        
        
    }
}
