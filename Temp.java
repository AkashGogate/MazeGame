package AkashGMaze;

// import javax.swing.*;
// import java.awt.*;
// import java.awt.event.*;
// import java.io.*;
// import java.util.ArrayList;
// import java.awt.image.*;
// import javax.imageio.ImageIO;

// public class MazeProject extends JPanel implements KeyListener, ActionListener
// {	//private Graphics2D g3;
// 	private JFrame frame;
// 	private int size = 30, width = 1500, height = 1000;
// 	private char[][] maze;
// 	private Timer t;
// 	private MazeElement finish;
// 	private Explorer explorer;
// 	private UDB udb;
// 	public int xNum = 0;
// 	public int yNum = 0;

// 	public MazeProject(){
// 		//Maze variables
// 		setBoard("maze0.txt");
// 		frame=new JFrame("A-Mazing Program");
// 		frame.setSize(width,height);
// 		frame.add(this);
// 		frame.addKeyListener(this);
// 		frame.setResizable(false);
// 		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
// 		frame.setVisible(true);
// 		t = new Timer(500, this);  // will trigger actionPerformed every 500 ms
// 		t.start();

// 	}

// 	// All Graphics handled in this method.  Don't do calculations here
// 	public void paintComponent(Graphics g)
// 	{
// 		super.paintComponent(g);
// 		Graphics2D g2=(Graphics2D)g;
// 		g2.setColor(Color.BLACK);
// 		g2.fillRect(0,0,frame.getWidth(),frame.getHeight());

// 		for(int r=0;r<maze.length;r++){
// 			for(int c=0;c<maze[0].length;c++){

// 				g2.setColor(Color.GRAY);
// 				if (maze[r][c]=='#')
// 					g2.fillRect(c*size+size,r*size+size,size,size); //Wall
// 				else
// 					g2.drawRect(c*size+size,r*size+size,size,size);  //Open
				
// 				Location here = new Location(r, c);
				
// 				if(here.equals(finish.getLoc())){
// 					g2.drawImage(finish.getImg(), c*size+size, r*size+size, size, size, null);
// 				}
// 				if(here.equals(explorer.getLoc())){
// 					g2.drawImage(explorer.getImg(), c*size+size, r*size+size, size, size, null);
// 				}
// 				if(here.equals(udb.getLoc())){
// 					g2.drawImage(udb.getImg(), c*size+size, r*size+size, size, size, null);
// 				}

// 				}

				
// 		}

// 		// Display at bottom of page
// 		int hor = size;
// 		int vert = maze.length*size+ 2*size;
// 		g2.setFont(new Font("Arial",Font.BOLD,20));
// 		g2.setColor(Color.PINK);
// 		g2.drawString("PRINT STUFF HERE  ",hor,vert);
// 	}

// 	public void keyPressed(KeyEvent e){
// 		System.out.println(e.getKeyCode());
// 		explorer.move(e.getKeyCode(), maze);
// 		// Call explorer method
// 		repaint();
// 	}

// 	/*** empty methods needed for interfaces **/
// 	public void keyReleased(KeyEvent e){}
// 	public void keyTyped(KeyEvent e){}
// 	public void actionPerformed(ActionEvent e) {
// 		udb.move(0,maze);
// 		repaint();
// 	}

// 	public void setBoard(String fileName){

// 		//load(fileName);
// 		//char temp = new char[xNum][yNum];
// 		char[][] temp = {{'#','#','#','#','#','#','#','#'},
// 						{'#','U',' ','E',' ',' ','F','#'},
// 						{'#','#','#','#','#','#','#','#'}};
// 		maze = temp;

// 		for(int r = 0; r<maze.length; r++){
// 			for(int c = 0; c < maze[0].length; c++){
// 				char symbol = maze[r][c];
// 				if(symbol == 'F'){
// 					finish = new MazeElement(new Location(r,c),size, "finish.png");
// 				}
// 				else if(symbol == 'E'){
// 					explorer = new Explorer(new Location(r,c), size);
// 				}
// 				else if(symbol == 'U'){
// 					udb = new UDB(new Location(r,c), size, "udb.png");
// 				}
// 			}
// 		}

		
// 	}

// 	public void load(String fileName){
// 		int xNum = 0;
// 		int yNum = 0;
// 		boolean flag = true;
// 		try{
// 			File file = new File(fileName);
// 			BufferedReader br = new BufferedReader(new FileReader(file));
// 			String line="";
// 			while((line=br.readLine())!= null) 
// 			{
// 				if(flag == true){
// 					for(int i = 0; i<line.length(); i++){
// 						xNum++;
// 					}
// 				}
// 				else{
					
// 				}
// 				yNum++;
// 				flag = false;
// 			}

// 			maze = new char[xNum][yNum];
// 			int r = 0;
// 			while((line=br.readLine())!= null) 
// 			{
// 					for(int c = 0; c<maze.length; c++){
// 						maze[r][c] = line.charAt(c);
// 					}
// 					r++;
				
// 			}
// 		}
// 			catch (IOException io){
// 				System.err.println("File Error: "+io);
// 		}
// 	}

// 	public static void main(String[] args){
// 		MazeProject app=new MazeProject();
// 	}
// }
