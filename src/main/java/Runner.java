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

        boolean debug = true;

        // User interface:
        Scanner s = new Scanner(System.in);
        System.out.println("How big should the board be (it can not be 10+)");
        int len = Integer.parseInt(s.next());
        MineSweeper board = new MineSweeper(10, len, len);
        boolean running = true;
        while (running) {
            if (board.isLost()) {
                System.out.println("You Lost!");
                board.printAll();
                running=false;
                break;
            }
            board.print();
            System.out.println("what move do you want to do ('q' or 'R rowcolumn' or 'F rowcolumn')?");
            String input = s.nextLine();
            System.out.println("(your input is "+input+")");
            if (input.equals("q")) {
                running = false;
                break;
            } else if (input.length()==4) {
                if (input.substring(0,2).equals("F ")) {// flag(row, col)
                    int i1 = Integer.parseInt(input.substring(2,3));
                    int i2 = Integer.parseInt(input.substring(3,4));
                    if (debug) {
                        System.out.println("index is ["+(board.len()-i1)+"]["+(i2-1)+"]");
                    }
                    board.flag(board.len()-i1, i2-1);
                } else if (input.substring(0,2).equals("R ")) {// (row, col)
                    int i1 = Integer.parseInt(input.substring(2,3));
                    int i2 = Integer.parseInt(input.substring(3,4));
                    if (debug) {
                        System.out.println("index is ["+(board.len()-i1)+"]["+(i2-1)+"]");
                    }
                    board.reveal(board.len()-i1, i2-1);
                } else {
                    System.out.println("Invalid input");
                }
            } else {
                System.out.println("Invalid input");
            }
        }
        System.out.println("The program has ended");
        s.close();
    }
}
