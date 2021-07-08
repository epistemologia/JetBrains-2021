package tictactoe;
import java.util.Scanner;

import static java.lang.String.copyValueOf;

public class Main {

    public static String result = "";
    public static char[][] c = new char[3][3];

    private static boolean Analyze() {
        boolean ongoing = true;
        int emptyCells = 0;
        int xCells = 0;
        int oCells = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                emptyCells = c[i][j] == ' ' ? emptyCells + 1 : emptyCells;
                xCells = c[i][j] == 'X' ? xCells + 1 : xCells;
                oCells = c[i][j] == 'O' ? oCells + 1 : oCells;
            }
        }
        
        int xWins = 0;
        int oWins = 0;

        for (int i = 0; i < 3; i++) {
            if (c[i][0] == c[i][1] && c[i][1] == c[i][2]) {
                if (c[i][0] == 'O') {
                    oWins++;
                } else if (c[i][0] == 'X') {
                    xWins++;
                }
            }
            if (c[0][i] == c[1][i] && c[1][i] == c[2][i]) {
                if (c[0][i] == 'O') {
                    oWins++;
                } else if (c[0][i] == 'X') {
                    xWins++;
                }
            }
        }
        if (c[0][0] == c[1][1] && c[1][1] == c[2][2]) {
            if (c[0][0] == 'O') {
                oWins++;
            } else if (c[0][0] == 'X') {
                xWins++;
            }
        }
        if (c[2][0] == c[1][1] && c[1][1] == c[0][2]) {
            if (c[2][0] == 'O') {
                oWins++;
            } else if (c[2][0] == 'X') {
                xWins++;
            }
        }
        
        result = "";        
        if (oWins + xWins > 1) {
            result = "Impossible";
            ongoing = false;
        }
        if (oWins == 1 && xWins == 0) {
            result = "O wins";
            ongoing = false;
        }
        if (oWins == 0 && xWins == 1) {
            result = "X wins";
            ongoing = false;
        }
        if (oWins == 0 && xWins == 0 && emptyCells == 0) {
            result = "Draw";
            ongoing = false;
        }
        if (emptyCells > 0 && oWins == 0 && xWins == 0) {
            result = "Game not finished";
        }
        if (oCells > xCells + 1) {
            result = "Impossible";
            ongoing = false;
        }
        if (xCells > oCells + 1) {
            result = "Impossible";
            ongoing = false;
        }
        System.out.println(xCells + " "+oCells + " "+emptyCells + " "+xWins + " "+oWins + " "+ result);
        if (!ongoing) {
            System.out.println(result);
        }
/*
        System.out.println ("emptyCells = "+emptyCells);
        System.out.println ("x Wins = "+xWins);
        System.out.println ("o Wins = "+oWins);
        System.out.println ("x cells = "+xCells);
        System.out.println ("o cells = "+oCells);
*/
        return ongoing;
    }

    public static void preenche(String s) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                c[i][j] = s.charAt(3 * i + j);
                c[i][j] = c[i][j] == '_' ? ' ' : c[i][j];
            }
        }
    }

    public static void printGrid(char[][] c) {
        System.out.println("---------");
        System.out.print("| ");
        System.out.print(c[0][0] + " " + c[0][1] + " " + c[0][2]);
        System.out.println(" |");
        System.out.print("| ");
        System.out.print(c[1][0] + " " + c[1][1] + " " + c[1][2]);
        System.out.println(" |");
        System.out.print("| ");
        System.out.print(c[2][0] + " " + c[2][1] + " " + c[2][2]);
        System.out.println(" |");
        System.out.println("---------");
    }
    
    public static void limpa(char[][] c) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                c[i][j] = ' ';
            }
        }
    }

    public static void main(String[] args) {
        // write your code here

        Scanner scanner = new Scanner(System.in);
        boolean emAndamento = true;
        int line;
        int col;
        int player;
        final String constante = "XO";

        limpa(c);
        printGrid(c);
        player = 0;
        while (emAndamento) {
            System.out.print("Enter the coordinates: ");
            boolean valid = false;
            while (!valid) {
                String entry = scanner.nextLine();
                line = entry.charAt(0) - '1';
                col = entry.charAt(2) - '1';
			    if (line > 2 || col > 2 || line < 0 || col < 0) {
                    System.out.println("Coordinates should be from 1 to 3!");
                    continue;
	    		}
                if (entry.charAt(1) != ' ') {
                    System.out.println("You should enter numbers!");
                    continue;
                }    
 //               System.out.println(line);
 //               System.out.println(col);
	    		if (c[line][col] == 'X') {
		    		System.out.println("This cell is occupied! Choose another one!");
			    } else if (c[line][col] == 'O')
    			{
	    			System.out.println("This cell is occupied! Choose another one!");
		    	} else {
			    	c[line][col] = constante.charAt(player);
				    valid = true;
    			}
            }
            printGrid(c);
            emAndamento = Analyze();
//            System.out.println(emAndamento);
            player = 1 - player;
        }
    }
}
