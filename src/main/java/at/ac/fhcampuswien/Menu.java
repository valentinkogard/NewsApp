package at.ac.fhcampuswien;

public class Menu {


    private AppController controller;
    private static final String INVALID_INPUT_MESSAGE = "";
    private static final String EXIT_MESSAGE = "";

    public void start() {
        printMenu();
    }

    private void handleInput(String input) {

    }

    private void getArticleCount(AppController ctrl) {

    }

    private void getTopHeadlinesAustria(AppController ctrl) {

    }

    private static void printExitMessage() {
        System.out.println(EXIT_MESSAGE);
    }

    private static void printInvalidInputMessage() {
        System.out.println(INVALID_INPUT_MESSAGE);
    }


    private static void printMenu(){

        System.out.print("****************************** \n" +
                "*  Welcome to NewsApp  * \n" +
                "****************************** \n" +
                "Enter what you want to do: \n"+
                "a: Get top headlines Austria \n"+
                "b: Get all news about bitcoin \n"+
                "y: Count articles \n"+
                "q: quit program\n");
    }
}
