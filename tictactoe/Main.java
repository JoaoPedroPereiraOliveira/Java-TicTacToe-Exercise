package tictactoe;
import java.util.Scanner;

public class Main {
    public static void showBoard(char[][] game){
        System.out.println("---------");

        for(int y = 0; y < 3; y++) {
            System.out.print("| ");

            for(int x = 0; x < 3; x++) {
                if (game[x][y] != 0)
                    System.out.print(game[x][y] + " ");
                else
                    System.out.print("  ");
            }

            System.out.print("|");
            System.out.println();
        }

        System.out.println("---------");
    }

    public static void interact(char[][] game, int playNumb) {
        Scanner scanner = new Scanner(System.in);

        try {
            int xCord = scanner.nextInt();
            int yCord = scanner.nextInt();

            if((xCord < 1 || xCord > 3) || (yCord < 1 || yCord > 3)){
                System.out.println("Coordinates should be from 1 to 3!");
                interact(game, playNumb);
                return;
            } else if (game[yCord - 1][xCord - 1] == 0) {
                game[yCord - 1][xCord - 1] = playNumb % 2 == 0 ? 'O' : 'X';
            } else {
                System.out.println("This cell is occupied! Choose another one!");
                interact(game, playNumb);
                return;
            }

        } catch (RuntimeException e) {
            System.out.println("You should enter numbers!");
            interact(game, playNumb);
            return;
        }
    }

    public static String win(char[][] game, int playNumb){
        int count = 0;
        char winner = ' ';
        
        if (game[0][0] == game[1][1] && game[1][1] == game[2][2] && game[0][0] != 0) {
            count++;
            winner = game[0][0];
        }

        if (game[0][2] == game[1][1] && game[1][1] == game[2][0] && game[0][2] != 0) {
            count++;
            winner = game[0][2];
        }

        for (int y = 0; y < 3; y++) {
            if (game[0][y] == game[1][y] && game[1][y] == game[2][y] && game[0][y] != 0) {
                count++;
                winner = game[0][y];
            }

            if (game[y][0] == game[y][1] && game[y][1] == game[y][2] && game[y][0] != 0) {
                count++;
                winner = game[y][0];
            }
        }

        if (count == 1){
            return winner + " wins";
        } else if (playNumb == 9) {
            return "Draw";
        }

        return null;

    }

    public static void main(String[] args) {
        char[][] game = new char[3][3];
        int playNumb = 0;
        String message = null;

        do {
            showBoard(game);

            playNumb++;

            interact(game, playNumb);

            message = win(game, playNumb);

        } while (playNumb != 10 && message == null);

        showBoard(game);

        System.out.println(message);
    }
}
