package at.ac.fhcampuswien;

import org.junit.jupiter.api.DisplayName;
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

    @Test
    @DisplayName("displayMenuTest")
    public void displayMenuTest() {

        Menu menu = new Menu();

        String actual = menu.displayMenu();
        String expected = "****************************** \n" +
                "*  Welcome to NewsApp  * \n" +
                "****************************** \n" +
                "Enter what you want to do: \n"+
                "a: Get top headlines Austria \n"+
                "b: Get all news about bitcoin \n"+
                "y: Count articles \n"+
                "q: quit program\n";

        assertEquals(expected, actual);
    }
}
