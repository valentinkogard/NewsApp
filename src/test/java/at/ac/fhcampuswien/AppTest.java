package at.ac.fhcampuswien;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    private PrintStream originalOut;
    private InputStream originalIn;
    private ByteArrayOutputStream bos;
    private PrintStream ps;

    @BeforeEach
    public void setupStreams() throws IOException {
        originalOut = System.out;
        originalIn = System.in;

        bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));

        PipedOutputStream pos = new PipedOutputStream();
        PipedInputStream pis = new PipedInputStream(pos);
        System.setIn(pis);
        ps = new PrintStream(pos, true);
    }

    @Test
    public void myFirstTest(){
        App myApp = new App();

        String actual = myApp.welcomeMessage();
        String expected = "Hello World!";

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("displayMenuTest")
    public void displayMenuTest() {
        Menu myMenu = new Menu();
        myMenu.start();

        String expected = "****************************** \n" +
                "*  Welcome to NewsApp  * \n" +
                "****************************** \n" +
                "Enter what you want to do: \n"+
                "a: Get top headlines Austria \n"+
                "b: Get all news about bitcoin \n"+
                "y: Count articles \n"+
                "q: quit program\n";

        assertEquals(expected, bos.toString());
    }
}
