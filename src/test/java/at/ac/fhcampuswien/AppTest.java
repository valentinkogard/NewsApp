package at.ac.fhcampuswien;

import org.junit.jupiter.api.*;
import java.io.*;
import java.lang.reflect.Field;
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
    @DisplayName("setArticlesTest1")
    void setArticlesTest1() throws NoSuchFieldException {
        List<Article> refList = new ArrayList<>();
        refList.add(new Article("Author0", "Article0"));
        refList.add(new Article("Author1", "Article1"));
        refList.add(new Article("Author2", "Article2"));
        refList.add(new Article("Author3", "Article3"));

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
    @DisplayName("getArticlesCountTest1")
    public void getArticleCountTest() {
        List<Article> refList = new ArrayList<>();
        AppController appController = new AppController();
        appController.setArticles(refList);

        for (int i = 0; i <= 10; i++) {
            assertEquals(refList.size(), appController.getArticleCount());
            refList.add(new Article("Article", "Author"));
            appController.setArticles(refList);
        }
    }

    @Test
    @DisplayName("getArticleCountTest2")
    public void getArticleCountTest2() {
        List<Article> refList = null;
        AppController appController = new AppController();
        appController.setArticles(refList);

        assertEquals(0, appController.getArticleCount());
    }

    @Test
    @DisplayName("getTopHeadlinesAustriaTest1")
    public void getTopHeadlinesAustriaTest1(){
        List<Article> refList = new ArrayList<>();
        refList.add(new Article("Article", "Author"));
        AppController appController = new AppController();
        appController.setArticles(refList);

        assertEquals(refList, appController.getTopHeadlinesAustria());
    }

    @Test
    @DisplayName("getTopHeadlinesAustriaTest2")
    public void getTopHeadlinesAustriaTest2(){
        List<Article> refList = null;
        AppController appController = new AppController();
        appController.setArticles(refList);

        assertEquals(new ArrayList<Article>(), appController.getTopHeadlinesAustria());
    }

    @Test
    @DisplayName("getAllNewsBitcoinTest1")
    public void getAllNewsBitcoinTest1(){
        List<Article> refList = new ArrayList<>();
        refList.add(new Article("Author0", "Article0 bitcoin"));
        refList.add(new Article("Author1", "Article1"));
        refList.add(new Article("Author2", "Article2"));
        refList.add(new Article("Author3", "Article3 bitcoin"));

        List<Article> onlyBitcoinNews = new ArrayList<>();
        for(Article i : refList){
            if(i.getTitle().toLowerCase().contains("bitcoin")){
                onlyBitcoinNews.add(i);
            }
        }

        AppController appController = new AppController();
        appController.setArticles(refList);

        List<Article> bitcoinList = appController.getAllNewsBitcoin();

        assertEquals(bitcoinList, onlyBitcoinNews);
    }

    @Test
    @DisplayName("BitcoinNewsTest2")
    public void getAllNewsBitcoinTest2(){
        List<Article> refList = new ArrayList<>();
        refList.add(new Article("Author1", "Article1"));
        refList.add(new Article("Author2", "Article2"));

        List<Article> onlyBitcoinNews = new ArrayList<>();
        for(Article i : refList){
            if(i.getTitle().toLowerCase().contains("bitcoin")){
                onlyBitcoinNews.add(i);
            }
        }

        AppController appController = new AppController();
        appController.setArticles(refList);

        List<Article> bitcoinList = appController.getAllNewsBitcoin();

        assertEquals(bitcoinList, onlyBitcoinNews);
    }

    @Test
    @DisplayName("FilterListTest")
    public void filterListTest(){
        String wordToSearch = "test";

        List<Article> refList = new ArrayList<>();
        refList.add(new Article("Author0", "Article0 test"));
        refList.add(new Article("Author1", "Article1"));
        refList.add(new Article("Author2", "Article2"));
        refList.add(new Article("Author3", "Article3 test"));

        List<Article> refListFiltered = new ArrayList<>();
        for(Article i : refList){
            if(i.getTitle().toLowerCase().contains(wordToSearch)){
                refListFiltered.add(i);
            }
        }

        AppController appController = new AppController();
        appController.setArticles(refList);

        List<Article> filteredList = AppController.filterList(wordToSearch, refList);

        assertEquals(refListFiltered, filteredList);
    }
}