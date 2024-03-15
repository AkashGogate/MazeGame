package AkashGMaze;
import java.awt.Color;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.image.*;
import javax.imageio.ImageIO;

public class Explorer extends MazeElement {

    public static final String[] FILE_NAMES = {"AkashGMaze/up.png", "AkashGMaze/right.png", "AkashGMaze/down.png", "AkashGMaze/left.png"};
	private int steps, dir;
    private BufferedImage[] images;



    public Explorer(Location loc, int size) {
        super(loc, size, "AkashGMaze/up.png");
        dir = 1; //facing right initially
        steps = 0;
        images = new BufferedImage[4];
        try {
            for(int i = 0; i<images.length; i++){
                images[i] = ImageIO.read(new File(FILE_NAMES[i]));
            }
			
		} catch (IOException e) {
				System.out.println("Image ->["+images[0]+"] not loaded");
		}
    }
    @Override
    public BufferedImage getImg()
	{
		return images[dir];
	}
    public int getSteps(){
        return steps;
    }

	public void move(int key, char[][] maze) {
        //if (this.getLoc() == null) return;
        int r = this.getLoc().getR();
        int c = this.getLoc().getC();
        if(key == 38){
            //up key so move forward
            if(dir == 1){ //east
                if(c<maze[r].length-1 && maze[r][c+1] != '#'){
                    getLoc().incC(1);
                    steps++;
                }
            }
            else if(dir == 2){ //north
                if(r<maze.length-1 && maze[r+1][c] != '#'){
                    getLoc().incR(1);
                    steps++;
                }
            }
            else if(dir == 0){ //south
                if(r>0 && maze[r-1][c] != '#'){
                    getLoc().incR(-1);
                    steps++;
                }
            }
            else if(dir == 3){ //west
                if(c>0 && maze[r][c-1] != '#'){
                    getLoc().incC(-1);
                    steps++;
                }
            }
        }
        if(key == 37){
            //left key so turn left
            if(dir == 1){ //east
                dir = 0;
            }
            else if(dir == 0){ //north
                dir = 3;
            }
            else if(dir == 2){ //south
                dir = 1;
            }
            else if(dir == 3){ //west
                dir = 2;
            }
        }
        if(key == 39){
            //right key so turn right
            if(dir == 1){ //east
                dir = 2;
            }
            else if(dir == 0){ //north
                dir = 1;
            }
            else if(dir == 2){ //south
                dir = 3;
            }
            else if(dir == 3){ //west
                dir = 0;
            }
        }
    } // DOES NOTHING BY DEFAULT. COMPLETE FOR MOVING ELEMENTS
}
