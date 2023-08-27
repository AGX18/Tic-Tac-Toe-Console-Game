package tictactoe;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // write your code here
        Scanner scanner = new Scanner(System.in);


        // making a grid
        char[] characters = new char[9];
        for (int i = 0; i < 9; i++) {
            characters[i] = '_';
        }
        // X will always start the game.
        // we will toggle this variable after each move
        boolean isX = true;

        // we print the empty grid
        printGrid(characters);
        // declaring the game statue
        String statue = gameStatue(characters);
        
        while (statue.equals("Game not finished")) {

            makeMove(scanner, characters, isX);
            // printing the grid after each move
            printGrid(characters);
            // we check the game statues after each move
            statue = gameStatue(characters);
            isX = !isX;
            
        }


        System.out.println(gameStatue(characters));









    }

    private static void makeMove(Scanner scanner, char[] characters, boolean isX) {
        // The user should input 2 coordinate numbers that represent the cell where they want to place their X or O.
        //  the first coordinate goes from top to bottom and the second coordinate goes from left to right. The coordinates can include the numbers 1, 2, or 3.
        // If the input is incorrect, inform the user why their input is wrong:
        // Print This cell is occupied! Choose another one! if the cell is not empty.
        // Print You should enter numbers! if the user enters non-numeric symbols in the coordinates input.
        // Print Coordinates should be from 1 to 3! if the user enters coordinates outside the game grid.
        // Keep prompting the user to enter the coordinates until the user input is valid.
        // If the input is correct, update the grid to include the user's move and print the updated grid to the console.
        int firstCoordinate = -1;
        int secondCoordinate = -1 ;
        boolean validInput = false;
        boolean isNum = true;
        while (!validInput) {
            isNum = true;
            try {
                firstCoordinate = scanner.nextInt();
                secondCoordinate = scanner.nextInt();
                scanner.nextLine();

                //validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("You should enter numbers!");
                isNum = false;
                scanner.nextLine();
            }

//            if(isNum)
//                continue;

            if (firstCoordinate < 1 || firstCoordinate > 3 || secondCoordinate < 1 || secondCoordinate > 3) {
                validInput = false;
                System.out.println("Coordinates should be from 1 to 3!");
            }
            else{
                int index = (firstCoordinate - 1) * 3 + secondCoordinate - 1;
                if (characters[index] != '_') {
                    validInput = false;
                    System.out.println("This cell is occupied! Choose another one!");
                } else {
                    validInput = true;
                    if(isX)
                        characters[index] = 'X';
                    else
                        characters[index] = 'O';
                }
            }

        }
    }

    private static void printGrid(char[] characters) {
        System.out.println("---------");
        for (int i = 1; i < 4; i++) {
            System.out.print("| ");
            for (int j = (i - 1) * 3; j < i * 3; j++) {
                System.out.print(characters[j]);
                System.out.print(" ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }


    private static String gameStatue(char[] characters) {
        String statue = "Game not finished";
        // Possible States
        // Game not finished : when neither side has three in a row but the grid still has empty cells.
        // Draw : when no side has a three in a row and the grid has no empty cells.
        // X wins : when the grid has three X’s in a row (including diagonals).
        // O wins when the grid has three O’s in a row (including diagonals).
        // Impossible : when the grid has three X’s in a row as well as three O’s in a row,
        // or there are a lot more X's than O's or vice versa (the difference should be 1 or 0;
        // if the difference is 2 or more, then the game state is impossible).

        // Game not finished
        for(int i = 0; i < 9; i++){
            if(characters[i] == '_'){
                statue = "Game not finished";
                break;
            }
        }

        // "" wins

        for(int i= 0; i < 3; i++){
            if(characters[i*3] == characters[i*3+1] && characters[i*3+1] == characters[i*3+2])/* row */
            {
                if(characters[i*3] == 'X'){
                    if(statue.equals("O wins")) {
                        statue = "Impossible";
                        break;
                    }
                    else  {
                        statue = "X wins";

                    }
                }
                else if (characters[i*3] == 'O'){
                    if(statue.equals("X wins")) {
                        statue = "Impossible";
                        break;
                    }
                    else {
                        statue = "O wins";

                    }

                }

            }
            else if(characters[i] == characters[i+3] && characters[i+3] == characters[i+6] /* column */){
                if(characters[i] == 'X'){
                    if(statue.equals("O wins")) {
                        statue = "Impossible";
                    }
                    else {
                        statue = "X wins";

                    }

                }
                else if (characters[i] == 'O'){
                    if(statue.equals("X wins")) {
                        statue = "Impossible";
                    }
                    else {
                        statue = "O wins";

                    }
                }

            }

        }

        if(characters[0] == characters[4] && characters[4] == characters[8]){ // left diagonal
            if(characters[0] == 'X')
                if(statue.equals("O wins"))
                    statue = "Impossible";
                else
                    statue = "X wins";
            else if (characters[0] == 'O'){
                if(statue.equals("X wins"))
                    statue = "Impossible";
                else
                    statue = "O wins";
            }
        }
        else if(characters[2] == characters[4] && characters[4] == characters[6]){ // right diagonal
            if(characters[2] == 'X')
                if(statue.equals("O wins")) {
                    statue = "Impossible";

                }
                else {
                    statue = "X wins";

                }
            else if (characters[2] == 'O'){
                if(statue.equals("X wins"))
                    statue = "Impossible";
                else
                    statue = "O wins";
            }
        }


        // Impossible
        int xCount = 0;
        int oCount = 0;
        for(int i = 0; i < 9; i++){
            if(characters[i] == 'X' ){
                xCount++;
            } else if (characters[i] == 'O'){
                oCount++;
            }
        }

        if(xCount - oCount > 1 || oCount - xCount > 1){
            statue = "Impossible";
        }
        else if (statue.equals("Game not finished") && xCount + oCount == 9){
            statue = "Draw";
        }

        return statue;
    }
}
