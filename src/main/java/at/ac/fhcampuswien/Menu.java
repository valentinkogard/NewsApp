package at.ac.fhcampuswien;

import java.util.Scanner;

public class Menu {


    private AppController controller;
    private static final String INVALID_INPUT_MESSAGE = "Invalid input! Please enter an existing option!";
    private static final String EXIT_MESSAGE = "Bye bye!";

    public void start() {
        Scanner scanner = new Scanner(System.in);
        while(true){
            printMenu();

            String input = scanner.next();
            handleInput(input.toLowerCase());
        }
    }

    private void handleInput(String input) {
        switch (input.charAt(0)) {
            case 'y':

                break;
            case 'q':
                printExitMessage();
                System.exit(0);
                break;
            default:
                printInvalidInputMessage();
        }
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
