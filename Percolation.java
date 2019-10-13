//import static sun.java2d.cmm.ColorTransform.In;

import java.util.Scanner;

public class Percolation {
     private boolean[][] grid;
     private WeightedQuickUnionFind weightedQFGrid;
     private WeightedQuickUnionFind weightedQFFull;
     private int virtualTop;
     private int virtualBottom;
     private int openSites;
     private int gridSize;
     private int gridSquared;

     public Percolation(int N) {
          //Constructor creating a NxN Grid which are initially all blocked.
          if (N > 0) {
               gridSize=N;
               gridSquared=N*N;

               grid = new boolean[N][N];
               for (int i = 0; i < grid.length; i++) {
                    for (int j = i; j < grid.length; j++) {
                         grid[i][j] = false;
                    }
               }
          }
          else{
               throw new IllegalArgumentException("Check N, it should be Greater than 0");
          }
          weightedQFGrid=new WeightedQuickUnionFind((N*N)+2); //this includes the virtual top and bottom that we need to check if the system percolates or not
          weightedQFGrid=new WeightedQuickUnionFind((N*N)+1); //this just includes the virtual top
          virtualBottom=(N*N)+2;
          virtualTop=(N*N);
          openSites=0;
     }

     public void open(int row, int col){
          //opens the site(row,col) if it is not open already.
     validateSite(row, col);

      int shiftRow=row-1;
      int shiftCol=col-1;
      int flatIndex=flattenGrid(row, col)-1;

      if(isOpen(row,col)){
           return;
      }

      this.grid[shiftRow][shiftCol]=true;
      openSites++;

      if(row==1){ //for top row
           weightedQFGrid.union(virtualTop,flatIndex);
           weightedQFFull.union(virtualTop,flatIndex);
      }
      if(row==gridSize){  //for bottom row
           weightedQFGrid.union(virtualBottom,flatIndex);
      }

      //check and open left
       if(isOnGrid(row,col-1)&&isOpen(row,col-1)){
            weightedQFGrid.union(flatIndex,flattenGrid(row,col-1)-1);
            weightedQFFull.union(flatIndex,flattenGrid(row,col-1)-1);
       }

       //check and open right
       if(isOnGrid(row,col+1)&&isOpen(row,col+1)){
            weightedQFGrid.union(flatIndex,flattenGrid(row,col+1)-1);
            weightedQFFull.union(flatIndex,flattenGrid(row,col+1)-1);
       }

       //check and open top
       if(isOnGrid(row-1,col)&&isOpen(row-1,col)){
            weightedQFGrid.union(flatIndex,flattenGrid(row-1,col)-1);
            weightedQFFull.union(flatIndex,flattenGrid(row-1,col)-1);
       }

       //check and open bottom
       if(isOnGrid(row+1,col)&&isOpen(row+1,col)){
            weightedQFGrid.union(flatIndex,flattenGrid(row+1,col)-1);
             weightedQFFull.union(flatIndex,flattenGrid(row+1,col)-1);
       }
    }

     public boolean isOpen(int row, int col){
          //this returns the number of open sites in the grid.
      validateSite(row,col);
      return (this.grid[row-1][col-1]);
     }

     public boolean isFull(int row, int col){
//          //this returns true or false depending whether is the site full.
//     //  a site is an open site that canb e connected to an open sites in the to row
//          //via a chain of neighboring open sites.
          validateSite(row,col);
          return weightedQFFull.connected(virtualTop,flattenGrid(row,col)-1);
     }

     public int numberOfOpenSites(){
          return openSites;
     }

     public boolean percolates(){
          return weightedQFGrid.connected(virtualTop,virtualBottom);
     }

     private int encode(int row, int col ){
          return row*col;
     }

     private int flattenGrid(int row, int col){
          return gridSize*(row-1)+col;
     }

     private void validateSite(int row, int col){
          if(!isOnGrid(row,col)){
               throw new IndexOutOfBoundsException("Index is out of bounds");
          }
     }

     private boolean isOnGrid(int row, int col){
          int shiftRow=row-1;
          int shiftCol=col-1;
          return (shiftCol>=0 && shiftRow>=0 && shiftRow<gridSize && shiftCol<gridSize);
     }

//    public static void main(String[] args) {
//        String filename = args[0];
//        Scanner in=new Scanner(filename) ;
//
//        int N = in.nextInt();
//        Percolation perc = new Percolation(N);
//        while (in!=null) {
//            int i = in.nextInt();
//            int j = in.nextInt();
//            perc.open(i, j);
//        }
//        StdOut.println(perc.numberOfOpenSites() + " open sites");
//        if (perc.percolates()) {
//            StdOut.println("percolates");
//        }
//        else {
//            StdOut.println("does not percolate");
//        }
//
//        // Check if site (i, j) optionally specified on the command line
//        // is full.
//        if (args.length == 3) {
//            int i = Integer.parseInt(args[1]);
//            int j = Integer.parseInt(args[2]);
//            StdOut.println(perc.isFull(i, j));
//        }
//    }
}

