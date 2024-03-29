package units.sdm;

import java.util.Scanner;

public class ConsoleReversi implements View {

    protected Game game;

    @Override
    public void installLogic(Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        String title = "      ___           ___           ___           ___           ___           ___             ___         \n" +
                "     /  /\\         /  /\\         /  /\\         /  /\\         /  /\\         /  /\\           /__/\\        ___\n" +
                "    /  /  \\       /  /  \\       /  / /        /  /  \\       /  /  \\       /  /  \\          \\__\\ \\      /  /\\\n" +
                "   /  / /\\ \\     /  / /\\ \\     /  / /        /  / /\\ \\     /  / /\\ \\     /__/ /\\ \\         /  /  \\    /  / /\n" +
                "  /  /  \\ \\ \\   /  /  \\ \\ \\   /  / /        /  /  \\ \\ \\   /  /  \\ \\ \\   _\\_ \\ \\ \\ \\       /  / /\\/   /  / /\n" +
                " /__/ /\\ \\_\\ \\ /__/ /\\ \\ \\ \\ /__/ /   ___  /__/ /\\ \\ \\ \\ /__/ /\\ \\_\\ \\ /__/\\ \\ \\ \\ \\     /  / /--   /__/ /\n" +
                " \\__\\/-|  \\/ / \\  \\ \\ \\ \\_\\/ |  | |  /  /\\ \\  \\ \\ \\ \\_\\/ \\__\\/-|  \\/ / \\  \\ \\ \\ \\_\\/  __/  / /      \\__\\/\n" +
                "    |  | |  /   \\  \\ \\ \\ \\   |  | | /  / /  \\  \\ \\ \\ \\      |  | |  /   \\  \\ \\_\\ \\   /__/\\/ /     ___\n" +
                "    |  | |\\/     \\  \\ \\_\\/   |  | |/  / /    \\  \\ \\_\\/      |  | |\\/     \\  \\ \\/ /   \\  \\  /     /__/\\\n" +
                "    |__| |        \\  \\ \\     |__| |__/ /      \\  \\ \\        |__| |-       \\  \\  /     \\  \\ \\     \\__\\/\n" +
                "     \\__\\|         \\__\\/      \\__\\____/        \\__\\/         \\__\\|         \\__\\/       \\__\\/        \n";
        System.out.println(title);

        displayGameStart();
    }

    @Override
    public void displayGameStart() {
        Scanner scan = new Scanner(System.in);

        System.out.println("What's your name player Black?");
        String nameBlack;
        nameBlack = scan.next();
        game.setPlayerBlack(nameBlack);

        System.out.println("What's your name player White?");
        String nameWhite;
        nameWhite = scan.next();
        game.setPlayerWhite(nameWhite);

        System.out.println("Are you ready?");

        scan.next();

        game.turn();
    }

    @Override
    public void displayTurn() {
        displayCheckerboard();

        Scanner scan = new Scanner(System.in);

        System.out.println(game.getCurrentPlayerName() + ", make your move, specify row (1-8):");
        String row = scan.next();

        System.out.println("Specify column (a-h):");
        String column = scan.next();

        game.validateAndTryPlace(row, column);
    }

    public void displayCheckerboard() {
        AbstractCheckerboard checkerboard = game.getCheckerboard();

        System.out.print(checkerboard.toString());
    }

    @Override
    public void displayGameOver() {
        displayCheckerboard();
        System.out.println("\n" + game.getWinnerName() + " wins!");

        displayResults();
    }

    @Override
    public void displayDraw(){
        displayCheckerboard();
        System.out.println("\nThis is a Draw!");

        displayResults();
    }

    protected void displayResults() {
        AbstractCheckerboard checkerboard = game.getCheckerboard();
        System.out.println(game.getPlayerBlack() + " disks: " + checkerboard.getNumberOfBlacks());
        System.out.println(game.getPlayerWhite() + " disks: " + checkerboard.getNumberOfWhites());

        System.out.println("Press any key to terminate");
        Scanner scan = new Scanner(System.in);
        scan.next();
    }

    @Override
    public void displayNoMoves() {
        displayCheckerboard();
        System.out.println(game.getCurrentPlayerName() + " no moves available!");
        System.out.println("Press any key to skip the turn");
        Scanner scan = new Scanner(System.in);
        scan.next();
        game.nextTurn();
    }

    @Override
    public void displayNotAllowed() {
        Scanner scan = new Scanner(System.in);

        System.out.println("Invalid coordinates, specify new row (1-8):");
        String row = scan.next();

        System.out.println("Specify new column (a-h):");
        String column = scan.next();

        game.validateAndTryPlace(row, column);
    }
}
