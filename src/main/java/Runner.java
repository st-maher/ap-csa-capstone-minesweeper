import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main entry point for the Minesweeper game.
 *
 */
public class Runner {
    public static void main(String[] args) {
        // TODO: implement a move bomb function
        // ToDo: make help menu (?), documentation

        // User interface:
        Scanner s = new Scanner(System.in);
        var history = new ArrayList<String>();
        // get len, bombs, and name
        System.out.println("How big should the board be (it can not be 10+)");
        int len = Integer.parseInt(s.nextLine());
        if (len >= 10) {
            s.close();
            throw new IllegalArgumentException("I said that the length can not be 10+");
        }
        System.out.println("How many bombs do you want on the board?");
        int bombs = Integer.parseInt(s.nextLine());
        System.out.println("What is your name (and also your adress)?");
        String name = s.nextLine();

        // game loop
        MineSweeper board = new MineSweeper(bombs, len, len);
        boolean running = true;
        printHelpMenu();
        while (running) {
            // test if you won or lost
            if (board.isLost()) {
                System.out.println(name + ", you lost!");
                board.printAll();
                System.out.println("Your input history is " + history);
                running = false;
                break;
            } else if (board.isWon()) {
                System.out.println(name + ", you won!");
                board.printAll();
                System.out.println("Your input history is " + history);
                running = false;
                break;
            }
            
            // gets user input
            board.print();
            System.out.println("what move do you want to do ('q' or 'R rowcolumn' or 'F rowcolumn')?");
            String input = s.nextLine();
            history.add(input);
            System.out.println("(your input is " + input + ")");
            // event handler
            if (input.equals("q")) { // input: q
                running = false;
                break;
            } else if (input.length() == 4) { // input: R xx, F xx, help
                if (input.substring(0, 2).equals("F ")) {// //input: F xx
                    int i1 = Integer.parseInt(input.substring(2, 3));
                    int i2 = Integer.parseInt(input.substring(3, 4));
                    if ((0 <= i1 && i1 < len) && (0 <= i2 && i2 < len)) {
                        board.flag(i1, i2);
                    } else {
                        System.out.println("That is out of bounds.");
                    }
                } else if (input.substring(0, 2).equals("R ")) {// input: R xx
                    int i1 = Integer.parseInt(input.substring(2, 3));
                    int i2 = Integer.parseInt(input.substring(3, 4));
                    if ((0 <= i1 && i1 < len) && (0 <= i2 && i2 < len)) {
                        board.reveal(i1, i2);
                    } else {
                        System.out.println("That is out of bounds.");
                    }
                } else if (input.equals("help")) {// input: help
                    printHelpMenu();
                } else {
                    System.out.println("Invalid input");
                }
            } else if (input.equals("?")) {// input: ?
                printHelpMenu();
            } else {
                System.out.println("Invalid input");
            }
        }
        System.out.println("The program has ended");
        s.close();
    }

    /**
     * Prints the help menu for the game.
     */
    public static void printHelpMenu() {
        System.out.println("Help menu: uhh do you know how to play minesweeper?");
    }
}
