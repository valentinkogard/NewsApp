package at.ac.fhcampuswien;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    @Test
    public void myFirstTest(){
        App myApp = new App();

        String actual = myApp.welcomeMessage();
        String expected = "Hello World!";

        assertEquals(expected, actual);
    }
}
