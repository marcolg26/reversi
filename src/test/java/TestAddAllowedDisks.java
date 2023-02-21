package units.sdm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class TestAddAllowedDisks {

    @Test
    void addPegs() {

        int[][] checkerboardMatrix = {{0, 0, -1, -1, -1, -1, -1, -1},
                {0, 0, 1, -1, -1, -1, -1, -1},
                {0, 0, 0, -1, -1, -1, -1, 0},
                {0, 0, -1, -1, -1, -1, -1, 0},
                {1, 1, -1, -1, 1, 1, -1, -1},
                {0, -1, 1, -1, 0, 0, -1, 0},
                {-1, 0, 0, 1, 0, 0, -1, 0},
                {0, 0, 0, 0, 1, 0, 0, 0}};

        int[][] referenceCheckerboard = {{0, 0, -1, -1, -1, -1, -1, -1},
                {0, 0, 1, -1, -1, -1, -1, -1},
                {0, 0, 2, -1, -1, -1, -1, 2},
                {0, 0, -1, -1, -1, -1, -1, 0},
                {1, 1, -1, -1, 1, 1, -1, -1},
                {2, -1, 1, -1, 2, 0, -1, 0},
                {-1, 2, 2, 1, 0, 0, -1, 2},
                {0, 0, 0, 0, 1, 0, 0, 0}};

        Checkerboard checkerboard = new Checkerboard(checkerboardMatrix);
        checkerboard.addAllowedDisks(1);

        assertArrayEquals(checkerboard.checkerboard, referenceCheckerboard);

    }


}