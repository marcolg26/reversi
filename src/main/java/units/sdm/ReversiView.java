package units.sdm;

public interface ReversiView {

    void installLogic(Game game);

    void show();

    void displayTurn();

    void displayGameOver();

    void displayDraw();

    void displayNoMoves();

    void displayNotAllowed();
}

