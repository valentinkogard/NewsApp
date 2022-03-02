package at.ac.fhcampuswien;

public class App {

    public String welcomeMessage(){
        return "Hello World!";
    }

    public String displayMenu(){

        return "****************************** \n" +
                "*  Welcome to NewsApp  * \n" +
                "****************************** \n" +
                "Enter what you want to do: \n"+
                "a: Get top headlines Austria \n"+
                "b: Get all news about bitcoin \n"+
                "y: Count articles \n"+
                "q: quit program\n";
    }

    public static void main(String[] args) {

    }
}
