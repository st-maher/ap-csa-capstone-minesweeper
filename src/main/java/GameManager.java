import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.BiConsumer;

/**
 * Manage user-input
 *
 */

public class GameManager {
    public static int getLen(Scanner s) {
        System.out.println("How big should the board be (it can not be 10+)");
        int len = Integer.parseInt(s.nextLine());
        if (len >= 10) {
            s.close();
            throw new IllegalArgumentException("I said that the length can not be 10+");
        }
        return len;
    }
    public static int getBombs(Scanner s) {
        System.out.println("How many bombs do you want on the board?");
        return Integer.parseInt(s.nextLine());
    }
    public static String getName(Scanner s) {
        System.out.println("What is your name (and also your adress)?");
        return s.nextLine();
    }
    public static void mark(BiConsumer<Integer, Integer> action, String input, MineSweeper board) {
        int i1 = Integer.parseInt(input.substring(2, 3));
        int i2 = Integer.parseInt(input.substring(3, 4));
        if ((0 <= i1 && i1 < board.getRows()) && (0 <= i2 && i2 < board.getRows())) {
            action.accept(i1, i2);
        } else {
            System.out.println("That is out of bounds.");
        }
    }
    public static void executeCommand() {
        // User interface:
        Scanner s = new Scanner(System.in);
        var history = new ArrayList<String>();
        // get len, bombs, and name
        int len = getLen(s);
        int bombs = getBombs(s);
        String name = getName(s);
        // game loop
        MineSweeper board = new MineSweeper(bombs, len, len);
        boolean running = true;
        printHelpMenu();
        while (running) {
            // test if you won or lost
            if (board.isLost()) {
                System.out.println(name.toLowerCase() + ", you lost!"); //delete toLowerCase() (although teacher want method)
                board.printAll();
                System.out.println("Your input history is:");
                for (String ele: history) {System.out.println(ele);}
                running = false;
                break;
            } else if (board.isWon()) {
                System.out.println(name.toUpperCase() + ", you won!");//delete toUpperCase() (although teacher want method)
                board.printAll();
                System.out.println("Your input history is:");
                for (String ele: history) {System.out.println(ele);}
                running = false;
                break;
            }
            
            // gets user input
            board.print();
            System.out.println("what move do you want to do ('q' or 'R rowcolumn' or 'F rowcolumn' (also do not use '?'))?");
            String input = s.nextLine();
            history.add(input);
            System.out.println("(your input is " + input + ")");
            // event handler
            if (input.equals("q")) { // input: q
                running = false;
                break;
            } else if (input.length() == 4) { // input: R xx, F xx, help
                if (input.substring(0, 2).equals("F ")) {// //input: F xx
                    mark(board::flag, input, board);
                } else if (input.substring(0, 2).equals("R ")) {// input: R xx
                    mark(board::reveal, input, board);
                } else if (input.equals("help")) {// input: help
                    board.help();
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
