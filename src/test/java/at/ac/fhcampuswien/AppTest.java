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
    public void setArticlesTest1() throws NoSuchFieldException {
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
    @DisplayName("setArticlesTest2")
    public void setArticlesTest2() throws NoSuchFieldException {
        List<Article> refList = null;

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
    public void getTopHeadlinesAustriaTest1() throws IOException {
        List<Article> refList = new ArrayList<>();
        refList.add(new Article("Article", "Author"));
        AppController appController = new AppController();
        appController.setArticles(refList);

        assertEquals(refList, appController.getArticles());
    }

    @Test
    @DisplayName("getTopHeadlinesAustriaTest2")
    public void getTopHeadlinesAustriaTest2() throws IOException {
        List<Article> refList = null;
        AppController appController = new AppController();
        appController.setArticles(refList);

        assertEquals(null, appController.getArticles());
    }

    @Test
    @DisplayName("getAllNewsBitcoinTest1")
    public void getAllNewsBitcoinTest1() throws IOException {
        List<Article> refList = new ArrayList<>();
        refList.add(new Article("Author0", "Article0 bitcoin"));
        refList.add(new Article("Author1", "Article1"));
        refList.add(new Article("Author2", "Article2"));
        refList.add(new Article("Author3", "Article3 bitcoin"));


        List<Article> onlyBitcoinNews = new ArrayList<>();
        onlyBitcoinNews.add(new Article("Author0", "Article0 bitcoin"));
        onlyBitcoinNews.add(new Article("Author3", "Article3 bitcoin"));
        NewsResponse nr = new NewsResponse("ok", 2, onlyBitcoinNews);

        AppController appController = new AppController();
        appController.setArticles(refList);

        List<Article> bitcoinList = nr.getArticles();

        assertEquals(bitcoinList, onlyBitcoinNews);
    }

    @Test
    @DisplayName("BitcoinNewsTest2")
    public void getAllNewsBitcoinTest2(){
        List<Article> refList = new ArrayList<>();
        refList.add(new Article("Author1", "Article1"));
        refList.add(new Article("Author2", "Article2"));

        List<Article> onlyBitcoinNews = new ArrayList<>();
        //don't add objects to onlyBitcoinNews
        NewsResponse nr = new NewsResponse("ok", 2, onlyBitcoinNews);

        AppController appController = new AppController();
        appController.setArticles(refList);

        List<Article> bitcoinList = nr.getArticles();

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

    @Test
    @DisplayName("FilterListTest2")
    public void filterListTest2(){
        String wordToSearch = "abc";

        List<Article> refList = new ArrayList<>();
        refList.add(new Article("Author0", "Article0"));
        refList.add(new Article("Author1", "Article1 abc test"));
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

    /**
     *
     *
     * TESTING STREAMS
     *
     *
     */

    @Test
    @DisplayName("sourceWithMostArticlesTest")
    public void sourceWithMostArticlesTest() {

        List<Article> refList = new ArrayList<>();

        Source commonSource = new Source("STANDARD");
        Source otherSource = new Source("other");

        refList.add(new Article(commonSource));
        refList.add(new Article(commonSource));
        refList.add(new Article(otherSource));
        refList.add(new Article(commonSource));
        refList.add(new Article(otherSource));
        refList.add(new Article(commonSource));

        String receivedSolution = new StreamFilters().sourceWithMostArticles(refList);

        assertEquals(commonSource.getName(), receivedSolution);
    }

    @Test
    @DisplayName("authorWithLongestNameTest")
    public void authorWithLongestNameTest() {

        List<Article> refList = new ArrayList<>();

        String longestName = "Maximilian Mustermann";

        refList.add(new Article(longestName, "title"));
        refList.add(new Article("Author100", "title"));
        refList.add(new Article("Author2", "title"));
        refList.add(new Article("Author30000000", "title"));

        String receivedSolution = new StreamFilters().authorWithLongestName(refList);

        assertEquals(longestName, receivedSolution);
    }

    @Test
    @DisplayName("numberOfArticlesNytTest")
    public void numberOfArticlesNytTest() {

        List<Article> refList = new ArrayList<>();

        Source nyt = new Source("New York Times");
        Source something = new Source("something");

        refList.add(new Article(nyt));
        refList.add(new Article(something));
        refList.add(new Article(nyt));
        refList.add(new Article(nyt));
        refList.add(new Article(something));
        refList.add(new Article(nyt));
        refList.add(new Article(something)); //4 articles from Reuters

        long receivedSolution = new StreamFilters().numberOfArticlesNyt(refList);

        assertEquals(4, receivedSolution);
    }

    @Test
    @DisplayName("articlesWithHeadlineSub15CharsListTest")
    public void articlesWithHeadlineSub15CharsListTest() {

        List<Article> refList = new ArrayList<>();

        String headlineSub15 = "Random";
        String headlineOver15 = "This is a random headline written just to test a function I think we are long enough";

        Article articleSub15 = new Article("Author", headlineSub15);
        Article articleSub152 = new Article("Author", headlineSub15);

        refList.add(articleSub15);
        refList.add(new Article("Author", headlineOver15));
        refList.add(articleSub152);
        refList.add(new Article("Author", headlineOver15));

        List<Article> expectedList = new ArrayList<>();

        expectedList.add(articleSub15);
        expectedList.add(articleSub152);

        List<Article> receivedSolution = new StreamFilters().articlesWithHeadlineSub15CharsList(refList);

        assertEquals(expectedList, receivedSolution);
    }

    @Test
    @DisplayName("articlesWithHeadlineSub15CharsListTest2")
    public void articlesWithHeadlineSub15CharsListTest2() {

        List<Article> refList = new ArrayList<>();

        String headlineOver15 = "This is a random headline written just to test a function I think we are long enough";

        Article articleOver15 = new Article("Author", headlineOver15);

        refList.add(articleOver15);
        refList.add(articleOver15);
        refList.add(articleOver15);
        refList.add(articleOver15);

        List<Article> expectedList = new ArrayList<>();

        List<Article> receivedSolution = new StreamFilters().articlesWithHeadlineSub15CharsList(refList);

        assertEquals(expectedList, receivedSolution);
    }

    @Test
    @DisplayName("articlesSortedByLengthThenAlphabeticallytest")
    public void articlesSortedByLengthThenAlphabeticallyTest() {

        List<Article> refList = new ArrayList<>();

        Article article1 = new Article("Author1", "Title1", "Short");
        Article article2 = new Article("Author2", "Title2", "Average");
        Article article3 = new Article("Author3", "Title3", "Very Long Description");

        refList.add(article2);
        refList.add(article3);
        refList.add(article1);

        List<Article> expectedList = new ArrayList<>();

        expectedList.add(article1);
        expectedList.add(article2);
        expectedList.add(article3);

        List<Article> receivedSolution = new StreamFilters().articlesSortedByLengthThenAlphabetically(refList);

        assertEquals(expectedList, receivedSolution);
    }

    @Test
    @DisplayName("articlesSortedByLengthThenAlphabeticallytest2")
    public void articlesSortedByLengthThenAlphabeticallyTest2() {

        List<Article> refList = new ArrayList<>();

        Article article1 = new Article("Author1", "Title1", "Short");
        Article article2 = new Article("Author2", "Title2", "Average");
        Article article3 = new Article("Author3", "Title3", "Very Long Description");
        Article article4 = new Article("Author4", "Title4", "Aery Long Description");
        Article article5 = new Article("Author5", "Title5", "Bery Long Description");

        refList.add(article4);
        refList.add(article2);
        refList.add(article3);
        refList.add(article1);
        refList.add(article5);

        List<Article> expectedList = new ArrayList<>();

        expectedList.add(article1);
        expectedList.add(article2);
        expectedList.add(article4);
        expectedList.add(article5);
        expectedList.add(article3);

        List<Article> receivedSolution = new StreamFilters().articlesSortedByLengthThenAlphabetically(refList);

        assertEquals(expectedList, receivedSolution);
    }













}