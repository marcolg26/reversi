import org.junit.jupiter.api.Test;
import units.sdm.ConsoleReversi;
import units.sdm.Grid;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestConsoleReversi {
    @Test
    void consoleOutput() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        ConsoleReversi view = new ConsoleReversi();
        view.show(new Grid());

        String expectedOutput = (char) 27 + "[4m  |A|B|C|D|E|F|G|H" + (char) 27 + "[0m|\n" +
                                (char) 27 + "[4m1 | | | | | | | | " + (char) 27 + "[0m|\n" +
                                (char) 27 + "[4m2 | | | | | | | | " + (char) 27 + "[0m|\n" +
                                (char) 27 + "[4m3 | | | | | | | | " + (char) 27 + "[0m|\n" +
                                (char) 27 + "[4m4 | | | |o|\u001B[31mo\u001B[0m" + (char) 27 + "[4m| | | " + (char) 27 + "[0m|\n" +
                                (char) 27 + "[4m5 | | | |\u001B[31mo\u001B[0m" + (char) 27 + "[4m|o| | | " + (char) 27 + "[0m|\n" +
                                (char) 27 + "[4m6 | | | | | | | | " + (char) 27 + "[0m|\n" +
                                (char) 27 + "[4m7 | | | | | | | | " + (char) 27 + "[0m|\n" +
                                (char) 27 + "[4m8 | | | | | | | | " + (char) 27 + "[0m|\n";

        assertEquals(expectedOutput, outputStream.toString());
    }
}
