package AkashGMaze;
public class Location {
    public int row;
    public int col;

    public Location(int r, int c){
        row = r;
        col = c;
    }
    public int getR(){
        return row;
    }
    public int getC(){
        return col;
    }

    public void incR(int ind){
        row+=ind;
    }
    public void incC(int ind){
        col+=ind;
    }
    public void set(int r, int c){
        row = r;
        col = c;
    }
    public boolean equals(Location loc){
        if(loc == null){
            return false;
        }
        if(loc.row == this.row && loc.col == this.col){
            return true;
        }
        else{
            return false;
        }
    }
    public String toString(){
        return "(" + row + ", " + col + ")";
    }
}
