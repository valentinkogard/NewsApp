package at.ac.fhcampuswien;

public class App {

    public String welcomeMessage(){
        return "Hello World!";
    }

    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.start();
    }
}
