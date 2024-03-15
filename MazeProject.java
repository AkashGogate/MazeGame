package AkashGMaze;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.awt.image.*;
import javax.imageio.ImageIO;
import static javax.swing.JOptionPane.showMessageDialog;

public class MazeProject extends JPanel implements KeyListener, ActionListener
{
	private JFrame frame;
	private int size = 30, width = 1500, height = 1000;
	private char[][] maze;
	private Timer t;
	private MazeElement finish = new MazeElement(new Location(0, 1), size, "AkashGMaze/flags.png");
	private Explorer explorer = new Explorer(new Location(1,0), size);
	private UDB udb = new UDB(new Location(1, 1), size, "AkashGMaze/udb.png");
	private Coin coin = new Coin(new Location(2, 1), size, "AkashGMaze/coin.png");
	boolean collected = false;
	public int xNum = 0;
	public int yNum = 0;
	int lives = 3;
	int coins;
	boolean drawCoin = true;
	boolean finished = false;
	boolean finishedFirst = true;
	boolean finishedSecond = true;

	public MazeProject(){
		//Maze variables
		//init variables
		String userDirectoryPath = System.getProperty("user.dir");

		setBoard("AkashGMaze/maze0.txt");
		frame=new JFrame("A-Mazing Program");
		frame.setSize(width,height);
		frame.add(this);
		frame.addKeyListener(this);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		t = new Timer(500, this);  // will trigger actionPerformed every 500 ms
		t.start();
	}

	// All Graphics handled in this method.  Don't do calculations here
	public void paintComponent(Graphics g)
	{
		
		if(explorer.intersects(finish) && collected){
			if(finished == false){
				if(finishedFirst == true){
					showMessageDialog(null, "You finished the first maze!");
					finishedFirst = false;
				}
				setBoard("AkashGMaze/maze1.txt");
				drawCoin = true;
				collected = false;
				finished = true;
				
			}
			else if(finishedSecond == true){
				if(finishedSecond){
					showMessageDialog(null, "You finished the second maze!");
					finishedSecond = false;
				}
				setBoard("AkashGMaze/maze2.txt");
				drawCoin = true;
				collected = false;
				finished = true;
			}
			else{
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));	
			}

			
		}
		if(lives == 0){
			frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));	
		}
		
		super.paintComponent(g);
		Graphics2D g2=(Graphics2D)g;
		g2.setColor(Color.BLACK);
		g2.fillRect(0,0,frame.getWidth(),frame.getHeight());

		for(int r=0;r<maze.length;r++){
			
			for(int c=0;c<maze[0].length;c++){
				
				g2.setColor(Color.GRAY);
				if (maze[r][c]=='#')
					g2.fillRect(c*size+size,r*size+size,size,size); //Wall
				else
					g2.drawRect(c*size+size,r*size+size,size,size);  //Open

				Location here = new Location(r, c);
			
				if(here.equals(finish.getLoc())){
					g2.drawImage(finish.getImg(), c*size+size, r*size+size, size, size, null);
				}
				if(here.equals(explorer.getLoc())){
					g2.drawImage(explorer.getImg(), c*size+size, r*size+size, size, size, null);
				}
				if(here.equals(udb.getLoc())){
					g2.drawImage(udb.getImg(), c*size+size, r*size+size, size, size, null);
				}
				if(here.equals(coin.getLoc())&& drawCoin){
					if(coin == null){
						continue;
					}
					g2.drawImage(coin.getImg(), c*size+size, r*size+size, size, size, null);
				}
				
					
			}
		}

		// Display at bottom of page
		int hor = size;
		int vert = maze.length*size+ 2*size;
		g2.setFont(new Font("Arial",Font.BOLD,20));
		int steps = explorer.getSteps();
		g2.setColor(Color.PINK);
		g2.drawString("You have taken " + steps + " steps" , hor,vert);
		g2.drawString("You have " + lives + " lives" , hor,vert+20);
		g2.drawString("You have " + coins + " coins" , hor+400,vert);

	}

	public void keyPressed(KeyEvent e){
		System.out.println(e.getKeyCode());
		explorer.move(e.getKeyCode(), maze);
		repaint();
	}

	/*** empty methods needed for interfaces ***/
	public void keyReleased(KeyEvent e){}
	public void keyTyped(KeyEvent e){}
	public void actionPerformed(ActionEvent e) {
		// This code block is checking if the explorer object intersects with the udb object. If they do
		// intersect, it checks if the lives variable is equal to 0. If lives is 0, it dispatches a window
		// closing event to close the frame. Otherwise, it decrements the lives variable by 1.
				if(explorer.intersects(udb) ){
					if(lives == 0){
						frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
					}
					else{
						lives--;
					}
					
				}
				if(explorer.intersects(coin)&& collected == false){
					collected = true;
					drawCoin = false;
					coins++;
				}
				udb.move(0, maze);
				repaint();
		}

	public void setBoard(String fileName){
		//replace this code with load from file
		//make sure all rows have same number of cols

		load(fileName);

		for(int r = 0; r<maze.length; r++){
			for(int c = 0; c < maze[0].length; c++){
				char symbol = maze[r][c];
				if(symbol == 'F'){
					finish = new MazeElement(new Location(r,c),size, "AkashGMaze/flags.png");
				}
				else if(symbol == 'E'){
					explorer = new Explorer(new Location(r,c), size);
				}
				else if(symbol == 'U'){
					udb = new UDB(new Location(r,c), size, "AkashGMaze/udb.png");
				}
				else if(symbol == 'C'){
					coin = new Coin(new Location(r,c), size, "AkashGMaze/coin.png");
				}
			}
		}
	}
		//Function to load the maze files into my board
		public void load(String fileName){
		int xNum = 0;
		int yNum = 0;
		boolean flag = true;
		try{
			File file = new File(fileName);
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line="";
			while((line=br.readLine())!= null) 
			{
				if(flag == true){
					xNum = line.length();
				}
				else{
					
				}
				yNum++;
				flag = false;
			}
			
			maze = new char[yNum][xNum];
			int r = 0;
			br = new BufferedReader(new FileReader(file));
			line="";
			while((line=br.readLine())!= null) 
			{
					System.out.println(" ");
					for(int c = 0; c<xNum; c++){
						
						maze[r][c] = line.charAt(c);
						System.out.print(maze[r][c] + " ");
						
					}
					r++;
			}
		}
			catch (IOException io){
				System.err.println("File Error: "+io);
		}
	}

	public static void main(String[] args){
		MazeProject app=new MazeProject();
	}
}

