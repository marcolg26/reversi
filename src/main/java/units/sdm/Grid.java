package units.sdm;

public class Grid {
    int size = 8;
    int grid[][] = new int[size][size];

    public Grid() {

        grid[3][3] = -1;
        grid[4][3] = 1;
        grid[3][4] = 1;
        grid[4][4] = -1;


    }

    public Grid(int[][] grid) {
        this.grid = grid;
    }

    public boolean AllowMove(int x, int y, int ColourTurn) {

        return false;

    }

}
