package units.sdm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheckerboardTest {
    private static final int B = Checkerboard.B;
    private static final int W = Checkerboard.W;
    private static final int N = Checkerboard.N;
    private static final int A = Checkerboard.A;

    @Test
    void createDefaultCheckerboard() {
        Checkerboard checkerboard = new Checkerboard();

        int[][] referenceCheckerboard = {{N, N, N, N, N, N, N, N},
                {N, N, N, N, N, N, N, N},
                {N, N, N, N, N, N, N, N},
                {N, N, N, W, B, N, N, N},
                {N, N, N, B, W, N, N, N},
                {N, N, N, N, N, N, N, N},
                {N, N, N, N, N, N, N, N},
                {N, N, N, N, N, N, N, N}};

        assertArrayEquals(referenceCheckerboard, checkerboard.getMatrix());
    }

    @Test
    void createDefaultCheckerboardCountBlack() {
        Checkerboard checkerboard = new Checkerboard();
        int expectedNBlacks = 2;

        int nBlacks = checkerboard.getNumberOfBlacks();

        assertEquals(expectedNBlacks, nBlacks);
    }

    @Test
    void createDefaultCheckerboardCountWhite() {
        Checkerboard checkerboard = new Checkerboard();
        int expectedNWhite = 2;

        int nWhite = checkerboard.getNumberOfBlacks();

        assertEquals(expectedNWhite, nWhite);
    }

    @Test
    void createComplexCheckerboard() {
        int[][] referenceCheckerboard = {{W, W, B, W, N, N, N, W},
                {B, B, W, B, W, N, W, W},
                {B, B, W, W, W, W, B, W},
                {N, N, W, W, W, W, B, W},
                {N, B, B, B, B, B, B, W},
                {N, N, N, W, B, W, B, W},
                {N, N, W, W, W, B, B, W},
                {N, N, N, W, N, N, B, W}};

        Checkerboard checkerboard = new Checkerboard(referenceCheckerboard);

        assertArrayEquals(referenceCheckerboard, checkerboard.getMatrix());
    }

    @Test
    void addAllowedDisks() {
        Checkerboard checkerboard = new Checkerboard(CheckerboardUtility.COMPLEX_CHECKERBOARD);
        checkerboard.markAllowedPlacings(B);

        assertArrayEquals(CheckerboardUtility.COMPLEX_CHECKERBOARD_ALLOWED_DISKS, checkerboard.getMatrix());
    }

    @Test
    void allowPlaceDefaultCheckerboard() {
        Checkerboard checkerboard = new Checkerboard();

        for (int i = 0; i < Checkerboard.SIZE; i++) {
            for (int j = 0; j < Checkerboard.SIZE; j++) {
                if (checkerboard.isPlaceAllowed(i, j, B))
                    checkerboard.getMatrix()[i][j] = A;
            }
        }

        assertArrayEquals(CheckerboardUtility.SIMPLE_CHECKERBOARD_ALLOWED_DISKS, checkerboard.getMatrix());
    }

    @Test
    void allowPlaceComplexCheckerboard() {
        Checkerboard checkerboard = new Checkerboard(CheckerboardUtility.COMPLEX_CHECKERBOARD);

        for (int i = 0; i < Checkerboard.SIZE; i++) {
            for (int j = 0; j < Checkerboard.SIZE; j++) {
                if (checkerboard.isPlaceAllowed(i, j, B))
                    checkerboard.getMatrix()[i][j] = A;
            }
        }

        assertArrayEquals(CheckerboardUtility.COMPLEX_CHECKERBOARD_ALLOWED_DISKS, checkerboard.getMatrix());
    }

    @Test
    void disksCountBlack() {
        Checkerboard checkerboard = new Checkerboard(CheckerboardUtility.COMPLEX_CHECKERBOARD);
        int numBlacks = 8;

        assertEquals(numBlacks, checkerboard.getNumberOfBlacks());
    }

    @Test
    void disksCountWhite() {
        Checkerboard checkerboard = new Checkerboard(CheckerboardUtility.COMPLEX_CHECKERBOARD);
        int numWhites = 29;

        assertEquals(numWhites, checkerboard.getNumberOfWhites());
    }

    @Test
    void placeWhite() {
        Checkerboard checkerboard = new Checkerboard();

        int[][] expectedMatrix = {{N, N, N, N, N, N, N, N},
                {N, N, N, N, N, N, N, N},
                {N, N, N, N, W, N, N, N},
                {N, N, N, W, W, N, N, N},
                {N, N, W, W, W, N, N, N},
                {N, N, N, N, N, N, N, N},
                {N, N, N, N, N, N, N, N},
                {N, N, N, N, N, N, N, N}};

        checkerboard.place(4, 2, W);
        checkerboard.place(2, 4, W);

        assertArrayEquals(expectedMatrix, checkerboard.getMatrix());
    }

    @Test
    void placeBlack() {
        Checkerboard checkerboard = new Checkerboard();

        int[][] expectedMatrix = {{N, N, N, N, N, N, N, N},
                {N, N, N, N, N, N, N, N},
                {N, N, N, B, N, N, N, N},
                {N, N, N, B, B, N, N, N},
                {N, N, N, B, B, B, N, N},
                {N, N, N, N, N, N, N, N},
                {N, N, N, N, N, N, N, N},
                {N, N, N, N, N, N, N, N}};

        checkerboard.place(2, 3, B);
        checkerboard.place(4, 5, B);

        assertArrayEquals(expectedMatrix, checkerboard.getMatrix());
    }

    @Test
    void blackPlaceAtBounds() {
        int[][] borderMatrix = {{B, N, N, N, B, N, N, B},
                {N, W, N, N, W, N, W, N},
                {N, N, W, N, W, W, N, N},
                {B, W, W, N, N, W, W, B},
                {N, N, N, N, N, N, N, N},
                {N, N, W, N, W, W, N, N},
                {N, W, N, N, W, N, W, N},
                {B, N, N, N, B, N, N, B}};
        Checkerboard checkerboard = new Checkerboard(borderMatrix);

        int[][] expectedMatrix = {{B, N, N, N, B, N, N, B},
                {N, B, N, N, B, N, B, N},
                {N, N, B, N, B, B, N, N},
                {B, B, B, B, B, B, B, B},
                {N, N, N, B, B, N, N, N},
                {N, N, B, N, B, B, N, N},
                {N, B, N, N, B, N, B, N},
                {B, N, N, N, B, N, N, B}};

        checkerboard.place(3, 3, B);
        checkerboard.place(3, 4, B);
        checkerboard.place(4, 3, B);
        checkerboard.place(4, 4, B);

        assertArrayEquals(expectedMatrix, checkerboard.getMatrix());
    }

    @Test
    void whitePlaceAtBounds() {
        int[][] borderMatrix = {{W, N, N, N, W, N, N, W},
                {N, B, N, N, B, N, B, N},
                {N, N, B, N, B, B, N, N},
                {W, B, B, N, N, B, B, W},
                {N, N, N, N, N, N, N, N},
                {N, N, B, N, B, B, N, N},
                {N, B, N, N, B, N, B, N},
                {W, N, N, N, W, N, N, W}};
        Checkerboard checkerboard = new Checkerboard(borderMatrix);

        int[][] expectedMatrix = {{W, N, N, N, W, N, N, W},
                {N, W, N, N, W, N, W, N},
                {N, N, W, N, W, W, N, N},
                {W, W, W, W, W, W, W, W},
                {N, N, N, W, W, N, N, N},
                {N, N, W, N, W, W, N, N},
                {N, W, N, N, W, N, W, N},
                {W, N, N, N, W, N, N, W}};

        checkerboard.place(3, 3, W);
        checkerboard.place(3, 4, W);
        checkerboard.place(4, 3, W);
        checkerboard.place(4, 4, W);

        assertArrayEquals(expectedMatrix, checkerboard.getMatrix());
    }

    @Test
    void whitePlaceNotAllowed() {
        Checkerboard checkerboard = new Checkerboard(CheckerboardUtility.COMPLEX_CHECKERBOARD);
        checkerboard.markAllowedPlacings(B);

        Checkerboard referenceCheckerboard = new Checkerboard(checkerboard.getMatrix());

        //empty square with no adjacent
        checkerboard.place(0, 0, W);
        //square marked as allowed for black, but not allowed for white
        checkerboard.place(6, 7, W);
        //square that is allowed for white but is occupied by black
        checkerboard.place(4, 4, W);
        //square that is allowed for white but is occupied by white
        checkerboard.place(4, 6, W);

        assertArrayEquals(referenceCheckerboard.getMatrix(), checkerboard.getMatrix());
    }

    @Test
    void blackPlaceNotAllowed() {
        Checkerboard checkerboard = new Checkerboard(CheckerboardUtility.COMPLEX_CHECKERBOARD);
        checkerboard.markAllowedPlacings(W);

        Checkerboard referenceCheckerboard = new Checkerboard(checkerboard.getMatrix());

        //empty square with no adjacent
        checkerboard.place(0, 0, B);
        //square marked as allowed for white, but not allowed for black
        checkerboard.place(0, 1, B);
        //square that is allowed for black but is occupied by white
        checkerboard.place(1, 6, B);
        //square that is allowed for black but is occupied by black
        checkerboard.place(4, 4, B);

        assertArrayEquals(referenceCheckerboard.getMatrix(), checkerboard.getMatrix());
    }

    @Test
    void placeUpdateDisks() {
        Checkerboard checkerboard = new Checkerboard();
        int initialNBlacks = checkerboard.getNumberOfBlacks();
        int initialNWhites = checkerboard.getNumberOfWhites();

        checkerboard.place(2, 3, B);

        int finalNBlacks = checkerboard.getNumberOfBlacks();
        int finalNWhites = checkerboard.getNumberOfWhites();

        assertNotEquals(initialNBlacks, finalNBlacks);
        assertNotEquals(initialNWhites, finalNWhites);
    }

    @Test
    void placeUpdateDisksToExpectedValues() {
        Checkerboard checkerboard = new Checkerboard();

        checkerboard.place(2, 3, B);

        int nBlacks = checkerboard.getNumberOfBlacks();
        int nWhites = checkerboard.getNumberOfWhites();

        assertEquals(4, nBlacks);
        assertEquals(1, nWhites);
    }

    @Test
    void whiteHasNoAllowedPlacings() {
        int[][] whiteHasNoPlaces = {{B, B, B, B, B, B, B, B},
                {W, W, W, W, W, W, W, B},
                {W, W, B, W, B, B, W, B},
                {B, W, B, B, W, W, W, B},
                {B, W, B, W, W, B, W, B},
                {B, W, B, W, B, W, W, B},
                {W, B, W, W, W, W, W, N},
                {B, B, B, B, B, B, B, N}};
        Checkerboard checkerboard = new Checkerboard(whiteHasNoPlaces);

        assertFalse(checkerboard.existAllowedPlace(W));
    }

    @Test
    void whiteHasAllowedPlacings() {
        int[][] whiteHasPlaces = {{B, B, B, B, B, B, B, W},
                {W, W, W, W, W, W, W, B},
                {W, W, B, W, B, B, W, B},
                {B, W, B, B, W, W, W, B},
                {B, W, B, W, W, B, W, B},
                {B, W, B, W, B, W, W, B},
                {W, B, W, W, W, W, W, N},
                {B, B, B, B, B, B, B, N}};
        Checkerboard checkerboard = new Checkerboard(whiteHasPlaces);

        assertTrue(checkerboard.existAllowedPlace(W));
    }

    @Test
    void blackHasNoAllowedPlacings() {
        int[][] blackHasNoPlaces = {{W, W, B, B, B, B, B, B},
                {W, W, W, W, W, W, W, B},
                {W, W, W, W, B, B, W, B},
                {B, W, B, W, W, W, W, B},
                {B, W, B, W, W, W, W, B},
                {B, W, B, W, B, W, W, B},
                {W, W, W, W, W, W, W, N},
                {B, B, B, B, B, B, B, N}};
        Checkerboard checkerboard = new Checkerboard(blackHasNoPlaces);

        assertFalse(checkerboard.existAllowedPlace(B));
    }

    @Test
    void blackHasAllowedPlacings() {
        int[][] blackHasPlaces = {{B, B, B, B, B, B, B, B},
                {W, W, W, W, W, W, W, B},
                {W, W, B, W, B, B, W, B},
                {B, W, B, B, W, W, W, B},
                {B, W, B, W, W, B, W, B},
                {B, W, B, W, B, W, W, B},
                {W, B, W, W, W, W, W, N},
                {B, B, B, B, B, B, B, N}};
        Checkerboard checkerboard = new Checkerboard(blackHasPlaces);

        assertTrue(checkerboard.existAllowedPlace(B));
    }

    @Test
    void endGameFullCheckerboard() {
        int[][] fullMatrix = {{W, W, W, B, B, B, B, B},
                {W, W, W, W, W, W, W, B},
                {W, W, W, W, W, W, W, W},
                {W, W, W, W, W, B, W, B},
                {W, W, W, W, B, W, B, B},
                {W, W, B, B, B, B, B, B},
                {W, B, B, W, W, W, W, B},
                {B, B, B, W, W, W, W, B}};
        Checkerboard checkerboard = new Checkerboard(fullMatrix);

        assertTrue(checkerboard.isGameover());
    }

    @Test
    void endGameNoAllowedPlace() {
        int[][] notFullMatrix = {{W, W, W, W, W, W, W, W},
                {W, W, W, W, W, W, W, W},
                {W, W, W, W, W, W, W, W},
                {W, W, W, W, W, W, W, N},
                {W, W, W, W, W, W, N, N},
                {W, W, W, W, W, W, N, B},
                {W, W, W, W, W, W, W, N},
                {W, W, W, W, W, W, W, W}};
        Checkerboard checkerboard = new Checkerboard(notFullMatrix);

        assertTrue(checkerboard.isGameover());
    }

    @Test
    void endGameBothHaveAllowedPlaces() {
        Checkerboard checkerboard = new Checkerboard();

        assertFalse(checkerboard.isGameover());
    }

    @Test
    void endGameWhiteHasAllowedPlace() {
        int[][] whiteHasAPMatrix = {{B, B, B, B, B, B, B, W},
                {W, W, W, W, W, W, W, B},
                {W, W, B, W, B, B, W, B},
                {B, W, B, B, W, W, W, B},
                {B, W, B, W, W, B, W, B},
                {B, W, B, W, B, W, W, B},
                {W, B, W, W, W, W, W, N},
                {B, B, B, B, B, B, B, N}};
        Checkerboard checkerboard = new Checkerboard(whiteHasAPMatrix);

        assertFalse(checkerboard.isGameover());
    }

    @Test
    void endGameBlackHasAllowedPlace() {
        int[][] blackHasAPMatrix = {{B, B, B, B, B, B, B, B},
                {W, W, W, W, W, W, W, B},
                {W, W, B, W, B, B, W, B},
                {B, W, B, B, W, W, W, B},
                {B, W, B, W, W, B, W, B},
                {B, W, B, W, B, W, W, B},
                {W, B, W, W, W, W, W, N},
                {B, B, B, B, B, B, B, N}};
        Checkerboard checkerboard = new Checkerboard(blackHasAPMatrix);

        assertFalse(checkerboard.isGameover());
    }

    @Test
    void removeAllowedDisks() {
        Checkerboard checkerboard = new Checkerboard(CheckerboardUtility.COMPLEX_CHECKERBOARD_ALLOWED_DISKS);
        checkerboard.unmarkAllowedPlacings();

        assertArrayEquals(CheckerboardUtility.COMPLEX_CHECKERBOARD, checkerboard.getMatrix());
    }

    @Test
    void defaultCheckerboardToString() {
        Checkerboard checkerboard = new Checkerboard();

        String referenceString = "   [A][B][C][D][E][F][G][H]\n" +
                "[1][ ][ ][ ][ ][ ][ ][ ][ ]\n" +
                "[2][ ][ ][ ][ ][ ][ ][ ][ ]\n" +
                "[3][ ][ ][ ][ ][ ][ ][ ][ ]\n" +
                "[4][ ][ ][ ][W][B][ ][ ][ ]\n" +
                "[5][ ][ ][ ][B][W][ ][ ][ ]\n" +
                "[6][ ][ ][ ][ ][ ][ ][ ][ ]\n" +
                "[7][ ][ ][ ][ ][ ][ ][ ][ ]\n" +
                "[8][ ][ ][ ][ ][ ][ ][ ][ ]\n";

        assertEquals(referenceString, checkerboard.toString());
    }

    @Test
    void defaultCheckerboardAllowedPlaceToString() {
        Checkerboard checkerboard = new Checkerboard();

        String referenceString = "   [A][B][C][D][E][F][G][H]\n" +
                "[1][ ][ ][ ][ ][ ][ ][ ][ ]\n" +
                "[2][ ][ ][ ][ ][ ][ ][ ][ ]\n" +
                "[3][ ][ ][ ][A][ ][ ][ ][ ]\n" +
                "[4][ ][ ][A][W][B][ ][ ][ ]\n" +
                "[5][ ][ ][ ][B][W][A][ ][ ]\n" +
                "[6][ ][ ][ ][ ][A][ ][ ][ ]\n" +
                "[7][ ][ ][ ][ ][ ][ ][ ][ ]\n" +
                "[8][ ][ ][ ][ ][ ][ ][ ][ ]\n";

        checkerboard.markAllowedPlacings(B);

        assertEquals(referenceString, checkerboard.toString());
    }

    @Test
    void constructorThrowsException() {
        int[][] checkerboardMatrix = {{0, 1, 2, -1, 10000, W, W, W},
                {N, N, B, W, W, W, W, W},
                {N, N, N, W, W, W, W, B},
                {N, N, W, W, W, W, W, N},
                {B, B, W, W, B, B, W, W},
                {N, W, B, W, N, N, W, N},
                {W, N, N, B, N, N, W, N},
                {N, N, N, N, B, N, N, N}};

        boolean throwedExc = false;

        try {
            Checkerboard checkerboard = new Checkerboard(checkerboardMatrix);
        } catch (Checkerboard.InvalidSquareValueException e) {
            throwedExc = true;
        }

        assertTrue(throwedExc);
    }

    @Test
    void placeThrowsException() {
        boolean throwedExc = false;
        Checkerboard checkerboard = new Checkerboard();

        try {
            checkerboard.place(2, 3, 0);
        } catch (Checkerboard.InvalidColorValueException e) {
            throwedExc = true;
        }

        assertTrue(throwedExc);
    }

    @Test
    void allowPlaceThrowsException() {
        boolean throwedExc = false;
        Checkerboard checkerboard = new Checkerboard();

        try {
            checkerboard.isPlaceAllowed(2, 3, 0);
        } catch (Checkerboard.InvalidColorValueException e) {
            throwedExc = true;
        }

        assertTrue(throwedExc);
    }

    @Test
    void existAllowedPlaceThrowsException() {
        boolean throwedExc = false;
        Checkerboard checkerboard = new Checkerboard();

        try {
            checkerboard.existAllowedPlace(0);
        } catch (Checkerboard.InvalidColorValueException e) {
            throwedExc = true;
        }

        assertTrue(throwedExc);
    }

    @Test
    void addAllowedDisksThrowsException() {
        boolean throwedExc = false;
        Checkerboard checkerboard = new Checkerboard();

        try {
            checkerboard.markAllowedPlacings(0);
        } catch (Checkerboard.InvalidColorValueException e) {
            throwedExc = true;
        }

        assertTrue(throwedExc);
    }
}