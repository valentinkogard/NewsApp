package at.ac.fhcampuswien;

import org.junit.jupiter.api.*;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    private PrintStream originalOut;
    private InputStream originalIn;
    private ByteArrayOutputStream bos;
    private PrintStream ps;

    @BeforeAll
    public static void init(){
        System.out.println("Testing NewsApp_PROG2_Gr2_SubGr6");
    }

    @AfterAll
    public static void finish(){
        System.out.println("Finished Testing NewsApp_PROG2_Gr2_SubGr6");
    }

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

    @AfterEach
    public void tearDownStreams() {
        // undo the binding in System
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    @Test
    @DisplayName("myFirstTest")
    void myFirstTest(){
        App myApp = new App();

        String actual = myApp.welcomeMessage();
        String expected = "Hello World!";

        assertEquals(expected, actual);
    }

    /*
    @Test
    void welcomeMsg() throws NoSuchMethodException {
        Method m = Menu.class.getMethod("printMenu");

        String shouldMsg =
                "****************************** \n" +
                "*  Welcome to NewsApp  * \n" +
                "****************************** \n" +
                "Enter what you want to do: \n"+
                "a: Get top headlines Austria \n"+
                "b: Get all news about bitcoin \n"+
                "y: Count articles \n"+
                "q: quit program\n";

        String actualInput = bos.toString();
        assertEquals(shouldMsg, actualInput);
    }
    */
    /*
    @Test
    void inputTest(){
        App myApp = new App();
        String invalidInput = "Invalid input! Please enter an existing option!";
        ps.println("k");
        String actualInput = bos.toString();
        assertEquals(invalidInput, actualInput);
    }*/

    @Test
    void setArticlesTest() throws NoSuchFieldException {
        List<Article> refList = new ArrayList<>();
        refList.add(new Article("Article0", "Author0"));
        refList.add(new Article("Article1", "Author1"));
        refList.add(new Article("Article2", "Author2"));
        refList.add(new Article("Article3", "Author3"));

        AppController appController = new AppController();
        appController.setArticles(refList);

        Field field = AppController.class.getDeclaredField("articles");
        field.setAccessible(true);
        List<Article> actualField = null;
        try {
            actualField = (List<Article>) field.get(appController);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        assertEquals(actualField, refList);
    }

    @Test
    void getArticleCountTest(){

    }

    @Test
    void getTopHeadlinesAustriaTest(){

    }

    @Test
    void getAllNewsBitcoinTest(){

    }

    @Test
    void filterListTest(){

    }


}
