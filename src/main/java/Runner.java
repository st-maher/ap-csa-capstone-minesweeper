import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main entry point for the Minesweeper game.
 *
 * This is your starting point. Build your game by adding classes
 * and implementing the game logic as described in your project handout.
 */
public class Runner {
    public static void main(String[] args) {
        // TODO: implement a move bomb function
        // ToDo: test input history, get player name, get difficulty, make help menu (?), documentation


        // User interface:
        Scanner s = new Scanner(System.in);
        var history = new ArrayList<String>();
        var helpMenu = "Help menu: ";
        //get len and name
        System.out.println("How big should the board be (it can not be 10+)");
        int len = Integer.parseInt(s.nextLine());
        if (len>=10) {s.close(); throw new IllegalArgumentException("I said that the length can not be 10+");}
        System.out.println("What is your name (and also your adress)?");
        String name = s.nextLine();
        //game loop
        MineSweeper board = new MineSweeper(10, len, len);
        boolean running = true;
        while (running) {
            if (board.isLost()) {
                System.out.println(name+", you lost!");
                board.printAll();
                System.out.println("Your input history is "+history);
                running=false;
                break;
            } else if (board.isWon()) {
                System.out.println(name+", you won!");
                board.printAll();
                System.out.println("Your input history is "+history);
                running=false;
                break;
            }
            board.print();
            System.out.println("what move do you want to do ('q' or 'R rowcolumn' or 'F rowcolumn')?");
            String input = s.nextLine();
            history.add(input);
            System.out.println("(your input is "+input+")");
            if (input.equals("q")) {
                running = false;
                break;
            } else if (input.length()==4) {
                if (input.substring(0,2).equals("F ")) {// flag(row, col)
                    int i1 = Integer.parseInt(input.substring(2,3));
                    int i2 = Integer.parseInt(input.substring(3,4));
                    if ((0<=i1 && i1<len) && (0<=i2 && i2<len)) {
                        System.out.println("That is out of bounds.");
                    } else {
                        board.flag(i1, i2);
                    }
                } else if (input.substring(0,2).equals("R ")) {// (row, col)
                    int i1 = Integer.parseInt(input.substring(2,3));
                    int i2 = Integer.parseInt(input.substring(3,4));
                    if ((0<=i1 && i1<len) && (0<=i2 && i2<len)) {
                        System.out.println("That is out of bounds.");
                    } else {
                        board.reveal(i1, i2);
                    }
                } else if (input.equals("help")) {
                    board.help();
                } else {
                    System.out.println("Invalid input");
                }
            } else if (input.equals("?")) {
                System.out.println("help menu: "+helpMenu);
            } else {
                System.out.println("Invalid input");
            }
        }
        System.out.println("The program has ended");
        s.close();
    }        
}
