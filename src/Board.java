import edu.princeton.cs.algs4.StdRandom;

import java.util.ArrayList;

public class Board {
    private final int[][] blocks;
    private final int dimen;

    public Board(int[][] blocks) {
        this.dimen = blocks.length;
        this.blocks = copyBlocks(blocks);
    }           // construct a board from an n-by-n array of blocks

    // (where blocks[i][j] = block in row i, column j)
    public int dimension() {
        return dimen;
    }                 // board dimension n

    public int hamming() {
        int priority = 0;
        int d = dimen;
        for (int i = 0; i < dimen; i++) {
            for (int j = 0; j < dimen; j++) {
                if (blocks[i][j] != 0 && blocks[i][j] != (i * (d) + j + 1)) {
                    priority++;
                }
            }
        }
        return priority;
    }                   // number of blocks out of place

    public int manhattan() {
        int priority = 0;
        for (int i = 0; i < dimen; i++) {
            for (int j = 0; j < dimen; j++) {
                int key = blocks[i][j];
                if (key != 0) {
                    int x = (key - 1) / dimen;
                    int y = (key - 1) % dimen;
                    int dx = x >= i ? x - i : i - x;
                    int dy = y >= j ? y - j : j - y;
                    priority += dx + dy;
                }
            }
        }
        return priority;
    }                 // sum of Manhattan distances between blocks and goal

    public boolean isGoal() {
        for (int i = 0; i < dimen; i++) {
            for (int j = 0; j < dimen; j++) {
                if ((i == dimen - 1) && (j == dimen - 1)) break;

                if (blocks[i][j] != (i * (dimen) + j + 1)) {
                    return false;
                }
            }
        }
        return true;
    }                // is this board the goal board?

    public Board twin() {
        int[][] newBlocks = copyBlocks(blocks);
        for (int row = 0; row < dimen; row++)
            for (int col = 0; col < dimen - 1; col++)
                if (newBlocks[row][col] != 0 && newBlocks[row][col+1] != 0) {
                    swap(newBlocks, row, col+1, row, col);
                    return new Board(newBlocks);
                }
        throw new RuntimeException();
    }                    // a board that is obtained by exchanging any pair of blocks

    public boolean equals(Object y) {
        return y instanceof Board && this.toString().equals(y.toString());
    }        // does this board equal y?

    public Iterable<Board> neighbors() {
        ArrayList<Board> boards = new ArrayList<>();
        int x = 0;
        int y = 0;
        for (int i = 0; i < dimen; i++) {
            for (int j = 0; j < dimen; j++) {
                if (blocks[i][j] == 0) {
                    if (i - 1 >= 0) {
                        int[][] blcoks1 = copyBlocks(blocks);
                        swap(blcoks1, i - 1, j, i, j);
                        boards.add(new Board(blcoks1));
                    }
                    if (j - 1 >= 0) {
                        int[][] blcoks2 = copyBlocks(blocks);
                        swap(blcoks2, i, j - 1, i, j);
                        boards.add(new Board(blcoks2));
                    }
                    if (i + 1 < dimen) {
                        int[][] blcoks3 = copyBlocks(blocks);
                        swap(blcoks3, i + 1, j, i, j);
                        boards.add(new Board(blcoks3));
                    }
                    if (j + 1 < dimen) {
                        int[][] blcoks4 = copyBlocks(blocks);
                        swap(blcoks4, i, j+1, i, j);
                        boards.add(new Board(blcoks4));
                    }
                    break;
                }
            }
        }
        return boards;
    }     // all neighboring boards

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(dimen);
        sb.append(System.lineSeparator());
        for (int i = 0; i < dimen; i++) {
            for (int j = 0; j < dimen; j++)
                sb.append(blocks[i][j] + " ");
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }               // string representation of this board (in the output format specified below)

    private int[][] copyBlocks(int[][] origin) {
        int[][] newBlocks = new int[dimen][dimen];
        for (int i = 0; i < dimen; i++) {
            for (int j = 0; j < dimen; j++)
                newBlocks[i][j] = origin[i][j];
        }
        return newBlocks;
    }

    private void swap(int[][] blocks, int newX, int newY, int X, int Y) {
        int temp = blocks[newX][newY];
        blocks[newX][newY] = blocks[X][Y];
        blocks[X][Y] = temp;
    }

    public static void main(String[] args) {

    } // unit tests (not graded)
}