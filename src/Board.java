import edu.princeton.cs.algs4.StdRandom;

import java.util.ArrayList;

public class Board {
    private int[][] blocks;
    private int dimen;
    public Board(int[][] blocks) {
        this.blocks = blocks;
        this.dimen = blocks.length;
    }           // construct a board from an n-by-n array of blocks

    // (where blocks[i][j] = block in row i, column j)
    public int dimension() {
        return dimen;
    }                 // board dimension n

    public int hamming() {
        int priority = 0;
        int d = dimen;
        for(int i=0; i<dimen; i++){
            for(int j=0; j<dimen; j++) {
                if(blocks[i][j] != 0 && blocks[i][j] != (i*(d) + j + 1)){
                    priority++;
                }
            }
        }
        return priority;
    }                   // number of blocks out of place

    public int manhattan() {
        int priority = 0;
        for(int i=0; i<dimen; i++){
            for(int j=0; j<dimen; j++) {
                int key = blocks[i][j];
                if( key != 0 ){
                    int x = (key-1)/dimen;
                    int y = (key-1)%dimen ;
                    int dx = x >= i ? x-i : i-x;
                    int dy = y >= j ? y-j : j-y;
                    priority += dx + dy;
                }
            }
        }
        return priority;
    }                 // sum of Manhattan distances between blocks and goal

    public boolean isGoal() {
        for(int i=0; i<dimen; i++){
            for(int j=0; j<dimen; j++) {
                if((i == dimen-1) && (j == dimen-1 )) break;

                if(blocks[i][j] != (i*(dimen) + j + 1)){
                    return false;
                }
            }
        }
        return true;
    }                // is this board the goal board?

    public Board twin() {
        int x1 = StdRandom.uniform(dimen);
        int y1 = StdRandom.uniform(dimen);

        int x2 = StdRandom.uniform(dimen);
        int y2 = StdRandom.uniform(dimen);

        swap(blocks, x1, y1, x2, y2);
        return this;
    }                    // a board that is obtained by exchanging any pair of blocks

    public boolean equals(Object y) {
        return this.equals(y);
    }        // does this board equal y?

    public Iterable<Board> neighbors() {
        ArrayList<Board> boards = new ArrayList<>();
        int x = 0;
        int y = 0;
        for(int i=0; i<dimen; i++){
            for(int j=0; j<dimen; j++) {
                if(blocks[i][j] == 0){
                    if(i-1>=0){
                        int[][] blcoks1 = copyBlocks(blocks);
                        swap(blcoks1,i-1, j, i, j);
                        boards.add(new Board(blcoks1));
                    }
                    if(j-1>=0){
                        int[][] blcoks2 = copyBlocks(blocks);
                        swap(blcoks2,i, j-1, i, j);
                        boards.add(new Board(blcoks2));
                    }
                    if(i+1<=dimen){
                        swap(blcoks1,i-1, j, i, j);
                        boards.add(new Board(blcoks1));
                    }
                    if(i-1>=0){
                        swap(blcoks1,i-1, j, i, j);
                        boards.add(new Board(blcoks1));
                    }
                    break;
                }
            }
        }
        if(x-1 >= 0 ){
            int temp = blocks[x-1][y];
        }

        return boards;
    }     // all neighboring boards

    public String toString() {
        return "";
    }               // string representation of this board (in the output format specified below)

    private int[][] copyBlocks(int[][] origin){
        int[][] newBlocks = new int[dimen][dimen];
        for(int i=0; i<dimen; i++){
            for(int j=0; j<dimen; j++)
                newBlocks[i][j] = origin[i][j];
        }
        return newBlocks;
    }

    private void swap(int[][] blocks, int preX, int preY, int X, int Y){
        int temp = blocks[preX][preY];
        blocks[preX][preY] = blocks[X][Y];
        blocks[X][Y] = temp;
    }


    public static void main(String[] args) {
    } // unit tests (not graded)
}