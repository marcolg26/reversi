package units.sdm;

public class ReversiGame implements Game {
    private String playerWhite;
    private String playerBlack;

    private int colorTurn = 1;

    private Checkerboard checkerboard;

    private ReversiView view;

    public ReversiGame(ReversiView view) {
        this.playerBlack = "Black";
        this.playerWhite = "White";

        this.checkerboard = new Checkerboard();

        this.view = view;
    }

    public ReversiGame(ReversiView view, Checkerboard checkerboard) {
        this.playerBlack = "Black";
        this.playerWhite = "White";

        this.checkerboard = new Checkerboard(checkerboard.getMatrix());

        this.view = view;
    }

    @Override
    public void start() {
        view.show();
    }

    @Override
    public String getPlayerWhite() {
        return playerWhite;
    }

    @Override
    public String getPlayerBlack() {
        return playerBlack;
    }

    @Override
    public void setPlayerWhite(String name) {
        if (name.length() > 0)
            playerWhite = name;
    }

    @Override
    public void setPlayerBlack(String name) {
        if (name.length() > 0)
            playerBlack = name;
    }

    @Override
    public Checkerboard getCheckerboard() {
        return checkerboard;
    }

    @Override
    public void turn() {
        if (checkerboard.isGameover())
            if (isDraw())
                view.displayDraw();
            else
                view.displayGameOver();
        else {
            if (!checkerboard.existAllowedPlace(colorTurn)) {
                view.displayNoMoves();
                return;
            }
            checkerboard.markAllowedPlacings(colorTurn);
            view.displayTurn();
        }
    }

    @Override
    public String getCurrentPlayerName() {
        if (colorTurn == Checkerboard.B)
            return playerBlack;

        return playerWhite;
    }

    @Override
    public void validateAndTryPlace(String row, String column) {
        int rowIndex;
        int columnIndex;

        try {
            rowIndex = Integer.parseInt(row) - 1;
        } catch (NumberFormatException ex) {
            view.displayNotAllowed();
            return;
        }

        if (column.length() > 0) {
            columnIndex = column.toUpperCase().toCharArray()[0] - 65;
            tryPlace(rowIndex, columnIndex);
        }
        else
            view.displayNotAllowed();
    }

    @Override
    public void tryPlace(int x, int y) {
        if (x >= 0 && x < Checkerboard.SIZE && y >= 0 && y < Checkerboard.SIZE && checkerboard.isPlaceAllowed(x, y, colorTurn)) {
            checkerboard.place(x, y, colorTurn);
            nextTurn();
        } else
            view.displayNotAllowed();
    }

    @Override
    public void nextTurn() {
        colorTurn = -colorTurn;
        checkerboard.unmarkAllowedPlacings();
        turn();
    }

    @Override
    public boolean isDraw() {
        return checkerboard.getNumberOfBlacks() == checkerboard.getNumberOfWhites();
    }

    @Override
    public String getWinnerName() {
        if (checkerboard.getNumberOfBlacks() > checkerboard.getNumberOfWhites())
            return playerBlack;

        return playerWhite;
    }
}
