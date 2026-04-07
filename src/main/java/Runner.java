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

        // User interface:
        Scanner s = new Scanner(System.in);
        System.out.println("How big should the board be (it can not be 10+)");
        int len = s.nextInt();
        MineSweeper board = new MineSweeper(10, len, len);
        boolean running = true;
        while (running) {
            board.print();
            System.out.println("what move do you want to make ('q' or 'R row column' or 'F row column')?");
            String input = s.nextLine();
            System.out.println("(your input is "+input+")");
            if (input.equals("q")) {
                running = false;
                break;
            } else if (input.substring(0,2).equals("F ")) {// flag(row, col)
                int i1 = Integer.parseInt(input.substring(2,3));
                int i2 = Integer.parseInt(input.substring(4,5));
                board.flag(i1, i2);
            } else if (input.substring(0,2).equals("R ")) {// (row, col)
                int i1 = Integer.parseInt(input.substring(2,3));
                int i2 = Integer.parseInt(input.substring(4,5));
                board.reveal(i1, i2);
            } else {
                System.out.println("Invalid input");
            }
        }
        System.out.println("The program has ended");
        s.close();
    }
}
