import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Main {

    public static void main(String[] args) {
	// write your code here
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        int[][] blocks1 = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                blocks[i][j] = in.readInt();

                blocks1[i][j] = i * n + j + 1;
            }
//        blocks1[1][2] = 0;
//        blocks1[2][2] = 6;
        Board initial = new Board(blocks);
        //Board test = initial.twin();
        StdOut.println(initial.toString());
//        StdOut.println(test.toString());
//        for (Board board : initial.neighbors()){
//            StdOut.println(board.toString());
//        }


        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}
